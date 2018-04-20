package com.example.helloboot.springBoot.binlogListener.net;

import com.example.helloboot.springBoot.binlogListener.io.XOutputStream;

import java.io.IOException;

public interface TransportOutputStream extends XOutputStream {

    void writePacket(Packet packet) throws IOException;
}
