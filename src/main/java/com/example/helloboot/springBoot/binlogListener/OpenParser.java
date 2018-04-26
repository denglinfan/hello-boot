package com.example.helloboot.springBoot.binlogListener;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventListener;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParser;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.FileBasedBinlogParser;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.parser.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class OpenParser {

    protected long stopPosition;
    protected long startPosition;
    protected String binlogFileName;
    protected String binlogFilePath;

    protected BinlogParser binlogParser;
    protected BinlogEventListener binlogEventListener;
    protected final AtomicBoolean running = new AtomicBoolean(false);

    public boolean isRunning() {
        return this.running.get();
    }

    public void start() throws Exception{
        if(!this.running.compareAndSet(false, true)){
            return ;
        }

        if(this.binlogParser == null){
            this.binlogParser = getDefaultBinlogParser();
        }
        this.binlogParser.setEventListener(this.binlogEventListener);
        this.binlogParser.start();
    }

    public void stop(long timeout, TimeUnit unit) throws Exception{
        if(!this.running.compareAndSet(true, false)){
            return ;
        }
        this.binlogParser.stop(timeout,unit);
    }

    public long getStopPosition() {
        return stopPosition;
    }

    public void setStopPosition(long stopPosition) {
        this.stopPosition = stopPosition;
    }

    public long getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(long startPosition) {
        this.startPosition = startPosition;
    }

    public String getBinlogFileName() {
        return binlogFileName;
    }

    public void setBinlogFileName(String binlogFileName) {
        this.binlogFileName = binlogFileName;
    }

    public String getBinlogFilePath() {
        return binlogFilePath;
    }

    public void setBinlogFilePath(String binlogFilePath) {
        this.binlogFilePath = binlogFilePath;
    }

    public BinlogParser getBinlogParser() {
        return binlogParser;
    }

    public void setBinlogParser(BinlogParser binlogParser) {
        this.binlogParser = binlogParser;
    }

    public BinlogEventListener getBinlogEventListener() {
        return binlogEventListener;
    }

    public void setBinlogEventListener(BinlogEventListener binlogEventListener) {
        this.binlogEventListener = binlogEventListener;
    }

    protected FileBasedBinlogParser getDefaultBinlogParser() throws Exception {
        /*  */
        final FileBasedBinlogParser r = new FileBasedBinlogParser();
        r.registerEventParser(new StopEventParser());
        r.registerEventParser(new RotateEventParser());
        r.registerEventParser(new IntVarEventParser());
        r.registerEventParser(new XidEventParser());
        r.registerEventParser(new RandEventParser());
        r.registerEventParser(new QueryEventParser());
        r.registerEventParser(new UserVarEventParser());
        r.registerEventParser(new IncidentEventParser());
        r.registerEventParser(new TableMapEventParser());
        r.registerEventParser(new WriteRowsEventParser());
        r.registerEventParser(new UpdateRowsEventParser());
        r.registerEventParser(new DeleteRowsEventParser());
        r.registerEventParser(new WriteRowsEventV2Parser());
        r.registerEventParser(new UpdateRowsEventV2Parser());
        r.registerEventParser(new DeleteRowsEventV2Parser());
        r.registerEventParser(new FormatDescriptionEventParser());
        r.registerEventParser(new GtidEventParser());

        //
        r.setStopPosition(this.stopPosition);
        r.setStartPosition(this.startPosition);
        r.setBinlogFileName(this.binlogFileName);
        r.setBinlogFilePath(this.binlogFilePath);
        return r;
    }
}
