package com.zhous.mini_jvm.clz;

/**
 * Created by soulk on 2017/4/17.
 * ACC_PUBLIC  0x0001  public，表示字段可以从任何包访问。
 * ACC_PRIVATE  0x0002  private，表示字段仅能该类自身调用。
 * ACC_PROTECTED  0x0004  protected，表示字段可以被子类调用。
 * ACC_STATIC  0x0008  static，表示静态字段。
 * ACC_FINAL  0x0010  final，表示字段定义后值无法修改（JLS  §17.5）
 */
public class AccessFlag {
    private int flagValue;

    public AccessFlag(int value) {
        this.flagValue = value;
    }

    public int getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(int flag) {
        this.flagValue = flag;
    }

    public boolean isPublicClass(){
        return (this.flagValue & 0x0001) != 0;
    }
    public boolean isFinalClass(){
        return (this.flagValue & 0x0010) != 0;
    }
    public boolean isPrivateClass(){
        return (this.flagValue & 0x0002) != 0;
    }

    public  boolean isProtectedClass(){
        return (this.flagValue & 0x0004) != 0;
    }
    public boolean isStaticClass(){
        return (this.flagValue & 0x0008) != 0;
    }



}
