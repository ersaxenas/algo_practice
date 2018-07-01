package com.lrn.leetcode.amazon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SerializeandDeserializeBST449 {

	public static class Codec2 {
		private static final String SEP = ",";
		private static final String NULL = "null";
		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			StringBuilder sb = new StringBuilder();
			if (root == null) {
				return NULL;
			}
			//traverse it recursively if you want to, I am doing it iteratively here
			Stack<TreeNode> st = new Stack<>();
			st.push(root);
			while (!st.empty()) {
				root = st.pop();
				sb.append(root.val).append(SEP);
				if (root.right != null) {
					st.push(root.right);
				}
				if (root.left != null) {
					st.push(root.left);
				}
			}
			return sb.toString();
		}

		// Decodes your encoded data to tree.
		// pre-order traversal
		public TreeNode deserialize(final String data) {
			if (data.equals(NULL)) {
				return null;
			}
			String[] strs = data.split(SEP);
			Queue<Integer> q = new LinkedList<>();
			for (String e : strs) {
				q.offer(Integer.parseInt(e));
			}
			return getNode(q);
		}

		// some notes:
		//   5
		//  3 6
		// 2   7
		private TreeNode getNode(final Queue<Integer> q) { //q: 5,3,2,6,7
			if (q.isEmpty()) {
				return null;
			}
			TreeNode root = new TreeNode(q.poll());//root (5)
			Queue<Integer> samllerQueue = new LinkedList<>();
			while (!q.isEmpty() && (q.peek() < root.val)) {
				samllerQueue.offer(q.poll());
			}
			//smallerQueue : 3,2   storing elements smaller than 5 (root)
			root.left = getNode(samllerQueue);
			//q: 6,7   storing elements bigger than 5 (root)
			root.right = getNode(q);
			return root;
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

	public static class Codec {

		// Encodes a tree to a single string.
		public String serialize(final TreeNode root) {
			StringBuilder sbr = new StringBuilder();
			if(root == null) {
				return sbr.toString();
			}
			serialize(root, sbr);
			System.out.println(sbr);
			return sbr.toString();
		}

		public void serialize(final TreeNode node, final StringBuilder sbr) {
			if(node==null) {
				return;
			}
			/*pre order traversal*/
			sbr.append(node.val).append(",");
			serialize(node.left,sbr);
			serialize(node.right,sbr);
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(final String data) {
			if((data == null) || (data.length() ==0)) {
				return null;
			}
			Queue<Integer> queue = new LinkedList<>();
			/*split and append all the nodes to queue*/
			for(String str: data.split(",")) {
				queue.add(Integer.valueOf(str));
			}

			return getNode(queue);
		}

		public TreeNode getNode(final Queue<Integer> queue) {
			/*recursion base case*/
			if(queue.isEmpty()) {
				return null;
			}
			TreeNode root = new TreeNode(queue.poll());
			/*construct left tree with all the nodes smaller then node*/
			Queue<Integer> queueOfSmallerNodes = new LinkedList<>();
			while((!queue.isEmpty()) && (queue.peek() < root.val) ) {
				queueOfSmallerNodes.add(queue.poll());
			}
			root.left=getNode(queueOfSmallerNodes);
			/*construct right tree with all the nodes greater then node
			 * since all the smaller nodes have been moved to small queue so just use queue passed as method param
			 * */
			root.right = getNode(queue);
			return root;

		}

	}

	public static void main(final String[] args) {
		TreeNode node = new TreeNode(1);
		node.left = new TreeNode(3);
		node.right = new TreeNode(2);
		node.left.left = new TreeNode(4);
		node.left.left.left = new TreeNode(5);
		Codec code = new Codec();
		code.serialize(node);
		Codec2 code2 = new Codec2();
		System.out.println(code2.serialize(node));
	}

}
