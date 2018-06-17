package com.lrn.cci.treeandgraph;

import java.util.Random;

public class TreeUtil {

	public static BinarySearchTree<Integer, String> getTreeOfIntAndString(final int size) {
		BinarySearchTree<Integer, String> binaryTree = new BinarySearchTree<>();
		Random randomGen = new Random();
		randomGen.ints(size, 1, 1000).forEach(num -> binaryTree.put(num, String.valueOf(num)));
		return binaryTree;
	}

}
