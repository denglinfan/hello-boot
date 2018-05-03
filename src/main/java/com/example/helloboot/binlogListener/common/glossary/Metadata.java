package com.example.helloboot.binlogListener.common.glossary;

import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;

import java.io.Serializable;
import java.util.Arrays;

public class Metadata implements Serializable {

    private static final long serialVersionUID = 4634414541769527837L;

    private final byte[] type;
    private final int[] metadata;

    public Metadata(byte[] type, int[] metadata) {
        this.type = type;
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("metadata",Arrays.toString(metadata)).toString();
    }

    public byte getType(int column){
        return this.type[column];
    }

    public int getMetadata(int column){
        return this.metadata[column];
    }

    public static final Metadata valueOf(byte[] type, byte[] data){
        return null;
    }
}
