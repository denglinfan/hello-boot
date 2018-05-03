package com.example.helloboot.binlogListener.binlog.impl.event;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.binlogListener.binlog.UserVariable;

public class UserVarEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.USER_VAR_EVENT;

    private int varNameLength;
    private StringColumn varName;
    private int isNull;
    private int varType;
    private int varCollation;
    private int varValueLength;
    private UserVariable varValue;

    public UserVarEvent() {
    }

    public UserVarEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("varNameLength",varNameLength)
                .append("varName",varName)
                .append("isNull",isNull)
                .append("varType",varType)
                .append("varCollation",varCollation)
                .append("varValueLength",varValueLength)
                .append("varValue",varValue).toString();
    }

    public int getVarNameLength() {
        return varNameLength;
    }

    public void setVarNameLength(int varNameLength) {
        this.varNameLength = varNameLength;
    }

    public StringColumn getVarName() {
        return varName;
    }

    public void setVarName(StringColumn varName) {
        this.varName = varName;
    }

    public int getIsNull() {
        return isNull;
    }

    public void setIsNull(int isNull) {
        this.isNull = isNull;
    }

    public int getVarType() {
        return varType;
    }

    public void setVarType(int varType) {
        this.varType = varType;
    }

    public int getVarCollation() {
        return varCollation;
    }

    public void setVarCollation(int varCollation) {
        this.varCollation = varCollation;
    }

    public int getVarValueLength() {
        return varValueLength;
    }

    public void setVarValueLength(int varValueLength) {
        this.varValueLength = varValueLength;
    }

    public UserVariable getVarValue() {
        return varValue;
    }

    public void setVarValue(UserVariable varValue) {
        this.varValue = varValue;
    }
}
