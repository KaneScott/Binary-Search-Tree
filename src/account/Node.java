package account;

public class Node<T extends Comparable<T>> {
	protected T value;
	private Node<T> left;
	private Node<T> right;

	/**
	 * Creates a node with specified key value.
	 * 
	 * @param value
	 *            key representing node
	 */
	public Node(T value) {
		this.value = value;
	}
	

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}
}