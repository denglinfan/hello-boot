package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QMasterDataWrittenCode extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_MASTER_DATA_WRITTEN_CODE;

    private final int value;

    public QMasterDataWrittenCode(int value) {
        super(TYPE);
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value",value).toString();
    }

    public int getValue() {
        return value;
    }

    public static QMasterDataWrittenCode valueOf(XInputStream tis) throws IOException{
        return new QMasterDataWrittenCode(tis.readInt(4));
    }
}
