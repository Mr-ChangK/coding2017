package com.zhous.mini_jvm.loader;

import java.util.Arrays;

/**
 * Created by soulk on 2017/4/17.
 */
public class ByteCodeIterator {

    byte[] codes;
    int pos = 0;



    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;

    }

    public String nextU4ToHexString() {

        byte[] code = new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] };
        return byteToHexString(code);

    }

    public int nextU2ToInt() {

        byte[] code = new byte[] { codes[pos++],codes[pos++] };
        return ByteToInt(code);
    }

    public int nextU1toInt() {

        byte[] code = new byte[] { codes[pos++] };
        return ByteToInt(code);

    }

    public byte[] getBytes(int len) {
        if (pos + len >= codes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        byte[] data = Arrays.copyOfRange(codes, pos, pos + len);
        pos += len;
        return data;
    }

    private int ByteToInt(byte[] codes){
        String s1 = byteToHexString(codes);
        return Integer.valueOf(s1, 16).intValue();
    }

    public static String byteToHexString(byte[] codes ){
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<codes.length;i++){
            byte b = codes[i];
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if(strHex.length()< 2){
                strHex = "0" + strHex;
            }
            buffer.append(strHex);
        }
        return buffer.toString();
    }
    public void back(int n) {
        this.pos -= n;
    }


    public String nextUxToHexString(int len) {
        byte[] tmp = new byte[len];

        for (int i = 0; i < len; i++) {
            tmp[i] = codes[pos++];
        }
        return byteToHexString(tmp).toLowerCase();

    }
}
