package com.example.helloboot.binlogListener.common.glossary.column;

import com.example.helloboot.binlogListener.common.glossary.Column;

import java.util.Date;

public class DateTimeColumn implements Column {

    private static final long serialVersionUID = 6444968242222031354L;

    private final Date value;

    private DateTimeColumn(Date value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public Date getValue() {
        return value;
    }

    public static final DateTimeColumn valueOf(Date value){
        return new DateTimeColumn(value);
    }
}
