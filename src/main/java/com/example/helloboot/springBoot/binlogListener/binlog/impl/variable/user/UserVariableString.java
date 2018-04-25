package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.user;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

public class UserVariableString extends AbstractUserVariable {

    public static final int TYPE = MySqlConstants.STRING_RESULT;

    private final byte[] value;
    private final int collation;

    public UserVariableString(byte[] value, int collation) {
        super(TYPE);
        this.value = value;
        this.collation = collation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value",value)
                .append("collation",collation).toString();
    }

    @Override
    public byte[] getValue() {
        return value;
    }

    public int getCollation() {
        return collation;
    }
}
