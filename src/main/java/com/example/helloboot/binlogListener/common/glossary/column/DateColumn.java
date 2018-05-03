package com.example.helloboot.binlogListener.common.glossary.column;

import com.example.helloboot.binlogListener.common.glossary.Column;

import java.sql.Date;

public class DateColumn implements Column {

    private static final long serialVersionUID = 959710929844516680L;

    private final Date value;

    private DateColumn(Date value) {
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

    public static final DateColumn valueOf(Date value){
        return new DateColumn(value);
    }
}
