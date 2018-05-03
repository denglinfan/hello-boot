package com.example.helloboot.binlogListener.binlog.impl.event;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;

public class StopEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.STOP_EVENT;

    public StopEvent() {
    }

    public StopEvent(BinlogEventV4Header header){
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header).toString();
    }
}
