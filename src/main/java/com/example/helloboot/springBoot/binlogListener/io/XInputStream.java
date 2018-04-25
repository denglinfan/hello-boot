package com.example.helloboot.springBoot.binlogListener.io;

import com.example.helloboot.springBoot.binlogListener.common.glossary.column.BitColumn;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.glossary.UnsignedLong;

import java.io.IOException;

public interface XInputStream {

    void close() throws IOException;

    int available() throws IOException;

    boolean hasMore() throws IOException;

    void setReadLimit(int limit) throws IOException;

    long skip(long n) throws IOException;

    int readInt(int length) throws  IOException;

    long readLong(int length) throws IOException;

    byte[] readBytes(int length) throws IOException;

    BitColumn readBit(int length) throws IOException;

    int readSignedInt(int length) throws IOException;

    long readSignedLong(int length) throws IOException;

    UnsignedLong readUnsignedLong() throws IOException;

    StringColumn readLengthCodedString() throws IOException;

    StringColumn readNullTerminatedString() throws  IOException;

    StringColumn readFixedLengthString(int length) throws IOException;

    int readInt(int length, boolean littleEndian) throws  IOException;

    long readLong(int length, boolean littleEndian) throws IOException;

    BitColumn readBit(int length, boolean littleEndian) throws IOException;
}
