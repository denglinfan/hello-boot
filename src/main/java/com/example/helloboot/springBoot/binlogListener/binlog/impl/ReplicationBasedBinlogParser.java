package com.example.helloboot.springBoot.binlogListener.binlog.impl;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventParser;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.BinlogEventV4HeaderImpl;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;
import com.example.helloboot.springBoot.binlogListener.net.Transport;
import com.example.helloboot.springBoot.binlogListener.net.impl.packet.EOFPacket;
import com.example.helloboot.springBoot.binlogListener.net.impl.packet.ErrorPacket;
import com.example.helloboot.springBoot.binlogListener.net.impl.packet.OKPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ReplicationBasedBinlogParser extends AbstractBinlogParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplicationBasedBinlogParser.class);

    protected Transport transport;
    protected String binlogFileName;

    public ReplicationBasedBinlogParser() {
    }

    @Override
    protected void doStart() throws Exception {

    }

    @Override
    protected void doStop(long timeout, TimeUnit unit) throws Exception {

    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public String getBinlogFileName() {
        return binlogFileName;
    }

    public void setBinlogFileName(String binlogFileName) {
        this.binlogFileName = binlogFileName;
    }

    @Override
    protected void doParser() throws Exception {

        final XInputStream is = this.transport.getInputStream();
        final Context context = new Context(this.binlogFileName);
        while(isRunning()){
            try {
                final int packetLength = is.readInt(3);
                final int packetSequence = is.readInt(1);
                is.setReadLimit(packetLength);

                final int packetMarker = is.readInt(1);
                if(packetMarker != OKPacket.PACKET_MARKER){
                    if((byte)packetMarker == ErrorPacket.PACKET_MARKER){
                        final ErrorPacket packet = ErrorPacket.valueOf(packetLength,packetSequence,packetMarker,is);
                        throw new RuntimeException(packet.toString());
                    } else if((byte) packetMarker == EOFPacket.PACKET_MARKER){
                        final EOFPacket packet = EOFPacket.valueOf(packetLength,packetSequence,packetMarker,is);
                        throw new RuntimeException(packet.toString());
                    } else{
                        throw new RuntimeException("assertion failed, invalid packet marker:" + packetMarker);
                    }
                }

                final BinlogEventV4HeaderImpl header = new BinlogEventV4HeaderImpl();
                header.setTimeStamp(is.readLong(4) * 1000L);
                header.setEventType(is.readInt(1));
                header.setServerId(is.readLong(4));
                header.setEventLength(is.readInt(4));
                header.setNextPosition(is.readLong(4));
                header.setFlags(is.readInt(2));
                header.setBinlogFileName(this.binlogFileName);
                header.setTimeStampOfReceipt(System.currentTimeMillis());

                if(isVerbose() && LOGGER.isInfoEnabled()){
                    LOGGER.info("received an event, sequence:{}, header:{}",packetSequence,header);
                }

                if(this.eventFilter != null && !this.eventFilter.accepts(header,context)){
                    this.defaultParser.parser(is,header,context);
                } else {
                    BinlogEventParser parser = getEventParser(header.getEventType());
                    if(parser == null){
                        parser = this.defaultParser;
                    }
                    parser.parser(is,header,context);
                }

                if(is.available() != 0){
                    throw new RuntimeException("assertion failed, available:" + is.available() + ", event type:" + header.getEventType());
                }
            } finally {
                is.setReadLimit(0);
            }
        }
    }
}
