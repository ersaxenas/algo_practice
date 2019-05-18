package com.lrn.cci.treeandgraph;

public class CheckSubtree {

	public boolean checkSubtree(final BTreeNode<Integer, String> tree1, final BTreeNode<Integer, String> tree2) {
		if ((tree1 == null) || (tree2 == null)) {
			return false;
		}
		return subTree(tree1, tree2);
	}

	public boolean subTree(final BTreeNode<Integer, String> tree1, final BTreeNode<Integer, String> tree2) {
		/*base case 1*/
		if ((tree1 != null) && tree2.getKey().equals(tree1.getKey())) {
			/* tree1 and tree2 are at same node */
			return matchTree(tree1, tree2);
		}
		/*base case 2*/
		if(tree1 == null) {
			return false;
		}
		/*recursive case*/
		return subTree(tree1.getLeftNode(), tree2) || subTree(tree1.getRightNode(), tree2);
	}

	public boolean matchTree(final BTreeNode<Integer, String> tree1, final BTreeNode<Integer, String> tree2) {
		/*base case 1*/
		if ((tree1 == null) && (tree2 == null)) {
			return true;
		}
		/*base case 2*/
		/*different height - not same tree*/
		if ((tree1 == null) || (tree2 == null)) {
			return false;
		}
		/*base case 3*/
		/* if keys don't match then return false */
		if (!tree1.getKey().equals(tree2.getKey())) {
			return false;
		}
		/*recursive case*/
		/* next : match child nodes */
		return matchTree(tree1.getLeftNode(), tree2.getLeftNode()) && matchTree(tree1.getRightNode(), tree2.getRightNode());
	}

	public static void main(final String[] args) {
		BinarySearchTree<Integer, String> btree = TreeUtil.getTreeOfIntAndString(15);

		BTreeNode<Integer, String> rootNode1 = btree.getRootNode();
		BTreeNode<Integer, String> rootNode2 = btree.getRootNode();
		int cnt = 0;
		while (cnt < 2) {
			if (rootNode2.getRightNode() != null) {
				rootNode2 = rootNode2.getRightNode();
			} else if (rootNode2.getLeftNode() != null) {
				rootNode2 = rootNode2.getLeftNode();
			}
			cnt++;
		}
		CheckSubtree checkSubTree = new CheckSubtree();
		System.out.println(checkSubTree.checkSubtree(rootNode1, rootNode2));

		BinarySearchTree<Integer, String> btree2 = TreeUtil.getTreeOfIntAndString(5);
		System.out.println(checkSubTree.checkSubtree(rootNode1, btree2.getRootNode()));
	}

}
