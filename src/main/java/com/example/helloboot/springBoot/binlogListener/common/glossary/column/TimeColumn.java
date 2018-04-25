package com.example.helloboot.springBoot.binlogListener.common.glossary.column;

import com.example.helloboot.springBoot.binlogListener.common.glossary.Column;

import java.sql.Time;

public class TimeColumn implements Column {

    private static final long serialVersionUID = 2408833111678694298L;

    private final Time value;

    private TimeColumn(Time value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public Time getValue() {
        return value;
    }

    public static final TimeColumn valueOf(Time value){
        return new TimeColumn(value);
    }
}
