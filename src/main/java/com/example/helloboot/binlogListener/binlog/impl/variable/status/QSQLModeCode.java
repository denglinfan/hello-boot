package com.example.helloboot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QSQLModeCode extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_SQL_MODE_CODE;

    private final long sqlMode;

    public QSQLModeCode(long sqlMode) {
        super(TYPE);
        this.sqlMode = sqlMode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sqlMode",sqlMode).toString();
    }

    public long getSqlMode() {
        return sqlMode;
    }

    public static QSQLModeCode valueOf(XInputStream tis) throws IOException{
        return new QSQLModeCode(tis.readLong(8));
    }
}
