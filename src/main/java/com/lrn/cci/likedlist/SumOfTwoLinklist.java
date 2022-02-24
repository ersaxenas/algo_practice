package com.lrn.cci.likedlist;

public class SumOfTwoLinklist {

	public void sumTwoLists(final LinkedList lst1, final LinkedList lst2) {
		if (((lst1 == null) || (lst2 == null)) || (lst1.isEmpty() || lst2.isEmpty())) {
			return;
		}
		/* if lists are not of same size then add zeors */
		if (lst1.getSize() != lst2.getSize()) {
			if (lst1.getSize() > lst2.getSize()) {
				padList(lst1, lst1.getSize() - lst2.getSize());
			} else {
				padList(lst2, lst2.getSize() - lst1.getSize());
			}
		}
		LinkedList.printList(lst1.getFirst());
		LinkedList.printList(lst2.getFirst());
		LinkedList.Node head = sum(lst1.getFirst(), lst2.getFirst());
		/* case when last digit proa carry. */
		if (head.getCarry() != 0) {
			head.setItem(head.getCarry());
		} else {
			head = head.getNext();
		}
		LinkedList.printList(head);

	}

	public LinkedList.Node sum(final LinkedList.Node node1, final LinkedList.Node node2) {
		/* recursion: base case */
		if ((node1 == null) ) {
			LinkedList.Node node = new LinkedList.Node(0, null);
			node.setCarry(0);
			return node;
		}
		/* previous node from recursive call */
		LinkedList.Node node = sum(node1.getNext(), node2.getNext());
		/* create new node */
		LinkedList.Node newNode = new LinkedList.Node(0, node);
		/* sum with carry */
		Integer additionResult = (Integer) node1.getItem() + (Integer) node2.getItem() + node.getCarry();
		if (additionResult >= 10) {
			node.setItem(additionResult % 10);
			newNode.setCarry(1);
		} else {
			node.setItem(additionResult);
			newNode.setCarry(0);
		}
		return newNode;
	}

	public void padList(final LinkedList lst1, final int noOfZeros) {
		for (int cnt = 0; cnt < noOfZeros; cnt++) {
			lst1.add(new Integer(0));
		}
	}

	public static void main(final String[] args) {
		LinkedList lst1 = new LinkedList().add(9).add(0).add(6);
		LinkedList lst2 = new LinkedList().add(1).add(0).add(6);
		SumOfTwoLinklist obj = new SumOfTwoLinklist();
		obj.sumTwoLists(lst1, lst2);
	}

}
