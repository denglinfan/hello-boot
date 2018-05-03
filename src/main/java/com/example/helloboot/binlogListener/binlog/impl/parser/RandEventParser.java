package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.binlogListener.binlog.impl.event.RandEvent;
import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;

public class RandEventParser extends AbstractBinlogEventParser {

    public RandEventParser() {
        super(RandEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final RandEvent event = new RandEvent(header);
        event.setRandSeed1(is.readLong(8));
        event.setRandSeed2(is.readLong(8));
        context.getEventListener().onEvents(event);
    }
}
