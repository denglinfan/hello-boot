package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QUpdatedDBNames extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_UPDATED_DB_NAMES;

    private final int accessedDbCount;
    private final StringColumn[] accessedDbs;

    public QUpdatedDBNames(int accessedDbCount, StringColumn[] accessedDbs) {
        super(TYPE);
        this.accessedDbCount = accessedDbCount;
        this.accessedDbs = accessedDbs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accessedDbCount",accessedDbCount)
                .append("accessedDbs",accessedDbs).toString();
    }

    public int getAccessedDbCount() {
        return accessedDbCount;
    }

    public StringColumn[] getAccessedDbs() {
        return accessedDbs;
    }

    public static QUpdatedDBNames valueOf(XInputStream tis) throws IOException{
        int accessedDbCount = tis.readInt(1);
        StringColumn accessedDbs[] = null;
        if(accessedDbCount > MySqlConstants.MAX_DBS_IN_EVENT_MTS){
            accessedDbCount = MySqlConstants.MAX_DBS_IN_EVENT_MTS;
        } else{
            accessedDbs = new StringColumn[accessedDbCount];
            for(int i = 0; i < accessedDbCount; i++){
                accessedDbs[i] = tis.readNullTerminatedString();
            }
        }
        return new QUpdatedDBNames(accessedDbCount,accessedDbs);
    }
}
