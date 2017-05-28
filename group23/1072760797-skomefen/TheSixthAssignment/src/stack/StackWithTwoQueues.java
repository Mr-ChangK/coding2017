package stack;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class StackWithTwoQueues {

	private Queue<Integer> q1 = new LinkedBlockingQueue<Integer>();
	private Queue<Integer> q2 = new LinkedBlockingQueue<Integer>();
	private int index = 1;

	public void push(int data) {
		if (index == 1) {
			q1.add(data);
		}
		if (index == 2) {
			q2.add(data);
		}
	}

	public int pop() {
		if (index == 1) {
			if (q1.size() == 0)
				throw new RuntimeException("stack is empty");
			index = 2;
			return (Integer) ReturnLastAddOthersToDesQueue(q1,q2);
		}
		if (index == 2) {
			if (q2.size() == 0)
				throw new RuntimeException("stack is empty");
			index = 1;
			return (Integer) ReturnLastAddOthersToDesQueue(q2,q1);
		}
		return -1;
	}

	private Object ReturnLastAddOthersToDesQueue(Queue src,Queue des) {
		int count = src.size() - 1;
		for (int i = 0; i < count; i++)
			des.add(src.remove());
		return src.remove();
	}

}
