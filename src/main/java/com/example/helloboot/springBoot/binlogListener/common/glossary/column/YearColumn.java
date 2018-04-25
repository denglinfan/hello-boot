package com.example.helloboot.springBoot.binlogListener.common.glossary.column;

import com.example.helloboot.springBoot.binlogListener.common.glossary.Column;

public class YearColumn implements Column {

    private static final long serialVersionUID = 6428744630692270846L;

    private static final YearColumn[] CACHE = new YearColumn[255];
    static {
        for(int i = 0; i < CACHE.length; i++){
            CACHE[i] = new YearColumn(i + 1900);
        }
    }

    private final int value;

    private YearColumn(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public static final YearColumn valueOf(int value){
        final int index = value - 1900;
        return (index >= 0 && index < CACHE.length) ? CACHE[index] : new YearColumn(value);
    }
}
