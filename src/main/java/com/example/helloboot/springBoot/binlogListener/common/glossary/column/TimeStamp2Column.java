package com.example.helloboot.springBoot.binlogListener.common.glossary.column;

import com.example.helloboot.springBoot.binlogListener.common.glossary.Column;

import java.sql.Timestamp;

public class TimeStamp2Column implements Column {

    private static final long serialVersionUID = 6334849626188321306L;

    private final Timestamp value;

    private TimeStamp2Column(Timestamp value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public Timestamp getValue() {
        return value;
    }

    public static final TimeStamp2Column valueOf(Timestamp value){
        return new TimeStamp2Column(value);
    }
}
