package bst;

import account.AccountNode;
import account.Node;

public class AccountBSTGenericAccount<T extends Comparable<T>> {
	private Node<T> root;

	public AccountBSTGenericAccount() {
		root = null;
	}

	public AccountBSTGenericAccount(Node<T> r) {
		root = r;
	}

	public void insert(Node<T> x) {
		root = insert(x, root);
	}

	public void remove(Comparable<T> x) {
		root = remove(x, root);
	}

	public Comparable<T> findMin() {
		return elementAt(findMin(root));
	}

	public Comparable<T> findMax() {
		return elementAt(findMax(root));
	}

	public Node<T> find(Comparable<T> x) {
		return find(x, root);
	}

	public void printTree() {
		if (root == null)
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	private Comparable<T> elementAt(Node<T> t) {
		return t == null ? null : t.getValue();
	}

	private Node<T> insert(Node<T> x, Node<T> t) {
		if (t == null)
			t = x;
		else if (x.getValue().compareTo(t.getValue()) < 0)
			t.setLeft(insert(x, t.getLeft()));
		else if (x.getValue().compareTo(t.getValue()) > 0)
			t.setRight(insert(x, t.getRight()));
		else
			;
		// Duplicate; do nothing
		return t;
	}

	@SuppressWarnings("unchecked")
	private Node<T> remove(Comparable<T> x, Node<T> t) {
		// Given node is null, do nothing.
		if (t == null)
			return t;
		// Left tree single child
		if (x.compareTo(t.getValue()) < 0)
			t.setLeft(remove(x, t.getLeft()));
		// Right tree single child
		else if (x.compareTo(t.getValue()) > 0)
			t.setRight(remove(x, t.getRight()));
		// Two children
		else if (t.getLeft() != null && t.getRight() != null) {
			Node<T> setTo = findMin(t.getRight());
			t.setValue(setTo.getValue());
			if (t instanceof AccountNode && t.getRight() instanceof AccountNode) {
				((AccountNode<T>)t).setBalance(((AccountNode<T>)setTo).getBalance());
			}
			t.setRight(remove(t.getValue(), t.getRight()));
		}
		// Set next iteration of remove search to left or right
		else {
			t = (t.getLeft() != null) ? t.getLeft() : t.getRight();
		}
		return t;
	}

	// Finds smallest Node within given subtree
	private Node<T> findMin(Node<T> t) {
		// If given node is null return null.
		if (t == null)
			return null;
		// If left tree leaf = null return current node
		else if (t.getLeft() == null)
			return t;
		// Recursively search left tree leaves.
		return findMin(t.getLeft());
	}

	// Finds the largest Node within given subtree
	private Node<T> findMax(Node<T> t) {
		// Iteratively searches for largest right tree leaf.
		if (t != null)
			while (t.getRight() != null)
				t = t.getRight();
		return t;
	}

	private Node<T> find(Comparable<T> x, Node<T> t) {
		if (t == null)
			return null;
		//Show traversal route for purpose of assignment
		else
			System.out.print(t.getValue()+" ");
		// Left tree
		if (x.compareTo(t.getValue()) < 0) {
			return find(x, t.getLeft());
		}
		// Right tree
		else if (x.compareTo(t.getValue()) > 0) {
			return find(x, t.getRight());
		}
		// Found node
		else {
			return t;
		}
	}

	@SuppressWarnings("unchecked")
	private void printTree(Node<T> t) {
		if (t != null) {
			printTree(t.getLeft());
			if (t instanceof AccountNode) {
				System.out.println("KEY: " + t.getValue() + "\tBAL: " + ((AccountNode<T>) t).getBalance());
			} else
				System.out.println(t.getValue());
			printTree(t.getRight());
		}
	}
}