package com.lrn.leetcode.amazon;

public class ValidBST98 {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(final int x) { val = x; }
		@Override
		public String toString() {
			return "val=" + val + "";
		}
	}

	public boolean isValidBST(final TreeNode root) {
		return checkIfBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean checkIfBST(final TreeNode root, final long min, final long max) {
		if(root == null) {
			return true;
		}
		if((root.val >= max) || (root.val <= min)) {
			return false;
		}
		return checkIfBST(root.left, min, root.val) || checkIfBST(root.right, root.val, max);
	}

}
