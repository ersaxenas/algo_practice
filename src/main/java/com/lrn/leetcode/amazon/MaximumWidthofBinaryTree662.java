package com.lrn.leetcode.amazon;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthofBinaryTree662 {

	static class AnnotatedNode {
		TreeNode node;
		int depth, pos;
		AnnotatedNode(final TreeNode n, final int d, final int p) {
			node = n;
			depth = d;
			pos = p;
		}
	}

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

	public static int widthOfBinaryTree(final TreeNode root) {
		Queue<AnnotatedNode> queue = new LinkedList();
		queue.add(new AnnotatedNode(root, 0, 0));
		int pos=0, left=0, depth=0;
		while(!queue.isEmpty()) {
			AnnotatedNode anode = queue.poll();
			if(anode.node!=null) {
				queue.add(new AnnotatedNode(anode.node.left, anode.depth+1, anode.pos*2));
				queue.add(new AnnotatedNode(anode.node.right, anode.depth+1, (anode.pos*2) + 1));
				if(depth!=anode.depth) {
					depth=anode.depth;
					left=anode.pos;
				}
				pos = Math.max(pos, (anode.pos-left)+1);
			}
		}
		return pos;
	}

	public static void main(final String[] args) {
		TreeNode node = new TreeNode(1);
		node.left = new TreeNode(3);
		node.right = new TreeNode(2);
		node.left.left = new TreeNode(5);
		node.left.right = new TreeNode(3);
		node.right.right = new TreeNode(9);
		System.out.println(widthOfBinaryTree(node));
	}

}
