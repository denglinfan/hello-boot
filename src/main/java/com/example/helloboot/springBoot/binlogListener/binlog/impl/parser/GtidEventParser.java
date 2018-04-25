package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.GtidEvent;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class GtidEventParser extends AbstractRowEventParser {

    public GtidEventParser() {
        super(MySqlConstants.GTID_LOG_EVENT);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        GtidEvent event = new GtidEvent(header);
        is.readBytes(1);
        event.setSourceId(is.readBytes(16));
        event.setTransactionId(is.readLong(8,true));
        is.skip(is.available());
        context.getEventListener().onEvents(event);
    }
}
