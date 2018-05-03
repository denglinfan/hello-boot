package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.binlogListener.binlog.impl.event.DeleteRowsEventV2;
import com.example.helloboot.binlogListener.common.glossary.Row;
import com.example.helloboot.binlogListener.io.XInputStream;
import com.example.helloboot.binlogListener.binlog.impl.event.TableMapEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DeleteRowsEventV2Parser extends AbstractRowEventParser {

    public DeleteRowsEventV2Parser() {
        super(DeleteRowsEventV2.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final long tableId = is.readLong(6);
        final TableMapEvent tme = context.getTableMapEvent(tableId);
        if(this.rowEventFilter != null && !this.rowEventFilter.accepts(header,context,tme)){
            is.skip(is.available());
            return ;
        }

        final DeleteRowsEventV2 event = new DeleteRowsEventV2(header);
        event.setTableId(tableId);
        event.setReserved(is.readInt(2));
        event.setExtraInfoLength(is.readInt(2));
        if(event.getExtraInfoLength() > 2){
            event.setExtraInfo(is.readBytes(event.getExtraInfoLength() - 2));
        }
        event.setColumnCount(is.readUnsignedLong());
        event.setUsedColumns(is.readBit(event.getColumnCount().intValue()));
        event.setRows(parseRows(is, tme, event));
        context.getEventListener().onEvents(event);
    }

    protected List<Row> parseRows(XInputStream is, TableMapEvent tme, DeleteRowsEventV2 dre)
        throws IOException{
        final List<Row> r = new LinkedList<>();
        while (is.available() > 0){
            r.add(parseRow(is, tme, dre.getUsedColumns()));
        }

        return r;
    }
}
