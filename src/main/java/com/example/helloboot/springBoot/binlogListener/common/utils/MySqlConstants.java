package com.example.helloboot.springBoot.binlogListener.common.utils;

public class MySqlConstants {

    //
    public static final int MAX_PACKET_LENGTH                   = (256 * 256 * 256 -1);


    //log_event
    public static final int QUERY_EVENT                         = 2;
    public static final int TABLE_MAP_EVENT                     = 19;

    //command
    public static final int COM_BINLOG_DUMP                     = 0x12;

    //client capabilitise
    public static final int CLIENT_LONG_FLAG                    = 4;            /* Get all column flags */
    public static final int CLIENT_CONNECT_WITH_DB              = 8;            /* One can specify db on connect */
    public static final int CLIENT_PROTOCOL_41                  = 512;          /* New 4.1 protocol */
    public static final int CLIENT_SECURE_CONNECTION            = 32768;        /* New 4.1 authentication */
}
