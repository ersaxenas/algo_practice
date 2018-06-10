package com.lrn.cci.likedlist;

public class IntersectionOfTwoLists {

	/*
	 * Solution 1. reverse the two linklist then traverse both the lists if first node matches then lists intersect and if they start to diverse will get intersection point (node
	 * before they start to diverge)
	 *
	 * Solution 2. a. Run through both the list and find length. If last node of both the list is same the lists intersect. b. compare the lengths of two lists. Now iterate through
	 * the longer list by the difference in the length of two lists. ex. list1len = 10 and list2len = 12 then iterate list2 by two elements. now both the list have same element
	 * left to iterate. 3. now start iterating both lists and stop where the node reference of both the lists are same.
	 */
	public void findIntersection(final LinkedList.Node head1, final LinkedList.Node head2) {

		/* implementing solution 2 */

		LinkedList.Node lastOfList1 = null, lastOfList2 = null, nodeOfList1 = head1, nodeOfList2 = head2;
		int lengthOfList1 = 0, lengthOfList2 = 0;
		while (true) {
			if (nodeOfList1 != null) {
				if (nodeOfList1.getNext() == null) {
					lastOfList1 = nodeOfList1;
				}
				nodeOfList1 = nodeOfList1.getNext();
				lengthOfList1++;
			}
			if (nodeOfList2 != null) {
				if (nodeOfList2.getNext() == null) {
					lastOfList2 = nodeOfList2;
				}
				nodeOfList2 = nodeOfList2.getNext();
				lengthOfList2++;
			}
			if ((nodeOfList1 == null) && (nodeOfList2 == null)) {
				break;
			}
		}
		System.out.println("Length of list1 is " + lengthOfList1 + " and last nod is " + lastOfList1);
		System.out.println("Length of list2 is " + lengthOfList2 + " and last nod is " + lastOfList2);
		if (lastOfList1.getItem() == lastOfList2.getItem()) {
			System.out.println("List are intersecting.");
			nodeOfList1 = head1;
			nodeOfList2 = head2;
			if (lengthOfList1 > lengthOfList2) {
				int cnt = lengthOfList1 - lengthOfList2;
				while (cnt != 0) {
					nodeOfList1 = nodeOfList1.getNext();
					cnt--;
				}
			} else {
				int cnt = lengthOfList2 - lengthOfList1;
				while (cnt != 0) {
					nodeOfList2 = nodeOfList2.getNext();
					cnt--;
				}
			}
			while (nodeOfList1.getItem() != nodeOfList2.getItem()) {
				nodeOfList1 = nodeOfList1.getNext();
				nodeOfList2 = nodeOfList2.getNext();
			}

			System.out.println("Intersection is at node :" + nodeOfList1);

		} else {
			System.out.println("Lists dont intersect");
		}

	}

	public static void main(final String[] args) {
		LinkedList list1 = new LinkedList();
		list1.add("1").add("2").add("3").add("4").add("10").add("11").add("12");
		LinkedList list2 = new LinkedList();
		list2.add("5").add("6").add("7").add("10").add("11").add("12");
		IntersectionOfTwoLists obj = new IntersectionOfTwoLists();
		obj.findIntersection(list1.getFirst(), list2.getFirst());
	}

}
