package com.example.helloboot.binlogListener.net;

public interface TransportContext {

    long getThreadId();

    String getScramble();

    int getProtocolVersion();

    String getServerHost();

    int getServerPort();

    int getServerStatus();

    int getServerCollation();

    String getServerVersion();

    int getServerCapabilities();
}
