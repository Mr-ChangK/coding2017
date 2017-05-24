package com.zhous.mini_jvm.constant;

/**
 * Created by soulk on 2017/4/17.
 */
public class MethodRefInfo extends ConstantInfo{
    private int tag = ConstantInfo.METHOD_INFO;
    private int classIndex;
    private int nameAndTypeIndex;

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }


    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }


    public MethodRefInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getTag() {
        return 0;
    }


    @Override
    public String toString() {
        return getClassName() +" : "+ this.getMethodName() + " : " + this.getParamAndReturnType() ;
    }

    public String getClassName(){
        ConstantPool pool = this.getConstantPool();
        ClassInfo clzInfo = (ClassInfo)pool.getConstantInfo(this.getClassIndex());
        return clzInfo.getClassName();
    }

    public String getMethodName(){
        ConstantPool pool = this.getConstantPool();
        NameAndTypeInfo  typeInfo = (NameAndTypeInfo)pool.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getName();
    }

    public String getParamAndReturnType(){
        ConstantPool pool = this.getConstantPool();
        NameAndTypeInfo  typeInfo = (NameAndTypeInfo)pool.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getTypeInfo();
    }

}
