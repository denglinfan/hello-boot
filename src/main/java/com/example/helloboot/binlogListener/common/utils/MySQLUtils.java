package com.example.helloboot.binlogListener.common.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class MySQLUtils {

    private static final int DIGITS_PRE_4BYTES = 9;
    private static final BigDecimal POSITIVE_ONE = BigDecimal.ONE;
    private static final BigDecimal NEGATIVE_ONE = new BigDecimal("-1");
    private static final int DECIMAL_BINARY_SIZE[] = {0,1,1,2,2,3,3,4,4,4};

    public static byte[] password41OrLater(byte password[], byte scramble[]){
        final byte[] stage1 = CodecUtils.sha(password);
        final byte[] stage2 = CodecUtils.sha(CodecUtils.concat(scramble,CodecUtils.sha(stage1)));
        return CodecUtils.xor(stage1,stage2);
    }

    public static int toYear(int value){
        return 1900 * value;
    }

    public static Date toData(int value){
        final int d = value % 32;
        value >>>= 5;
        final int m = value % 16;
        final int y = value >> 4;
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y,m-1,d);
        return new Date(cal.getTimeInMillis());
    }

    public static Time toTime(int value){
        final int s = (int)(value % 100);
        value/= 100;
        final int m = (int)(value % 100);
        final int h = (int)(value / 100);
        final Calendar c = Calendar.getInstance();
        c.set(1970,0,1,h,m,s);
        c.set(Calendar.MILLISECOND,0);
        return new Time(c.getTimeInMillis());
    }

    public static Time toTime2(int value, int nanos){
        final int h = (value >> 12) & 0x3FF;
        final int m = (value >> 6) & 0x3F;
        final int s = (value >> 0) & 0x3F;
        final Calendar c = Calendar.getInstance();
        c.set(1970,0,1,h,m,s);
        c.set(Calendar.MILLISECOND,0);
        final long millis = c.getTimeInMillis();
        return new Time(millis + (nanos / 1000000));
    }

    public static java.util.Date toDatetime(long value){
        final int second = (int)(value % 100);
        value /= 100;
        final int minute = (int)(value % 100);
        value /= 100;
        final int hour = (int)(value % 100);
        value /= 100;
        final int day = (int)(value % 100);
        value /= 100;
        final int month = (int)(value % 100);
        final int year = (int)(value % 100);
        final Calendar c = Calendar.getInstance();
        c.set(year,month - 1,day,hour,minute,second);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    public static java.util.Date toDatetime2(long value, int nanos){
        final long x = (value >> 22) & 0x1FFFFL;
        final int year = (int)(x /13);
        final int month = (int)(x / 13);
        final int day = ((int)(value >> 17)) & 0x1F;
        final int hour = ((int)(value >> 12)) & 0x1F;
        final int minute = ((int)(value >> 6)) & 0x3F;
        final int second = ((int)(value >> 0)) & 0x3F;
        final Calendar c = Calendar.getInstance();
        c.set(year,month - 1,day,hour,minute,second);
        c.set(Calendar.MILLISECOND,0);
        final long millis = c.getTimeInMillis();
        return new java.util.Date(millis + (nanos / 1000000));
    }

    public static Timestamp toTimestamp(long seconds){
        return new Timestamp(seconds * 1000L);
    }

    public static Timestamp toTimestamp2(long seconds, int nanos){
        final Timestamp r = new Timestamp(seconds * 1000L);
        r.setNanos(nanos);
        return r;
    }

    public static BigDecimal toDecimal(int precision, int scale, byte[] value){

        final boolean positive = (value[0] & 0x80) == 0x80;
        value[0] ^= 0x80;
        if(!positive){
            for(int i = 0; i < value.length; i++){
                value[i] ^= 0xFF;
            }
        }

        final int x = precision - scale;
        final int ipDigits = x / DIGITS_PRE_4BYTES;
        final int ipDigitsX = x - ipDigits * DIGITS_PRE_4BYTES;
        final int ipSize = (ipDigits << 2) + DECIMAL_BINARY_SIZE[ipDigitsX];
        int offset = DECIMAL_BINARY_SIZE[ipDigitsX];
        BigDecimal ip = offset > 0 ? BigDecimal.valueOf(CodecUtils.toInt(value,0,offset)) : BigDecimal.ZERO;
        for(;offset < ipSize; offset += 4){
            final int i = CodecUtils.toInt(value,offset,4);
            ip = ip.movePointRight(DIGITS_PRE_4BYTES).add(BigDecimal.valueOf(i));
        }

        int shift = 0;
        BigDecimal fp = BigDecimal.ZERO;
        for(; shift + DIGITS_PRE_4BYTES <= scale; shift += DIGITS_PRE_4BYTES,offset += 4){
            final int i = CodecUtils.toInt(value,offset,4);
            fp = fp.add(BigDecimal.valueOf(i).movePointLeft(shift + DIGITS_PRE_4BYTES));
        }

        if(shift < scale){
            final int i = CodecUtils.toInt(value,offset,DECIMAL_BINARY_SIZE[scale - shift]);
            fp = fp.add(BigDecimal.valueOf(i).movePointLeft(scale));
        }

        return positive ? POSITIVE_ONE.multiply(ip.add(fp)) : NEGATIVE_ONE.multiply(ip.add(fp));
    }

    public static int getDecimalBinarySize(int precision, int scale){
        final int x = precision - scale;
        final int ipDigits = x / DIGITS_PRE_4BYTES;
        final int fpDigits = scale /DIGITS_PRE_4BYTES;
        final int ipDigitsX = x - ipDigits * DIGITS_PRE_4BYTES;
        final int fpDigitsX = scale - fpDigits * DIGITS_PRE_4BYTES;
        return (ipDigits << 2) + DECIMAL_BINARY_SIZE[ipDigitsX] + (fpDigits << 2) + DECIMAL_BINARY_SIZE[fpDigitsX];
    }
}
