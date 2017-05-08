package com.zhous.mini_jvm.constant;

/**
 * Created by soulk on 2017/4/17.
 */
public class NameAndTypeInfo extends ConstantInfo{
    private int tag = ConstantInfo.NAME_AND_TYPE_INFO;


    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    private int nameIndex;  //index1

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    private int descriptorIndex; //index2


    public NameAndTypeInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getTag() {
        return 0;
    }

    public String getName(){
        ConstantPool pool = this.getConstantPool();
        UTF8Info utf8Info1 = (UTF8Info)pool.getConstantInfo(nameIndex);
        return utf8Info1.getValue();
    }

    public String getTypeInfo(){
        ConstantPool pool = this.getConstantPool();
        UTF8Info utf8Info2 = (UTF8Info)pool.getConstantInfo(descriptorIndex);
        return utf8Info2.getValue();
    }

    public String toString(){
        return "(" + getName() + "," + getTypeInfo()+")";
    }


}
