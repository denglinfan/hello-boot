package com.example.helloboot.binlogListener.net.impl.packet;

import com.example.helloboot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.binlogListener.io.util.XDeserializer;
import com.example.helloboot.binlogListener.io.util.XSerializer;
import com.example.helloboot.binlogListener.net.Packet;

import java.io.IOException;

public class GreetingPacket extends AbstractPacket {

    private static final long serialVersionUID = 5506239117316020734L;

    private int protocoVersion;
    private StringColumn serverVersion;
    private long threadId;
    private StringColumn scramble1;
    private int serverCapabilities;
    private int serverCollation;
    private int serverStatus;
    private StringColumn scramble2;
    private StringColumn pluginProvidedData;

    @Override
    public byte[] getPacketBody() throws IOException {
        final XSerializer s = new XSerializer(128);
        s.writeInt(this.protocoVersion,1);
        s.writeNullTerminatedString(this.serverVersion);
        s.writeLong(this.threadId,4);
        s.writeNullTerminatedString(this.scramble1);
        s.writeInt(this.serverCapabilities,2);
        s.writeInt(this.serverCollation,1);
        s.writeInt(this.serverStatus,2);
        s.writeInt(0,13);
        s.writeNullTerminatedString(this.scramble2);
        s.writeNullTerminatedString(this.pluginProvidedData);
        return s.toByteArray();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("protocoVersion",protocoVersion)
                .append("serverVersion",serverVersion)
                .append("threadId",threadId)
                .append("scramble1",scramble1)
                .append("serverCapabilities",serverCapabilities)
                .append("serverCollation",serverCollation)
                .append("serverStatus",serverStatus)
                .append("scramble2",scramble2)
                .append("pluginProvidedData",pluginProvidedData).toString();
    }

    public int getProtocoVersion() {
        return protocoVersion;
    }

    public StringColumn getServerVersion() {
        return serverVersion;
    }

    public long getThreadId() {
        return threadId;
    }

    public StringColumn getScramble1() {
        return scramble1;
    }

    public int getServerCapabilities() {
        return serverCapabilities;
    }

    public int getServerCollation() {
        return serverCollation;
    }

    public int getServerStatus() {
        return serverStatus;
    }

    public StringColumn getScramble2() {
        return scramble2;
    }

    public static GreetingPacket valueOf(Packet packet) throws IOException{
        final XDeserializer d = new XDeserializer(packet.getPacketBody());
        final GreetingPacket r = new GreetingPacket();
        r.length = packet.getLength();
        r.sequence = packet.getSequence();
        r.protocoVersion = d.readInt(1);
        r.serverVersion = d.readNullTerminatedString();
        r.threadId = d.readLong(4);
        r.scramble1 = d.readNullTerminatedString();
        r.serverCapabilities = d.readInt(2);
        r.serverCollation = d.readInt(1);
        r.serverStatus = d.readInt(2);
        d.skip(13);
        r.scramble2 = d.readNullTerminatedString();
        if(d.hasMore()){
            r.pluginProvidedData = d.readNullTerminatedString();
        }
        return r;
    }
}
