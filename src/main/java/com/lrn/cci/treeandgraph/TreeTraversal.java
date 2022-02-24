package com.lrn.cci.treeandgraph;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal<K extends Comparable<K>, V> {

	public void inOrderTraversal(final BTreeNode<K, V> node, final Queue<BTreeNode<K, V>> queue) {
		/* visit the left branch then current node and finally right branch */
		if (node != null) {
			inOrderTraversal(node.getLeftNode(), queue);
			queue.add(node);
			inOrderTraversal(node.getRightNode(), queue);
		}
	}

	public void preOrderTraversal(final BTreeNode<K, V> node, final Queue<BTreeNode<K, V>> queue) {
		/* visit the current node first and then its child first left and then right */
		if (node != null) {
			queue.add(node);
			preOrderTraversal(node.getLeftNode(), queue);
			preOrderTraversal(node.getRightNode(), queue);
		}
	}

	public void postOrderTraversal(final BTreeNode<K, V> node, final Queue<BTreeNode<K, V>> queue) {
		/* visit child node first then visit current node */
		if (node != null) {
			postOrderTraversal(node.getLeftNode(), queue);
			postOrderTraversal(node.getRightNode(), queue);
			queue.add(node);
		}
	}

	public void printQueue(final Queue<BTreeNode<K, V>> queue) {
		queue.forEach(node -> {
			// System.out.println(node.getKey() + " : " + node.getVal());
			System.out.println(node);
		});
	}

	public static void main(final String[] args) {
		Queue<BTreeNode<Integer, String>> queue = new LinkedList<>();
		BinarySearchTree<Integer, String> bTree = TreeUtil.getTreeOfIntAndString(5);
		TreeTraversal<Integer, String> treeTraversal = new TreeTraversal<>();
		System.out.println("In-Order Traversal:");
		treeTraversal.inOrderTraversal(bTree.getRootNode(), queue);
		treeTraversal.printQueue(queue);
		queue.clear();
		System.out.println("Pre-Order Traversal:");
		treeTraversal.preOrderTraversal(bTree.getRootNode(), queue);
		treeTraversal.printQueue(queue);
		queue.clear();
		System.out.println("Post-Order Traversal:");
		treeTraversal.postOrderTraversal(bTree.getRootNode(), queue);
		treeTraversal.printQueue(queue);
		queue.clear();
	}

}
