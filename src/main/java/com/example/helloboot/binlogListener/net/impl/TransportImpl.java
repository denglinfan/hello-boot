package com.example.helloboot.binlogListener.net.impl;

import com.example.helloboot.binlogListener.common.utils.IOUtils;
import com.example.helloboot.binlogListener.io.SocketFactory;
import com.example.helloboot.binlogListener.io.util.ActiveBufferedInputStream;
import com.example.helloboot.binlogListener.net.Packet;
import com.example.helloboot.binlogListener.net.TransportException;
import com.example.helloboot.binlogListener.net.TransportInputStream;
import com.example.helloboot.binlogListener.net.TransportOutputStream;
import com.example.helloboot.binlogListener.net.impl.packet.ErrorPacket;
import com.example.helloboot.binlogListener.net.impl.packet.GreetingPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class TransportImpl extends AbstractTransport {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransportImpl.class);

    protected Socket socket;
    protected TransportInputStream is;
    protected TransportOutputStream os;
    protected SocketFactory socketFactory;
    protected int level1BufferSize = 1024 * 1024;
    protected int level2BufferSize = 8 * 1024 * 1024;
    protected final AtomicBoolean connected = new AtomicBoolean(false);

    @Override
    public boolean isConnected() {
        return this.connected.get();
    }

    @Override
    public void disconnect() throws Exception {
        if(!this.connected.compareAndSet(true,false)){
            return;
        }

        IOUtils.closeQuietly(this.is);
        IOUtils.closeQuietly(this.os);
        IOUtils.closeQuietly(this.socket);

        if(isVerbose() && LOGGER.isInfoEnabled()){
            LOGGER.info("disconnected from {}:{}",this.context.getServerHost(),this.context.getServerPort());
        }
    }

    @Override
    public void connect(String host, int port) throws Exception {
        if(!this.connected.compareAndSet(false,true)){
            return;
        }
        if(isVerbose() && LOGGER.isInfoEnabled()){
            LOGGER.info("connecting to host:{},port:{}",host,port);
        }

        this.socket = this.socketFactory.create(host,port);
        this.os = new TransportOutputStreamImpl(this.socket.getOutputStream());
        if(this.level2BufferSize <= 0){
            this.is = new TransportInputStreamImpl(this.socket.getInputStream(),this.level1BufferSize);
        }else{
            this.is = new TransportInputStreamImpl(
                    new ActiveBufferedInputStream(
                            this.socket.getInputStream(),this.level2BufferSize),this.level1BufferSize);
        }

        final Packet packet = this.is.readPacket();
        if(packet.getPacketBody()[0] == ErrorPacket.PACKET_MARKER){
            final ErrorPacket error = ErrorPacket.valueOf(packet);
            if(LOGGER.isInfoEnabled()){
                LOGGER.info("failed to connect to host:{},port:{},error",new Object[]{host,port,error});
            }
            throw new TransportException(error);
        } else{
            final GreetingPacket greeting = GreetingPacket.valueOf(packet);
            this.context.setServerHost(host);
            this.context.setServerPort(port);
            this.context.setServerStatus(greeting.getServerStatus());
            this.context.setServerVersion(greeting.getServerVersion().toString());
            this.context.setServerCollation(greeting.getServerCollation());
            this.context.setServerCapabilities(greeting.getServerCapabilities());
            this.context.setThreadId(greeting.getThreadId());
            this.context.setProtocolVersion(greeting.getProtocoVersion());
            this.context.setScramble(greeting.getScramble1().toString() + greeting.getScramble2().toString());

            if(isVerbose() && LOGGER.isInfoEnabled()){
                LOGGER.info("connected to host:{},port:{},context:{}",new Object[]{host,port,this.context});
            }
        }

        this.authenticator.login(this);
    }

    @Override
    public TransportInputStream getInputStream() {
        return this.is;
    }

    @Override
    public TransportOutputStream getOutputStream() {
        return this.os;
    }

    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public int getLevel1BufferSize() {
        return level1BufferSize;
    }

    public void setLevel1BufferSize(int level1BufferSize) {
        this.level1BufferSize = level1BufferSize;
    }

    public int getLevel2BufferSize() {
        return level2BufferSize;
    }

    public void setLevel2BufferSize(int level2BufferSize) {
        this.level2BufferSize = level2BufferSize;
    }
}
