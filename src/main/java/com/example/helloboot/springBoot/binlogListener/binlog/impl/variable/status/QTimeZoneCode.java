package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QTimeZoneCode extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_TIME_ZONE_CODE;

    private final StringColumn timeZone;

    public QTimeZoneCode(StringColumn timeZone) {
        super(TYPE);
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("timeZone",timeZone).toString();
    }

    public StringColumn getTimeZone() {
        return timeZone;
    }

    public static QTimeZoneCode valueOf(XInputStream tis) throws IOException{
        final int length = tis.readInt(1);
        return new QTimeZoneCode(tis.readFixedLengthString(length));
    }
}
