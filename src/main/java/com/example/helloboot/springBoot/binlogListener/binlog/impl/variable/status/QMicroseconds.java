package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QMicroseconds extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_MICROSECONDS;

    private final int startUsec;

    public QMicroseconds(int startUsec) {
        super(TYPE);
        this.startUsec = startUsec;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("startUsec",startUsec).toString();
    }

    public int getStartUsec() {
        return startUsec;
    }

    public static QMicroseconds valueOf(XInputStream tis) throws IOException{
        return new QMicroseconds(tis.readInt(3));
    }
}
