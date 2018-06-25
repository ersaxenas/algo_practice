package com.lrn.cci.treeandgraph;

public class CommonAncestor {

	/* approach 1 if link to parent exists*/

	public int findDepth(final BTreeNode<Integer, Integer> node) {
		/*traverse up to root node parent == null*/

		int depth = 0;
		BTreeNode<Integer, Integer> currentNode = node;
		while(currentNode != null) {
			currentNode = currentNode.getParent();
			depth++;
		}
		return depth;
	}

	public BTreeNode<Integer, Integer> moveNodeUp(final BTreeNode<Integer, Integer> node, final int moveBy) {
		BTreeNode<Integer, Integer> currentNode = node;
		int cnt = moveBy;
		while(cnt > 0) {
			currentNode = node.getParent();
			cnt--;
		}
		return currentNode;
	}

	public BTreeNode<Integer, Integer>  findCommonAncestor(final BTreeNode<Integer, Integer> root, final BTreeNode<Integer, Integer> node1, final BTreeNode<Integer, Integer> node2) {
		int diff = findDepth(node1) - findDepth(node2);
		BTreeNode<Integer, Integer> nodea, nodeb;
		/*assign deeper node to nodea*/
		if(diff<1) {
			nodea = node2; /*a = big tree*/
			nodeb = node1; /*b = small tree*/
			diff = diff * -1;
		} else {
			nodea = node1;
			nodeb = node2;
		}
		/*Move deeper node up by difference*/
		if(diff != 0) {
			nodea = moveNodeUp(nodea, diff);
		}
		/*move both the nodes up and check if parent is common*/
		while((nodea!=null) || (nodeb != null)) {
			if(nodea == nodeb) {
				break;
			}
			nodea = nodea.getParent();
			nodeb = nodeb.getParent();
		}
		return ((nodea == null) || (nodeb == null)) ? null: nodea;
	}

	/*approach 2 if parent is not available*/

	public boolean findNode(final BTreeNode<Integer, Integer> node, final BTreeNode<Integer, Integer> nodeTofind) {
		if(node == null) {
			return false;
		}
		BTreeNode<Integer, Integer> currentNode = node;
		if(currentNode == nodeTofind) {
			return true;
		}
		return findNode(currentNode.getLeftNode(), nodeTofind) || findNode(currentNode.getRightNode(), nodeTofind);
	}


	public BTreeNode<Integer, Integer>  findCommonAncestor2(final BTreeNode<Integer, Integer> root, final BTreeNode<Integer, Integer> node1, final BTreeNode<Integer, Integer> node2) {
		/*if both nodes are in left side tree*/
		if(findNode(root.getLeftNode(), node1) && findNode(root.getLeftNode(), node2)) {
			return findCommonAncestor2(root.getLeftNode(), node1, node2);
		}
		if(findNode(root.getRightNode(), node1) && findNode(root.getRightNode(), node2)) {
			return findCommonAncestor2(root.getRightNode(), node1, node2);
		}
		return root;
	}
}
