package com.zhous.mini_jvm.field;

import com.zhous.mini_jvm.constant.ConstantPool;
import com.zhous.mini_jvm.loader.ByteCodeIterator;

/**
 * Created by soulk on 2017/4/18.
 */
public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;



    private ConstantPool pool;

    public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {

        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.pool = pool;
    }




    public static Field parse(ConstantPool pool,ByteCodeIterator iter){

        return null;
    }

}

