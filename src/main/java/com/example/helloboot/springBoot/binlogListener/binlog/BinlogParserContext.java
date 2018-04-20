package com.example.helloboot.springBoot.binlogListener.binlog;

import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.TableMapEvent;

public interface BinlogParserContext {

    String getBinlogFileName();

    BinlogEventListener getEventListener();

    TableMapEvent getTableMapEvent(long tableId);
}
