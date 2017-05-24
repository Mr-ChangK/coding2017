package com.zhous.mini_jvm.clz;

/**
 * Created by soulk on 2017/4/17.
 */
public class ClassIndex {

    public int getThisClassIndex() {
        return thisClassIndex;
    }

    private int thisClassIndex;

    public int getSuperClassIndex() {
        return superClassIndex;
    }

    private int superClassIndex;

    public void setThisClassIndex(int thisClassIndex) {
        this.thisClassIndex = thisClassIndex;
    }

    public void setSuperClassIndex(int superClassIndex) {
        this.superClassIndex = superClassIndex;
    }
}
