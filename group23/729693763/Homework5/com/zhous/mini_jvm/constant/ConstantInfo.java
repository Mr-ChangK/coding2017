package com.zhous.mini_jvm.constant;

/**
 * Created by soulk on 2017/4/17.
 */
public abstract class ConstantInfo {
    //字段属性
    public static final int UTF8_INFO = 1;
    public static final int FLOAT_INFO = 4;
    public static final int CLASS_INFO = 7;
    public static final int STRING_INFO = 8;
    public static final int FIELD_INFO = 9;
    public static final int METHOD_INFO = 10;
    public static final int NAME_AND_TYPE_INFO = 12;

    protected ConstantPool constantPool;
    public ConstantInfo(){

    }
    public ConstantInfo(ConstantPool pool) {
        this.constantPool = pool;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }
    public ConstantInfo getConstantInfo(int index){
        return this.constantPool.getConstantInfo(index);
    }

    //Tag 就是估计的 1 byte
    public abstract int getTag();

}
