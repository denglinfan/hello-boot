package com.example.helloboot.binlogListener.net.impl.packet.command;

import com.example.helloboot.binlogListener.net.impl.packet.AbstractPacket;

public abstract class AbstractCommandPacket extends AbstractPacket {

    private static final long serialVersionUID = -8046179372409111502L;

    protected final int command;

    public AbstractCommandPacket(int command) {
        this.command = command;
    }

    public int getCommand() {
        return command;
    }
}
