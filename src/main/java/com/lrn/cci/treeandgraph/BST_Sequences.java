package com.lrn.cci.treeandgraph;

import java.util.ArrayList;
import java.util.LinkedList;

public class BST_Sequences {

	public static void weaveLists(final LinkedList<Integer> first, final LinkedList<Integer> second, final ArrayList<LinkedList<Integer>> results, final LinkedList<Integer> prefix) {
		/* One list is empty. Add the remainder to [a cloned] prefix and
		 * store result. */
		if ((first.size() == 0) || (second.size() == 0)) {
			/*remember to clone prefix*/
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
			result.addAll(first);
			result.addAll(second);
			results.add(result);
			return;
		}

		/* Recurse with head of first added to the prefix. Removing the
		 * head will damage first, so we’ll need to put it back where we
		 * found it afterwards. */
		/*remove head from first*/
		int headFirst = first.removeFirst();
		/*append to prefix*/
		prefix.addLast(headFirst);
		/*recurse*/
		weaveLists(first, second, results, prefix);
		/*undo : remove first's head from prefix */
		prefix.removeLast();
		/*undo : put it back on first*/
		first.addFirst(headFirst);

		/* Do the same thing with second, damaging and then restoring
		 * the list.*/
		/*remove head from second*/
		int headSecond = second.removeFirst();
		/*add to head to prefix*/
		prefix.addLast(headSecond);
		/*recurse*/
		weaveLists(first, second, results, prefix);
		/*undo: remove second's head from the prefix*/
		prefix.removeLast();
		/*undo : put it back on prefix*/
		second.addFirst(headSecond);
	}

	public static ArrayList<LinkedList<Integer>> allSequences(final TreeNode node) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

		if (node == null) {
			result.add(new LinkedList<Integer>());
			return result;
		}

		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.data);

		/* Recurse on left and right subtrees. */
		ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
		ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

		/* Weave together each list from the left and right sides. */
		for (LinkedList<Integer> left : leftSeq) {
			for (LinkedList<Integer> right : rightSeq) {
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
				weaveLists(left, right, weaved, prefix);
				result.addAll(weaved);
			}
		}
		return result;
	}

	public static void main(final String[] args) {
		TreeNode node = new TreeNode(2);
		//int[] array = {100, 50, 20, 75, 150, 120, 170};
		int[] array = {1, 3};
		for (int a : array) {
			node.insertInOrder(a);
		}
		ArrayList<LinkedList<Integer>> allSeq = allSequences(node);
		for (LinkedList<Integer> list : allSeq) {
			System.out.println(list);
		}
		System.out.println(allSeq.size());
	}




	static class TreeNode {
		public int data;
		public TreeNode left;
		public TreeNode right;
		public TreeNode parent;
		private int size = 0;

		public TreeNode(final int d) {
			data = d;
			size = 1;
		}

		private void setLeftChild(final TreeNode left) {
			this.left = left;
			if (left != null) {
				left.parent = this;
			}
		}

		private void setRightChild(final TreeNode right) {
			this.right = right;
			if (right != null) {
				right.parent = this;
			}
		}

		public void insertInOrder(final int d) {
			if (d <= data) {
				if (left == null) {
					setLeftChild(new TreeNode(d));
				} else {
					left.insertInOrder(d);
				}
			} else {
				if (right == null) {
					setRightChild(new TreeNode(d));
				} else {
					right.insertInOrder(d);
				}
			}
			size++;
		}

		public int size() {
			return size;
		}

		public boolean isBST() {
			if (left != null) {
				if ((data < left.data) || !left.isBST()) {
					return false;
				}
			}

			if (right != null) {
				if ((data >= right.data) || !right.isBST()) {
					return false;
				}
			}

			return true;
		}

		public int height() {
			int leftHeight = left != null ? left.height() : 0;
			int rightHeight = right != null ? right.height() : 0;
			return 1 + Math.max(leftHeight, rightHeight);
		}

		public TreeNode find(final int d) {
			if (d == data) {
				return this;
			} else if (d <= data) {
				return left != null ? left.find(d) : null;
			} else if (d > data) {
				return right != null ? right.find(d) : null;
			}
			return null;
		}

		private TreeNode createMinimalBST(final int arr[], final int start, final int end){
			if (end < start) {
				return null;
			}
			int mid = (start + end) / 2;
			TreeNode n = new TreeNode(arr[mid]);
			n.setLeftChild(createMinimalBST(arr, start, mid - 1));
			n.setRightChild(createMinimalBST(arr, mid + 1, end));
			return n;
		}

		public TreeNode createMinimalBST(final int array[]) {
			return createMinimalBST(array, 0, array.length - 1);
		}


	}

}



