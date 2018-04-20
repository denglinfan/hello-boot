package com.example.helloboot.springBoot.binlogListener.net.impl;

import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySQLUtils;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.io.util.XSerializer;
import com.example.helloboot.springBoot.binlogListener.net.Packet;
import com.example.helloboot.springBoot.binlogListener.net.Transport;
import com.example.helloboot.springBoot.binlogListener.net.TransportContext;
import com.example.helloboot.springBoot.binlogListener.net.TransportException;
import com.example.helloboot.springBoot.binlogListener.net.impl.packet.ErrorPacket;
import com.example.helloboot.springBoot.binlogListener.net.impl.packet.OKPacket;
import com.example.helloboot.springBoot.binlogListener.net.impl.packet.RawPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthenticatorImpl implements Transport.Authenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatorImpl.class);

    public static final int DEFAULT_CAPABILITIES =
            (MySqlConstants.CLIENT_LONG_FLAG | MySqlConstants.CLIENT_PROTOCOL_41 | MySqlConstants.CLIENT_SECURE_CONNECTION);

    protected String user;
    protected String password;
    protected String initialSchema;
    protected int clientCollation;
    protected int clientCapabilities;
    protected int maximumPacketLength;
    protected String encoding = "utf-8";

    public void login(Transport transport) throws IOException{
        final TransportContext ctx = transport.getContext();
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("start to login, user:{},host:{}",new Object[]{this,user,ctx.getServerHost(),ctx.getServerPort()});
        }
        final XSerializer s = new XSerializer(64);
        s.writeInt(buildClientCapabilities(),4);
        s.writeInt(this.maximumPacketLength,4);
        s.writeInt(this.clientCollation > 0 ? this.clientCollation : ctx.getServerCollation(),1);
        s.writeBytes((byte) 0,23);
        s.writeNullTerminatedString(StringColumn.valueOf(this.user.getBytes(this.encoding)));
        s.writeInt(20,1);
        s.writeBytes(MySQLUtils.password41OrLater(this.password.getBytes(this.encoding),ctx.getScramble().getBytes(this.encoding)));

        if(this.initialSchema != null){
            s.writeNullTerminatedString(StringColumn.valueOf(this.initialSchema.getBytes(this.encoding)));
        }

        final RawPacket request = new RawPacket();
        request.setSequence(1);
        request.setPacketBody(s.toByteArray());
        request.setLength(request.getPacketBody().length);
        transport.getOutputStream().writePacket(request);
        transport.getOutputStream().flush();

        final Packet response = transport.getInputStream().readPacket();
        if(response.getPacketBody()[0] == ErrorPacket.PACKET_MARKER){
            final ErrorPacket error = ErrorPacket.valueOf(response);
            if(LOGGER.isInfoEnabled()){
                LOGGER.info("login failed, user:{},error:{}",this.user,error);
            }
            throw new TransportException(error);
        } else if(response.getPacketBody()[0] == OKPacket.PACKET_MARKER){
            final OKPacket ok = OKPacket.valueOf(response);
            if(LOGGER.isInfoEnabled()){
                LOGGER.info("login successfully, user:{},detail:{}",this.user,ok);
            }
        } else{
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("login failed, unknown packet:",response);
            }
            throw new RuntimeException("assertion failed,invalid packet:" + response);
        }
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

    public String getInitialSchema() {
        return initialSchema;
    }

    public void setInitialSchema(String initialSchema) {
        this.initialSchema = initialSchema;
    }

    public int getClientCollation() {
        return clientCollation;
    }

    public void setClientCollation(int clientCollation) {
        this.clientCollation = clientCollation;
    }

    public int getClientCapabilities() {
        return clientCapabilities;
    }

    public void setClientCapabilities(int clientCapabilities) {
        this.clientCapabilities = clientCapabilities;
    }

    public int getMaximumPacketLength() {
        return maximumPacketLength;
    }

    public void setMaximumPacketLength(int maximumPacketLength) {
        this.maximumPacketLength = maximumPacketLength;
    }

    protected int buildClientCapabilities(){
        int r = this.clientCapabilities > 0 ? this.clientCapabilities : DEFAULT_CAPABILITIES;
        if(this.initialSchema != null){
            r |= MySqlConstants.CLIENT_CONNECT_WITH_DB;
        }
        return r;
    }
}
