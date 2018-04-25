package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class IntvarEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.INTVAR_EVENT;

    private int type;
    private UnsignedLong value;

    public IntvarEvent() {
    }

    public IntvarEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("type",type)
                .append("value",value).toString();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UnsignedLong getValue() {
        return value;
    }

    public void setValue(UnsignedLong value) {
        this.value = value;
    }
}
