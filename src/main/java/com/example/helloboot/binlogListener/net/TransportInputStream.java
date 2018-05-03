package com.example.helloboot.binlogListener.net;

import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;

public interface TransportInputStream extends XInputStream {

    Packet readPacket() throws IOException;
}
