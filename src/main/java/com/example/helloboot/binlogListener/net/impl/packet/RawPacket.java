package com.example.helloboot.binlogListener.net.impl.packet;

import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;

import java.io.IOException;

public class RawPacket extends AbstractPacket {

    private static final long serialVersionUID = 4109090905397000303L;

    private byte packetBody[];

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("length",length)
                .append("sequence",sequence).toString();
    }

    @Override
    public byte[] getPacketBody() throws IOException {
        return packetBody;
    }

    public void setPacketBody(byte[] packetBody) {
        this.packetBody = packetBody;
    }
}
