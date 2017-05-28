package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		if(root != null){
			result.add(root.getData());
			preOrderVisit(root, result);
		}
		return result;
	}

	private static <T> void preOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		BinaryTreeNode<T> left = node.getLeft();
		BinaryTreeNode<T> right = node.getRight();
		if(left != null){
			result.add(left.getData());
			preOrderVisit(left, result);
		}
		if(right != null){
			result.add(right.getData());
			preOrderVisit(right, result);
		}
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		if(root != null){
			inOrderVisit(root, result);
		}
		return result;
	}
	
	private static <T> void inOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		BinaryTreeNode<T> left = node.getLeft();
		BinaryTreeNode<T> right = node.getRight();
		if(left != null){
			inOrderVisit(left, result);
		}
		result.add(node.getData());
		if(right != null){
			inOrderVisit(right, result);
		}
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		if(root != null){
			postOrderVisit(root, result);
		}
		return result;
	}
	
	private static <T> void postOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		BinaryTreeNode<T> left = node.getLeft();
		BinaryTreeNode<T> right = node.getRight();
		if(left != null){
			postOrderVisit(left, result);
		}
		if(right != null){
			postOrderVisit(right, result);
		}
		result.add(node.getData());
	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> rightNodes = new Stack<BinaryTreeNode<T>>();
		BinaryTreeNode<T> cur = root;
		while(cur != null || rightNodes.size() > 0){
			if(cur == null){
				cur = rightNodes.pop();
			}
			result.add(cur.getData());
			BinaryTreeNode<T> right = cur.getRight();
			if(right != null){
				rightNodes.push(right);
			}
			cur = cur.getLeft();
		}
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		if(root != null){
			Stack<BinaryTreeNode<T>> parentNodes = new Stack<BinaryTreeNode<T>>();
			BinaryTreeNode<T> cur = root;
			boolean leftTree = true;
			while(cur != null || parentNodes.size() > 0){
				if(leftTree){
					BinaryTreeNode<T> left = cur.getLeft();
					if(left != null){
						parentNodes.push(cur);
						cur = left;
					}else{
						result.add(cur.getData());
						if(parentNodes.size() > 0){
							cur = parentNodes.pop();
							result.add(cur.getData());
							leftTree = false;
						}else{
							cur = null;
						}
					}
				}else{
					BinaryTreeNode<T> right = cur.getRight();
					if(right != null){
						cur = right;
						leftTree = true;
					}else{
						result.add(parentNodes.pop().getData());
					}
				}
			}
		}
		return result;
	}
	
}