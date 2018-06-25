package com.lrn.leetcode.amazon;

public class AddTwoNumbers3 {

	/*You are given two non-empty linked lists representing two non-negative integers.
	 * The digits are stored in reverse order and each of their nodes contain a single digit.
	 * Add the two numbers and return it as a linked list.
	 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 * Explanation: 342 + 465 = 807.
	 */

	public static class ListNode {
		int val;
		ListNode next;
		ListNode(final int x) { val = x; }
		ListNode(final int x, final ListNode node) { val = x; next= node;}
	}

	public ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {
		return addTwoNumbers(l1, l2,0);
	}

	public ListNode addTwoNumbers(final ListNode l1, final ListNode l2, final int carry) {
		if((l1==null) && (l2==null) && (carry ==0)) {
			return null;
		}
		if((l1==null) && (l2==null) && (carry !=0)) {
			return new ListNode(carry);
		}
		int sum = 0, newCarry=0;
		ListNode node=null, next1=null, next2=null;
		if(l1!=null) {
			sum= sum + l1.val;
			next1 = l1.next;
		}
		if(l2!=null) {
			sum= sum + l2.val;
			next2 = l2.next;
		}
		sum = sum + carry;
		if(sum >=10) {
			sum = sum%10;
			newCarry =1;
		}
		node = new ListNode(sum);
		node.next = addTwoNumbers(next1, next2, newCarry);
		return node;
	}

	public static void main(final String[] args) {
		AddTwoNumbers3 obj = new AddTwoNumbers3();
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(4);
		ListNode node3 = new ListNode(3);
		node1.next = node2;
		node2.next= node3;
		ListNode node12 = new ListNode(5);
		ListNode node13 = new ListNode(6);
		ListNode node14 = new ListNode(4);
		node12.next = node13;
		node12.next = node14;
		ListNode node = obj.addTwoNumbers(node1, node12);
		System.out.println(node.val);
		//		[2,4,3]
		//				[5,6,4]
	}


}
