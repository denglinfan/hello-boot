package com.example.helloboot.binlogListener.binlog;

public interface BinlogEventFilter {

    boolean accepts(BinlogEventV4Header header, BinlogParserContext context);
}
