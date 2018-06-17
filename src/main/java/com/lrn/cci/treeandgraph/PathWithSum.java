package com.lrn.cci.treeandgraph;

import java.util.ArrayList;
import java.util.Random;

public class PathWithSum {

	/**
	 * You are given a binary tree in which each node contains a value.
	 * Design an algorithm to print all paths which sum up to that value.
	 * Note that it can be any path in the tree
	 * - it does not have to start at the root.
	 *
	 */
	public void pathWithSumFromNode(final BTreeNode<Integer, String> node, final ArrayList<Integer> nodeBuffer, final int targetSum, final int currentSum) {
		if(node==null ) {
			return;
		}
		/*add current node key/data to list*/
		int currentNodeVal = node.getKey();
		nodeBuffer.add(currentNodeVal);
		/*check target sum*/
		if(targetSum ==(currentSum + currentNodeVal)) {
			/*sum found*/
			/*print list*/
			printList(nodeBuffer);
		}
		ArrayList<Integer> nodeBufferLeft =  (ArrayList<Integer>) nodeBuffer.clone();
		ArrayList<Integer> nodeBufferRight =  (ArrayList<Integer>) nodeBuffer.clone();

		pathWithSumFromNode(node.getLeftNode(), nodeBufferLeft, targetSum, currentSum + currentNodeVal);
		pathWithSumFromNode(node.getRightNode(), nodeBufferRight, targetSum, currentSum + currentNodeVal);

	}

	public void pathWithSum(final BTreeNode<Integer, String> node, final int targetSum) {
		/*base case*/
		if(node == null ) {
			return;
		}
		BTreeNode<Integer, String> currentNode= node;
		pathWithSumFromNode(currentNode, new ArrayList<>(), targetSum, 0);
		/*recursive case*/
		pathWithSumFromNode(currentNode.getLeftNode(), new ArrayList<>(), targetSum, 0);
		pathWithSumFromNode(currentNode.getRightNode(), new ArrayList<>(), targetSum, 0);

	}

	public void printList(final ArrayList<Integer> nodeBuffer) {
		nodeBuffer.forEach(num -> {
			System.out.print(" " + num +" ->");
		});
		System.out.println("\n");
	}

	public static void main(final String[] args) {
		Random random = new Random();
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
		random.ints(10000, 1, 50).forEach(num -> {
			bst.put(num, String.valueOf(num));
		});
		//		bst.put(3, "3");
		//		bst.put(2, "3");
		//		bst.put(5, "3");
		//		bst.put(1, "3");
		//		bst.put(4, "3");
		//		bst.put(8, "3");
		//		bst.put(6, "3");
		//		bst.put(9, "3");
		//		bst.put(7, "3");
		//		Queue<BTreeNode<Integer, String>> queue = new LinkedList<>();
		//		TreeTraversal<Integer, String> treeTra	= new TreeTraversal<>();
		//		treeTra.preOrderTraversal(bst.getRootNode(), queue);
		//		queue.forEach(node->{
		//			System.out.println(node);
		//		});
		PathWithSum obj = new PathWithSum();
		for(int cnt =0; cnt< 50; cnt++) {
			System.out.println("path for " + cnt);
			obj.pathWithSum(bst.getRootNode(), cnt);
		}

	}
}
