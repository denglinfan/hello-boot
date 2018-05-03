package com.example.helloboot.binlogListener.binlog.impl.event;

public class AbstractRowEvent extends AbstractBinlogEventV4 {

    protected long tableId;
    protected int reserved;

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
}
