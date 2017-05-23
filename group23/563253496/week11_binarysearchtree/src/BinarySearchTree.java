/**
 * Created by bdl19 on 2017/5/18.
 */
public class BinarySearchTree<T extends Comparable> {

    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        BinaryTreeNode<T> node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return (T) node.data;
    }

    public T findMax() {
        BinaryTreeNode<T> node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return (T) node.data;
    }

    public int height(BinaryTreeNode<T> node) {
        int height = 0;
        if (node != null) {
            int lchildHeight = height(node.getLeft());
            int rchildHeight = height(node.getRight());
            height = (lchildHeight > rchildHeight) ? lchildHeight + 1 : rchildHeight + 1;

        }
        return height;
    }

    public int size(BinaryTreeNode<T> node) {
        int size = 0;

        if (node != null) {
            return 1 + size(node.getLeft()) + size(node.getRight());
        }

        return size;
    }

    public void remove(int e) {
        BinaryTreeNode node = this.getRoot();
        BinaryTreeNode parent = null;
        while ((int) node.data != e) {
            parent = node;
            if (e < (int) node.data) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        if ((node.getLeft() == null) && (node.getRight() != null)) {
            if (parent.getLeft() == node) {
                parent.setLeft(node.getRight());
            } else {
                parent.setRight(node.getRight());
            }
        }
        if ((node.getLeft() != null) && (node.getRight() == null)) {
            if (parent.getLeft() == node) {
                parent.setLeft(node.getLeft());
            } else {
                parent.setRight(node.getLeft());
            }
        }
        if ((node.getLeft() != null) && (node.getRight() != null)) {
            //parent = node;
            BinaryTreeNode leaf = node.getRight();
            BinaryTreeNode n = node.getRight();
            while (leaf.getLeft() != null) {
                node = leaf;
                leaf = leaf.getLeft();
            }
            if ((int)parent.getLeft().data == e) {
                parent.setLeft(leaf);
                parent.getLeft().setRight(n);
            }
            if ((int)parent.getRight().data == e){
                parent.setRight(leaf);
                parent.getRight().setRight(n);
            }
            node.setLeft(null);

        }

    }

}