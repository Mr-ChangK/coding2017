package com.zhous.mini_jvm.loader;

import com.zhous.mini_jvm.clz.AccessFlag;
import com.zhous.mini_jvm.clz.ClassFile;
import com.zhous.mini_jvm.clz.ClassIndex;
import com.zhous.mini_jvm.constant.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by soulk on 2017/4/17.
 * 解析从ClassFileLoader读取的bytes
 */
public class ClassFileParser {

    public ClassFileParser(){}


    //Main 方法
    public ClassFile parse(byte[] codes){
        ClassFile clzFile = new ClassFile();
        ByteCodeIterator iter = new ByteCodeIterator(codes);

        ConstantPool pool = null;
        AccessFlag flag = null;
        ClassIndex clzIndex = null;

        String magicNumber = iter.nextU4ToHexString();

        //魔数
        if (!"cafebabe".equals(magicNumber)) {
            return null;
        }

        clzFile.setMinorVersion(iter.nextU2ToInt());
        clzFile.setMajorVersion(iter.nextU2ToInt());

        //加载常量池：
        pool = parseConstantPool(iter);
        clzFile.setPool(pool);

        flag = parseAccessFlag(iter);
        clzFile.setAccessFlag(flag);

        clzIndex = parseClassIndex(iter);
        clzFile.setClassIndex(clzIndex);


        return clzFile;
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iter) {
        int thisClassIndex = iter.nextU2ToInt();
        int superClassIndex = iter.nextU2ToInt();

        ClassIndex clzIndex = new ClassIndex();

        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);

        return clzIndex;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        AccessFlag aflag = new AccessFlag(iter.nextU2ToInt());
        return aflag;
    }

    //将byte迭代器传入，能得到一个完整的常量池；
    private ConstantPool parseConstantPool(ByteCodeIterator iter) {

        //读取完魔数，次版本号，主版本号后， u2就是常量数
        int constPoolCount = iter.nextU2ToInt();

        System.out.println("Constant Pool Count :" + constPoolCount);

        ConstantPool pool = new ConstantPool();

        pool.addConstantInfo(new NullConstantInfo());

        for (int i = 1; i <= constPoolCount - 1; i++) {

            int tag = iter.nextU1toInt();

            if (tag == 7) {
                // Class Info
                int nameIndex = iter.nextU2ToInt();
                ClassInfo clzInfo = new ClassInfo(pool);
                clzInfo.setNameIndex(nameIndex);

                pool.addConstantInfo(clzInfo);

            } else if (tag == 1) {
                // UTF-8 String
                int len = iter.nextU2ToInt();
                byte[] data = iter.getBytes(len);
                String value = null;
                try {
                    value = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                UTF8Info utf8Str = new UTF8Info(pool);
                utf8Str.setLength(len);
                utf8Str.setValue(value);
                pool.addConstantInfo(utf8Str);

            }else if (tag == 8) {
                //String info
                StringInfo info = new StringInfo(pool);
                info.setStringIndex(iter.nextU2ToInt());
                pool.addConstantInfo(info);
            } else if (tag == 9) {
                //FieldRef info
                FieldRefInfo field = new FieldRefInfo(pool);
                field.setClassIndex(iter.nextU2ToInt());
                field.setNameAndTypeIndex(iter.nextU2ToInt());
                pool.addConstantInfo(field);
            } else if (tag == 10) {
                // MethodRef info
                MethodRefInfo method = new MethodRefInfo(pool);
                method.setClassIndex(iter.nextU2ToInt());
                method.setNameAndTypeIndex(iter.nextU2ToInt());
                pool.addConstantInfo(method);
            } else if (tag == 12) {
                // Name and Type Info
                NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
                nameType.setNameIndex(iter.nextU2ToInt());
                nameType.setDescriptorIndex(iter.nextU2ToInt());
                pool.addConstantInfo(nameType);
            } else {
                throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
            }
        }

        System.out.println("Finished reading Constant pool ");
        return pool;
    }

}
