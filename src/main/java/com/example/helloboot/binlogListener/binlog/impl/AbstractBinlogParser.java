package com.example.helloboot.binlogListener.binlog.impl;

import com.example.helloboot.binlogListener.binlog.*;
import com.example.helloboot.binlogListener.common.utils.XThreadFactory;
import com.example.helloboot.binlogListener.binlog.impl.event.RotateEvent;
import com.example.helloboot.binlogListener.binlog.impl.event.TableMapEvent;
import com.example.helloboot.binlogListener.binlog.impl.parser.NopEventParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractBinlogParser implements BinlogParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBinlogParser.class);

    protected Thread worker;
    protected ThreadFactory threadFactory;
    protected BinlogEventFilter eventFilter;
    protected BinlogEventListener eventListener;
    protected boolean clearTableMapEventsOnRotate = true;
    protected final List<BinlogParserListener> parserListeners;
    protected final AtomicBoolean verbose = new AtomicBoolean(false);
    protected final AtomicBoolean running = new AtomicBoolean(false);
    protected final BinlogEventParser defaultParser = new NopEventParser();
    protected final BinlogEventParser[] parsers = new BinlogEventParser[128];

    protected abstract void doParser() throws Exception;
    protected abstract void doStart() throws Exception;
    protected abstract void doStop(long timeout, TimeUnit unit) throws Exception;

    public AbstractBinlogParser() {
        this.threadFactory = new XThreadFactory("binlog-parser",false);
        this.parserListeners = new CopyOnWriteArrayList<BinlogParserListener>();
    }

    @Override
    public boolean isRunning() {
        return this.running.get();
    }

    @Override
    public void start() throws Exception {
        if(!this.running.compareAndSet(false,true)){
            return ;
        }

        doStart();

        this.worker = this.threadFactory.newThread(new Task());
        this.worker.start();

        notifyOnStart();
    }

    @Override
    public void stop(long timeout, TimeUnit unit) throws Exception {
        if(!this.running.compareAndSet(true,false)){
            return ;
        }

        try {
            final long now = System.nanoTime();
            doStop(timeout,unit);
            timeout -= unit.convert(System.nanoTime() - now, TimeUnit.NANOSECONDS);

            if(timeout > 0){
                unit.timedJoin(this.worker,timeout);
                this.worker = null;
            }
        } finally {
            notifyOnStop();
        }
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public BinlogEventFilter getEventFilter() {
        return eventFilter;
    }

    public BinlogEventListener getEventListener() {
        return eventListener;
    }

    public boolean isClearTableMapEventsOnRotate() {
        return clearTableMapEventsOnRotate;
    }

    public void setClearTableMapEventsOnRotate(boolean clearTableMapEventsOnRotate) {
        this.clearTableMapEventsOnRotate = clearTableMapEventsOnRotate;
    }

    public boolean isVerbose() {
        return this.verbose.get();
    }

    public void setVerbose(boolean verbose){
        this.verbose.set(verbose);
    }

    @Override
    public void setEventFilter(BinlogEventFilter filter) {
        this.eventFilter = filter;
    }

    @Override
    public void setEventListener(BinlogEventListener listener) {
        this.eventListener = listener;
    }

    public void clearEventParsers(){
        for(int i = 0; i < this.parsers.length; i++){
            this.parsers[i] = null;
        }
    }

    public BinlogEventParser getEventParser(int type){
        return this.parsers[type];
    }

    public BinlogEventParser unregisterEventParser(int type){
        return this.parsers[type] = null;
    }

    public void registerEventParser(BinlogEventParser parser){
        this.parsers[parser.getEventType()] = parser;
    }

    public void setEventParsers(List<BinlogEventParser> parsers){
        clearEventParsers();
        if(parsers != null){
            for(BinlogEventParser parser : parsers){
                registerEventParser(parser);
            }
        }
    }

    @Override
    public List<BinlogParserListener> getParserListeners() {
        return new ArrayList<BinlogParserListener>(this.parserListeners);
    }

    @Override
    public boolean addParserListtener(BinlogParserListener listener) {
        return this.parserListeners.add(listener);
    }

    @Override
    public boolean removeParserListener(BinlogParserListener listener) {
        return this.parserListeners.remove(listener);
    }

    @Override
    public void setParserListeners(List<BinlogParserListener> listeners) {
        this.parserListeners.clear();
        if(listeners != null){
            this.parserListeners.addAll(listeners);
        }
    }

    private void notifyOnStart(){
        for(BinlogParserListener listener : this.parserListeners){
            listener.onStart(this);
        }
    }

    private void notifyOnStop(){
        for(BinlogParserListener listener : this.parserListeners){
            listener.onStop(this);
        }
    }

    private void notifyOnException(Exception exception){
        for(BinlogParserListener listener : this.parserListeners){
            listener.onException(this, exception);
        }
    }

    protected class Task implements Runnable{
        @Override
        public void run() {
            try {
                doParser();
            } catch (Exception e) {
                notifyOnException(e);
                LOGGER.error("failed to parser binlog",e);
            } finally {
                try {
                    stop(0,TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    LOGGER.error("failed to stop binlog parser",e);
                }
            }
        }
    }

    protected class Context implements BinlogParserContext, BinlogEventListener{

        private String binlogFileName;
        private final Map<Long, TableMapEvent> tableMapEvents = new HashMap<>();

        public Context() {
        }

        public Context(String binlogFileName) {
            this.binlogFileName = binlogFileName;
        }

        @Override
        public void onEvents(BinlogEventV4 event) {
            if(event == null){
                return ;
            }

            if(event instanceof TableMapEvent){
                final TableMapEvent tme = (TableMapEvent) event;
                this.tableMapEvents.put(tme.getTableId(), tme);
            } else if(event instanceof RotateEvent){
                final RotateEvent re = (RotateEvent) event;
                this.binlogFileName = re.getBinlogFileName().toString();
                if(isClearTableMapEventsOnRotate()){
                    this.tableMapEvents.clear();
                }
            }

            try {
                AbstractBinlogParser.this.eventListener.onEvents(event);
            } catch (Exception e) {
                LOGGER.error("failed to notify binlog event listener, event:" + event, e);
            }
        }

        @Override
        public String getBinlogFileName() {
            return binlogFileName;
        }

        public final void setBinlogFileName(String name){
            this.binlogFileName = name;
        }

        @Override
        public BinlogEventListener getEventListener() {
            return this;
        }

        @Override
        public TableMapEvent getTableMapEvent(long tableId) {
            return this.tableMapEvents.get(tableId);
        }
    }
}
