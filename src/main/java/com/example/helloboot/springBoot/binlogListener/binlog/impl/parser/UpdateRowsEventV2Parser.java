package com.example.helloboot.springBoot.binlogListener.binlog.impl.parser;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.TableMapEvent;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.UpDateRowsEventV2;
import com.example.helloboot.springBoot.binlogListener.common.glossary.Pair;
import com.example.helloboot.springBoot.binlogListener.common.glossary.Row;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UpdateRowsEventV2Parser extends AbstractRowEventParser {

    public UpdateRowsEventV2Parser() {
        super(UpDateRowsEventV2.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final long tableId = is.readLong(6);
        final TableMapEvent tme = context.getTableMapEvent(tableId);
        if(this.rowEventFilter != null && !this.rowEventFilter.accepts(header,context,tme)){
            is.skip(is.available());
            return ;
        }

        final UpDateRowsEventV2 event = new UpDateRowsEventV2(header);
        event.setTableId(tableId);
        event.setReserved(is.readInt(2));
        event.setExtraInFoLength(is.readInt(2));
        if(event.getExtraInFoLength() > 2){
            event.setExtraInfo(is.readBytes(event.getExtraInFoLength() - 2));
        }
        event.setColumnCount(is.readUnsignedLong());
        event.setUsedColumnBefore(is.readBit(event.getColumnCount().intValue()));
        event.setUsedColumnAfter(is.readBit(event.getColumnCount().intValue()));
        event.setRows(parseRows(is, tme, event));
        context.getEventListener().onEvents(event);
    }

    protected List<Pair<Row>> parseRows(XInputStream is, TableMapEvent tme, UpDateRowsEventV2 ure)
        throws IOException{
        final List<Pair<Row>> r = new LinkedList<>();
        while (is.available() > 0){
            final Row before = parseRow(is, tme, ure.getUsedColumnBefore());
            final Row after = parseRow(is, tme, ure.getUsedColumnAfter());
            r.add(new Pair<Row>(before,after));
        }
        return r;
    }
}
