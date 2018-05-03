package com.example.helloboot.binlogListener.binlog;

import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;

public interface BinlogEventParser {

    int getEventType();

    void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException;
}
