package com.example.helloboot.springBoot.binlogListener.net;

import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public interface TransportInputStream extends XInputStream {

    Packet readPacket() throws IOException;
}
