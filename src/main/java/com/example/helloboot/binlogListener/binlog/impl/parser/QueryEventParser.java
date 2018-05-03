package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.binlogListener.binlog.StatusVariable;
import com.example.helloboot.binlogListener.binlog.impl.event.QueryEvent;
import com.example.helloboot.binlogListener.binlog.impl.variable.status.*;
import com.example.helloboot.binlogListener.io.util.XDeserializer;
import com.example.helloboot.binlogListener.io.XInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryEventParser extends AbstractBinlogEventParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryEventParser.class);

    public QueryEventParser() {
        super(QueryEvent.EVENT_TYPE);
    }

    @Override
    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        final QueryEvent event = new QueryEvent(header);
        event.setThreadId(is.readLong(4));
        event.setElapsedTime(is.readLong(4));
        event.setDatabaseNameLength(is.readInt(1));
        event.setErrorCode(is.readInt(2));
        event.setStatusVariablesLength(is.readInt(2));
        event.setStatusVariables(parseStatusVariables(is.readBytes(event.getStatusVariablesLength())));
        event.setDatabaseName(is.readNullTerminatedString());
        event.setSql(is.readFixedLengthString(is.available()));
        context.getEventListener().onEvents(event);
    }

    private List<StatusVariable> parseStatusVariables(byte[] data) throws IOException {
        final List<StatusVariable> r = new ArrayList<>();
        final XDeserializer d = new XDeserializer(data);
        boolean abort = false;
        while (!abort && d.available() > 0){
            final int type = d.readInt(1);
            switch (type){
                case QAutoIncrement.TYPE:
                    r.add(QAutoIncrement.valueOf(d));break;
                case QCatalogCode.TYPE:
                    r.add(QCatalogCode.valueOf(d));break;
                case QCatalogNzCode.TYPE:
                    r.add(QCatalogNzCode.valueOf(d));break;
                case QCharsetCode.TYPE:
                    r.add(QCharsetCode.valueOf(d));break;
                case QCharsetDatabaseCode.TYPE:
                    r.add(QCharsetDatabaseCode.valueOf(d)); break;
                case QFlags2Code.TYPE:
                    r.add(QFlags2Code.valueOf(d)); break;
                case QLcTimeNamesCode.TYPE:
                    r.add(QLcTimeNamesCode.valueOf(d)); break;
                case QSQLModeCode.TYPE:
                    r.add(QSQLModeCode.valueOf(d)); break;
                case QTableMapForUpdateCode.TYPE:
                    r.add(QTableMapForUpdateCode.valueOf(d)); break;
                case QTimeZoneCode.TYPE:
                    r.add(QTimeZoneCode.valueOf(d)); break;
                case QMasterDataWrittenCode.TYPE:
                    r.add(QMasterDataWrittenCode.valueOf(d)); break;
                case QInvoker.TYPE:
                    r.add(QInvoker.valueOf(d)); break;
                case QUpdatedDBNames.TYPE:
                    r.add(QUpdatedDBNames.valueOf(d)); break;
                case QMicroseconds.TYPE:
                    r.add(QMicroseconds.valueOf(d)); break;
                default:
                    LOGGER.warn("unknown status variable type: " + type);
                    abort = true; break;
            }
        }
        return r;
    }
}
