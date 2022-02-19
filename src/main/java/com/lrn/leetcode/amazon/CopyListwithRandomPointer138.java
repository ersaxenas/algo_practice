package com.lrn.leetcode.amazon;

import java.util.HashMap;
import java.util.Map;

public class CopyListwithRandomPointer138 {

	class RandomListNode {
		int label;
		RandomListNode next;
		RandomListNode random;
		RandomListNode(final int x) { this.label = x; }
	};

	public RandomListNode copyRandomList(final RandomListNode head) {
		if(head == null) {
			return null;
		}
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode node = head;
		while(node!= null) {
			/*make a copy each node */
			map.put(node, new RandomListNode(node.label));
			/*move to next node*/
			node = node.next;
		}

		/*Once again iterate and restore links*/
		node = head;
		while(node!=null) {
			/*copy ndoe.next link*/
			map.get(node).next = map.get(node.next);
			/*copy node.random node link*/
			map.get(node).random = map.get(node.random);
			/*move to next node*/
			node = node.next;
		}
		return map.get(head);
	}



	public static void main(final String[] args) {

	}

}
