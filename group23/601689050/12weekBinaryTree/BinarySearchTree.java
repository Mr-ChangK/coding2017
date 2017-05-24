/**
 * Created by lxx on 2017/5/23.
 */
import java.util.ArrayList;

import java.util.List;


public class BinarySearchTree<T extends Comparable> {



    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root){

        this.root = root;

    }

    public BinaryTreeNode<T> getRoot(){

        return root;

    }


    public List<T> levelVisit(){

        List<T> result = new ArrayList<T>();
        Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
        BinaryTreeNode node = root;
        if(node == null){
            return null;
        }else{
            queue.enQueue(node);
            while (!queue.isEmpty()){
                node = queue.deQueue();
                result.add((T) node.data);
                if (node.left != null){
                    queue.enQueue(node.left);
                }
                if (node.right != null){
                    queue.enQueue(node.right);
                }
            }
            }


        return result;

    }
    public boolean isValid(){
        if (root == null){
            return true;
        }
        ArrayList<Integer> list = new ArrayList<>();
        inOrder(root,list);
        for (int i = 0;i < list.size() -1 ;i++){
            if (list.get(i) >= list.get(i+1)){
                return false;
            }
        }
        return true;
    }
    public void inOrder(BinaryTreeNode root, ArrayList<Integer> list){
        if (root == null){
            return;
        }
        inOrder(root.left,list);
        list.add(root.data);
        inOrder(root.right,list);
    }

    public Comparable getLowestCommonAncestor(Integer n1, Integer n2){

        if (root == null){
            return null;
        }
        return getLowestCommonAncestor(root,n1,n2);
    }

    private Integer getLowestCommonAncestor(BinaryTreeNode<T> root, Integer n1, Integer n2) {
        int min = (int) n1;
        int max = (int) n2;
        BinaryTreeNode<T> parent = null;
        if (min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        while (true){
            if (root.data < min){
                parent = root;
                root = root.right;
            } else if (root.data > max){
                parent = root;
                root = root.left;
            }else if (root.data == min || root.data == max){
                return parent.data;
            }else {
                return root.data;
            }
        }
    }

    public List<Integer> getNodesBetween(BinaryTreeNode root,Integer n1, Integer n2){
        if (root == null){
            return null;
        }
        List<Integer> elements = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        inOrder(root,list);
        int min =  n1;
        int max =  n2;
        if (min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for (int i =0; i < list.size();i++){
            if ( min < list.get(i) && list.get(i) < max){
                elements.add(list.get(i));
            }
        }
        return elements;
    }


    public static void  main(String srgs[]){
        BinarySearchTree<Integer> tree = null;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);

        root.left = new BinaryTreeNode<Integer>(2);

        root.right = new BinaryTreeNode<Integer>(8);

        root.left.left = new BinaryTreeNode<Integer>(1);

        root.left.right = new BinaryTreeNode<Integer>(4);

        root.left.right.left = new BinaryTreeNode<Integer>(3);

        tree = new BinarySearchTree<Integer>(root);




       root= tree.getRoot();


        System.out.println(tree.levelVisit());
        System.out.println(tree.isValid());

        System.out.println(tree.getLowestCommonAncestor(3,8));
        System.out.println(tree.getNodesBetween(root,1,6));

    }

}
