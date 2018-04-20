package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class AbstractBinlogEventV4 implements BinlogEventV4 {

    protected BinlogEventV4Header header;

    @Override
    public BinlogEventV4Header getHeader() {
        return header;
    }

    public void setHeader(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("header",header).toString();
    }
}
