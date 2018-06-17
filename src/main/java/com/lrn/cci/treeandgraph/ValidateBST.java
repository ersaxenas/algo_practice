package com.lrn.cci.treeandgraph;

public class ValidateBST {

	/*if btree doesnot have duplicate nodes.
	 * simply get inorder nodes and then check if returned list is sorted.
	 * if every element is less then its next element then it is a btree.
	 * left node < current node < right node
	 * */

	/*if tree can contain duplicate adjacent nodes.
	 * left node <= current node < right node
	 * min       <= data
	 *              data         < max
	 *
	 * */

	public boolean checkIfBtree(final BinarySearchTree<Integer, Integer> tree) {
		return checkIfBst(tree.getRootNode()	, null, null);
	}

	public boolean checkIfBst(final BTreeNode<Integer, Integer> node, final Integer min, final Integer max) {
		System.out.println("key : "+ ((node == null)? "null": node.getKey()) + " min :" + ((min == null)? "null": min) + " max: " + ((max == null)? "null": max));
		/*base case 1*/
		if(node == null) {
			return true;
		}
		/*base case 2*/
		if(((min!= null) && (node.getKey() <= min)) || ((max!=null) && (max < node.getKey()))) {
			return false;
		}
		/*recursive case*/
		if(!checkIfBst(node.getLeftNode(), min, node.getKey()) || !checkIfBst(node.getRightNode(), node.getKey(), max)) {
			return false;
		}
		return true;
	}
	public static void main(final String[] args) {
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
		bst.put(20, 20);
		bst.put(10, 10);
		bst.put(30, 30);
		bst.put(5, 5);
		bst.put(15, 15);
		bst.put(3, 3);
		bst.put(7, 7);
		bst.put(17, 17);
		ValidateBST obj = new ValidateBST();
		obj.checkIfBtree(bst);

	}


}
