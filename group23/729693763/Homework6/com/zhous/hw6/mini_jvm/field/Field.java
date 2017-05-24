package com.zhous.hw6.mini_jvm.field;

import com.zhous.hw6.mini_jvm.constant.ConstantPool;
import com.zhous.hw6.mini_jvm.constant.UTF8Info;
import com.zhous.hw6.mini_jvm.loader.ByteCodeIterator;

/**
 * Created by soulk on 2017/4/18.
 */
public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

//    这两个暂时跳过先
//    u2 attributes_count;
//    attribute_info attributes[attributes_count];


    private ConstantPool pool;

    public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {

        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.pool = pool;
    }




    public static Field parse(ConstantPool pool,ByteCodeIterator iter){

        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descIndex = iter.nextU2ToInt();
        int attributeCount = iter.nextU2ToInt();

        Field field = new Field(accessFlag,nameIndex,descIndex,pool);
        if(attributeCount > 0){
            throw new RuntimeException("Field Attribute has not been implemented");
        }

        return field;
    }
    public String toString() {
        String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
        return name +":"+ desc;
    }
}

