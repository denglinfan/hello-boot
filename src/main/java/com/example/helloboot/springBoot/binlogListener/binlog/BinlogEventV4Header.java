package com.example.helloboot.springBoot.binlogListener.binlog;

public interface BinlogEventV4Header {

    int getHeaderLength();

    int getPosition();

    long getTimeStamp();

    int getEventType();

    long getServerId();

    long getEventLength();

    long getNextPosition();

    int getFlags();

    String getBinlogFileName();

    long getTimeStampOfReceipt();
}
