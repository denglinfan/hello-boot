package com.example.helloboot.testSpringBoot;

import com.example.helloboot.binlogListener.OpenParser;
import com.example.helloboot.binlogListener.binlog.impl.event.XidEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class OpenParserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenParserTest.class);

    public static void main(String args[]) throws Exception{
        final OpenParser op = new OpenParser();
        op.setBinlogFileName("mysql-bin.000006");
        op.setBinlogFilePath("D:/dev/mysql/log");
        op.setBinlogEventListener((event) -> {
            if(event instanceof XidEvent){
                LOGGER.info("{}", event);
            }
        });
        op.start();

        LOGGER.info("press 'q' to stop");
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(String line = br.readLine(); line != null; line = br.readLine()){
            if(line.equals("q")){
                op.stop(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
                break;
            }
        }
    }
}
