package com.example.helloboot.binlogListener.binlog.impl.event;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.common.glossary.Row;
import com.example.helloboot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.binlogListener.common.glossary.column.BitColumn;

import java.util.List;

public final class WriteRowsEvent extends AbstractRowEvent {

    public static final int EVENT_TYPE = MySqlConstants.WRITE_ROWS_EVENT;

    private UnsignedLong columnCount;
    private BitColumn usedCoulumns;
    private List<Row> rows;

    public WriteRowsEvent() {
    }

    public WriteRowsEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("tableId",tableId)
                .append("reserved",reserved)
                .append("columnCount",columnCount)
                .append("usedCoulumns",usedCoulumns)
                .append("rows",rows).toString();
    }

    public UnsignedLong getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(UnsignedLong columnCount) {
        this.columnCount = columnCount;
    }

    public BitColumn getUsedCoulumns() {
        return usedCoulumns;
    }

    public void setUsedCoulumns(BitColumn usedCoulumns) {
        this.usedCoulumns = usedCoulumns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
