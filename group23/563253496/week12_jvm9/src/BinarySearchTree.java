/**
 * Created by bdl19 on 2017/5/23.
 */

import sun.awt.image.ImageWatched;

import java.util.*;

//import com.coding.basic.queue.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/*
public class BinarySearchTree<T extends Comparable> {

    BinaryTreeNode<T> root;
    public BinarySearchTree(BinaryTreeNode<T> root){
        this.root = root;
    }
    public BinaryTreeNode<T> getRoot(){
        return root;
    }
    public T findMin(){
        return null;
    }
    public T findMax(){
        return null;
    }
    public int height() {
        return -1;
    }
    public int size() {
        return -1;
    }
    public void remove(T e){

    }
    public List<T> levelVisit(){

        return null;
    }
    public boolean isValid(){
        return false;
    }
    public T getLowestCommonAncestor(T n1, T n2){
        return null;

    }
    public List<T> getNodesBetween(T n1, T n2){
        return null;
    }

}*/

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
            if ((int) parent.getLeft().data == e) {
                parent.setLeft(leaf);
                parent.getLeft().setRight(n);
            }
            if ((int) parent.getRight().data == e) {
                parent.setRight(leaf);
                parent.getRight().setRight(n);
            }
            node.setLeft(null);

        }

    }

    public List<BinaryTreeNode> levelVisit() {
        List<BinaryTreeNode> temp = new LinkedList();
        Queue<BinaryTreeNode> queue = new LinkedBlockingQueue<>();

        if (root == null) {
            return null;
        }
        temp.add(this.getRoot());
        if (this.getRoot().getLeft() != null) {
            queue.add(this.getRoot().getLeft());
        }
        if (this.getRoot().getRight() != null) {
            queue.add(this.getRoot().getRight());
        }
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.remove();
            temp.add(node);
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }

        return temp;




        /*
        temp.add(node);
        temp.addAll(levelVisit(node.getLeft()));
        temp.addAll(levelVisit(node.getRight()));
        return temp;*/
    }


    private LinkedList<Integer> inorder(BinaryTreeNode node) {
        LinkedList<Integer> result = new LinkedList<>();
        if (node == null) {
            return result;
        }
        if (node.getLeft() != null) {
            result.addAll(inorder(node.getLeft()));
        }

        result.add((Integer) node.data);

        if (node.getRight() != null) {
            result.addAll(inorder(node.getRight()));
        }
        return result;
    }

    public boolean isValid() {
        LinkedList<Integer> inorder = inorder(this.getRoot());
        if (inorder.size() == 1 || inorder.size() == 0) {
            return true;
        }

        int a = inorder.get(0);
        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i) < a) {
                return false;
            }
            a = inorder.get(i);
        }
        return true;

    }


    private LinkedList<Integer> preorder(BinaryTreeNode node) {
        LinkedList<Integer> result = new LinkedList<>();
        if (node == null) {
            return result;
        }
        result.add((Integer) node.data);
        result.addAll(preorder(node.getLeft()));
        result.addAll(preorder(node.getRight()));
        return result;

    }


    public T getLowestCommonAncestor(BinaryTreeNode node, T n1, T n2) {

        if (node == null || n1 == null || n2 == null) {
            return null;
        }
        if (((int) n1 > (int) node.data) && ((int) n2 > (int) node.data)) {
            node = node.getRight();
            return getLowestCommonAncestor(node, n1, n2);
        } else if (((int) n1 < (int) node.data) && ((int) n2 < (int) node.data)) {
            node = node.getLeft();
            return getLowestCommonAncestor(node, n1, n2);
        }else {
            return (T) node.data;
        }


    }

    public List<T> getNodesBetween(T n1, T n2) {
        List<T> result = new LinkedList<T>();
        LinkedList<Integer> in = inorder(this.getRoot());
        int a = in.indexOf(n1);
        int b = in.indexOf(n2);
        for (int i = (int) n1 + 1; i < (int) n2; i++) {
            result.add((T) in.get(i));
        }
        return result;
    }


}