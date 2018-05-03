package com.example.helloboot.binlogListener.io.util;

import com.example.helloboot.binlogListener.common.glossary.column.BitColumn;
import com.example.helloboot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.binlogListener.io.XInputStream;
import com.example.helloboot.binlogListener.io.impl.XInputStreamImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XDeserializer implements XInputStream {

    private final XInputStream tis;

    public XDeserializer(byte[] data) {
        this.tis = new XInputStreamImpl(new ByteArrayInputStream(data));
    }

    @Override
    public void close() throws IOException {
        this.tis.close();
    }

    @Override
    public int available() throws IOException {
        return this.tis.available();
    }

    @Override
    public boolean hasMore() throws IOException {
        return this.tis.hasMore();
    }

    @Override
    public void setReadLimit(int limit) throws IOException {
        this.tis.setReadLimit(limit);
    }

    @Override
    public long skip(long n) throws IOException {
        return this.tis.skip(n);
    }

    @Override
    public int readInt(int length) throws IOException {
        return this.tis.readInt(length);
    }

    @Override
    public long readLong(int length) throws IOException {
        return this.tis.readLong(length);
    }

    @Override
    public byte[] readBytes(int length) throws IOException {
        return this.tis.readBytes(length);
    }

    @Override
    public BitColumn readBit(int length) throws IOException {
        return readBit(length);
    }

    @Override
    public int readSignedInt(int length) throws IOException {
        return this.tis.readSignedInt(length);
    }

    @Override
    public long readSignedLong(int length) throws IOException {
        return this.tis.readSignedLong(length);
    }

    @Override
    public UnsignedLong readUnsignedLong() throws IOException {
        return tis.readUnsignedLong();
    }

    @Override
    public StringColumn readLengthCodedString() throws IOException {
        return this.tis.readLengthCodedString();
    }

    @Override
    public StringColumn readNullTerminatedString() throws IOException {
        return this.tis.readNullTerminatedString();
    }

    @Override
    public StringColumn readFixedLengthString(int length) throws IOException {
        return this.tis.readFixedLengthString(length);
    }

    @Override
    public int readInt(int length, boolean littleEndian) throws IOException {
        return this.tis.readInt(length,littleEndian);
    }

    @Override
    public long readLong(int length, boolean littleEndian) throws IOException {
        return this.tis.readLong(length,littleEndian);
    }

    @Override
    public BitColumn readBit(int length, boolean littleEndian) throws IOException {
        return tis.readBit(length,littleEndian);
    }
}
