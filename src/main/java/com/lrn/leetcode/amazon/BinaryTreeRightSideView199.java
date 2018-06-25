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
