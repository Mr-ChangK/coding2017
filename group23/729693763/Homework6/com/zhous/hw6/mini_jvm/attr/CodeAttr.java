package com.zhous.hw6.mini_jvm.attr;

import com.zhous.hw6.mini_jvm.clz.ClassFile;
import com.zhous.hw6.mini_jvm.constant.ConstantPool;
import com.zhous.hw6.mini_jvm.loader.ByteCodeIterator;

/**
 * Created by soulk on 2017/5/5.
 */
public class CodeAttr extends AttributeInfo {

    private int maxStack;
    private int maxLocals;
    private int codeLen;

    public String getCode() {
        return code;
    }

    private String code;

    public LineNumberTable getLineNumTable() {
        return lineNumTable;
    }

    public void setLineNumTable(LineNumberTable lineNumTable) {
        this.lineNumTable = lineNumTable;
    }

    public LocalVariableTable getLocalVarTable() {
        return localVarTable;
    }

    public void setLocalVarTable(LocalVariableTable localVarTable) {
        this.localVarTable = localVarTable;
    }

    public StackMapTable getStackMapTable() {
        return stackMapTable;
    }

    //嵌套属性，Method中的
    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;


    //TODO:这里忽视了异常；
    //u2 exception_table_length

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code ) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;

    }
    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){
        int attrNameIndex = iter.nextU2ToInt();
        int attrLen = iter.nextU4ToInt();
        int maxStack = iter.nextU2ToInt();
        int maxLocals = iter.nextU2ToInt();
        int codeLen = iter.nextU4ToInt();

        String code = iter.nextUxToHexString(codeLen);

        CodeAttr codeAttr = new CodeAttr(attrNameIndex,attrLen, maxStack,maxLocals,codeLen,code);

        int exceptionTableLen = iter.nextU2ToInt();
        //TODO 处理exception
        if(exceptionTableLen>0){
            String exTable = iter.nextUxToHexString(exceptionTableLen);
            System.out.println("Encountered exception table , just ignore it :" + exTable);
        }

        int subAttrCount = iter.nextU2ToInt();
        for(int x=1; x<=subAttrCount; x++){
            int subAttrIndex = iter.nextU2ToInt();
            String subAttrName = clzFile.getPool().getUTF8String(subAttrIndex);

            //已经向前移动了U2, 现在退回去。
            iter.back(2);
            //line item table
            if(AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)){

                LineNumberTable t = LineNumberTable.parse(iter);
                codeAttr.setLineNumTable(t);
            }
            else if(AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)){
                LocalVariableTable t = LocalVariableTable.parse(iter);
                codeAttr.setLocalVarTable(t);
            }
            else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)){
                StackMapTable t = StackMapTable.parse(iter);
                codeAttr.setStackMapTable(t);
            }
            else{
                throw new RuntimeException("Need code to process " + subAttrName);
            }
        }

        return codeAttr;
    }



    public String toString(ConstantPool pool){
        StringBuilder buffer = new StringBuilder();
        //buffer.append("Code:").append(code).append("\n");

        buffer.append("\n");
        buffer.append(this.lineNumTable.toString());
        buffer.append(this.localVarTable.toString(pool));
        return buffer.toString();
    }
    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;

    }



}
