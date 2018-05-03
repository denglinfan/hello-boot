package com.example.helloboot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QInvoker extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_INVOKER;

    private final StringColumn user;
    private final StringColumn host;

    public QInvoker(StringColumn user, StringColumn host) {
        super(TYPE);
        this.user = user;
        this.host = host;
    }

    public StringColumn getUser() {
        return user;
    }

    public StringColumn getHost() {
        return host;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user",user)
                .append("host",host).toString();
    }

    public static QInvoker valueOf(XInputStream tis) throws IOException{
        final int userLength = tis.readInt(1);
        final StringColumn user = tis.readFixedLengthString(userLength);
        final int hostLength = tis.readInt(1);
        final StringColumn host = tis.readFixedLengthString(hostLength);
        return new QInvoker(user,host);
    }
}
