package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.StopEvent;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class StopEventParser extends AbstractBinlogEventParser {

    public StopEventParser() {
        super(StopEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final StopEvent event = new StopEvent(header);
        context.getEventListener().onEvents(event);
    }
}
