package com.zhous.hw6.mini_jvm.method;

import com.zhous.hw6.mini_jvm.attr.AttributeInfo;
import com.zhous.hw6.mini_jvm.attr.CodeAttr;
import com.zhous.hw6.mini_jvm.clz.ClassFile;
import com.zhous.hw6.mini_jvm.constant.ConstantPool;
import com.zhous.hw6.mini_jvm.constant.UTF8Info;
import com.zhous.hw6.mini_jvm.loader.ByteCodeIterator;

/**
 * Created by soulk on 2017/5/5.
 */
public class Method {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAttr;

    private ClassFile clzFile;

    public ClassFile getClzFile() {
        return clzFile;
    }

    public int getNameIndex() {
        return nameIndex;
    }
    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr code) {
        this.codeAttr = code;
    }




    public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }


    public static Method parse(ClassFile clzFile, ByteCodeIterator iter){

        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descIndex = iter.nextU2ToInt();
        int attribCount = iter.nextU2ToInt();
        
        Method m = new Method(clzFile, accessFlag, nameIndex, descIndex);

        for (int i = 1; i <= attribCount; i++) {
            int attrNameIndex = iter.nextU2ToInt();
            String attrName = clzFile.getPool().getUTF8String(attrNameIndex);
            iter.back(2);

            if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
                m.setCodeAttr(codeAttr);
            } else{
                //TODO:这里只完成了CODE的属性 in Method
                throw new RuntimeException("only CODE attribute is implemented , please implement the "+ attrName);
            }
        }
        return m;
    }

















    public String toString() {

        ConstantPool pool = this.clzFile.getPool();
        StringBuilder buffer = new StringBuilder();

        String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();

        buffer.append(name).append(":").append(desc).append("\n");

        buffer.append(this.codeAttr.toString(pool));

        return buffer.toString();
    }




}
