package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogEventParser;
import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;

public class NopEventParser implements BinlogEventParser {

    @Override
    public int getEventType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final int available = is.available();
        is.skip(available);
    }
}
