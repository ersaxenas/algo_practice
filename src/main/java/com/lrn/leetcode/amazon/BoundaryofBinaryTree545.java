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
		lst.add(root.val);
		leftBoundry(root.left, lst);
		leafNodes(root.left, lst);
		leafNodes(root.right, lst);
		rightBoundry(root.right, lst);
		return lst;
	}

	public void leftBoundry(final TreeNode root, final List<Integer> lst) {
		if((root==null)||((root.left==null) && (root.right==null)) ) {
			return;
		}
		lst.add(root.val);
		if(root.left == null) {
			leftBoundry(root.right, lst);
		} else {
			leftBoundry(root.left, lst);
		}
	}

	public void rightBoundry(final TreeNode root, final List<Integer> lst) {
		if((root==null)||((root.left==null) && (root.right==null)) ) {
			return;
		}
		if(root.right == null) {
			rightBoundry(root.left, lst);
		} else {
			rightBoundry(root.right, lst);
		}
		lst.add(root.val);
	}

	public void leafNodes(final TreeNode root, final List<Integer> lst) {
		if((root==null)) {
			return;
		}
		if((root.left==null) && (root.right==null)) {
			lst.add(root.val);
			return;
		}
		leafNodes(root.left, lst);
		leafNodes(root.right, lst);
	}



}
