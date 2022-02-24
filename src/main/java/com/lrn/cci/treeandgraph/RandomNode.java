package com.lrn.cci.treeandgraph;

import java.util.Random;

public class RandomNode {

	public static Random random = new Random();

	public BTreeNode<Integer, String> getRandomNode(final BTreeNode<Integer, String> node) {
		int rand = random.nextInt(node.getCount());
		/* if random number is less then size the go left */
		if (rand == 0) {
			return node;
		}
		/*go left*/
		if ((node.getLeftNode() != null) && ((rand >= 1) && (rand<= node.getLeftNode().getCount()))) {
			return getRandomNode(node.getLeftNode());
		} /* go right*/
		else {
			return getRandomNode(node.getRightNode());
		}
	}

	public static void main(final String[] args) {
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
		// random.ints(20, 1, 1000).forEach(num -> {
		// System.out.println("bst.put("+num+",\""+ String.valueOf(num) +"\");");
		// });
		bst.put(908, "908");
		bst.put(371, "371");
		bst.put(120, "120");
		bst.put(503, "503");
		bst.put(768, "768");
		bst.put(710, "710");
		bst.put(225, "225");
		bst.put(957, "957");
		bst.put(592, "592");
		bst.put(389, "389");
		bst.put(928, "928");
		bst.put(613, "613");
		bst.put(50, "50");
		bst.put(8, "8");
		bst.put(303, "303");
		bst.put(848, "848");
		bst.put(816, "816");
		bst.put(94, "94");
		bst.put(728, "728");
		bst.put(819, "819");

		RandomNode randNode = new RandomNode();
		for(int cnt = 0; cnt < 30; cnt++) {
			System.out.println(randNode.getRandomNode(bst.getRootNode()));
		}


	}

}
