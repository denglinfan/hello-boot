package com.example.helloboot.binlogListener.binlog.impl.event;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;

public class RandEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.RAND_EVENT;

    private long randSeed1;
    private long randSeed2;

    public RandEvent() {
    }

    public RandEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("randSeed1",randSeed1)
                .append("randSeed2",randSeed2).toString();
    }

    public long getRandSeed1() {
        return randSeed1;
    }

    public void setRandSeed1(long randSeed1) {
        this.randSeed1 = randSeed1;
    }

    public long getRandSeed2() {
        return randSeed2;
    }

    public void setRandSeed2(long randSeed2) {
        this.randSeed2 = randSeed2;
    }
}
