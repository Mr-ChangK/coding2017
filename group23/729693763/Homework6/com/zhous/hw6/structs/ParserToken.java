package com.zhous.hw6.structs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soulk on 2017/5/6.
 */
public class ParserToken {

    public static List<Token> parse(String expr){
        List<Token> tokens = new ArrayList<Token>();

        for (int i=0;i<expr.length();) {

            char t = expr.charAt(i);

            if(isOperator(t)){
                Token token = new Token(Token.OPERATOR,String.valueOf(t));
                tokens.add(token);
                i++;
            } else if(Character.isDigit(t)){
                int nextOperatorIndex = findNextOperator(expr,i);
                String value = expr.substring(i, nextOperatorIndex);
                Token token = new Token(Token.NUMBER, value);
                tokens.add(token);
                i = nextOperatorIndex;
            }   else {
                System.out.println("char :[" + t + "] is not number or operator,ignore");
                i++;
            }
        } 
        return tokens;
    }


    public static int findNextOperator(String expr,int i){

        for (; i < expr.length(); i++) {
            char temp = expr.charAt(i);
            if(Character.isDigit(temp) ){
                continue;
            } else if(isOperator(temp)){
                break;
            } else{
                throw  new RuntimeException("Error Character, not Number or Operator");
            }
        }
        return  i;
    }


    private  static boolean isOperator(char c) {
        String sc = String.valueOf(c);
        return Token.OPERATORS.contains(sc);
    }



}
