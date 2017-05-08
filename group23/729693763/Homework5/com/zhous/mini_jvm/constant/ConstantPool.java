package com.zhous.mini_jvm.constant;

import com.zhous.mini_jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soulk on 2017/4/17.
 */
public class ConstantPool {

    private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();
    public ConstantPool() {
    }

    public ConstantInfo getConstantInfo(int index) {
       return this.constantInfos.get(index);
    }

    public void addConstantInfo(ConstantInfo info){
        constantInfos.add(info);
    }

    public int getSize(){
        return constantInfos.size() - 1;
    }

    public String getUTF8String(int index){
        return ((UTF8Info)this.constantInfos.get(index)).getValue();
    }
}
