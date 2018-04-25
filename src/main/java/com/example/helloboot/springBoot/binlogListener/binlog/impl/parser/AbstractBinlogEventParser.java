package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventParser;

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
