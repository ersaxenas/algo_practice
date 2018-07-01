package com.lrn.leetcode.amazon;

public class SplitLinkedListinParts725 {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode(final int x) { val = x; }
	}

	public ListNode[] splitListToParts(final ListNode root, final int k) {
		ListNode[] lstnode = new  ListNode[k];
		if(root==null) {
			return lstnode;
		}
		int N=0;
		ListNode currNode=root, prevNode=null;
		/*find the length of the list*/
		while(currNode!=null) {
			N++;
			currNode=currNode.next;
		}
		/*calculate sublist size*/
		int sublistsize = N/k;
		/*get the remainder*/
		int rem = N%k;
		currNode = root;
		/*create k list*/
		for(int lstIdx=0; lstIdx<k; lstIdx++) {
			lstnode[lstIdx] = currNode;
			/*add elements to the list and remainder to the list*/
			for(int cnt=0; cnt<(sublistsize+((rem>0)?1:0));cnt++) {
				prevNode = currNode;
				if(currNode!=null) {
					currNode = currNode.next;
				}
			}
			if(prevNode!=null) {
				prevNode.next = null;
			}
			rem--;
		}
		return lstnode;
	}

	public static void main(final String[] args) {

	}

}
