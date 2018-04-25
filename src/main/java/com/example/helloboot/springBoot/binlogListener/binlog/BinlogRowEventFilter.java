package com.example.helloboot.springBoot.binlogListener.binlog;

import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.TableMapEvent;

public interface BinlogRowEventFilter {

    boolean accepts(BinlogEventV4Header header, BinlogParserContext xontext, TableMapEvent event);
}
