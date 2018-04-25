package com.example.helloboot.springBoot.binlogListener.common.glossary.column;

import com.example.helloboot.springBoot.binlogListener.common.glossary.Column;

import java.math.BigDecimal;

public class DecimalColumn implements Column {

    private static final long serialVersionUID = -3798378473095594835L;

    private final BigDecimal value;
    private final int precision;
    private final int scale;

    private DecimalColumn(BigDecimal value, int precision, int scale) {
        this.value = value;
        this.precision = precision;
        this.scale = scale;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public int getPrecision() {
        return precision;
    }

    public int getScale() {
        return scale;
    }

    public static final DecimalColumn valueOf(BigDecimal value, int precision, int scale){
        if(precision < scale){
            throw new IllegalArgumentException("invalid precision:" + precision + ",scale:" + scale);
        }
        return new DecimalColumn(value,precision,scale);
    }
}
