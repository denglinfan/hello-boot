package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.user;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class UserVariableRow extends AbstractUserVariable {

    public static final int TYPE = MySqlConstants.ROW_RESULT;

    private final byte[] value;

    public UserVariableRow(byte[] value) {
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
