package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.common.glossary.Row;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.BitColumn;
import com.example.helloboot.springBoot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

import java.util.List;

public class WriteRowsEventV2 extends AbstractRowEvent {

    public static final int EVENT_TYPE = MySqlConstants.WRITE_ROWS_EVENT_V2;

    private int extraInfoLength;
    private byte extraInfo[];
    private UnsignedLong columnCount;
    private BitColumn usedColumns;
    private List<Row> rows;

    public WriteRowsEventV2() {
    }

    public WriteRowsEventV2(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("tableId",tableId)
                .append("reserved",reserved)
                .append("extraInfoLength",extraInfoLength)
                .append("extraInfo",extraInfo)
                .append("columnCount",columnCount)
                .append("usedColumns",usedColumns)
                .append("rows",rows).toString();
    }

    public int getExtraInfoLength() {
        return extraInfoLength;
    }

    public void setExtraInfoLength(int extraInfoLength) {
        this.extraInfoLength = extraInfoLength;
    }

    public byte[] getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(byte[] extraInfo) {
        this.extraInfo = extraInfo;
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
