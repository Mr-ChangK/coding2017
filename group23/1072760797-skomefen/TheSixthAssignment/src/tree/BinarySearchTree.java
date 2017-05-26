package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class BinarySearchTree<T extends Comparable> {

	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		if (root == null) {
			return null;
		}
		BinaryTreeNode<T> min = root;
		while (min.left != null)
			min = min.left;

		return min.getData();
	}

	public T findMax() {
		if (root == null) {
			return null;
		}
		BinaryTreeNode<T> max = root;
		while (max.right != null)
			max = max.right;
		return max.getData();
	}

	public int height() {
		if (root == null)
			return -1;

		BinaryTreeNode<T> temp = root;
		HashMap<BinaryTreeNode<T>, Integer> allLeaf = getAllLeafNode(root, 1);

		int h = 0;
		for (Entry<BinaryTreeNode<T>, Integer> e : allLeaf.entrySet()) {
			if (h < e.getValue()) {
				h = e.getValue();
			}
		}
		return h;
	}

	public HashMap<BinaryTreeNode<T>, Integer> getAllLeafNode(
			BinaryTreeNode<T> node, int level) {

		if (level < 1) {
			throw new RuntimeException("level must bigger 0");
		}
		if (node == null) {
			return null;
		}

		HashMap<BinaryTreeNode<T>, Integer> allLeaf = new HashMap<BinaryTreeNode<T>, Integer>();
		if (isLeafNode(node)) {
			allLeaf.put(node, level);
			return allLeaf;
		}
		if (node.getLeft() != null)
			allLeaf.putAll((getAllLeafNode(node.getLeft(), level + 1)));
		if (node.getRight() != null)
			allLeaf.putAll((getAllLeafNode(node.getRight(), level + 1)));
		return allLeaf;
	}

	public boolean isLeafNode(BinaryTreeNode<T> node) {

		if (node == null) {
			return false;
		}

		if (node.getLeft() == null && node.getRight() == null)
			return true;
		return false;
	}

	public int size() {
		if (root == null) {
			return -1;
		}
		ArrayList<BinaryTreeNode<T>> allnode = getAllNode(root);
		return allnode.size();
	}

	public ArrayList<BinaryTreeNode<T>> getAllNode(BinaryTreeNode<T> node) {

		if (node == null) {
			return null;
		}

		ArrayList<BinaryTreeNode<T>> array = new ArrayList<BinaryTreeNode<T>>();

		array.add(node);

		if (isLeafNode(node)) {
			return array;
		}

		if (node.getLeft() != null)
			array.addAll(getAllNode(node.getLeft()));

		if (node.getRight() != null)
			array.addAll(getAllNode(node.getRight()));

		return array;
	}

	public void remove(T e) {
		if (root == null) {
			throw new RuntimeException("root is empty");
		}
		if (e == null) {
			throw new RuntimeException("element is not exist");
		}

		BinaryTreeNode<T> node = getNode(e);

		if (node == null) {
			throw new RuntimeException("element is not exist");
		}

		if (isLeafNode(node)) {
			node = null;
		}

		BinaryTreeNode<T> parent = getParent(node);
		BinaryTreeNode<T> rightChild = node.getRight();
		BinaryTreeNode<T> leftChild = node.getLeft();

		if (rightChild != null) {

			BinarySearchTree<T> rightSearch = new BinarySearchTree<T>(rightChild);
			T min = rightSearch.findMin();
			BinaryTreeNode<T> minNode = rightSearch.getNode(min);

			if (leftChild != null)
				minNode.setLeft(leftChild);

			if (minNode != rightChild)
				minNode.setRight(rightChild);

			if (parent == null)
				return;

			if (parent.getRight() == node) {
				parent.setRight(minNode);
			}

			if (parent.getLeft() == node) {
				parent.setLeft(minNode);
			}

			node = null;
			return;
		}

		if (parent == null) {
			root = leftChild;
			node = null;
			return;
		}

		if (node == parent.getLeft())
			parent.setLeft(leftChild);

		if (node == parent.getRight())
			parent.setRight(leftChild);

		node = null;

	}

	public BinaryTreeNode<T> getParent(BinaryTreeNode<T> node) {
		if (root == null) {
			return null;
		}
		if (node == null) {
			return null;
		}

		ArrayList<BinaryTreeNode<T>> allNode = getAllNode(root);

		for (BinaryTreeNode<T> e : allNode) {
			if(isParent(e, node))
				return e;
		}

		return null;
	}

	public boolean isParent(BinaryTreeNode<T> parent, BinaryTreeNode<T> child) {

		if (parent.getLeft() == child || parent.getRight() == child) {
			return true;
		}

		return false;
	}

	public BinaryTreeNode<T> getNode(T e) {
		if (e == null)
			return null;
		if (root == null) {
			throw new RuntimeException("root is empty");
		}
		if (root.getData() == e) {
			return root;
		}
		return getNode(root, e);
	}

	public BinaryTreeNode<T> getNode(BinaryTreeNode<T> root, T e) {

		if (root == null) {
			return null;
		}

		if (e == null) {
			return null;
		}

		if (root.getData() == e)
			return root;

		BinaryTreeNode<T> result = null;
		if (root.getLeft() != null)
			result = getNode(root.getLeft(), e);

		if (result != null)
			return result;

		if (root.getRight() != null)
			result = getNode(root.getRight(), e);

		return result;
	}

	public List<T> levelVisit() {

		return null;
	}

	public boolean isValid() {
		return false;
	}

	public T getLowestCommonAncestor(T n1, T n2) {
		return null;

	}

	public List<T> getNodesBetween(T n1, T n2) {
		return null;
	}

}
