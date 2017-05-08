package com.zhous.hw6.mini_jvm.constant;

/**
 * Created by soulk on 2017/4/17.
 */
public class ClassInfo extends ConstantInfo{
    private int tag = CLASS_INFO;
    private int nameIndex;

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }


    public ClassInfo(ConstantPool pool){
        super(pool);
    }

    public int getTag(){
        return tag;
    }


    public String getClassName() {
        int index = getNameIndex();
        UTF8Info utf8Info = (UTF8Info)constantPool.getConstantInfo(index);
        return utf8Info.getValue();
    }


}
