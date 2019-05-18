package com.lrn.cci.treeandgraph;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class BinarySearchTree<K extends Comparable<K>, V> {

	private BTreeNode<K, V> rootNode;
	/* no recursion*/
	public Optional<V> get(final K key) {
		/* start with root node */
		BTreeNode<K, V> node = rootNode;
		while (node != null) {
			int cmp = key.compareTo(node.getKey());
			/* key is small then go to left */
			if (cmp == -1) {
				node = node.getLeftNode();
			}
			/* key is large then go to right */
			else if (cmp == 1) {
				node = node.getRightNode();
			}
			/* key found */
			else {
				return Optional.of(node.getVal());
			}
		}
		return Optional.empty();
	}

	/*recursion*/
	public BinarySearchTree<K, V> put(final K key, final V value) {
		rootNode = put(rootNode, key, value);
		return this;
	}
	/*recursion*/
	private BTreeNode<K, V> put(final BTreeNode<K, V> node, final K key, final V value) {
		/* recursion case case: if null node then create new node and return */
		if (node == null) {
			return new BTreeNode<K, V>(key, value, 1);
		}
		/* recursion : recursive case */
		int cmp = key.compareTo(node.getKey());
		/* if key is less then current node then go left */
		if (cmp == -1) {
			node.setLeftNode(put(node.getLeftNode(), key, value));
		}
		/* if key is greater then current node then go right */
		else if (cmp == 1) {
			node.setRightNode(put(node.getRightNode(), key, value));
		}
		/* keys are equal so update the value of the current node */
		else {
			node.setVal(value);
		}

		/* to keep track of size of the tree */
		/* current node size = 1 + size of left subtree + size of right subtree */
		node.setCount(1 + sizeOf(node.getLeftNode()) + sizeOf(node.getRightNode()));

		/* return current node */
		return node;
	}

	public int size() {
		return sizeOf(rootNode);
	}

	private int sizeOf(final BTreeNode<K, V> node) {
		if (node == null) {
			return 0;
		} else {
			return node.getCount();
		}
	}

	public int rank(final K key) {
		return rankOf(rootNode, key);
	}

	/**
	 * finds rank of a given node. Method can help find number of keys less then a given key.
	 *
	 * @param node
	 * @param key
	 * @return
	 */
	public int rankOf(final BTreeNode<K, V> node, final K key) {
		if (node == null) {
			return 0;
		}
		int cmp = key.compareTo(node.getKey());
		/* if key is smaller then go to the left to find keys smaller then current key */
		if (cmp == -1) {
			return rankOf(node.getLeftNode(), key);
		}
		/*
		 * if key is greater then rank = size of left sub tree (all the keys on the left) + 1 (for key at the current node) + rank of the right sub tree
		 */
		else if (cmp == 1) {
			return 1 + sizeOf(node.getLeftNode()) + rankOf(node.getRightNode(), key);
		}
		/* if key equal to current node then rank is size of left sub tree (left subtree will have all the key less then current node) */
		else {
			return sizeOf(node.getLeftNode());
		}

	}

	/* tree traversal or inorder traversal */
	public Iterable<K> getKeys() {
		Queue<K> queue = new LinkedList<K>();
		inOrder(rootNode, queue);
		return queue;
	}

	/*recursion*/
	private void inOrder(final BTreeNode<K, V> node, final Queue<K> queue) {
		/* recursion base case */
		if (node == null) {
			return;
		}
		/* recursive case */
		/* 1. keep going left : it will take you to smallest key */
		inOrder(node.getLeftNode(), queue);
		/*
		 * 2. put current node on the queue. idea is to find smallest tree on the left sub tree and then add root node to the queue
		 */
		queue.add(node.getKey());
		/* 3. start with right subtree and repeat step 1,2,3 */
		inOrder(node.getRightNode(), queue);
	}

	public BTreeNode<K, V> getRootNode() {
		return rootNode;
	}

}
