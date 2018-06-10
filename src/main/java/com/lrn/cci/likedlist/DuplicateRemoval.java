package com.lrn.cci.likedlist;

public class DuplicateRemoval {

	public void removeDups(final LinkedList lst) {
		LinkedList.Node ptr1 = lst.getFirst(), ptr2 = null, tmp = null, prev = null;

		while ((ptr1 != null) && (ptr1.getNext() != null)) {
			ptr2 = ptr1;
			Object item = ptr1.getItem();
			while (ptr2.getNext() != null) {
				if (item.equals(ptr2.getNext().getItem())) {
					tmp = ptr2.getNext();
					ptr2.setNext(tmp.getNext());
					tmp.setNext(null);
				} else {
					ptr2 = ptr2.getNext();
				}
			}
			ptr1 = ptr1.getNext();
		}
	}

	public static void main(final String[] args) {
		LinkedList lst = new LinkedList();
		lst.add(10);
		lst.add(12);
		lst.add(11);
		lst.add(11);
		lst.add(12);
		lst.add(11);
		lst.add(10);
		lst.printList();
		DuplicateRemoval obj = new DuplicateRemoval();
		obj.removeDups(lst);
		lst.printList();
	}

}
