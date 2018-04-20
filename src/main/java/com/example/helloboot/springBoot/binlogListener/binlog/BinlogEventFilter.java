package com.example.helloboot.springBoot.binlogListener.binlog;

public interface BinlogEventFilter {

    boolean accepts(BinlogEventV4Header header, BinlogParserContext context);
}
