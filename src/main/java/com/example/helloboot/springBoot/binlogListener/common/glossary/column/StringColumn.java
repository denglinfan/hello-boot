package com.example.helloboot.springBoot.binlogListener.common.glossary.column;

import com.example.helloboot.springBoot.binlogListener.common.glossary.Column;

public class StringColumn implements Column {

    private static final long serialVersionUID = 1009717372407166422L;

    private final byte[] value;

    private StringColumn(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return new String(this.value);
    }

    public static final StringColumn valueOf(byte[] value){
        return new StringColumn(value);
    }
}
