package com.coding.basic.tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> tree = null;
	
	@Before
	public void setUp() throws Exception {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(1);
		root.left.right = new BinaryTreeNode<Integer>(5);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		root.right.right = new BinaryTreeNode<Integer>(10);
		root.left.right.left.right = new BinaryTreeNode<Integer>(4);
		tree = new BinarySearchTree<Integer>(root);
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}

	@Test
	public void testFindMin() {
		Assert.assertEquals(1, tree.findMin().intValue());
		
	}

	@Test
	public void testFindMax() {
		Assert.assertEquals(10, tree.findMax().intValue());
	}

	@Test
	public void testHeight() {
		Assert.assertEquals(5, tree.height());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(8, tree.size());
	}

	@Test
	public void testRemoveLeaf() {
		tree.remove(5);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.right.data.intValue());
		
	}
	@Test
	public void testRemoveMiddleNode() {
		tree.remove(2);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.data.intValue());
		Assert.assertEquals(5, root.left.right.data.intValue());
		Assert.assertEquals(4, root.left.right.left.data.intValue());
	}
	@Test
	public void testRemoveRootNode() {
		tree.remove(6);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(8, root.data.intValue());
		Assert.assertEquals(2, root.left.data.intValue());
		Assert.assertEquals(10, root.right.data.intValue());
	}
}