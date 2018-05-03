package com.example.helloboot.binlogListener.binlog.impl.event;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.glossary.Pair;
import com.example.helloboot.binlogListener.common.glossary.Row;
import com.example.helloboot.binlogListener.common.glossary.column.BitColumn;
import com.example.helloboot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;

import java.util.List;

public class UpdateRowsEvent extends AbstractRowEvent {

    public static final int EVENT_TYPE = MySqlConstants.UPDATE_ROWS_EVENT;

    private UnsignedLong columnCount;
    private BitColumn usedColumnsBefore;
    private BitColumn usedColumnsAfter;
    private List<Pair<Row>> rows;

    public UpdateRowsEvent() {
    }

    public UpdateRowsEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("tableId",tableId)
                .append("reserved",reserved)
                .append("columnCount",columnCount)
                .append("usedColumnsBefore",usedColumnsBefore)
                .append("usedColumnsAfter",usedColumnsAfter)
                .append("rows",rows).toString();
    }

    public UnsignedLong getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(UnsignedLong columnCount) {
        this.columnCount = columnCount;
    }

    public BitColumn getUsedColumnsBefore() {
        return usedColumnsBefore;
    }

    public void setUsedColumnsBefore(BitColumn usedColumnsBefore) {
        this.usedColumnsBefore = usedColumnsBefore;
    }

    public BitColumn getUsedColumnsAfter() {
        return usedColumnsAfter;
    }

    public void setUsedColumnsAfter(BitColumn usedColumnsAfter) {
        this.usedColumnsAfter = usedColumnsAfter;
    }

    public List<Pair<Row>> getRows() {
        return rows;
    }

    public void setRows(List<Pair<Row>> rows) {
        this.rows = rows;
    }
}
