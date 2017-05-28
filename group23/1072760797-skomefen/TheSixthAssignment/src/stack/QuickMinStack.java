package stack;

import util.ArrayUtil;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * 
 * @author liuxin
 * 
 */
public class QuickMinStack {

	private Stack stack;
	private Stack findMinStack;

	public void push(int data) {
		if (stack == null) {
			stack = new Stack();
			findMinStack = new Stack();

			stack.push(data);
			findMinStack.push(data);
			return;
		}
		stack.push(data);
		if (findMinStack.peek() >= data) {
			findMinStack.push(data);
		}
	}

	public int pop() {
		if (stack == null)
			throw new RuntimeException();
		if (findMinStack.peek() >= stack.peek()) {

			findMinStack.pop();
			return stack.pop();

		}

		return stack.pop();
	}

	public int findMin() {
		if (stack == null)
			throw new RuntimeException();

		return findMinStack.peek();
	}
}
