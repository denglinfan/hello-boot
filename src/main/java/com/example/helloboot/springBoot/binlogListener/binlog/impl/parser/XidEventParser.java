package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.XidEvent;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class XidEventParser extends AbstractBinlogEventParser {

    public XidEventParser() {
        super(XidEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final XidEvent event = new XidEvent(header);
        event.setXid(is.readLong(8));
        context.getEventListener().onEvents(event);
    }
}
