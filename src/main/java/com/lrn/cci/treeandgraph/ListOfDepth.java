package com.lrn.cci.treeandgraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class ListOfDepth {

	public ArrayList<LinkedList<BTreeNode<Integer, String>>> createLevelList(final BTreeNode< Integer, String> rootNode) {
		ArrayList<LinkedList<BTreeNode< Integer, String>>> result = new ArrayList<>();
		LinkedList<BTreeNode< Integer, String>> level0List = new LinkedList<>();
		level0List.add(rootNode);
		Queue<LinkedList<BTreeNode< Integer, String>>> queue = new LinkedList<>();
		queue.add(level0List);
		while(!queue.isEmpty()) {
			LinkedList<BTreeNode< Integer, String>> currentList = queue.poll();
			result.add(currentList);
			LinkedList<BTreeNode< Integer, String>> levelList = new LinkedList<>();
			if(!currentList.isEmpty()) {
				currentList.forEach(node -> {
					if(node.getLeftNode() != null) {
						levelList.add(node.getLeftNode());
					}
					if(node.getRightNode() != null) {
						levelList.add(node.getRightNode());
					}
				});
				queue.add(levelList);
			}
		}
		return result;
	}

	public static void main(final String[] args) {
		ListOfDepth obj = new ListOfDepth();
		Queue<BTreeNode<Integer, String>> queue = new LinkedList<>();
		BinarySearchTree<Integer, String> bTree = TreeUtil.getTreeOfIntAndString(10);
		TreeTraversal<Integer, String> treeTraversal = new TreeTraversal<>();
		System.out.println("Pre-Order Traversal:");
		treeTraversal.preOrderTraversal(bTree.getRootNode(), queue);
		treeTraversal.printQueue(queue);
		System.out.println("Level list:");
		ArrayList<LinkedList<BTreeNode<Integer, String>>> result = obj.createLevelList(bTree.getRootNode());
		result.forEach(list->{
			System.out.println("-----------------");
			list.forEach(node->{
				System.out.print(node + "   ");
			});
			System.out.println("-----------------");
		});
	}

}
