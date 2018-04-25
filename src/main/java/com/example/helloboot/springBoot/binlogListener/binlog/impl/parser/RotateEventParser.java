package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.RotateEvent;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class RotateEventParser extends AbstractBinlogEventParser {

    public RotateEventParser() {
        super(RotateEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final RotateEvent event = new RotateEvent(header);
        event.setBinlogPosition(is.readLong(8));
        event.setBinlogFileName(is.readFixedLengthString(is.available()));
        context.getEventListener().onEvents(event);
    }
}
