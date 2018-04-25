package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.user;

import com.example.helloboot.springBoot.binlogListener.binlog.UserVariable;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public abstract class AbstractUserVariable implements UserVariable {

    protected final int type;

    public AbstractUserVariable(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

    @Override
    public int getType() {
        return type;
    }
}
