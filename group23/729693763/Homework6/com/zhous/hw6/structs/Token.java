package com.zhous.hw6.structs;


import java.util.*;
import java.util.List;

/**
 * Created by soulk on 2017/5/6.
 */
public class Token {
    public static final List<String> OPERATORS = Arrays.asList("+","-","/","*");
    private static Map<String, Integer> priorities = new HashMap<String, Integer>();
    static {
        priorities.put("-",1);
        priorities.put("+",1);
        priorities.put("*",2);
        priorities.put("/",2);
    }

    final static int OPERATOR = 1;
    final static int NUMBER = 2;

    private int type;
    private String value;

    public Token(int type,String value){
        this.type = type;
        this.value = value;
    }

    public boolean isNumber(){
        return this.type == NUMBER;
    }
    public boolean isOperator(){
        return this.type == OPERATOR;
    }

    @Override
    public String toString() {
        return value;
    }

    public int getIntValue(){
        return Integer.valueOf(value).intValue();
    }

    public boolean HasHigherPriority(Token cmp){
        if(this.isNumber() || cmp.isNumber()){
            throw new RuntimeException("number does not priority! ");
        }
        return priorities.get(this.value) - priorities.get(cmp.value) > 0;


    }



}
