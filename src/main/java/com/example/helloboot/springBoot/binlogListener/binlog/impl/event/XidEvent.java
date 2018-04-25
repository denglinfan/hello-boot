package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class XidEvent extends AbstractBinlogEventV4{

    public static final int EVENT_TYPE = MySqlConstants.XID_EVENT;

    private long xid;

    public XidEvent() {
    }

    public XidEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("xid",xid).toString();
    }

    public long getXid() {
        return xid;
    }

    public void setXid(long xid) {
        this.xid = xid;
    }
}
