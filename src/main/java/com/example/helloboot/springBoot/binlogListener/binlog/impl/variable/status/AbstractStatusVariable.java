package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.binlog.StatusVariable;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class AbstractStatusVariable implements StatusVariable {

    protected final int type;

    public AbstractStatusVariable(int type) {
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
