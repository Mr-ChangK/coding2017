package com.zhous.structs;
import com.zhous.structs.MyArrayList.*;
public class Stack {
	private ArrayList elementData = new ArrayList();

	public void push(Object o){
		this.elementData.add(o);

	}

	public ArrayList getElementData() {
		return elementData;
	}

	public Object pop(){
		int size = this.elementData.size();

		if ( size > 0 ) {
			return this.elementData.remove(size - 1 );
		} else{
			throw new IndexOutOfBoundsException("Stack is empty");
		}
	}

	public Object peek(){

		if ( size() > 0 ) {
			return this.elementData.get(size() - 1 );
		} else{
			throw new IndexOutOfBoundsException("Stack is empty");
		}
	}
	public boolean isEmpty(){
		return size() == 0;
	}

	public int size(){
		return this.elementData.size();
	}

	public Stack() {
		// TODO Auto-generated constructor stub
	}


	public void Copy(Stack temp){
		this.elementData = temp.getElementData();
	}

	@Override
	public String toString() {
		if(this.size() == 0){
			return  super.toString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int i = 0; i < this.size(); i++) {
			sb.append(this.elementData.get(i) );
			sb.append(", ");
		}
		sb.replace(sb.length()-2,sb.length(),"]");
		return sb.toString();
	}
}
