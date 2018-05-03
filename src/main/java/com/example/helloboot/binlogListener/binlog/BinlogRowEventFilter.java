package com.example.helloboot.binlogListener.binlog;

import com.example.helloboot.binlogListener.binlog.impl.event.TableMapEvent;

public interface BinlogRowEventFilter {

    boolean accepts(BinlogEventV4Header header, BinlogParserContext xontext, TableMapEvent event);
}
