package com.example.helloboot.binlogListener.common.glossary.column;

import com.example.helloboot.binlogListener.common.glossary.Column;

public class EnumColumn implements Column {

    private static final long serialVersionUID = -6017298545673303080L;

    private final int value;

    private EnumColumn(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public Integer getValue() {
        return value;
    }

    public static final EnumColumn valueOf(int value){
        return new EnumColumn(value);
    }
}
