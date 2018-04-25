package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.IncidentEvent;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class IncidentEventParser extends AbstractBinlogEventParser {

    public IncidentEventParser() {
        super(IncidentEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context)
            throws IOException {
        final IncidentEvent event = new IncidentEvent(header);
        event.setIncidentNumber(is.readInt(1));
        event.setMessageLength(is.readInt(1));
        if(event.getMessageLength() > 0){
            event.setMessage(is.readFixedLengthString(event.getMessageLength()));
        }
        context.getEventListener().onEvents(event);
    }
}
