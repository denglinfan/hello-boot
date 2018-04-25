package com.example.helloboot.springBoot.binlogListener.binlog.impl.event;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.common.glossary.Metadata;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.BitColumn;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

import java.util.Arrays;

public class TableMapEvent extends AbstractBinlogEventV4 {

    public static final int EVENT_TYPE = MySqlConstants.TABLE_MAP_EVENT;

    private long tableId;
    private int reserved;
    private int databaseNameLength;
    private StringColumn databaseName;
    private int tableNameLength;
    private StringColumn tableName;
    private UnsignedLong columnCount;
    private byte[] columnTypes;
    private UnsignedLong columnMetadataCount;
    private Metadata columnMetaData;
    private BitColumn columnNullabilities;

    public TableMapEvent() {
    }

    public TableMapEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header",header)
                .append("tabled",tableId)
                .append("reserved",reserved)
                .append("databaseNameLength",databaseNameLength)
                .append("databaseName",databaseName)
                .append("tableNameLength",tableNameLength)
                .append("tableName",tableName)
                .append("columnCount",columnCount)
                .append("columnTypes", Arrays.toString(columnTypes))
                .append("columnMetadataCount",columnMetadataCount)
                .append("columnMetaData",columnMetaData)
                .append("columnNullabilities",columnNullabilities).toString();
    }

    public TableMapEvent copy(){
        final TableMapEvent r = new TableMapEvent();
        r.setHeader(this.header);
        r.setTableId(this.tableId);
        r.setReserved(this.reserved);
        r.setDatabaseNameLength(this.databaseNameLength);
        r.setDatabaseName(this.databaseName);
        r.setTableNameLength(this.tableNameLength);
        r.setTableName(this.tableName);
        r.setColumnCount(this.columnCount);
        r.setColumnTypes(this.columnTypes);
        r.setColumnMetadataCount(this.columnMetadataCount);
        r.setColumnMetaData(this.columnMetaData);
        r.setColumnNullabilities(this.columnNullabilities);
        return r;
    }

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

    public int getDatabaseNameLength() {
        return databaseNameLength;
    }

    public void setDatabaseNameLength(int databaseNameLength) {
        this.databaseNameLength = databaseNameLength;
    }

    public StringColumn getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(StringColumn databaseName) {
        this.databaseName = databaseName;
    }

    public int getTableNameLength() {
        return tableNameLength;
    }

    public void setTableNameLength(int tableNameLength) {
        this.tableNameLength = tableNameLength;
    }

    public StringColumn getTableName() {
        return tableName;
    }

    public void setTableName(StringColumn tableName) {
        this.tableName = tableName;
    }

    public UnsignedLong getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(UnsignedLong columnCount) {
        this.columnCount = columnCount;
    }

    public byte[] getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(byte[] columnTypes) {
        this.columnTypes = columnTypes;
    }

    public UnsignedLong getColumnMetadataCount() {
        return columnMetadataCount;
    }

    public void setColumnMetadataCount(UnsignedLong columnMetadataCount) {
        this.columnMetadataCount = columnMetadataCount;
    }

    public Metadata getColumnMetaData() {
        return columnMetaData;
    }

    public void setColumnMetaData(Metadata columnMetaData) {
        this.columnMetaData = columnMetaData;
    }

    public BitColumn getColumnNullabilities() {
        return columnNullabilities;
    }

    public void setColumnNullabilities(BitColumn columnNullabilities) {
        this.columnNullabilities = columnNullabilities;
    }
}
