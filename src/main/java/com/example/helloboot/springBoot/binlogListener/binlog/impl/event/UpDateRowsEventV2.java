package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.common.glossary.Pair;
import com.example.helloboot.springBoot.binlogListener.common.glossary.Row;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.BitColumn;
import com.example.helloboot.springBoot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

import java.util.List;

public class UpDateRowsEventV2 extends AbstractRowEvent {

    public static final int EVENT_TYPE = MySqlConstants.UPDATE_ROWS_EVENT_V2;

    private int extraInFoLength;
    private byte extraInfo[];
    private UnsignedLong columnCount;
    private BitColumn usedColumnBefore;
    private BitColumn usedColumnAfter;
    private List<Pair<Row>> rows;

    public UpDateRowsEventV2() {
    }

    public UpDateRowsEventV2(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("tableId",tableId)
                .append("reserved",reserved)
                .append("extraInFoLength",extraInFoLength)
                .append("extraInfo",extraInfo)
                .append("columnCount",columnCount)
                .append("usedColumnBefore",usedColumnBefore)
                .append("usedColumnAfter",usedColumnAfter)
                .append("rows",rows).toString();
    }

    public int getExtraInFoLength() {
        return extraInFoLength;
    }

    public void setExtraInFoLength(int extraInFoLength) {
        this.extraInFoLength = extraInFoLength;
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

    public BitColumn getUsedColumnBefore() {
        return usedColumnBefore;
    }

    public void setUsedColumnBefore(BitColumn usedColumnBefore) {
        this.usedColumnBefore = usedColumnBefore;
    }

    public BitColumn getUsedColumnAfter() {
        return usedColumnAfter;
    }

    public void setUsedColumnAfter(BitColumn usedColumnAfter) {
        this.usedColumnAfter = usedColumnAfter;
    }

    public List<Pair<Row>> getRows() {
        return rows;
    }

    public void setRows(List<Pair<Row>> rows) {
        this.rows = rows;
    }
}
