package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class RotateEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.ROTATE_EVENT;

    private long binlogPosition;
    private StringColumn binlogFileName;

    public RotateEvent() {
    }

    public RotateEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    public long getBinlogPosition() {
        return binlogPosition;
    }

    public void setBinlogPosition(long binlogPosition) {
        this.binlogPosition = binlogPosition;
    }

    public StringColumn getBinlogFileName() {
        return binlogFileName;
    }

    public void setBinlogFileName(StringColumn binlogFileName) {
        this.binlogFileName = binlogFileName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("binlogPosition",binlogPosition)
                .append("binlogFileName",binlogFileName).toString();
    }
}
