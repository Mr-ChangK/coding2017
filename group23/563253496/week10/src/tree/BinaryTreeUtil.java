package tree;

/**
 * Created by bdl19 on 2017/5/8.
 */

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
        result.add(root.getData());

        if (root.getLeft() != null) {
            result.addAll(preOrderVisit(root.getLeft()));
        }
        if (root.getRight() != null) {
            result.addAll(preOrderVisit(root.getRight()));
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        if (root.getLeft() != null) {
            result.addAll(inOrderVisit(root.getLeft()));
        }
        result.add(root.getData());
        if (root.getRight() != null) {
            result.addAll(inOrderVisit(root.getRight()));
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        if (root.getLeft() != null) {
            result.addAll(postOrderVisit(root.getLeft()));
        }
        if (root.getRight() != null) {
            result.addAll(postOrderVisit(root.getRight()));
        }
        result.add(root.getData());
        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
        List<T> result = new ArrayList<T>();

        while (root != null || !s.isEmpty()) {
            while (root != null) {
                result.add(root.getData());
                s.push(root);
                root = root.getLeft();
            }
            if (!s.isEmpty()) {
                root = s.pop().getRight();
            }
        }

        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
        Stack<BinaryTreeNode> s = new Stack<>();

        List<T> result = new ArrayList<T>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);
                root = root.getLeft();
            }

            if (!s.isEmpty()) {
                root = s.pop();
                result.add(root.getData());
                root = root.getRight();
            }
        }


        return result;
    }

}