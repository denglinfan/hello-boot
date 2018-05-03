package com.example.helloboot.binlogListener.net;

import com.example.helloboot.binlogListener.io.XOutputStream;

import java.io.IOException;

public interface TransportOutputStream extends XOutputStream {

    void writePacket(Packet packet) throws IOException;
}
