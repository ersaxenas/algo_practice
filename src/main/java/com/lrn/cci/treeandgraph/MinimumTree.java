package com.lrn.cci.treeandgraph;

public class MinimumTree {

	public BTreeNode<Integer, Integer> createMinTree(final int array[]) {
		return createMinTree(array, 0, array.length-1);
	}

	public  BTreeNode<Integer, Integer> createMinTree(final int array[], final int start, final int end) {
		BTreeNode<Integer, Integer> node = new BTreeNode<Integer, Integer>();
		if(start > end) {
			return null;
		}
		int mid = (start+end)/2;
		node.setKey(array[mid]);
		node.setVal(array[mid]);

		node.setLeftNode(createMinTree(array, start, mid-1));
		node.setRightNode(createMinTree(array, mid+1, end));
		return node;
	}

	public static void main(final String[] args) {
		MinimumTree minTree = new MinimumTree();
		int array[] = {1,2,3,4,5,6,7};
		BTreeNode<Integer, Integer> rootNode = minTree.createMinTree(array);
		System.out.println("min tree created");
	}

}
