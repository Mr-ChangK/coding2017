package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

	public HashMap<BinaryTreeNode<T>, Integer> getAllNode(
			BinaryTreeNode<T> node, int level) {

		if (level < 1) {
			throw new RuntimeException("level must bigger 0");
		}
		if (node == null) {
			return null;
		}

		HashMap<BinaryTreeNode<T>, Integer> allLeaf = new HashMap<BinaryTreeNode<T>, Integer>();

		allLeaf.put(node, level);

		if (isLeafNode(node)) {
			return allLeaf;
		}

		if (node.getLeft() != null)
			allLeaf.putAll((getAllNode(node.getLeft(), level + 1)));
		if (node.getRight() != null)
			allLeaf.putAll((getAllNode(node.getRight(), level + 1)));
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

			BinarySearchTree<T> rightSearch = new BinarySearchTree<T>(
					rightChild);
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
			if (isParent(e, node))
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

		if (root == null) {
			return null;
		}

		HashMap<BinaryTreeNode<T>, Integer> allNodeMap = getAllNode(root, 1);
		List<T> allNode = new ArrayList<T>();

		for (int i = 1; i <= height(); i++) {
			for (Entry<BinaryTreeNode<T>, Integer> e : allNodeMap.entrySet()) {
				if (e.getValue() == i)
					allNode.add(e.getKey().getData());
			}
		}

		return allNode;
	}

	public boolean isValid() {
		ArrayList<BinaryTreeNode<T>> allNode = getAllNode(root);
		for (BinaryTreeNode<T> node : allNode) {
			BinaryTreeNode<T> leftChild = node.getLeft();
			BinaryTreeNode<T> rightChild = node.getRight();
			if (leftChild != null) {
				if (leftChild.getData().compareTo(node.getData()) >= 0) {
					return false;
				}
			}
			if (rightChild != null) {
				if (rightChild.getData().compareTo(node.getData()) <= 0) {
					return false;
				}
			}
		}
		return true;
	}

	public T getLowestCommonAncestor(T n1, T n2) {

		List<BinaryTreeNode<T>> n1Ancestors = getAncestors(n1);
		List<BinaryTreeNode<T>> n2Ancestors = getAncestors(n2);

		if (n1Ancestors == null || n2Ancestors == null)
			return null;

		List<BinaryTreeNode<T>> commonAncestors = commonNode(n1Ancestors,
				n2Ancestors);

		if (commonAncestors == null)
			return null;

		HashMap<BinaryTreeNode<T>, Integer> allNode = getAllNode(root, 1);

		int commonAncestorsLevel = 1;

		T lowestCommonAncestor = null;

		for (Entry<BinaryTreeNode<T>, Integer> e : allNode.entrySet()) {
			for (BinaryTreeNode<T> ca : commonAncestors) {
				if (e.getKey() == ca && e.getValue() >= commonAncestorsLevel) {
					commonAncestorsLevel = e.getValue();
					lowestCommonAncestor = e.getKey().getData();
				}
			}
		}

		return lowestCommonAncestor;

	}

	private List<BinaryTreeNode<T>> commonNode(
			List<BinaryTreeNode<T>> n1List,
			List<BinaryTreeNode<T>> n2List) {

		if (n1List == null || n2List == null)
			return null;

		List<BinaryTreeNode<T>> commonNode = new ArrayList<BinaryTreeNode<T>>();
		for (BinaryTreeNode<T> n1a : n1List) {

			for (BinaryTreeNode<T> n2a : n2List) {
				if (n1a == n2a)
					commonNode.add(n1a);
			}

		}

		if (commonNode.isEmpty())
			return null;

		return commonNode;
	}

	public List<BinaryTreeNode<T>> getAncestors(T node) {
		if (root == null)
			return null;
		BinaryTreeNode<T> btnode = getNode(node);
		if (btnode == null)
			return null;
		return getAncestors(btnode);
	}

	public List<BinaryTreeNode<T>> getAncestors(BinaryTreeNode<T> node) {

		List<BinaryTreeNode<T>> ancestors = new ArrayList<BinaryTreeNode<T>>();

		if (root == null)
			return null;

		if (node == null)
			return null;

		BinaryTreeNode<T> ancestor = getParent(node);

		if (ancestor == null)
			return null;

		while (true) {

			ancestors.add(ancestor);
			ancestor = getParent(ancestor);

			if (ancestor == null) {
				break;
			}
		}
		return ancestors;
	}

	public List<T> getNodesBetween(T n1, T n2) {
		
		if(root==null)
			return null;
		
		List<BinaryTreeNode<T>> allNode = getAllNode(root);
		
		List<T> result = new ArrayList<T>();
		for(BinaryTreeNode<T> e:allNode){
			T data = e.getData();
			if(n1.compareTo(data)<0&&n2.compareTo(data)>0)
				result.add(data);
		}
		
		//排序
		HashSet<T> hs = new HashSet<T>(result);
		result.clear();
		result.addAll(hs);
		
		return result;
	}

	public int nodeLevel(T n){
		
		if (n==null) 
			return -1;
		
		List<BinaryTreeNode<T>>ancestor = getAncestors(n);
		
		if(ancestor==null)
			return -1;
		
		return ancestor.size()+1;
	}
	
	public Map<BinaryTreeNode<T>, Integer> getAncestorsMap(T node) {
		if (root == null)
			return null;
		if (node == null)
			return null;
		BinaryTreeNode<T> btnode = getNode(node);
		if (btnode == null)
			return null;
		return getAncestorsMap(btnode);
	}

	public Map<BinaryTreeNode<T>, Integer> getAncestorsMap(
			BinaryTreeNode<T> node) {

		if (root == null)
			return null;
		if (node == null)
			return null;
		
		List<BinaryTreeNode<T>> ancestorsList = getAncestors(node);
		
		if (ancestorsList == null)
			return null;
		
		Map<BinaryTreeNode<T>, Integer> allNode = getAllNode(root, 1);
		Map<BinaryTreeNode<T>, Integer> ancestorsMap = new HashMap<BinaryTreeNode<T>, Integer>();
		
		for (Entry<BinaryTreeNode<T>, Integer> e : allNode.entrySet()) {
			if (ancestorsList.contains(e.getKey())) {
				ancestorsMap.put(e.getKey(), e.getValue());
			}
		}
		
		if (ancestorsMap.isEmpty()) {
			return null;
		}
		return ancestorsMap;
	}
}
