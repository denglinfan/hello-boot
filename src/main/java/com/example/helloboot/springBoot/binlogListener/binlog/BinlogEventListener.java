package com.example.helloboot.springBoot.binlogListener.binlog;

public interface BinlogEventListener {

    void onEvents(BinlogEventV4 event);
}
