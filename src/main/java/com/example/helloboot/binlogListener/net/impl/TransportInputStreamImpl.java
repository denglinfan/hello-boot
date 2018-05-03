package com.example.helloboot.binlogListener.net.impl;

import com.example.helloboot.binlogListener.net.TransportInputStream;
import com.example.helloboot.binlogListener.io.impl.XInputStreamImpl;
import com.example.helloboot.binlogListener.net.Packet;
import com.example.helloboot.binlogListener.net.impl.packet.RawPacket;

import java.io.IOException;
import java.io.InputStream;

public class TransportInputStreamImpl extends XInputStreamImpl implements TransportInputStream {

    public TransportInputStreamImpl(InputStream is) {
        super(is);
    }

    public TransportInputStreamImpl(InputStream is, int size) {
        super(is, size);
    }

    @Override
    public Packet readPacket() throws IOException {
        final RawPacket r = new RawPacket();
        r.setLength(readInt(3));
        r.setSequence(readInt(1));

        int total = 0;
        final byte[] body = new byte[r.getLength()];
        while(total < body.length){
            total += this.read(body,total,body.length - total);
        }
        r.setPacketBody(body);
        return r;
    }
}
