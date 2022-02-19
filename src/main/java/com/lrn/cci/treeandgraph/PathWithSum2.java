package com.lrn.cci.treeandgraph;

import java.util.ArrayList;
import java.util.List;

public class PathWithSum2 {
	/*leet code*/

	List<List<Integer>> listOfList = new ArrayList<>();
	public List<List<Integer>> pathSum(final TreeNode root, final int sum) {
		pathWithSumFromNode(root, new ArrayList<>(), sum	, 0);
		return listOfList;
	}

	public void pathWithSumFromNode(final TreeNode node, final ArrayList<Integer> nodeBuffer, final int targetSum, final int currentSum) {
		if(node==null ) {
			return;
		}
		/*add current node key/data to list*/
		int currentNodeVal = node.val;
		nodeBuffer.add(currentNodeVal);
		/*check target sum*/
		if(((node.left ==null) && (node.right == null))  && (targetSum ==(currentSum + currentNodeVal))) {
			/*sum found*/
			listOfList.add(nodeBuffer);
			return;
		}
		ArrayList<Integer> nodeBufferLeft =  (ArrayList<Integer>) nodeBuffer.clone();
		ArrayList<Integer> nodeBufferRight =  (ArrayList<Integer>) nodeBuffer.clone();
		nodeBuffer.clear();

		pathWithSumFromNode(node.left, nodeBufferLeft, targetSum, currentSum + currentNodeVal);
		pathWithSumFromNode(node.right, nodeBufferRight, targetSum, currentSum + currentNodeVal);
	}


	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(final int x) { val = x; }
	}

	public static void main(final String[] args) {
		PathWithSum2 obj = new PathWithSum2();
		TreeNode node = new TreeNode(1);
		//node.right = new TreeNode(2);
		obj.pathSum(node, 1).forEach(list -> {
			list.forEach(num-> System.out.println(num));
		});
	}
}









