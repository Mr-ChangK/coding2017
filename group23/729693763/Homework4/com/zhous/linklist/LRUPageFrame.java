package com.zhous.linklist;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {

	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node() {
			prev = null;
			next = null;
		}
	}

	private int capacity;


	private Node first;// 链表头
	private Node last;// 链表尾


	public LRUPageFrame(int capacity) {

		this.capacity = capacity;
		InitNodes();
	}

	private void InitNodes() {
		if(this.capacity == 1){
			Node temp = new Node();temp.pageNum = -1;
			this.first = temp;
		} else{
			Node temp = new Node();temp.pageNum = -1;
			Node temp2 = new Node();temp2.pageNum = -1;

			this.first = temp;
			this.last = temp2;
			this.first.next = this.last;
			this.last.prev = this.first;

			if(this.capacity > 2){
				for (int i = 2; i < this.capacity; i++) {
					Node newNode = new Node();newNode.pageNum = -1;

					this.last.next = newNode;
					newNode.prev = this.last;
					this.last = newNode;

				}
			}
		}


	}

	/**
	 * 获取缓存中对象
	 *
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {

		boolean isFull = checkIsFull();

		if( !isFull) {
			Node pageNumInsertNode = this.last;
			for (int i = 0; i < this.capacity; i++) {
				if (pageNumInsertNode.pageNum < 0) {
					pageNumInsertNode.pageNum = pageNum;
					break;
				}
				pageNumInsertNode = pageNumInsertNode.prev;
			}
		} else {
			//满了情况下，是否存在有这样的pageNum
			Node findNode = isExistPageNum(pageNum);

			//没找到
			if(findNode == null){
				Node newNode = new Node();

				newNode.pageNum = pageNum;
				newNode.next = this.first;
				this.first.prev = newNode;

				this.first = newNode;

				//删除最后一个last
				Node deleteLast = this.last;
				this.last = deleteLast.prev;
				this.last.next = null;
			} else{
				//存在有这样的Node;
				if(findNode == this.last){
					//处理尾部并删除最后的Node
					findNode.prev.next = null;
					this.last = findNode.prev;

					findNode.next = this.first;
					this.first.prev = findNode;
					findNode.prev = null;
					this.first = findNode;
				} else if(findNode == this.first){
					//不需要做任何事
				} else {
					findNode.next.prev = findNode.prev;
					findNode.prev.next = findNode.next;

					//添加进头部
					findNode.next = this.first;
					this.first.prev = findNode;
					findNode.prev = null;
					this.first = findNode;


				}


			}






		}



	}

	private Node isExistPageNum(int pageNum) {
		Node temp = null;
		for (int i = 0; i < this.capacity; i++) {
			temp = this.first;
			if(temp.pageNum == pageNum){
				break;
			}
			temp = temp.next;
		}
		if(temp.pageNum != pageNum) {
			temp = null;
		}
		return temp;
	}

	//返回true，就是容器塞满了。 else false;
	private boolean checkIsFull() {
		return !(this.first.pageNum < 0);
	}


	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.pageNum);

			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}

}
