package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.IntvarEvent;
import com.example.helloboot.springBoot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class IntVarEventParser extends AbstractBinlogEventParser {

    public IntVarEventParser() {
        super(IntvarEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final IntvarEvent event = new IntvarEvent(header);
        event.setType(is.readInt(1));
        event.setValue(UnsignedLong.valueOf(is.readLong(8)));
        context.getEventListener().onEvents(event);
    }
}
