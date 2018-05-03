package com.example.helloboot.binlogListener.net.impl;

import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.io.impl.XOutputStreamImpl;
import com.example.helloboot.binlogListener.net.Packet;
import com.example.helloboot.binlogListener.net.TransportOutputStream;

import java.io.IOException;
import java.io.OutputStream;

public class TransportOutputStreamImpl extends XOutputStreamImpl implements TransportOutputStream {

    public TransportOutputStreamImpl(OutputStream out) {
        super(out);
    }

    @Override
    public void writePacket(Packet packet) throws IOException {
        final byte body[] = packet.getPacketBody();
        if(body.length < MySqlConstants.MAX_PACKET_LENGTH){
            writeInt(body.length,3);
            writeInt(packet.getSequence(),1);
            writeBytes(body);
            return;
        }

        int offset = 0;
        int sequence = packet.getSequence();
        for(;offset + MySqlConstants.MAX_PACKET_LENGTH <= body.length; offset += MySqlConstants.MAX_PACKET_LENGTH){
            writeInt(MySqlConstants.MAX_PACKET_LENGTH,3);
            writeInt(sequence++, 1);
            writeBytes(body,offset,MySqlConstants.MAX_PACKET_LENGTH);
        }

        writeInt(body.length - offset, 3);
        writeInt(sequence++,1);
        writeBytes(body,offset,body.length - offset);
    }
}
