package com.example.helloboot.binlogListener.binlog.impl.parser;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.binlogListener.binlog.UserVariable;
import com.example.helloboot.binlogListener.binlog.impl.event.UserVarEvent;
import com.example.helloboot.binlogListener.binlog.impl.variable.user.*;
import com.example.helloboot.binlogListener.io.XInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserVarEventParser extends AbstractBinlogEventParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserVarEventParser.class);

    public UserVarEventParser() {
        super(UserVarEvent.EVENT_TYPE);
    }

    public void parser(XInputStream is, BinlogEventV4Header header, BinlogParserContext context)
        throws IOException{
        final UserVarEvent event = new UserVarEvent(header);
        event.setVarNameLength(is.readInt(4));
        event.setVarName(is.readFixedLengthString(event.getVarNameLength()));
        event.setIsNull(is.readInt(1));
        if(event.getIsNull() == 0){
            event.setVarType(is.readInt(1));
            event.setVarCollation(is.readInt(4));
            event.setVarValueLength(is.readInt(4));
            event.setVarValue(parseUserVariables(is,event));
        }
        context.getEventListener().onEvents(event);
    }

    protected UserVariable parseUserVariables(XInputStream is, UserVarEvent event)
    throws IOException{
        final int type = event.getVarType();
        switch (type){
            case UserVariableDecimal.TYPE:
                return new UserVariableDecimal(is.readBytes(event.getVarValueLength()));
            case UserVariableInt.TYPE:
                return new UserVariableInt(is.readLong(event.getVarValueLength()),is.readInt(1));
            case UserVariableReal.TYPE:
                return new UserVariableReal(Double.longBitsToDouble(is.readLong(event.getVarValueLength())));
            case UserVariableRow.TYPE:
                return new UserVariableRow(is.readBytes(event.getVarValueLength()));
            case UserVariableString.TYPE:
                return new UserVariableString(is.readBytes(event.getVarValueLength()),event.getVarCollation());
            default:
                LOGGER.warn("unknown user variable type:" + type);return null;
        }
    }
}
