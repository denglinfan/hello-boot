package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.FormatDescriptionEvent;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class FormatDescriptionEventParser extends AbstractRowEventParser {

    public FormatDescriptionEventParser() {
        super(FormatDescriptionEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final FormatDescriptionEvent event = new FormatDescriptionEvent(header);
        event.setBinlogVersion(is.readInt(2));
        event.setServerVersion(is.readFixedLengthString(50));
        event.setCreateTimestamp(is.readLong(4)*1000L);
        event.setHeaderLength(is.readInt(1));
        event.setEventTypes(is.readBytes(is.available()));
        context.getEventListener().onEvents(event);
    }
}
