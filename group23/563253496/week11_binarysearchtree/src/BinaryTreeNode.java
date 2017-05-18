/**
 * Created by bdl19 on 2017/5/18.
 */
public class BinaryTreeNode<T> {

    public T data;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T data){
        this.data=data;
    }

    public BinaryTreeNode(BinaryTreeNode<T> node) {
        this.data= (T) node.data;
        this.left=node.left;
        this.right = node.right;
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

    public BinaryTreeNode<T> insert(Object o){
        return  null;
    }

}