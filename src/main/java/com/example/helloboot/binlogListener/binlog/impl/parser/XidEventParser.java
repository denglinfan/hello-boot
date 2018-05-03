package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.binlogListener.binlog.impl.event.XidEvent;
import com.example.helloboot.binlogListener.io.XInputStream;

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
