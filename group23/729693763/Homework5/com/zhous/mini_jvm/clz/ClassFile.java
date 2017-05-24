package com.zhous.mini_jvm.clz;

import com.zhous.mini_jvm.constant.ClassInfo;
import com.zhous.mini_jvm.constant.ConstantPool;
import com.zhous.mini_jvm.field.Field;
import com.zhous.mini_jvm.clz.ClassIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soulk on 2017/4/17.
 */
public class ClassFile {

    private int majorVersion;
    private AccessFlag accessFlag;

    public ClassIndex getClzIndex() {
        return clzIndex;
    }

    public void setClzIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    private ClassIndex clzIndex;
    private ConstantPool pool;


    public int getMinorVersion() {
        return minorVersion;
    }

    private int minorVersion;

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getPool() {
        return pool;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }

    private List<Field> fields = new ArrayList<Field>();


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



}
