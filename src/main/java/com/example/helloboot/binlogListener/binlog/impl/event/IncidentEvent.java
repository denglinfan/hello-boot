package com.example.helloboot.binlogListener.binlog.impl.event;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;

public final class IncidentEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.INCIDENT_EVENT;

    private int incidentNumber;
    private int messageLength;
    private StringColumn message;

    public IncidentEvent() {
    }

    public IncidentEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("incidentNumber",incidentNumber)
                .append("messageLength",messageLength)
                .append("message",message).toString();
    }

    public int getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(int incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public StringColumn getMessage() {
        return message;
    }

    public void setMessage(StringColumn message) {
        this.message = message;
    }
}
