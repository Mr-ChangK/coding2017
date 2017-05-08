package com.zhous.structs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackUtil {



	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
//		Stack result = new Stack();
//		while( !s.isEmpty() ){
//			result.push(s.pop());
//		}
//		s.Copy(result);

		//另一种方法
		Stack temp = new Stack();
		int size = s.size();
		for (int i = 0; i < size; i++) {
			Object val = s.pop();
			while(s.size() > i){
				temp.push(s.pop());
			}
			s.push(val);
			while (temp.size() > 0){
				s.push(temp.pop());
			}
		}

	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 *
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack result = new Stack();
		while( !s.isEmpty() ){
			Object data = s.pop();
			if( data.equals(o)){
				break;
			}
			result.push(data);
		}
		while( !result.isEmpty() ){
			s.push(result.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if (s.size() < len) {
			throw new IndexOutOfBoundsException();
		}

		Object[] data = new Object[len];
		Stack result = new Stack();

		for (int i = 0; i < len; i++) {
			Object temp = s.pop();
			data[i] = temp;
			result.push(temp);
		}
		while( !result.isEmpty() ){
			s.push(result.pop());
		}
		return data;
	}
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s){

		Stack symbol = new Stack();

		for (int i = 0; i < s.length(); i++) {
			Character ch = s.charAt(i);

			switch (ch){
				case '(':
				case '[':
				case '{':
					symbol.push(ch);
					break;
				case ')':
					if(!symbol.pop().equals('(')){
						return false;
					}
					break;
				case ']':
					if(!symbol.pop().equals('[')){
						return false;
					}
					break;
				case '}':
					if(!symbol.pop().equals('{')){
						return false;
					}
					break;
				default:
					continue;
			}
		}
		return true;
	}

	public static void main(String[] args){
		System.out.println(isValidPairs("(asdasd)"));
	}

}
