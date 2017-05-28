package stack;

import util.ArrayUtil;

public class Stack {
	private int index=0;
	private int[] stack;
	
	public void push(int data){
		if(stack==null){
			stack = new int[20];
			stack[0]=data;
			index++;
			return;
		}
		if(index>=stack.length){
			stack = ArrayUtil.changeLength(stack,20);
		}
		stack[index]=data;
		index++;

	}
	public int pop(){
		if(stack==null)
			throw new RuntimeException("stack is empty");
		if(index==0)
			throw new RuntimeException("stack is empty");
		index--;
		if(index<=stack.length-20){
			stack =  ArrayUtil.changeLength(stack,-20);
		}
		return stack[index];
	}

	public int peek(){
		if(stack==null)
			throw new RuntimeException("stack is empty");
		if(index==0)
			throw new RuntimeException("stack is empty");
		return stack[index-1];
	}
}
