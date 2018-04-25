package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QLcTimeNamesCode extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_LC_TIME_NAMES_CODE;

    private final int lcTimeNames;

    public QLcTimeNamesCode(int lcTimeNames) {
        super(TYPE);
        this.lcTimeNames = lcTimeNames;
    }

    public int getLcTimeNames() {
        return lcTimeNames;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("lcTimeNames",lcTimeNames).toString();
    }

    public static QLcTimeNamesCode valueOf(XInputStream tis) throws IOException{
        return new QLcTimeNamesCode(tis.readInt(2));
    }
}
