package com.example.helloboot.binlogListener.io.impl;

import com.example.helloboot.binlogListener.common.glossary.UnsignedLong;
import com.example.helloboot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.binlogListener.common.utils.CodecUtils;
import com.example.helloboot.binlogListener.io.ExceedLimitException;
import com.example.helloboot.binlogListener.io.util.XSerializer;
import com.example.helloboot.binlogListener.common.glossary.column.BitColumn;
import com.example.helloboot.binlogListener.io.XInputStream;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class XInputStreamImpl extends InputStream implements XInputStream {

    private int head = 0;
    private int tail = 0;
    private int readCount = 0;
    private int readLimit = 0;
    private final byte[] buffer;
    private final InputStream is;

    public XInputStreamImpl(InputStream is) {
        this(is,512 * 1024);
    }

    public XInputStreamImpl(InputStream is,int size) {
        this.is = is;
        this.buffer = new byte[size];
    }

    @Override
    public int readInt(int length) throws IOException {
        return readInt(length,true);
    }

    @Override
    public long readLong(int length) throws IOException {
        return readLong(length,true);
    }

    @Override
    public byte[] readBytes(int length) throws IOException {
        final byte[] r = new byte[length];
        this.read(r,0,length);
        return r;
    }

    @Override
    public BitColumn readBit(int length) throws IOException {
        return readBit(length,true);
    }

    @Override
    public UnsignedLong readUnsignedLong() throws IOException {
        final int v = this.read();
        if(v < 251) {
            return UnsignedLong.valueOf(v);
        } else if(v == 251){
            return null;
        } else if(v == 252){
            return UnsignedLong.valueOf(readInt(2));
        } else if(v == 253){
            return UnsignedLong.valueOf(readInt(3));
        } else if(v == 254){
            return UnsignedLong.valueOf(readInt(8));
        } else{
            throw new RuntimeException("assertion failed, should Not reach here");
        }
    }

    @Override
    public StringColumn readLengthCodedString() throws IOException {
        final UnsignedLong length = readUnsignedLong();
        return length == null ? null : readFixedLengthString(length.intValue());
    }

    @Override
    public StringColumn readNullTerminatedString() throws IOException {
        final XSerializer s = new XSerializer(128);
        while(true){
            final int v = this.read();
            if(v == 0) break;
            s.writeInt(v,1);
        }
        return StringColumn.valueOf(s.toByteArray());
    }

    @Override
    public StringColumn readFixedLengthString(int length) throws IOException {
        return StringColumn.valueOf(readBytes(length));
    }

    @Override
    public int readSignedInt(int length) throws IOException {
        int r = 0;
        for(int i = 0; i < length; ++i){
            final int v = this.read();
            r |= (v <<(i << 3));
            if((i == length - 1) && ((v & 0x80) == 0x80)){
                for(int j = length; j < 4; j++){
                    r |= (255 << (j << 3));
                }
            }
        }
        return r;
    }

    @Override
    public long readSignedLong(int length) throws IOException {
        long r = 0;
        for(int i = 0; i < length; ++i){
            final long v = this.read();
            r |= (v << (i << 3));
            if((i == length - -1) && ((v & 0x80) == 0x80)){
                for(int j = length; j < 8; j++){
                    r |= (255 << (j << 3));
                }
            }
        }
        return r;
    }

    @Override
    public int readInt(int length, boolean littleEndian) throws IOException {
        int r = 0;
        for(int i = 0; i < length; ++i){
            final int v = this.read();
            if(littleEndian){
                r |= (v << (i << 3));
            }else{
                r = (r << 8) | v;
            }
        }
        return r;
    }

    @Override
    public long readLong(int length, boolean littleEndian) throws IOException {
        long r = 0;
        for(int i = 0; i < length; ++i){
            final long v = this.read();
            if(littleEndian){
                r |= (v << ( i << 3));
            } else{
                r= ( r << 8) | v;
            }
        }
        return r;
    }

    @Override
    public BitColumn readBit(int length, boolean littleEndian) throws IOException {
        byte[] bytes = readBytes((int)((length + 7) >> 3));
        if(!littleEndian){
            bytes = CodecUtils.toBigEndian(bytes);
        }
        return BitColumn.valueOf(length,bytes);
    }

    @Override
    public long skip(long n) throws IOException {
        if(this.readLimit > 0 && (this.readCount + n) > this.readLimit){
            this.readCount += doSkip(this.readLimit - this.readCount);
            throw new ExceedLimitException();
        } else{
            this.readCount += doSkip(n);
            return n;
        }
    }

    @Override
    public int available() throws IOException {
        if(this.readLimit > 0){
            return this.readLimit - this.readCount;
        } else{
            return this.tail - this.head + this.is.available();
        }
    }

    @Override
    public void close() throws IOException {
        this.is.close();
    }

    @Override
    public boolean hasMore() throws IOException {
        if(this.head < this.tail){
            return true;
        }
        return this.available() > 0;
    }

    @Override
    public void setReadLimit(int limit) throws IOException {
        this.readCount = 0;
        this.readLimit = limit;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if(this.readLimit > 0 && (this.readCount + len) > this.readLimit){
            this.readCount += doRead(b,off,this.readLimit - this.readCount);
            throw new ExceedLimitException();
        }else{
            this.readCount += doRead(b, off,len);
            return len;
        }
    }

    @Override
    public int read() throws IOException {
        if(this.readLimit > 0 && (this.readCount + 1) > this.readLimit){
            throw new ExceedLimitException();
        } else{
            if(this.head >= this.tail){
                doFill();
            }
            final int r = this.buffer[this.head++] & 0xFF;
            ++this.readCount;
            return r;
        }
    }

    private void doFill() throws IOException{
        this.head = 0;
        this.tail = this.is.read(this.buffer,0,this.buffer.length);
        if(this.tail <= 0){
            throw new EOFException();
        }
    }

    private long doSkip(final long n ) throws IOException{
        long total = n;
        while(total > 0){
            final int availabale = this.tail - this.head;
            if(availabale >= total){
                this.head += total;
                break;
            } else{
                total -= availabale;
                doFill();
            }
        }
        return n;
    }

    private int doRead(final byte[] b, final int off, final int len) throws IOException{
        int total = len;
        int index = off;
        while(total > 0){
            final int available = this.tail - this.head;
            if(available >= total){
                System.arraycopy(this.buffer,this.head,b,index,total);
                this.head += total;
                break;
            } else{
                System.arraycopy(this.buffer,this.head,b,index,available);
                index += available;
                total -= available;
                doFill();
            }
        }
        return len;
    }
}
