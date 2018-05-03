package com.example.helloboot.binlogListener.binlog;

public interface BinlogEventV4Header {

    int getHeaderLength();

    long getPosition();

    long getTimeStamp();

    int getEventType();

    long getServerId();

    long getEventLength();

    long getNextPosition();

    int getFlags();

    String getBinlogFileName();

    long getTimeStampOfReceipt();
}
