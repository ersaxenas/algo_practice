package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView199 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(final int x) { val = x; }
	}

	public List<Integer> rightSideView(final TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if(root!= null) {
			addRightSideNode(root, result, 0);
		}
		return result;
	}
	/*
	 * for each level we need to add just one node that is the right most node.
	 * since we want add right most node only while traversing the tree go to right side first.
	 */
	public void addRightSideNode(final TreeNode node, final List<Integer> lst, final int level) {
		if(node == null) {
			return;
		}
		if(level == lst.size()) {
			lst.add(node.val);
		}

		addRightSideNode(node.right, lst, level+1);
		addRightSideNode(node.left, lst, level+1);

	}





}
