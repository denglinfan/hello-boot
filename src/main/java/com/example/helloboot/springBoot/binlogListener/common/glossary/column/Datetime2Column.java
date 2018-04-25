package com.example.helloboot.springBoot.binlogListener.common.glossary.column;

import com.example.helloboot.springBoot.binlogListener.common.glossary.Column;

import java.util.Date;

public class Datetime2Column implements Column {

    private static final long serialVersionUID = 6444968242222031354L;

    private final Date value;

    private Datetime2Column(Date value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public Date getValue() {
        return this.value;
    }

    public static final Datetime2Column valueOf(Date value){
        return new Datetime2Column(value);
    }
}
