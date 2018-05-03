package com.example.helloboot.binlogListener.binlog;

public interface BinlogParserListener {

    void onStart(BinlogParser parser);

    void onStop(BinlogParser parser);

    void onException(BinlogParser parser, Exception exception);

    class Adapter implements BinlogParserListener{
        @Override
        public void onStart(BinlogParser parser) {

        }

        @Override
        public void onStop(BinlogParser parser) {

        }

        @Override
        public void onException(BinlogParser parser, Exception exception) {

        }
    }
}
