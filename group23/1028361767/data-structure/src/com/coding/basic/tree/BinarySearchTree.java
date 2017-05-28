package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable> {
	
	List<T> list;
	int height;
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
		addToList();
	}
	private void addToList() {
		list = new ArrayList<T>();
		if(root != null){
			list.add(root.data);
			addChildrenToList(root);
		}
	}
	private void addChildrenToList(BinaryTreeNode<T> node) {
		BinaryTreeNode<T> left = node.left;
		BinaryTreeNode<T> right = node.right;
		if(left != null || right != null){
			height++;
			if(left != null){
				list.add(list.indexOf(node.data), left.data);
				addChildrenToList(left);
			}
			if(right != null){
				list.add(list.indexOf(node.data)+1, right.data);
				addChildrenToList(right);
			}
		}
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		checkSize();
		return list.get(0);
	}
	public T findMax(){
		checkSize();
		return list.get(list.size() - 1);
	}
	public int height() {
	    return height;
	}
	public int size() {
		return list.size();
	}
	public void remove(T e){
		BinaryTreeNode<T> parentNode = null;
		BinaryTreeNode<T> curNode = root;
		while(curNode != null){
			int result = e.compareTo(curNode.data);
			if(result == 0){
				break;
			}else{
				parentNode = curNode;
				if(result < 0){
					curNode = curNode.left;
				}else{
					curNode = curNode.right;
				}
			}
		}
		if(curNode == null){// 找不到节点
			throw new NoSuchElementException();
		}
		BinaryTreeNode<T> left = curNode.left;
		BinaryTreeNode<T> right = curNode.right;
		BinaryTreeNode<T> targetNode = null;
		if(left == null && right == null){// 叶子
			targetNode = curNode;
		}else if(right == null){// 右子节点不为空，直接右左节点
			targetNode = curNode.left;
		}else{// 右节点不为空，取右节点树中最左节点
			BinaryTreeNode<T> rightSubTreeParentNode = null;
			while(right.left != null){
				rightSubTreeParentNode = right;
				right = right.left;
			}
			targetNode = right;//FIXME 此最左节点有右支树
			targetNode.left = curNode.left;
			if(rightSubTreeParentNode != null){
				rightSubTreeParentNode.left = targetNode.right;
				targetNode.right = curNode.right;
			}else{
				targetNode.right = right.right;
			}
		}
		if(parentNode == null){
			root = targetNode;
		}else{
			if(parentNode.left.data.compareTo(e) == 0){// 在左分支上
				parentNode.left = targetNode;
			}else{
				parentNode.right = targetNode;
			}
		}
		addToList();
	}
	
	public List<T> levelVisit(){
		List<T> levelDatas = new ArrayList<>();
		if(root != null){
			levelDatas.add(root.data);
			Queue<BinaryTreeNode<T>> levelNodes = new LinkedList<>();
			BinaryTreeNode<T> curNode = root;
			while(curNode != null){
				BinaryTreeNode<T> left = curNode.left;
				BinaryTreeNode<T> right = curNode.right;
				addIfNotNull(levelDatas, levelNodes, left);
				addIfNotNull(levelDatas, levelNodes, right);
				curNode = levelNodes.poll();
			}
		}
		return levelDatas;
	}
	private void addIfNotNull(List<T> levelDatas, Queue<BinaryTreeNode<T>> levelNodes, BinaryTreeNode<T> node) {
		if(node != null){
			levelDatas.add(node.data);
			levelNodes.add(node);
		}
	}
	public boolean isValid(){
		boolean result = true;
		BinaryTreeNode<T> curNode = root;
		Queue<BinaryTreeNode<T>> levelNodes = new LinkedList<>();
		while(curNode != null && result){
			result = compareChildren(levelNodes, curNode);
			curNode = levelNodes.poll();
		}
		return result;
	}
	private boolean compareChildren(Queue<BinaryTreeNode<T>> levelNodes, BinaryTreeNode<T> curNode) {
		BinaryTreeNode<T> left = curNode.left;
		BinaryTreeNode<T> right = curNode.right;
		if(left != null){
			if(left.data.compareTo(curNode.data) >= 0){
				return false;
			}
			levelNodes.add(left);
		}
		if(right != null){
			if(right.data.compareTo(curNode.data) <= 0){
				return false;
			}
			levelNodes.add(right);
		}
		return true;
	}
	public T getLowestCommonAncestor(T n1, T n2){
		if(root == null){
			throw new NoSuchElementException();
		}
		List<BinaryTreeNode<T>> nodes1 = findNode(n1, root); 
		List<BinaryTreeNode<T>> nodes2 = findNode(n2, root);; 
		T target = null;
		end:
		for(int i=nodes1.size()-1;i>=0;i--){
			for(int j=nodes2.size()-1;j>=0;j--){
				if(nodes1.get(i).data.compareTo(nodes2.get(j).data) == 0){
					target = nodes1.get(i).data;
					break end;
				}
			}
		}
		return target;
        
	}
	private List<BinaryTreeNode<T>> findNode(T n, BinaryTreeNode<T> curNode) {
		List<BinaryTreeNode<T>> nodes = new ArrayList<>();
		boolean found = false;
		while(curNode != null && !found){
			nodes.add(curNode);
			int result = n.compareTo(curNode.data);
			if(result == 0){
				found = true;
			}else if(result < 0){
				curNode = curNode.left;
			}else{
				curNode = curNode.right;
			}
		}
		if(found){
			return nodes;
		}else{
			return null;
		}
	}
	public List<T> getNodesBetween(T n1, T n2){
		
		List<BinaryTreeNode<T>> nodes = new ArrayList<>();
		List<BinaryTreeNode<T>> nodes1 = findNode(n1, root);
		List<BinaryTreeNode<T>> nodes2 = findNode(n2, root);
		if(n1.compareTo(root.data) == n2.compareTo(root.data)){//在根节点的同一支树中
			int result = nodes1.size() - nodes2.size();
			if(result == 0){
				nodes.add(nodes1.get(nodes1.size() - 1));
			}else{
				if(result < 0){
					getNodesBetween(nodes, nodes1.get(nodes1.size()-1).data, nodes2);
				}else{
					getNodesBetween(nodes, nodes2.get(nodes2.size()-1).data, nodes1);
				}
			}
		}else{
			nodes.addAll(nodes1);
			nodes2.remove(0);//移除重复的根节点
			nodes.addAll(nodes2);
		}
		List<T> datas = new ArrayList<>();
		for(BinaryTreeNode<T> node : nodes){
			datas.add(node.data);
		}
		return datas;
	}
	
	private void getNodesBetween(List<BinaryTreeNode<T>> nodes, T value,
			List<BinaryTreeNode<T>> more) {
		for(int i=more.size()-1;;i--){
			BinaryTreeNode<T> node = more.get(i);
			nodes.add(node);
			if(node.data == value){
				return ;
			}
		}
		
	}
	private void checkSize(){
		if(list.size() == 0){
			throw new IndexOutOfBoundsException();
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(1);
		root.left.right = new BinaryTreeNode<Integer>(5);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		root.right.right = new BinaryTreeNode<Integer>(10);
		root.left.right.left.right = new BinaryTreeNode<Integer>(4);
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);
		System.out.println(tree.levelVisit());
		System.out.println(tree.isValid());
		System.out.println(tree.getLowestCommonAncestor(3, 4));
		System.out.println(tree.getNodesBetween(5, 10));
		System.out.println(tree.getNodesBetween(4, 2));
	}
}