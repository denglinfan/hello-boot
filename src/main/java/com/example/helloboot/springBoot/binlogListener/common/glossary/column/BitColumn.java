package com.example.helloboot.springBoot.binlogListener.common.glossary.column;

import com.example.helloboot.springBoot.binlogListener.common.glossary.Column;

import java.util.Arrays;

public final class BitColumn implements Column {
    private static final long serialVersionUID = 4193150509864408687L;

    private static final int BIT_MASKS[] = {1<<0, 1<<1, 1<<2, 1<<3, 1<<4, 1<<5, 1<<6, 1<<7};

    private final int length;
    private final byte[] value;

    public BitColumn(int length, byte[] value) {
        this.length = length;
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder r = new StringBuilder(this.length);
        for(int i = this.length - 1; i>=0; i--){
            r.append(get(i)?"1":"0");
        }
        return r.toString();
    }

    public int getLength() {
        return length;
    }

    public byte[] getValue() {
        return value;
    }

    public boolean get(int index){
        final int byteIndex = (index >> 3);
        final int bitIndex = (index - (byteIndex << 3));
        return (this.value[byteIndex] & BIT_MASKS[bitIndex]) != 0;
    }

    public void set(int index){
        final int byteIndex = (index >> 3);
        final int bitIndex = (index - (byteIndex << 3));
        this.value[byteIndex] |= BIT_MASKS[bitIndex];
    }

    public static final BitColumn valueOf(int length, byte[] value){
        if(length < 0 || length > (value.length << 3)){
            throw new IllegalArgumentException("invalid length:" + length);
        }
        return new BitColumn(length, value);
    }
}