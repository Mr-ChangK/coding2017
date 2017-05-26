package tree;

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
		preOrderList(result, root);
		return result;
	}

	private static <T> void preOrderList(List<T> result, BinaryTreeNode<T> root) {

		result.add(root.getData());

		if (root.getLeft() != null) {
			preOrderList(result, root.getLeft());
		}

		if (root.getRight() != null) {
			preOrderList(result, root.getRight());
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
		inOrderList(result, root);
		return result;
	}

	private static <T> void inOrderList(List<T> result, BinaryTreeNode<T> root) {

		if (root.getLeft() != null)
			inOrderList(result, root.getLeft());

		result.add(root.getData());

		if (root.getRight() != null)
			inOrderList(result, root.getRight());
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		postOrderList(result, root);
		return result;
	}

	private static <T> void postOrderList(List<T> result, BinaryTreeNode<T> root) {

		if (root.getLeft() != null)
			postOrderList(result, root.getLeft());

		if (root.getRight() != null)
			postOrderList(result, root.getRight());

		result.add(root.getData());

	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> load = new Stack<BinaryTreeNode<T>>();
		do {

			result.add(root.getData());

			if (root.getRight() != null) {
				load.push(root.getRight());
			}
			if (root.getLeft() != null) {
				load.push(root.getLeft());
			}

			root = load.pop();
		} while (!load.isEmpty());

		result.add(root.getData());

		return result;
	}

	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {

		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> load = new Stack<BinaryTreeNode<T>>();
		boolean endLeft = false;
		while (true) {
			if (root.left != null && !endLeft) {

				load.push(root);
				root = root.left;

				continue;
			}

			if (root.getRight() != null) {

				result.add(root.getData());
				root = root.getRight();
				endLeft = false;

				continue;
			}

			result.add(root.getData());
			if (load.isEmpty()) {
				break;
			}
			root = load.pop();
			endLeft = true;
		}
		return result;
	}

}
