package com.example.helloboot.springBoot.binlogListener;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventListener;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParser;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserListener;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.ReplicationBasedBinlogParser;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.parser.*;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.io.impl.SocketFactoryImpl;
import com.example.helloboot.springBoot.binlogListener.net.Packet;
import com.example.helloboot.springBoot.binlogListener.net.Transport;
import com.example.helloboot.springBoot.binlogListener.net.TransportException;
import com.example.helloboot.springBoot.binlogListener.net.impl.AuthenticatorImpl;
import com.example.helloboot.springBoot.binlogListener.net.impl.TransportImpl;
import com.example.helloboot.springBoot.binlogListener.net.impl.packet.ErrorPacket;
import com.example.helloboot.springBoot.binlogListener.net.impl.packet.command.ComBinlogDumpPacket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class OpenReplicator {

    protected int port = 3306;
    protected String host;
    protected String user;
    protected String password;
    protected int serverId = 6789;
    protected String binlogFileName;
    protected long binlogPosition = 4;
    protected String encoding = "utf-8";
    protected int levelBufferSize = 1024 * 1024;
    protected int level2BufferSize = 8 * 1024 * 1024;
    protected int socketReceiveBufferSize = 512 * 1024;

    protected Transport transport;
    protected BinlogParser binlogParser;
    protected BinlogEventListener binlogEventListener;
    protected final AtomicBoolean running = new AtomicBoolean(false);

    public boolean isRunning(){
        return this.running.get();
    }

    public void start() throws Exception{
        if(!this.running.compareAndSet(false,true)){
            return;
        }

        if(this.transport == null){
            this.transport = getDefaultTransport();
        }
        this.transport.connect(this.host,this.port);

        dumpBinlog();

        if(this.binlogParser == null){
            this.binlogParser = getDefaultBinlogParser();
        }

        this.binlogParser.setEventListener(this.binlogEventListener);
        this.binlogParser.addParserListtener(new BinlogParserListener.Adapter(){
            @Override
            public void onStop(BinlogParser parser) {
                stopQuietly(0, TimeUnit.MILLISECONDS);
            }
        });

        this.binlogParser.start();
    }

    public void stop(long timeout, TimeUnit unit) throws  Exception{
        if(!this.running.compareAndSet(true,false)){
            return;
        }

        this.transport.disconnect();
        this.binlogParser.stop(timeout,unit);
    }

    public void stopQuietly(long timeout, TimeUnit unit){
        try {
            stop(timeout,unit);
        } catch (Exception e) {

        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getBinlogFileName() {
        return binlogFileName;
    }

    public void setBinlogFileName(String binlogFileName) {
        this.binlogFileName = binlogFileName;
    }

    public long getBinlogPosition() {
        return binlogPosition;
    }

    public void setBinlogPosition(long binlogPosition) {
        this.binlogPosition = binlogPosition;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getLevelBufferSize() {
        return levelBufferSize;
    }

    public void setLevelBufferSize(int levelBufferSize) {
        this.levelBufferSize = levelBufferSize;
    }

    public int getLevel2BufferSize() {
        return level2BufferSize;
    }

    public void setLevel2BufferSize(int level2BufferSize) {
        this.level2BufferSize = level2BufferSize;
    }

    public int getSocketReceiveBufferSize() {
        return socketReceiveBufferSize;
    }

    public void setSocketReceiveBufferSize(int socketReceiveBufferSize) {
        this.socketReceiveBufferSize = socketReceiveBufferSize;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public BinlogParser getBinlogParser() {
        return binlogParser;
    }

    public void setBinlogParser(BinlogParser binlogParser) {
        this.binlogParser = binlogParser;
    }

    public BinlogEventListener getBinlogEventListener() {
        return binlogEventListener;
    }

    public void setBinlogEventListener(BinlogEventListener binlogEventListener) {
        this.binlogEventListener = binlogEventListener;
    }

    protected void dumpBinlog() throws Exception{
        final ComBinlogDumpPacket command = new ComBinlogDumpPacket();
        command.setBinlogFlag(0);
        command.setServerId(this.serverId);
        command.setBinlogPosition(this.binlogPosition);
        command.setBinlogFileName(StringColumn.valueOf(this.binlogFileName.getBytes(this.encoding)));
        this.transport.getOutputStream().writePacket(command);
        this.transport.getOutputStream().flush();

        final Packet packet = this.transport.getInputStream().readPacket();
        if(packet.getPacketBody()[0] == ErrorPacket.PACKET_MARKER){
            final ErrorPacket error = ErrorPacket.valueOf(packet);
            throw new TransportException(error);
        }
    }

    protected Transport getDefaultTransport() throws Exception{
        final TransportImpl r = new TransportImpl();
        r.setLevel1BufferSize(this.levelBufferSize);
        r.setLevel2BufferSize(this.level2BufferSize);

        final AuthenticatorImpl authenticator = new AuthenticatorImpl();
        authenticator.setUser(this.user);
        authenticator.setPassword(this.password);
        authenticator.setEncoding(this.encoding);
        r.setAuthenticator(authenticator);

        final SocketFactoryImpl socketFactory = new SocketFactoryImpl();
        socketFactory.setKeepAlive(true);
        socketFactory.setTcpNoDelay(false);
        socketFactory.setReceiveBufferSize(this.socketReceiveBufferSize);
        r.setSocketFactory(socketFactory);
        return r;
    }

    protected ReplicationBasedBinlogParser getDefaultBinlogParser() throws Exception{
        final ReplicationBasedBinlogParser r = new ReplicationBasedBinlogParser();
        r.registerEventParser(new StopEventParser());
        r.registerEventParser(new RotateEventParser());
        r.registerEventParser(new IntVarEventParser());
        r.registerEventParser(new XidEventParser());
        r.registerEventParser(new RandEventParser());
        r.registerEventParser(new QueryEventParser());
        r.registerEventParser(new UserVarEventParser());
        r.registerEventParser(new IncidentEventParser());
        r.registerEventParser(new TableMapEventParser());
        r.registerEventParser(new WriteRowsEventParser());
        r.registerEventParser(new UpdateRowsEventParser());
        r.registerEventParser(new DeleteRowsEventParser());
        r.registerEventParser(new WriteRowsEventV2Parser());
        r.registerEventParser(new UpdateRowsEventV2Parser());
        r.registerEventParser(new DeleteRowsEventV2Parser());
        r.registerEventParser(new FormatDescriptionEventParser());
        r.registerEventParser(new GtidEventParser());

        r.setTransport(this.transport);
        r.setBinlogFileName(this.binlogFileName);
        return r;
    }
}
