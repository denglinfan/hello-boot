package com.example.helloboot.testSpringBoot;

import com.example.helloboot.springBoot.binlogListener.OpenReplicator;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventListener;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenReplicatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenReplicatorTest.class);

    public static void main(String args[]) throws Exception{
        final OpenReplicator or = new OpenReplicator();
        or.setUser("root");
        or.setHost("localhost");
        or.setPassword("197239");
        or.setPort(3306);
        or.setServerId(1);
        or.setBinlogPosition(22);
        or.setBinlogFileName("mysql-bin.000006");

        or.setBinlogEventListener(new BinlogEventListener() {
            @Override
            public void onEvents(BinlogEventV4 event) {
                LOGGER.info("{}",event);
            }
        });

        or.start();
    }
}
