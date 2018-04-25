package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QCatalogNzCode extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_CATALOG_NZ_CODE;

    private final StringColumn catalogName;

    public QCatalogNzCode(StringColumn catalogName) {
        super(TYPE);
        this.catalogName = catalogName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("catalogName",catalogName).toString();
    }

    public StringColumn getCatalogName() {
        return catalogName;
    }

    public static QCatalogNzCode valueOf(XInputStream tis) throws IOException{
        final int length = tis.readInt(1);
        return new QCatalogNzCode(tis.readFixedLengthString(length));
    }
}
