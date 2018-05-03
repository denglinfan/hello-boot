package com.example.helloboot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QCharsetDatabaseCode extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_CHARSET_DATABASE_CODE;

    private final int collationDatabase;

    public QCharsetDatabaseCode(int collationDatabase) {
        super(TYPE);
        this.collationDatabase = collationDatabase;
    }

    public int getCollationDatabase() {
        return collationDatabase;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("collationDatabase",collationDatabase).toString();
    }

    public static QCharsetDatabaseCode valueOf(XInputStream tis) throws IOException{
        final int collationDatabase = tis.readInt(2);
        return new QCharsetDatabaseCode(collationDatabase);
    }
}
