package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

import java.util.Arrays;

public class FormatDescriptionEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.FORMAT_DESCRIPTION_EVENT;

    private int binlogVersion;
    private StringColumn serverVersion;
    private long createTimestamp;
    private int headerLength;
    private byte[] eventTypes;

    public FormatDescriptionEvent() {
    }

    public FormatDescriptionEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("binlogVersion",binlogVersion)
                .append("serverVersion",serverVersion)
                .append("createTimestamp",createTimestamp)
                .append("headerLength",headerLength)
                .append("eventTypes",Arrays.toString(eventTypes)).toString();
    }

    public int getBinlogVersion() {
        return binlogVersion;
    }

    public void setBinlogVersion(int binlogVersion) {
        this.binlogVersion = binlogVersion;
    }

    public StringColumn getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(StringColumn serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    public byte[] getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(byte[] eventTypes) {
        this.eventTypes = eventTypes;
    }
}
