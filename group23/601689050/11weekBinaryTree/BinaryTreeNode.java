package src;

/**
 * Created by lxx on 2017/5/18.
 */
public class BinaryTreeNode<T> {
    Integer data;
    public BinaryTreeNode<T> left = null;
    public BinaryTreeNode<T> right = null;
    public BinaryTreeNode<T> root;
    public BinaryTreeNode(BinaryTreeNode<T> root){
        this.root = root;
    }
    public BinaryTreeNode(Integer data){
        this.data = data;
    }
    public void setLeft(BinaryTreeNode<T> left){
        this.left = left;
    }
    public void setRigth(BinaryTreeNode<T> right){
        this.right = right;
    }
    public BinaryTreeNode<T> getLeft(){
        return left;
    }
    public BinaryTreeNode<T> getRigth(){
        return right;
    }
    public void visit(BinaryTreeNode<T> root){
        System.out.println(root.data);
    }
}
