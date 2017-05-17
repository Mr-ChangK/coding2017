package com.coding.basic.tree;

public class BinaryTreeNode<T> {

    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    private BinaryTreeNode<T> parent;

    public BinaryTreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    public BinaryTreeNode<T> insert(T o) {
        BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(o);
        BinaryTreeNode<T> root = findRoot(this);
        if (root.data == null) {
            root.data = newNode.data;
        } else {
            int newVal = getNodeIntVal(newNode);
            insert(root, newNode, newVal);
        }
        return newNode;
    }

    private void insert(BinaryTreeNode<T> node, BinaryTreeNode<T> newNode, int newVal) {
        int nodeVal = getNodeIntVal(node);
        if (newVal < nodeVal) {
            if (node.left == null) {
                newNode.parent = node;
                node.left = newNode;
            } else {
                insert(node.left, newNode, newVal);
            }
        } else {
            if (node.right == null) {
                newNode.parent = node;
                node.right = newNode;
            } else {
                insert(node.right, newNode, newVal);
            }
        }
    }

    private BinaryTreeNode<T> findRoot(BinaryTreeNode<T> binaryTreeNode) {
        while (binaryTreeNode.parent != null) {
            binaryTreeNode = binaryTreeNode.parent;
        }
        return binaryTreeNode;
    }

    private int getNodeIntVal(BinaryTreeNode<T> node) {
        if (node.data instanceof Integer) {
            return ((Integer) node.data).intValue();
        }
        return 0;
    }

    public int getDataIntVal() {
        if (data instanceof Integer) {
            return ((Integer) data).intValue();
        }
        return 0;
    }
}
