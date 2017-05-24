package src;

/**
 * Created by lxx on 2017/5/18.
 */
public class BinarySearchTree<T extends Comparable> {



    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root){

        this.root = root;

    }

    public BinaryTreeNode<T> getRoot(){

        return root;

    }

    public Integer findMin(){

        BinaryTreeNode<T> node = root;
        while(node.left != null){
            node = node.left;
        }
        return node.data;

    }

    public Integer findMax(){

        BinaryTreeNode<T> node = root;
        while (node.right != null){
            node = node.right;
        }
        return node.data;
    }

    public int height() {

        BinaryTreeNode<T> node = root;

        int height = 0;
        if(node != null){
            BinarySearchTree<T> leftTree = new BinarySearchTree<T>(node.left);
            int m = leftTree.height();
            BinarySearchTree<T> rightTree = new BinarySearchTree<T>(node.right);
            int n = rightTree.height();
            height =  (m > n) ? m+1 : n+1;
        }else {
            return 0;
        }
        return height;

    }

    public int size() {
        BinaryTreeNode<T> node = root;
        int size = 0;
        if(node == null) {
            return 0;
        }
        else {
            BinarySearchTree<T> leftTree = new BinarySearchTree(node.left);
            int m = leftTree.size();
            BinarySearchTree<T> rightTree = new BinarySearchTree(node.right);
            int n = rightTree.size();
            size =  m + n + 1 ;
            return size ;
        }

    }

    public void remove(T e){

        Integer key = (Integer) e;
        BinaryTreeNode<T> node = root;
        if(node == null){
            return;
        }
        BinaryTreeNode<T> p = null;
        while(node != null) {
            if (node.data > key) {
                p = node;
                node = node.left;
            } else if (node.data < key) {
                p = node;
                node = node.right;
            } else {

                break;
            }
        }
            if(node.left == null && node.right == null){
                if(p == null){
                    node = null;
                }else{
                    if(p.left == node){
                        p.left = null;
                    }else if(p.right == node){
                        p.right = null;
                    }
                }
            }else if (node.left != null && node.right == null){
                if (p == null){
                    node = node.left;
                }else{
                    if(p.left == node){
                        p.left = node.left;
                    }else if (p.right == node){
                        p.right = node.left;
                    }
                }
            }else if (node.left == null && node.right != null){
                if (p == null){
                    node = node.right;
                }else{
                    if (p.left == node){
                        p.left = node.right;
                    }else if (p.right == node){
                        p.right= node.right;
                    }
                }
            }else if(node.left != null && node.right != null){
                BinaryTreeNode<T> rMin = node.right;
                BinaryTreeNode<T> rMinP = null;
                while (rMin.left != null){
                    rMinP = rMin;
                    rMin = rMin.left;
                }
                int temp = node.data;
                node.data = rMin.data;
                rMin.data = temp;
                if (rMinP.left == rMin){
                    rMinP.left = rMin.right;
                }else if (rMinP.right == rMin){
                    rMinP.right = rMin.right;
                }
            }

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


        /*tree.remove(4);

        root= tree.getRoot();

        System.out.println("3," + root.left.right.data.intValue());*/
        tree.remove(2);
        root = tree.getRoot();
        System.out.println( "4," + root.left.right.data.intValue());
        System.out.println("3," + root.left.data.intValue());
        System.out.println(tree.height());
        System.out.println(tree.size());

        System.out.println(tree.findMax());
        System.out.println(tree.findMin());

    }

}
