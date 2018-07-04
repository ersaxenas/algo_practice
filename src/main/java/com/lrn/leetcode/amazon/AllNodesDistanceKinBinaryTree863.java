package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class AllNodesDistanceKinBinaryTree863 {

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

	public static class TreeNodeB {
		TreeNode node;
		int level =0;
		public TreeNodeB(final TreeNode node, final int level) {
			this.node = node;
			this.level = level;
		}
		@Override
		public String toString() {
			return "TreeNodeB [node=" + node.val + ", level=" + level + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = (prime * result) + ((node == null) ? 0 : node.hashCode());
			return result;
		}
		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			TreeNodeB other = (TreeNodeB) obj;
			if (node == null) {
				if (other.node != null) {
					return false;
				}
			} else if (node.val !=other.node.val) {
				return false;
			}
			return true;
		}

	}


	public static List<Integer> distanceK(final TreeNode root, final TreeNode target, final int K) {
		/*first dfs*/
		Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
		dfs(root, null, graph);
		/*BFS*/
		List<Integer> nodesAtLevel = new ArrayList<Integer>();
		Set<TreeNodeB> visited = new HashSet<>();
		Queue<TreeNodeB> queue = new LinkedList<>();
		TreeNodeB nodeB = new TreeNodeB(target, 0);
		queue.add(nodeB);
		visited.add(nodeB);
		while(!queue.isEmpty()) {
			TreeNodeB currNodeB = queue.poll();
			/*action*/
			if(currNodeB.level == K) {
				if(currNodeB.node != null) {
					nodesAtLevel.add(currNodeB.node.val);
				}
			}
			/*add linked node to queue*/
			for(TreeNode nd : graph.get(currNodeB.node)) {
				TreeNodeB newNode = new TreeNodeB(nd, currNodeB.level+1);
				if(!visited.contains(newNode)) {
					visited.add(newNode);
					queue.add(newNode);
				}
			}

		}
		return nodesAtLevel;
	}

	public static void dfs(final TreeNode node, final TreeNode parent, final Map<TreeNode, List<TreeNode>> graph) {
		if(node == null) {
			return;
		}
		List<TreeNode> lstParent = graph.containsKey(parent)?graph.get(parent):new ArrayList<>();
		List<TreeNode> lstNode = graph.containsKey(node)?graph.get(node):new ArrayList<>();
		lstNode.add(parent);
		lstParent.add(node);
		graph.put(parent, lstParent);
		graph.put(node, lstNode);
		dfs(node.left,node,graph);
		dfs(node.right,node,graph);
	}

	public static void main(final String[] args) {
		TreeNode node = new TreeNode(3);
		node.left = new TreeNode(5);
		node.right = new TreeNode(1);

		node.left.left = new TreeNode(6);
		node.left.right = new TreeNode(2);

		node.left.right.left = new TreeNode(7);
		node.left.right.right = new TreeNode(4);

		node.right.left = new TreeNode(0);
		node.right.right = new TreeNode(8);

		System.out.println(distanceK(node, node.left, 2));
	}

}
