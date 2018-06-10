package com.lrn.cci.likedlist;

public class Palindrome {

	public boolean checkPalindorme(final LinkedList.Node firstNode) {
		LinkedList.Node head, mid = null, secondHalfHead, previousToSlowPtr = null, slowPtr, fastPtr;
		head = firstNode;
		slowPtr = head;
		fastPtr = head;
		/*
		 * move fast pointer by +2 and slowPtr by +1 if list size is odd then fastPtr will not be NULL if list size is even then slowPrt will not NULL
		 */
		while ((fastPtr != null) && (fastPtr.getNext() != null)) {
			fastPtr = fastPtr.getNext().getNext();
			previousToSlowPtr = slowPtr;
			slowPtr = slowPtr.getNext();
		}
		if (fastPtr == null) {
			/* list size is even */
			mid = null;
			previousToSlowPtr.setNext(null);
			secondHalfHead = slowPtr;
		} else {
			/* List size is odd */
			mid = slowPtr;
			previousToSlowPtr.setNext(null);
			secondHalfHead = slowPtr.getNext();
		}

		secondHalfHead = reverseList(secondHalfHead);
		boolean result = compareList(head, secondHalfHead);
		secondHalfHead = reverseList(secondHalfHead);
		if (mid != null) {
			previousToSlowPtr.setNext(mid);
			mid.setNext(secondHalfHead);
		} else {
			previousToSlowPtr.setNext(secondHalfHead);
		}
		return result;
	}

	public boolean compareList(final LinkedList.Node head1, final LinkedList.Node head2) {
		LinkedList.Node node1 = head1;
		LinkedList.Node node2 = head2;
		while ((node1 != null) && (node2 != null)) {
			if (node1.getItem().equals(node2.getItem())) {
				node1 = node1.getNext();
				node2 = node2.getNext();
			} else {
				return false;
			}
		}
		/* if any list1 is greater then list 2 then below logic will capture such scenario */
		if ((node1 == null) && (node2 == null)) {
			return true;
		}
		return false;
	}

	public LinkedList.Node reverseList(final LinkedList.Node node) {
		LinkedList.Node currentNode = node, previousNode = null, nextNode = null;
		while (currentNode != null) {
			nextNode = currentNode.getNext();
			currentNode.setNext(previousNode);
			previousNode = currentNode;
			currentNode = nextNode;
		}
		return previousNode;
	}

	public static void main(final String[] args) {
		LinkedList lst = new LinkedList();
		lst.add("1").add("2").add("3");
		Palindrome palindrome = new Palindrome();
		LinkedList.printList(lst.getFirst());
		LinkedList.Node newHead = palindrome.reverseList(lst.getFirst());
		LinkedList.printList(newHead);
		LinkedList lst2 = new LinkedList();
		lst2.add("R").add("A").add("D").add("A").add("R");
		System.out.println("is RADAR palindrome :" + palindrome.checkPalindorme(lst2.getFirst()));
		lst2 = new LinkedList().add("a").add("b").add("a").add("a").add("b");
		System.out.println("is abaab palindrome :" + palindrome.checkPalindorme(lst2.getFirst()));
		lst2 = new LinkedList().add("b").add("a").add("a").add("b");
		System.out.println("is baab palindrome :" + palindrome.checkPalindorme(lst2.getFirst()));
		lst2.add("d");
		System.out.println("is baabd palindrome :" + palindrome.checkPalindorme(lst2.getFirst()));

	}

}
