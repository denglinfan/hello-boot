package com.example.helloboot.springBoot.binlogListener.common.glossary.column;

import com.example.helloboot.springBoot.binlogListener.common.glossary.Column;

import java.sql.Time;

public class Time2Column implements Column {

    private static final long serialVersionUID = 2408833111678694298L;

    private final Time value;

    private Time2Column(Time value) {
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

    public static final Time2Column valueOf(Time value){
        return new Time2Column(value);
    }
}
