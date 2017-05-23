package com.zhous.mini_jvm.constant;

/**
 * Created by soulk on 2017/4/17.
 */
public class FieldRefInfo extends ConstantInfo{

    private int tag = ConstantInfo.FIELD_INFO;

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

    public void setNameAndTypeIndex(int nameAndTypeIindex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }


    public FieldRefInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getTag() {
        return tag;
    }


    public String toString(){

        NameAndTypeInfo  typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());

        return getClassName() +" : "+  typeInfo.getName() + ":" + typeInfo.getTypeInfo() +"]";
    }

    public String getClassName(){

        ClassInfo classInfo = (ClassInfo) this.getConstantInfo(this.getClassIndex());

        UTF8Info utf8Info = (UTF8Info)this.getConstantInfo(classInfo.getNameIndex());

        return utf8Info.getValue();

    }

    public String getFieldName(){
        NameAndTypeInfo  typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getName();
    }

    public String getFieldType(){
        NameAndTypeInfo  typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getTypeInfo();
    }

}
