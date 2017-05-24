package com.zhous.hw6.mini_jvm.constant;


public class UTF8Info extends ConstantInfo{
    private int type = ConstantInfo.UTF8_INFO;
    private int length ;
    private String value;
    public UTF8Info(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getTag() {
        return 0;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    @Override
    public String toString() {
        return "UTF8Info [type=" + type + ", length=" + length + ", value=" + value +")]";
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }



}
