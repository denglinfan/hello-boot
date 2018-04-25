package com.example.helloboot.springBoot.binlogListener.common.utils;

public class MySqlConstants {

    //
    public static final int MAX_PACKET_LENGTH                   = (256 * 256 * 256 -1);


    //log_event
    public static final int QUERY_EVENT                         = 2;
    public static final int STOP_EVENT                          = 3;
    public static final int ROTATE_EVENT                        = 4;
    public static final int INTVAR_EVENT                        = 5;
    public static final int RAND_EVENT                          = 13;
    public static final int USER_VAR_EVENT                      = 14;
    public static final int FORMAT_DESCRIPTION_EVENT            = 15;
    public static final int XID_EVENT                           = 16;
    public static final int TABLE_MAP_EVENT                     = 19;
    public static final int WRITE_ROWS_EVENT                    = 23;
    public static final int UPDATE_ROWS_EVENT                   = 24;
    public static final int DELETE_ROWS_EVENT                   = 25;
    public static final int INCIDENT_EVENT                      = 26;
    public static final int WRITE_ROWS_EVENT_V2                 = 30;
    public static final int UPDATE_ROWS_EVENT_V2                = 31;
    public static final int DELETE_ROWS_EVENT_V2                = 32;
    public static final int GTID_LOG_EVENT                      = 33;

    //command
    public static final int COM_BINLOG_DUMP                     = 0x12;

    //client capabilitise
    public static final int CLIENT_LONG_FLAG                    = 4;            /* Get all column flags */
    public static final int CLIENT_CONNECT_WITH_DB              = 8;            /* One can specify db on connect */
    public static final int CLIENT_PROTOCOL_41                  = 512;          /* New 4.1 protocol */
    public static final int CLIENT_SECURE_CONNECTION            = 32768;        /* New 4.1 authentication */

    //status variable type
    public static final int Q_FLAGS2_CODE                       = 0;
    public static final int Q_SQL_MODE_CODE                     = 1;
    public static final int Q_CATALOG_CODE                      = 2;
    public static final int Q_AUTO_INCREMENT                    = 3;
    public static final int Q_CHARSET_CODE                      = 4;
    public static final int Q_TIME_ZONE_CODE                    = 5;
    public static final int Q_CATALOG_NZ_CODE                   = 6;
    public static final int Q_LC_TIME_NAMES_CODE                = 7;
    public static final int Q_CHARSET_DATABASE_CODE             = 8;
    public static final int Q_TABLE_MAP_FOR_UPDATE_CODE         = 9;
    public static final int Q_MASTER_DATA_WRITTEN_CODE          = 10;
    public static final int Q_INVOKER                           = 11;
    public static final int Q_UPDATED_DB_NAMES                  = 12;
    public static final int Q_MICROSECONDS                      = 13;

    public static final int MAX_DBS_IN_EVENT_MTS                = 254;

    //user variable type
    public static final int STRING_RESULT                       = 0;
    public static final int REAL_RESULT                         = 1;
    public static final int INT_RESULT                          = 2;
    public static final int ROW_RESULT                          = 3;
    public static final int DECIMAL_RESULT                      = 4;

    //column type
    public static final int TYPE_TINY                           = 1;
    public static final int TYPE_SHORT                          = 2;
    public static final int TYPE_LONG                           = 3;
    public static final int TYPE_FLOAT                          = 4;
    public static final int TYPE_DOUBLE                         = 5;
    public static final int TYPE_TIMESTAMP                      = 7;
    public static final int TYPE_LONGLONG                       = 8;
    public static final int TYPE_INT24                          = 9;
    public static final int TYPE_DATE                           = 10;
    public static final int TYPE_TIME                           = 11;
    public static final int TYPE_DATETIME                       = 12;
    public static final int TYPE_YEAR                           = 13;
    public static final int TYPE_VARCHAR                        = 15;
    public static final int TYPE_BIT                            = 16;
    public static final int TYPE_TIMESTAMP2                     = 17;
    public static final int TYPE_DATETIME2                      = 18;
    public static final int TYPE_TIME2                          = 19;
    public static final int TYPE_NEWDECIMAL                     = 246;
    public static final int TYPE_ENUM                           = 247;
    public static final int TYPE_SET                            = 248;
    public static final int TYPE_BLOB                           = 252;
    public static final int TYPE_VAR_STRING                     = 253;
    public static final int TYPE_STRING                         = 254;
}
