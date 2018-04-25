package com.example.helloboot.springBoot.binlogListener.binlog.impl.filter;

import com.example.helloboot.springBoot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogParserContext;
import com.example.helloboot.springBoot.binlogListener.binlog.BinlogRowEventFilter;
import com.example.helloboot.springBoot.binlogListener.binlog.impl.event.TableMapEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinlogRowEventFilterImpl implements BinlogRowEventFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinlogRowEventFilterImpl.class);

    private boolean verbose = true;

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    @Override
    public boolean accepts(BinlogEventV4Header header, BinlogParserContext xontext, TableMapEvent event) {
        if(event == null){
            if(isVerbose() && LOGGER.isWarnEnabled()){
                LOGGER.warn("failed to find TableMapEvent, header:{}",header);
            }
            return false;
        }
        return true;
    }
}
