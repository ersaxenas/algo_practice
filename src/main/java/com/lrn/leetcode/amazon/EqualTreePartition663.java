package com.lrn.leetcode.amazon;

import java.util.Stack;

public class EqualTreePartition663 {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		int sum=0;
		TreeNode(final int x) { val = x; }
		@Override
		public String toString() {
			return "val=" + val + "";
		}
	}

	public boolean checkEqualTree(final TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		int total = sum(root, stack);
		/*remove top node since we have total*/
		/*if total is even number then only tree can be divided in to 2 halves*/
		if((total%2)==0) {
			for(TreeNode node: stack) {
				if(node.sum == (total/2)) {
					System.out.println(node.val);;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Based on DFS function recursively calculates sum of each node tree.
	 */
	public int sum(final TreeNode node, final Stack<TreeNode> stack) {
		if(node==null) {
			return 0;
		}
		/*sum = sum of left tree plus sum of right tree*/
		int sum = sum(node.left,stack) + sum(node.right,stack) + node.val;
		node.sum = sum;
		stack.push(node);
		return sum;
	}




	public static void main(final String[] args) {
		TreeNode node = new TreeNode(5);
		node.left = new TreeNode(10);
		node.right = new TreeNode(10);
		node.right.left = new TreeNode(2);
		node.right.right = new TreeNode(3);
		EqualTreePartition663 obj= new EqualTreePartition663();
		System.out.println(obj.checkEqualTree(node));

	}

}
