package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogEventParser;

public abstract class AbstractBinlogEventParser implements BinlogEventParser {

    protected final int eventType;

    public AbstractBinlogEventParser(int eventType) {
        this.eventType = eventType;
    }

    @Override
    public int getEventType() {
        return eventType;
    }
}
