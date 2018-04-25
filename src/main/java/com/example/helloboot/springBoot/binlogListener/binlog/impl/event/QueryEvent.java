package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.StatusVariable;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;

import java.util.List;

public class QueryEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.QUERY_EVENT;

    private long threadId;
    private long elapsedTime;
    private int databaseNameLength;
    private int errorCode;
    private int statusVariablesLength;
    private List<StatusVariable> statusVariables;
    private StringColumn databaseName;
    private StringColumn sql;

    public QueryEvent() {
    }

    public QueryEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "QueryEvent{" +
                "threadId=" + threadId +
                ", elapsedTime=" + elapsedTime +
                ", databaseNameLength=" + databaseNameLength +
                ", errorCode=" + errorCode +
                ", statusVariablesLength=" + statusVariablesLength +
                ", statusVariables=" + statusVariables +
                ", databaseName=" + databaseName +
                ", sql=" + sql +
                '}';
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getDatabaseNameLength() {
        return databaseNameLength;
    }

    public void setDatabaseNameLength(int databaseNameLength) {
        this.databaseNameLength = databaseNameLength;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getStatusVariablesLength() {
        return statusVariablesLength;
    }

    public void setStatusVariablesLength(int statusVariablesLength) {
        this.statusVariablesLength = statusVariablesLength;
    }

    public List<StatusVariable> getStatusVariables() {
        return statusVariables;
    }

    public void setStatusVariables(List<StatusVariable> statusVariables) {
        this.statusVariables = statusVariables;
    }

    public StringColumn getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(StringColumn databaseName) {
        this.databaseName = databaseName;
    }

    public StringColumn getSql() {
        return sql;
    }

    public void setSql(StringColumn sql) {
        this.sql = sql;
    }
}
