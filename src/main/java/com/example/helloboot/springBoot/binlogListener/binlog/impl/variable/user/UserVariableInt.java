package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.user;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class UserVariableInt extends AbstractUserVariable {

    public static final int TYPE = MySqlConstants.INT_RESULT;

    private final long value;
    @SuppressWarnings("unused")
    private final int todo; //TODO

    public UserVariableInt(long value, int todo) {
        super(TYPE);
        this.value = value;
        this.todo = todo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value",value).toString();
    }

    @Override
    public Long getValue() {
        return value;
    }
}
