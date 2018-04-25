package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.user;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class UserVariableDecimal extends AbstractUserVariable {

    public static final int TYPE = MySqlConstants.DECIMAL_RESULT;

    private final byte[] value;

    public UserVariableDecimal(byte[] value) {
        super(TYPE);
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value",value).toString();
    }

    @Override
    public byte[] getValue() {
        return value;
    }
}
