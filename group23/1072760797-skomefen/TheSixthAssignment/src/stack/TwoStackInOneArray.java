package stack;

import util.ArrayUtil;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	private Object[] data = new Object[10];
	private int index1 = 0;
	private int index2 = data.length-1;
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(index1>index2){
			data = ArrayUtil.dilatationInCentre(data,index2,10);
		}
		data[index1] = o;
		index1++;
	}

	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(index1==0){
			throw new RuntimeException("Stack is empty");
		}
		index1--;
		return data[index1];
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if(index1==0){
			throw new RuntimeException("Stack is empty");
		}
		return data[index1-1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(index1>index2){
			data = ArrayUtil.dilatationInCentre(data,index2,10);
			index2+=10;
		}
		data[index2] = o;
		index2--;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(index2==data.length){
			throw new RuntimeException("Stack is empty");
		}
		index2++;
		return data[index2];
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if(index2==data.length){
			throw new RuntimeException("Stack is empty");
		}
		return data[index2+1];
	}
}
