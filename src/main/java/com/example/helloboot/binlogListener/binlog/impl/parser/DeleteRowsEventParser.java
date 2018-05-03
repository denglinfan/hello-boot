package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.binlogListener.binlog.impl.event.DeleteRowsEvent;
import com.example.helloboot.binlogListener.binlog.impl.event.TableMapEvent;
import com.example.helloboot.binlogListener.common.glossary.Row;
import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DeleteRowsEventParser extends AbstractRowEventParser {

    public DeleteRowsEventParser() {
        super(DeleteRowsEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final long tableId = is.readLong(6);
        final TableMapEvent tme = context.getTableMapEvent(tableId);
        if(this.rowEventFilter != null && !this.rowEventFilter.accepts(header,context,tme)){
            is.skip(is.available());
            return ;
        }

        final DeleteRowsEvent event = new DeleteRowsEvent(header);
        event.setTableId(tableId);
        event.setReserved(is.readInt(2));
        event.setColumnCount(is.readUnsignedLong());
        event.setUsedColumns(is.readBit(event.getColumnCount().intValue()));
        event.setRows(parseRows(is, tme, event));
        context.getEventListener().onEvents(event);
    }

    protected List<Row> parseRows(XInputStream is, TableMapEvent tme, DeleteRowsEvent dre)
    throws IOException{
        final List<Row> r = new LinkedList<>();
        while (is.available() > 0){
            r.add(parseRow(is, tme, dre.getUsedColumns()));
        }
        return r;
    }
}
