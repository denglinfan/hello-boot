package com.example.helloboot.springBoot.binlogListener.binlog.impl;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventParser;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.BinlogEventV4HeaderImpl;
import com.example.helloboot.springBoot.binlogListener.common.utils.CodecUtils;
import com.example.helloboot.springBoot.binlogListener.common.utils.IOUtils;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;
import com.example.helloboot.springBoot.binlogListener.io.impl.XInputStreamImpl;
import com.example.helloboot.springBoot.binlogListener.io.util.RamdomAccessFileInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileBasedBinlogParser extends AbstractBinlogParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedBinlogParser.class);

    protected XInputStream is;
    protected String binlogFileName;
    protected String binlogFilePath;
    protected long stopPosition = 0;
    protected long startPosition = 4;

    public FileBasedBinlogParser() {
    }

    @Override
    protected void doParser() throws Exception {
        final Context context = new Context(this.binlogFileName);
        while (isRunning() && is.available() > 0){
            try {
                final BinlogEventV4HeaderImpl header = new BinlogEventV4HeaderImpl();
                header.setTimeStamp(is.readLong(4) * 1000L);
                header.setEventType(is.readInt(1));
                header.setServerId(is.readLong(4));
                header.setEventLength(is.readInt(4));
                header.setNextPosition(is.readLong(4));
                header.setFlags(is.readInt(2));
                header.setBinlogFileName(this.binlogFileName);
                header.setTimeStampOfReceipt(System.currentTimeMillis());
                is.setReadLimit((int)(header.getEventLength() - header.getHeaderLength()));
                if(isVerbose() && LOGGER.isInfoEnabled()){
                    LOGGER.info("read an event, header:{}",header);
                }

                if(this.stopPosition > 0 && header.getPosition() > this.stopPosition){
                    break;
                }
                if(this.eventFilter != null && !this.eventFilter.accepts(header,context)){
                    this.defaultParser.parser(is,header,context);
                } else{
                    BinlogEventParser parser = getEventParser(header.getEventType());
                    if(parser == null){
                        parser = this.defaultParser;
                    }
                    parser.parser(is,header,context);
                }

                if(is.available() != 0){
                    throw new RuntimeException("assertion failed, available:" + is.available() + ", event type:" + header.getEventType());
                }
            } catch (Exception e) {
                IOUtils.closeQuietly(is);
                throw e;
            } finally {
                is.setReadLimit(0);
            }
        }
    }

    @Override
    protected void doStart() throws Exception {
        this.is = open(this.binlogFilePath + "/" + this.binlogFileName);
    }

    @Override
    protected void doStop(long timeout, TimeUnit unit) throws Exception {
        IOUtils.closeQuietly(this.is);
    }

    protected XInputStream open(String path) throws Exception{
        final XInputStream is = new XInputStreamImpl(new RamdomAccessFileInputStream(new File(path)));

        try {
            final byte[] magic = is.readBytes(MySqlConstants.BINLOG_MAGIC.length);
            if(!CodecUtils.equals(magic, MySqlConstants.BINLOG_MAGIC)){
                throw new RuntimeException("invalid binlog magic, file:" + path);
            }

            if(this.startPosition > MySqlConstants.BINLOG_MAGIC.length){
                is.skip(this.startPosition - MySqlConstants.BINLOG_MAGIC.length);
            }

            return is;
        } catch (IOException e) {
            IOUtils.closeQuietly(is);
            throw e;
        }
    }

    public XInputStream getIs() {
        return is;
    }

    public void setIs(XInputStream is) {
        this.is = is;
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
}
