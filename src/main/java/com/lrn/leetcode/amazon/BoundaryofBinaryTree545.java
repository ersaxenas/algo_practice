package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.List;

public class BoundaryofBinaryTree545 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(final int x) { val = x; }
	}

	public List<Integer> boundaryOfBinaryTree(final TreeNode root) {
		List<Integer> lst = new ArrayList<>();
		if(root == null) {
			return lst;
		}
		/*add root to the list*/
		lst.add(root.val);
		/*add left boundary nodes to the list ignoring leaf nodes*/
		leftBoundary(root.left, lst);
		/*add all leaf nodes of the right node*/
		leafNodes(root.left, lst);
		/* add all leaf nodes of the right node*/
		leafNodes(root.right, lst);
		/*add right boundary*/
		rightBoundary(root.right, lst);
		return lst;
	}

	public void leftBoundary(final TreeNode root, final List<Integer> lst) {
		/*if root is null then break
		 * if leaf node then break/return. leaf node == both the child nodes are null.
		 * */
		if((root==null)||((root.left==null) && (root.right==null)) ) {
			return;
		}
		lst.add(root.val);
		/*go to right only if left is null
		 * else go to left
		 * */
		if(root.left == null) {
			leftBoundary(root.right, lst);
		} else {
			leftBoundary(root.left, lst);
		}
	}

	public void rightBoundary(final TreeNode root, final List<Integer> lst) {
		if((root==null)||((root.left==null) && (root.right==null)) ) {
			return;
		}
		/*
		 *got to left only if right is null
		 *else got to right
		 */
		if(root.right == null) {
			rightBoundary(root.left, lst);
		} else {
			rightBoundary(root.right, lst);
		}
		lst.add(root.val);
	}

	public void leafNodes(final TreeNode root, final List<Integer> lst) {
		if((root==null)) {
			return;
		}
		/*
		 * if leaf node then add to list.
		 * leaf node == both the child are null
		 * */
		if((root.left==null) && (root.right==null)) {
			lst.add(root.val);
			return;
		}
		leafNodes(root.left, lst);
		leafNodes(root.right, lst);
	}



}
