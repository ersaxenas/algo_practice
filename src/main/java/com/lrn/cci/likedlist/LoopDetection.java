package com.lrn.cci.likedlist;

public class LoopDetection {

	/*
	 * 1. create two pointers slow and fast. slow iterates through list at +1 speed and fast runs at +2 speed. 2. if they collide/meet then there is a loop in the list. If not then
	 * there is no loop in the list. 3. if they collide/meet move slow pointer at the start of the list. Now move both slow and fast pointer by +1. The node at which they meet is
	 * the start of the loop.
	 */

	public void detectLoop(final LinkedList.Node head) {

		LinkedList.Node slowPointer = head, fastPointer = head;
		while ((fastPointer != null) && (fastPointer.getNext() != null)) {
			slowPointer = slowPointer.getNext();
			fastPointer = fastPointer.getNext().getNext();
			if (slowPointer.getItem().equals(fastPointer.getItem())) {
				break;
			}
		}

		if ((fastPointer == null) || (fastPointer.getNext() == null)) {
			System.out.println("loop not found in the list.");
		} else {
			System.out.println("loop detected in the list.");
			System.out.println("slow pointer is at " + slowPointer);
			System.out.println("fast pointer is at " + fastPointer);
			slowPointer = head;
			while (!slowPointer.getItem().equals(fastPointer.getItem())) {
				slowPointer = slowPointer.getNext();
				fastPointer = fastPointer.getNext();
			}
			System.out.println("Loop starts at node " + slowPointer);
		}
	}

	public static void main(final String[] args) {
		LinkedList lst = new LinkedList();
		for (int cnt = 0; cnt <= 30; cnt++) {
			lst.add(String.valueOf(cnt));
		}
		System.out.println("Test without loop");
		LoopDetection obj = new LoopDetection();
		obj.detectLoop(lst.getFirst());
		System.out.println("Test with loop");
		lst.getLast().setNext(lst.getFirst().getNext().getNext().getNext().getNext().getNext());
		obj.detectLoop(lst.getFirst());
	}

}
