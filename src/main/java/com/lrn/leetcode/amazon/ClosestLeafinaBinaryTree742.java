package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ClosestLeafinaBinaryTree742 {

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


	public Integer findClosestLeaf(final TreeNode root, final int k) {
		Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
		/*run dfs*/
		dfs(root, null, graph);
		/* run bfs from the node */
		Queue<TreeNode> queue = new LinkedList<>();
		Set<TreeNode> seen = new HashSet<>();
		/*find node with value k in graph*/
		for(TreeNode node: graph.keySet()) {
			if((node!=null) && (node.val == k)) {
				queue.add(node);
				seen.add(node);
				break;
			}
		}
		/*start BFS from k node*/
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			/*if a node is connected to only one node then it is a leaf node.
			 * logic: leaf nodes will only be connected to parent node.
			 * */
			if(node != null) {
				if(graph.get(node).size() <=1) {
					return node.val;
				} else {
					/*add unseen connected nodes to the queue.*/
					for(TreeNode adjNode: graph.get(node)) {
						if(!seen.contains(adjNode)) {
							queue.add(adjNode);
							seen.add(adjNode);
						}
					}
				}
			}
		}
		return null;
	}

	/*
	 * function run dfs over tree and prepares a graph to connected nodes
	 *
	 */
	public void dfs(final TreeNode node, final TreeNode parent ,final Map<TreeNode, List<TreeNode>> graph) {
		if(node ==null) {
			return;
		}
		if(!graph.containsKey(node)) {
			List<TreeNode> list = new ArrayList<>();
			graph.put(node, list);
		}
		if(!graph.containsKey(parent)) {
			List<TreeNode> list = new ArrayList<>();
			graph.put(parent, list);
		}
		graph.get(node).add(parent);
		graph.get(parent).add(node);
		dfs(node.left, node,graph);
		dfs(node.right, node,graph);
	}



	public static void main(final String[] args) {
		TreeNode node = new TreeNode(1);
		node.left = new TreeNode(3);
		node.right = new TreeNode(2);
		//node.left.left = new TreeNode(4);
		//node.left.left.left = new TreeNode(5);
		ClosestLeafinaBinaryTree742 obj = new ClosestLeafinaBinaryTree742();
		System.out.println(obj.findClosestLeaf(node, 1));

	}

}
