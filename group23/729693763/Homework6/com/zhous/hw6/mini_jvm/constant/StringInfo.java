package com.zhous.hw6.mini_jvm.constant;

/**
 * Created by soulk on 2017/4/17.
 */
public class StringInfo extends ConstantInfo{
    private int tag = STRING_INFO;


    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    private int stringIndex;

    public StringInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getTag() {
        return 0;
    }

    public String toString(){
        return this.getConstantPool().getUTF8String(stringIndex);
    }

}
