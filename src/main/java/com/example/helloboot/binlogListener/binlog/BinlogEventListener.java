package com.example.helloboot.binlogListener.binlog;

public interface BinlogEventListener {

    void onEvents(BinlogEventV4 event);
}
