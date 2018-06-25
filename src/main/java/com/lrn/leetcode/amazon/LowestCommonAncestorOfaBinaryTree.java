package com.lrn.leetcode.amazon;

public class LowestCommonAncestorOfaBinaryTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(final int x) {
			val = x;
		}
	}
	public TreeNode lowestCommonAncestor(final TreeNode root, final TreeNode p, final TreeNode q) {
		if(root == null) {
			return null;
		}
		if(findNode(root.left, p) && findNode(root.left, q)) {
			return lowestCommonAncestor(root.left, p, q);
		}
		if(findNode(root.right, p) && findNode(root.right, q)) {
			return lowestCommonAncestor(root.right, p, q);
		}
		return root;
	}

	public boolean findNode(final TreeNode root, final TreeNode nodeToFind) {
		TreeNode node = root;
		if(node == null) {
			return false;
		}
		if(node == nodeToFind) {
			return true;
		}
		return findNode(node.left, nodeToFind) || findNode(node.right, nodeToFind);
	}




	public static void main(final String[] args) {
		// TODO Auto-generated method stub

	}

}
