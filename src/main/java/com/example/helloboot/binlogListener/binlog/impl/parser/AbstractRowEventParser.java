package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogRowEventFilter;
import com.example.helloboot.binlogListener.common.glossary.column.*;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.binlog.impl.event.TableMapEvent;
import com.example.helloboot.binlogListener.binlog.impl.filter.BinlogRowEventFilterImpl;
import com.example.helloboot.binlogListener.common.glossary.Column;
import com.example.helloboot.binlogListener.common.glossary.Metadata;
import com.example.helloboot.binlogListener.common.glossary.Row;
import com.example.helloboot.binlogListener.common.utils.CodecUtils;
import com.example.helloboot.binlogListener.common.utils.MySQLUtils;
import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRowEventParser extends AbstractBinlogEventParser {

    protected BinlogRowEventFilter rowEventFilter;

    public AbstractRowEventParser(int eventType) {
        super(eventType);
        this.rowEventFilter = new BinlogRowEventFilterImpl();
    }

    public BinlogRowEventFilter getRowEventFilter() {
        return rowEventFilter;
    }

    public void setRowEventFilter(BinlogRowEventFilter rowEventFilter) {
        this.rowEventFilter = rowEventFilter;
    }

    protected Row parseRow(XInputStream is, TableMapEvent tme, BitColumn usedColumns)
        throws IOException{

        int unusedColumnCount = 0;
        final byte[] types = tme.getColumnTypes();
        final Metadata metadata = tme.getColumnMetaData();
        final BitColumn nullColumns = is.readBit(types.length);
        final List<Column> columns = new ArrayList<>(types.length);
        for(int i = 0; i < types.length; ++i){
            int length = 0;
            final int meta = metadata.getMetadata(i);
            int type = CodecUtils.toUnsigned(types[i]);
            if(type == MySqlConstants.TYPE_STRING && meta > 256){
                final int meta0 = meta >> 8;
                final int meta1 = meta & 0xFF;
                if((meta0 & 0x30) != 0x30){
                    type = meta0 | 0x30;
                    length = meta1 | (((meta0 & 0x30) ^ 0x30) << 4);
                } else{
                    switch (meta0){
                        case MySqlConstants.TYPE_SET:
                        case MySqlConstants.TYPE_ENUM:
                        case MySqlConstants.TYPE_STRING:
                            type = meta0;
                            length = meta1;
                            break;
                        default:
                            throw new RuntimeException("assertion failed, unknown column type:" + type);
                    }
                }
            }

            if(!usedColumns.get(i)){
                unusedColumnCount++;
                continue;
            } else if(nullColumns.get(i - unusedColumnCount)){
                columns.add(NullColumn.valueOf(type));
                continue;
            }

            switch (type){
                case MySqlConstants.TYPE_TINY:
                    columns.add(TinyColumn.valueOf(is.readSignedInt(1)));break;
                case MySqlConstants.TYPE_SHORT:
                    columns.add(ShortColumn.valueOf(is.readSignedInt(2)));break;
                case MySqlConstants.TYPE_INT24:
                    columns.add(Int24Column.valueOf(is.readSignedInt(3)));break;
                case MySqlConstants.TYPE_LONG:
                    columns.add(LongColumn.valueOf(is.readSignedInt(4)));break;
                case MySqlConstants.TYPE_LONGLONG:
                    columns.add(LongLongColumn.valueOf(is.readSignedLong(8)));break;
                case MySqlConstants.TYPE_FLOAT:
                    columns.add(FloatColumn.valueOf(Float.intBitsToFloat(is.readInt(4))));break;
                case MySqlConstants.TYPE_DOUBLE:
                    columns.add(DoubleColumn.valueOf(Double.longBitsToDouble(is.readLong(8))));break;
                case MySqlConstants.TYPE_YEAR:
                    columns.add(YearColumn.valueOf(MySQLUtils.toYear(is.readInt(1))));break;
                case MySqlConstants.TYPE_DATE:
                    columns.add(DateColumn.valueOf(MySQLUtils.toData(is.readInt(3))));break;
                case MySqlConstants.TYPE_TIME:
                    columns.add(TimeColumn.valueOf(MySQLUtils.toTime(is.readInt(3))));break;
                case MySqlConstants.TYPE_DATETIME:
                    columns.add(DateTimeColumn.valueOf(MySQLUtils.toDatetime(is.readLong(8))));break;
                case MySqlConstants.TYPE_TIMESTAMP:
                    columns.add(TimeStampColumn.valueOf(MySQLUtils.toTimestamp(is.readLong(4))));break;
                case MySqlConstants.TYPE_ENUM:
                    columns.add(EnumColumn.valueOf(is.readInt(length)));break;
                case MySqlConstants.TYPE_SET:
                    columns.add(SetColumn.valueOf(is.readLong(length)));break;
                case MySqlConstants.TYPE_BIT:
                    final int bitLength = (meta >> 8) * 8 + (meta & 0xFF);
                    columns.add(is.readBit(bitLength,false));
                    break;
                case MySqlConstants.TYPE_BLOB:
                    final int blobLength = is.readInt(meta);
                    columns.add(BlobColumn.valueOf(is.readBytes(blobLength)));
                    break;
                case MySqlConstants.TYPE_NEWDECIMAL:
                    final int precision = meta & 0xFF;
                    final int scale = meta >> 8;
                    final int decimalLength = MySQLUtils.getDecimalBinarySize(precision,scale);
                    columns.add(DecimalColumn.valueOf(MySQLUtils.toDecimal(precision,scale,is.readBytes(decimalLength)),precision,scale));
                case MySqlConstants.TYPE_STRING:
                    final int stringLength = length < 256 ? is.readInt(1) : is.readInt(2);
                    columns.add(is.readFixedLengthString(stringLength));
                    break;
                case MySqlConstants.TYPE_VARCHAR:
                case MySqlConstants.TYPE_VAR_STRING:
                    final int varcharLength = meta < 256 ? is.readInt(1) : is.readInt(2);
                    columns.add(is.readFixedLengthString(varcharLength));
                    break;
                case MySqlConstants.TYPE_TIME2:
                    final int value1 = is.readInt(3,false);
                    final int nanos1 = is.readInt((meta + 1) / 2, false);
                    columns.add(Time2Column.valueOf(MySQLUtils.toTime2(value1,nanos1)));
                    break;
                case MySqlConstants.TYPE_DATETIME2:
                    final long value2 = is.readLong(5,false);
                    final int nanos2 = is.readInt((meta + 1) / 2,false);
                    columns.add(Datetime2Column.valueOf(MySQLUtils.toDatetime2(value2,nanos2)));
                    break;
                case MySqlConstants.TYPE_TIMESTAMP2:
                    final long value3 = is.readLong(4,false);
                    final int nanos3 = is.readInt((meta+1) / 2,false);
                    columns.add(TimeStamp2Column.valueOf(MySQLUtils.toTimestamp2(value3,nanos3)));
                    break;
                default:
                    throw new RuntimeException("assertion failed, unknown column type :" + type);
            }
        }
        return new Row(columns);
    }
}
