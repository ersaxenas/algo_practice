package com.lrn.leetcode.amazon;

public class SplitBST776 {

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

	public TreeNode[] splitBST(final TreeNode root, final int V) {
		/*splitted[0] stores items <= v and splitted[1] stores elemets > V*/
		TreeNode[] splitted = new TreeNode[2];
		if(root == null) {
			return splitted;
		}
		/*if root.val is <= v so root will be part of the left subtree where all the elements <= v will be stored.
		 * in this case split right or cut right branch
		 * */
		if(root.val<=V) {
			splitted = splitBST(root.right, V);
			/*since splitted stores item <= V at [0] so store smaller root on right*/
			root.right = splitted[0];
			splitted[0] = root;
			return splitted;
		} else {
			/*if root.val is > v so root will NOT be part of the left subtree where all the elements <= v will be stored.
			 * in this case split left or cut left brach
			 * */
			splitted = splitBST(root.left, V);
			/*since splitted stores item >= V at [1] so store smaller root on left*/
			root.left=splitted[1];
			splitted[1] = root;
			return splitted;
		}
	}

}
