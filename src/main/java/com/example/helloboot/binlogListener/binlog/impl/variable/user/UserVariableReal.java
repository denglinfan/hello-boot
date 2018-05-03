package com.example.helloboot.binlogListener.binlog.impl.variable.user;

import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;

public class UserVariableReal extends AbstractUserVariable {

    public static final int TYPE = MySqlConstants.REAL_RESULT;

    private final double value;

    public UserVariableReal(double value) {
        super(TYPE);
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value",value).toString();
    }

    @Override
    public Double getValue() {
        return value;
    }
}
