/**
 * Created by lxx on 2017/5/9.
 */

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class BinaryTreeUtil {
    /*** 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     ** @param
     * @return
     */

    public static void main(String[] args) {
        TreeNode<Integer> e = new   TreeNode<Integer>(5);
        TreeNode<Integer> g = new   TreeNode<Integer>(7);
        TreeNode<Integer> h = new   TreeNode<Integer>(8);

        TreeNode<Integer> l = new   TreeNode<Integer>(12);
        TreeNode<Integer> m = new   TreeNode<Integer>(13);
        TreeNode<Integer> n = new   TreeNode<Integer>(14);
        TreeNode<Integer> k = new   TreeNode<Integer>(11, n, null);
        TreeNode<Integer> j = new   TreeNode<Integer>(10, l, m);
        TreeNode<Integer> i = new   TreeNode<Integer>(9, j, k);
        TreeNode<Integer> d = new   TreeNode<Integer>(4, null, g);

        TreeNode<Integer> f = new   TreeNode<Integer>(6, h, i);
        TreeNode<Integer> b = new   TreeNode<Integer>(2, d, e);
        TreeNode<Integer> c = new   TreeNode<Integer>(3, f, null);

        TreeNode<Integer> root = new   TreeNode<Integer>(1, b, c);

        BinaryTree<Integer> tree=new BinaryTree<Integer>(root);
        System.out.println("递归前序遍历二叉树结果：");
        tree.preOrder(root);
        System.out.println();
        System.out.println("非递归前序遍历二叉树结果：");
        tree.nonRecursivePreOrder(root);
        System.out.println();

        System.out.println("递归中序遍历二叉树结果：");
        tree.inOrder(root);
        System.out.println();
        System.out.println("非递归中序遍历二叉树结果：");
        tree.nonRecursiveInOrder(root);
        System.out.println();

        System.out.println("递归后序遍历二叉树结果：");
        tree.postOrder(root);
        System.out.println();
    }


    public static class TreeNode<T> {
        T data;
        TreeNode<T> left = null;
        TreeNode<T> right = null;
        public TreeNode(T data){
            this.data = data;
        }
        public TreeNode(T data,TreeNode left,TreeNode right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class BinaryTree<T> {
        public TreeNode<T> root;
        public BinaryTree(TreeNode<T> root){
            this.root = root;
        }
        public void preOrder(TreeNode<T> root) {
            if(root != null){
                visit(root);
                preOrder(root.left);
                preOrder(root.right);

            }
        }

        private void visit(TreeNode<T> root) {
            System.out.print(root.data + " ");
        }

        public void inOrder(TreeNode<T> root) {
            if(root != null){
                inOrder(root.left);
                visit(root);
                inOrder(root.right);
            }
        }

        public void postOrder(TreeNode<T> root) {
            if(root != null){
                postOrder(root.left);
                postOrder(root.right);
                visit(root);
            }
        }

        public void nonRecursivePreOrder(TreeNode<T> root) {
            Stack<TreeNode<T>> stack = new Stack<>();
            stack.push(root);
            if(root != null){
                while (!stack.isEmpty()){
                    TreeNode<T> node = stack.pop();
                    visit(node);
                    if(node.right != null){
                        stack.push(node.right);
                    }
                    if(node.left != null){
                        stack.push(node.left);
                    }
                }
            }
        }

        public void nonRecursiveInOrder(TreeNode<T> root) {
            Stack<TreeNode<T>> stack = new Stack<>();
            TreeNode<T> node = root;
            while (node != null || !stack.isEmpty()){
                while(node != null){
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                visit(node);
                node = node.right;
            }
        }
    }
}