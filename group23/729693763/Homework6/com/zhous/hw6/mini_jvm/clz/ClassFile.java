package com.zhous.hw6.mini_jvm.clz;

import com.zhous.hw6.mini_jvm.constant.ClassInfo;
import com.zhous.hw6.mini_jvm.constant.ConstantPool;
import com.zhous.hw6.mini_jvm.field.Field;
import com.zhous.hw6.mini_jvm.method.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soulk on 2017/4/17.
 */
public class ClassFile {

    private int majorVersion;
    private AccessFlag accessFlag;
    private ClassIndex clzIndex;
    private ConstantPool pool;
    private int minorVersion;

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    private List<Field> fields = new ArrayList<Field>();
    private List<Method> methods = new ArrayList<Method>();


    public ClassIndex getClzIndex() {
        return clzIndex;
    }

    public void setClzIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getPool() {
        return pool;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }


    public void setAccessFlag(AccessFlag accessFlag) {
        this.accessFlag = accessFlag;
    }

    public void setClassIndex(ClassIndex classIndex) {
        this.clzIndex = classIndex;
    }

    public void print(){

        if(this.accessFlag.isPublicClass()){
            System.out.println("Access flag : public  ");
        }
        System.out.println("Class Name:"+ getClassName());

        System.out.println("Super Class Name:"+ getSuperClassName());
    }

    private String getClassName(){
        int thisClassIndex = this.clzIndex.getThisClassIndex();
        ClassInfo thisClass = (ClassInfo)this.getPool().getConstantInfo(thisClassIndex);
        return thisClass.getClassName();
    }
    private String getSuperClassName(){
        ClassInfo superClass = (ClassInfo)this.getPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
        return superClass.getClassName();
    }


    //添加字段
    public void addField(Field parse) {
        this.fields.add(parse);
    }
    public List<Field> getField() {
        return fields;
    }

    public void addMethod(Method m){
        this.methods.add(m);
    }
    public List<Method> getMethods() {
        return methods;
    }

    public Method getMethod(String methodName, String paramAndReturnType){

        for(Method m :methods){

            int nameIndex = m.getNameIndex();
            int descriptionIndex = m.getDescriptorIndex();

            String name = this.getPool().getUTF8String(nameIndex);
            String desc = this.getPool().getUTF8String(descriptionIndex);
            if(name.equals(methodName) && desc.equals(paramAndReturnType)){
                return m;
            }
        }
        return null;
    }
    public Method getMainMethod(){
        return getMethod("main","([Ljava/lang/String;)V");
    }

}
