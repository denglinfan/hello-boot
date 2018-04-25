package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QTableMapForUpdateCode extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_TABLE_MAP_FOR_UPDATE_CODE;

    private final long tableMap;

    public QTableMapForUpdateCode(long tableMap) {
        super(TYPE);
        this.tableMap = tableMap;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tableMap",tableMap).toString();
    }

    public long getTableMap() {
        return tableMap;
    }

    public static QTableMapForUpdateCode valueOf(XInputStream tis) throws IOException{
        return new QTableMapForUpdateCode(tis.readLong(8));
    }
}
