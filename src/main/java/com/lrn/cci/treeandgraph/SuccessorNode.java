package com.lrn.cci.treeandgraph;

public class SuccessorNode {
	/*
	 * 1. (root nod case) if node is root node (parent is null) the
	 *    a. if right node is null - no successor return null
	 *    b. if right node is present then find left most child and it will be successor
	 * 2. (node is not root node)
	 *     a. if right node is not null then find left most child and it will be successor
	 *     b. (node is left node of some node) now go to parent nodes and until you find a parent node with left child.
	 *         now successor will be the parent
	 * */

	public BTreeNode<Integer, Integer> findLeftChild(final BTreeNode<Integer, Integer> node) {
		/*no recursion needed*/
		if(node == null) {
			return null;
		}
		BTreeNode<Integer, Integer> currentNode = node;
		while(currentNode.getLeftNode() != null) {
			currentNode = currentNode.getLeftNode();
		}
		return currentNode;
	}

	public BTreeNode<Integer, Integer> findSuccesor(final BTreeNode<Integer, Integer> node) {
		BTreeNode<Integer, Integer> currentNode = node;
		BTreeNode<Integer, Integer> parentNode;
		/*case 1 : root node*/
		if(currentNode.getParent() == null) {
			/*a*/
			if(currentNode.getRightNode() == null) {
				return null; /*root node with no right child. so no successor*/
			} else {
				/*b*/
				return findLeftChild(currentNode);
			}
		} else {
			/*case 2 */
			/*a*/
			if(currentNode.getRightNode() != null) {
				return findLeftChild(currentNode);
			}
			/*b*/
			else {
				parentNode = currentNode.getParent();
				while(parentNode != null ) {
					if(currentNode == parentNode.getLeftNode()) {
						break;
					} else {
						currentNode = parentNode;
						parentNode = currentNode.getParent();
					}
				}
			}
		}
		return currentNode;
	}



}
