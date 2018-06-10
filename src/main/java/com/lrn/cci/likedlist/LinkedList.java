package com.lrn.cci.likedlist;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class LinkedList {

	private Node first = null;
	private Node last = null;
	private int size = 0;

	/**
	 * Function adds an element at the end of the listl
	 *
	 * @param elem
	 */
	public LinkedList add(final Object elem) {
		if (first == null) {
			/* create first node */
			Node node = new Node(elem, null);
			first = node;
			last = node;
			size++;
		} else {
			/* append to the end of the list */
			Node node = new Node(elem, null);
			last.next = node;
			last = node;
			size++;
		}
		return this;
	}

	/**
	 * Removes an element from the list
	 *
	 * @param elem
	 * @return True / False
	 */
	public boolean remove(final Object elem) {
		/* if node is last node then set the */
		boolean result = false;
		/* if list is empty return false */
		if (!isEmpty()) {
			Node node = first;
			Node prev = null;
			Node tmp = null;
			while (node != null) {
				/* iterate over list */
				if (node.item.equals(elem)) {/* found match */
					if (node.next == null) {/* if last node */
						prev.next = null;
						last = prev;
					} else {
						/* copy next node to current node and make current node next to null */
						tmp = node.next;
						/* copy data */
						node.item = tmp.item;
						/* bypass tmp(next item) */
						node.next = tmp.next;
						/* nullify tmp next */
						tmp.next = null;
					}
					size--;
				}
				/**/
				prev = node;
				node = node.next;
			}
		}
		return result;
	}

	/**
	 * Return true if queue is empty else false
	 *
	 * @return true / false;
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns true if list not empty else false
	 *
	 * @return true / false
	 */
	public boolean isNotEmpty() {
		return first != null;
	}

	/**
	 * Function adds elements of the list to queue and returns its iterator.
	 *
	 * @return Iterator
	 */
	public Iterator iterator() {
		Queue queue = new ArrayDeque();
		if (isNotEmpty()) {
			Node node = first;
			while (node != null) {
				queue.add(node.item);
				node = node.next;
			}
		}
		return queue.iterator();
	}

	/**
	 * Function prints the content of the list.
	 */
	public void printList() {
		if (isNotEmpty()) {
			StringBuilder str = new StringBuilder();
			Node node = first;
			while (node != null) {
				str.append(node.item.toString()).append("->");
				node = node.next;
			}
			System.out.println(str.toString());
		} else {
			System.out.println("List is empty");
		}
	}

	public static void printList(final LinkedList.Node head) {
		if (head != null) {
			StringBuilder str = new StringBuilder();
			Node node = head;
			while (node != null) {
				str.append(node.item.toString()).append("->");
				node = node.next;
			}
			System.out.println(str.toString());
		} else {
			System.out.println("List is empty");
		}
	}

	/**
	 * Function gets an element at given index.
	 *
	 * @param index
	 * @return
	 */
	public Object getElement(final int index) {
		if (isEmpty()) {
			return null;
		}
		Object elem = null;
		if ((index > 0) && (index <= size)) {
			Node node = first;
			int curr = 1;
			while (node != null) {
				if (curr == index) {
					elem = node.item;
					break;
				}
				curr++;
				node = node.next;
			}
		}
		return elem;
	}

	/**
	 * Function removes element at given index.
	 *
	 * @param index
	 * @return
	 */
	public boolean remove(final int index) {
		boolean result = true;
		int cnt = 1;
		if (isNotEmpty()) {
			Node node = first;
			Node tmp = null, prev = null;
			while (node != null) {
				if (cnt == index) {
					if (node.next == null) {
						prev.next = null;
						last = prev;
					} else {
						tmp = node.next;
						node.item = tmp.item;
						node.next = tmp.next;
						tmp.next = null;
					}
					/* don't forget to update the size */
					size--;
				}
				prev = node;
				node = node.next;
				cnt++;
			}
		}
		return result;
	}

	public int getSize() {
		return size;
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(final Node first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(final Node last) {
		this.last = last;
	}

	public static class Node {
		private Object item;
		private Node next;
		/* for sum of two list problem */
		private int carry;

		@Override
		public String toString() {
			return "Node [item=" + item + ", next->" + ((next != null) ? next.item : "null") + "]";
		}

		public Node(final Object item, final Node node) {
			this.item = item;
			this.next = node;
		}

		public Object getItem() {
			return item;
		}

		public void setItem(final Object item) {
			this.item = item;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(final Node next) {
			this.next = next;
		}

		public int getCarry() {
			return carry;
		}

		public void setCarry(final int carry) {
			this.carry = carry;
		}
	}

	public static void main(final String[] args) {
		LinkedList list = new LinkedList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.remove("3");
	}
}
