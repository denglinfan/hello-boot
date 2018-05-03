package com.example.helloboot.binlogListener.common.glossary.column;

import com.example.helloboot.binlogListener.common.glossary.Column;

import java.sql.Timestamp;

public class TimeStampColumn implements Column {

    private static final long serialVersionUID = 6334849626188321306L;

    private final Timestamp value;

    private TimeStampColumn(Timestamp value) {
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

    public static final TimeStampColumn valueOf(Timestamp value){
        return new TimeStampColumn(value);
    }
}
