package com.example.helloboot.springBoot.binlogListener.binlog.impl.variable.status;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.springBoot.binlogListener.io.XInputStream;

import java.io.IOException;

public class QCharsetCode extends AbstractStatusVariable {

    public static final int TYPE = MySqlConstants.Q_CHARSET_CODE;

    private final int characterSetClient;
    private final int collationConnection;
    private final int collationServer;

    public QCharsetCode(int characterSetClient, int collationConnection, int collationServer) {
        super(TYPE);
        this.characterSetClient = characterSetClient;
        this.collationConnection = collationConnection;
        this.collationServer = collationServer;
    }

    public int getCharacterSetClient() {
        return characterSetClient;
    }

    public int getCollationConnection() {
        return collationConnection;
    }

    public int getCollationServer() {
        return collationServer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("characterSetClient",characterSetClient)
                .append("collationConnection",collationConnection)
                .append("collationServer",collationServer).toString();
    }

    public static QCharsetCode valueOf(XInputStream tis) throws IOException{
        final int characterSetClient = tis.readInt(2);
        final int collationConnection = tis.readInt(2);
        final int collationServer = tis.readInt(2);
        return new QCharsetCode(characterSetClient,collationConnection,collationServer);
    }
}
