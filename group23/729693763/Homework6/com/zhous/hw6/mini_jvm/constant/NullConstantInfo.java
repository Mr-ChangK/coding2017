package com.zhous.hw6.mini_jvm.constant;

/**
 * Created by soulk on 2017/4/17.
 */
public class NullConstantInfo extends ConstantInfo{

    public NullConstantInfo(){

    }
    @Override
    public int getTag() {
        return -1;
    }
}
