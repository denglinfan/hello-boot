package com.example.helloboot.binlogListener.binlog.impl.event;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;

public final class BinlogEventV4HeaderImpl implements BinlogEventV4Header {

    private long timeStamp;
    private int eventType;
    private long serverId;
    private long eventLength;
    private long nextPosition;
    private int flags;

    private String binlogFileName;
    private long timeStampOfReceipt;

    @Override
    public int getHeaderLength() {
        return 19;
    }

    @Override
    public long getPosition() {
        return this.nextPosition - this.eventLength;
    }

    @Override
    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    @Override
    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    @Override
    public long getEventLength() {
        return eventLength;
    }

    public void setEventLength(long eventLength) {
        this.eventLength = eventLength;
    }

    @Override
    public long getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(long nextPosition) {
        this.nextPosition = nextPosition;
    }

    @Override
    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    @Override
    public String getBinlogFileName() {
        return binlogFileName;
    }

    public void setBinlogFileName(String binlogFileName) {
        this.binlogFileName = binlogFileName;
    }

    @Override
    public long getTimeStampOfReceipt() {
        return timeStampOfReceipt;
    }

    public void setTimeStampOfReceipt(long timeStampOfReceipt) {
        this.timeStampOfReceipt = timeStampOfReceipt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("timeStamp", timeStamp)
                .append("eventType", eventType)
                .append("serverId", serverId)
                .append("eventLength", eventLength)
                .append("nextPosition", nextPosition)
                .append("flags", flags)
                .append("binlogFileName", binlogFileName)
                .append("timeStampOfReceipt", timeStampOfReceipt).toString();
    }
}
