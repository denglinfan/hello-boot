package com.example.helloboot.binlogListener.binlog;

import com.example.helloboot.binlogListener.binlog.impl.event.TableMapEvent;

public interface BinlogParserContext {

    String getBinlogFileName();

    BinlogEventListener getEventListener();

    TableMapEvent getTableMapEvent(long tableId);
}
