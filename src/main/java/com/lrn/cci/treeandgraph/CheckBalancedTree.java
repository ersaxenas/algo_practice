package com.lrn.cci.treeandgraph;

public class CheckBalancedTree {

	int getHeight(final BTreeNode<Integer, String> rootNode) {
		if (rootNode == null) { // base case
			return -1;
		}
		/* recursive case */
		return Math.max(getHeight(rootNode.getLeftNode()), getHeight(rootNode.getRightNode())) + 1;
	}

	boolean isBalanced(final BTreeNode<Integer, String> rootNode) {
		/* base case */
		if (rootNode == null) {
			return true;
		}
		int leftHeight = getHeight(rootNode.getLeftNode());
		int rightHeight = getHeight(rootNode.getRightNode());
		if ((leftHeight - rightHeight) > 1) {
			return false;
		} else {
			/* recursive case */
			return isBalanced(rootNode.getLeftNode()) && isBalanced(rootNode.getRightNode());
		}
	}

	public static void main(final String[] args) {
		CheckBalancedTree obj = new CheckBalancedTree();
		System.out.println(obj.isBalanced(TreeUtil.getTreeOfIntAndString(10).getRootNode()));
		System.out.println(obj.isBalanced(TreeUtil.getTreeOfIntAndString(3).getRootNode()));
	}
}
