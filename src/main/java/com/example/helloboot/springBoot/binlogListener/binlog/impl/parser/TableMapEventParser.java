package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.TableMapEvent;
import com.example.helloboot.springBoot.binlogListener.common.glossary.Metadata;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class TableMapEventParser extends AbstractBinlogEventParser {

    private boolean reusePreviousEvent = true;

    public TableMapEventParser() {
        super(TableMapEvent.EVENT_TYPE);
    }

    public boolean isReusePreviousEvent() {
        return reusePreviousEvent;
    }

    public void setReusePreviousEvent(boolean reusePreviousEvent) {
        this.reusePreviousEvent = reusePreviousEvent;
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context)
            throws IOException {
        final long tableId = is.readLong(6);
        if(this.reusePreviousEvent && context.getTableMapEvent(tableId) != null){
            is.skip(is.available());
            final TableMapEvent event = context.getTableMapEvent(tableId).copy();
            event.setHeader(header);
            context.getEventListener().onEvents(event);
            return;
        }

        final TableMapEvent event = new TableMapEvent(header);
        event.setTableId(tableId);
        event.setReserved(is.readInt(2));
        event.setDatabaseNameLength(is.readInt(1));
        event.setDatabaseName(is.readNullTerminatedString());
        event.setTableNameLength(is.readInt(1));
        event.setTableName(is.readNullTerminatedString());
        event.setColumnCount(is.readUnsignedLong());
        event.setColumnTypes(is.readBytes(event.getColumnCount().intValue()));
        event.setColumnMetadataCount(is.readUnsignedLong());
        event.setColumnMetaData(Metadata.valueOf(event.getColumnTypes(),is.readBytes(event.getColumnMetadataCount().intValue())));
        event.setColumnNullabilities(is.readBit(event.getColumnCount().intValue()));
        context.getEventListener().onEvents(event);
    }
}
