package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QFlags2Code extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_FLAGS2_CODE;

    private final int flags;

    public QFlags2Code(int flags) {
        super(TYPE);
        this.flags = flags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("flags",flags).toString();
    }

    public int getFlags() {
        return flags;
    }

    public static QFlags2Code valueOf(XInputStream tis) throws IOException{
        return new QFlags2Code(tis.readInt(4));
    }
}
