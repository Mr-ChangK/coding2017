package tree;

public class BinaryTreeNode<T> {
	
	public T data;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T data){
		this.data=data;
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
	
	@Override
	public String toString() {
		return ""+data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinaryTreeNode other = (BinaryTreeNode) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	
}