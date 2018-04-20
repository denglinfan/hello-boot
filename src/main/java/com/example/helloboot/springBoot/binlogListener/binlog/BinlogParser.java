package com.example.helloboot.springBoot.binlogListener.binlog;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface BinlogParser {

    boolean isRunning();

    void start() throws Exception;

    void stop(long timeout, TimeUnit unit) throws Exception;

    void setEventFilter(BinlogEventFilter filter);

    void setEventListener(BinlogEventListener listener);

    List<BinlogParserListener> getParserListeners();

    boolean addParserListtener(BinlogParserListener listener);

    boolean removeParserListener(BinlogParserListener listener);

    void setParserListeners(List<BinlogParserListener> listeners);
}
