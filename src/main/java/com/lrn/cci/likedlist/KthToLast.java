package com.lrn.cci.likedlist;

public class KthToLast {

	public Object returnKthElementToLast(final LinkedList list, final int k) {
		if (k > list.getSize()) {
			return null;
		}
		LinkedList.Node ptr1, ptr2;
		ptr1 = list.getFirst();
		ptr2 = list.getFirst();
		/* start loop with 1 instead of 0 */
		for (int cnt = 1; cnt < k; cnt++) {
			if (ptr1.getNext() != null) {
				ptr1 = ptr1.getNext();
			}
		}
		if (ptr1.getItem() == null) {
			return ptr1.getItem();
		}
		while (ptr1.getNext() != null) {
			ptr1 = ptr1.getNext();
			ptr2 = ptr2.getNext();
		}
		return ptr2.getItem();

	}

	public static void main(final String[] args) {
		LinkedList list = new LinkedList();
		list.add("1").add("2").add("3").add("4").add("5");
		KthToLast kthToLast = new KthToLast();
		list.printList();
		System.out.println(kthToLast.returnKthElementToLast(list, 2));
		System.out.println(kthToLast.returnKthElementToLast(list, 1));
		System.out.println(kthToLast.returnKthElementToLast(list, 5));
		System.out.println(kthToLast.returnKthElementToLast(list, 4));
	}

}
