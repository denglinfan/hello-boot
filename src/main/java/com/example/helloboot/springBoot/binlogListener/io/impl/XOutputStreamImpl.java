package com.example.helloboot.springBoot.binlogListener.io.impl;

import com.example.helloboot.springBoot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.springBoot.binlogListener.common.glossary.column.UnsignedLong;
import com.example.helloboot.springBoot.binlogListener.io.XOutputStream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class XOutputStreamImpl extends BufferedOutputStream implements XOutputStream {

    public XOutputStreamImpl(OutputStream out) {
        super(out);
    }

    @Override
    public void writeBytes(byte[] value) throws IOException {
        super.write(value,0,value.length);
    }

    @Override
    public void writeBytes(int value, int length) throws IOException {
        for(int i = 0; i < length; i++){
            super.write(value);
        }
    }

    @Override
    public void writeInt(int value, int length) throws IOException {
        for(int i = 0; i < length; i++){
            super.write(0x000000FF & (value >>>(i << 3)));
        }
    }

    @Override
    public void writeLong(long value, int length) throws IOException {
        for(int i = 0; i< length; i++){
            super.write((int)(0x00000000000000FF & (value >>> (i << 3))));
        }
    }

    @Override
    public void writeUnsignedLong(UnsignedLong value) throws IOException {
        final long length = value.longValue();
        if(length < 0){
            writeLong(254,1);
            writeLong(length,8);
        } else if(length < 251L){
            writeLong(length,1);
        } else if(length < 65536L){
            writeLong(252,1);
            writeLong(length,2);
        } else if(length < 16777216L){
            writeLong(253,1);
            writeLong(length,3);
        } else{
            writeLong(254,1);
            writeLong(length,8);
        }
    }

    @Override
    public void writeLengthCodedString(StringColumn value) throws IOException {
        writeUnsignedLong(UnsignedLong.valueOf(value.getValue().length));
        writeFixedLengthString(value);
    }

    @Override
    public void writeFixedLengthString(StringColumn value) throws IOException {
        super.write(value.getValue());
    }

    @Override
    public void writeNullTerminatedString(StringColumn value) throws IOException {
        super.write(value.getValue());
        super.write(0);
    }

    @Override
    public void writeBytes(byte[] value, int offset, int length) throws IOException {
        super.write(value,offset,length);
    }
}
