package com.example.helloboot.binlogListener.binlog.impl.event;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.binlogListener.common.glossary.Row;
import com.example.helloboot.binlogListener.common.glossary.column.BitColumn;

import java.util.List;

public class DeleteRowsEvent extends AbstractRowEvent {

    public static final int EVENT_TYPE = MySqlConstants.DELETE_ROWS_EVENT;

    private UnsignedLong columnCount;
    private BitColumn usedColumns;
    private List<Row> rows;

    public DeleteRowsEvent() {
    }

    public DeleteRowsEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("tableId",tableId)
                .append("reserved",reserved)
                .append("columnCount",columnCount)
                .append("usedColumns",usedColumns)
                .append("rows",rows).toString();
    }

    public UnsignedLong getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(UnsignedLong columnCount) {
        this.columnCount = columnCount;
    }

    public BitColumn getUsedColumns() {
        return usedColumns;
    }

    public void setUsedColumns(BitColumn usedColumns) {
        this.usedColumns = usedColumns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
