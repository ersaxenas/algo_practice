package com.lrn.cci.stackandqueue;

public class QueueImpl<T> {

	private StackNode<T> firstNode;
	private StackNode<T> lastNode;

	public void enqueue(final T item) {
		System.out.println("Enqueue :" + item);
		StackNode<T> node = new StackNode<>();
		node.setItem(item);
		if ((firstNode == null) || (lastNode == null)) {
			firstNode = node;
			lastNode = node;
		} else {
			lastNode.setNext(node);
			lastNode = node;
		}
	}

	public T dequque() {
		if (firstNode == null) {
			System.err.println("queue is empty");
			return null;
		}
		StackNode<T> tmp = firstNode;
		T item = tmp.getItem();
		if (tmp.getNext() == null) {
			System.err.println("Last item in the queue " + item);
			firstNode = null;
			lastNode = null;
		} else {
			firstNode = tmp.getNext();
		}
		tmp.setNext(null);
		System.err.println("Dequeue :" + item);
		return item;
	}

	public T peek() {
		if (firstNode == null) {
			System.err.println("queue is empty");
			return null;
		}
		return firstNode.getItem();

	}

	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}

	public static void main(final String[] args) {
		QueueImpl<Integer> queue = new QueueImpl<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		queue.enqueue(11);
		queue.enqueue(12);
		queue.enqueue(13);
		queue.dequque();
		queue.enqueue(14);
		queue.enqueue(15);
		queue.enqueue(16);
		queue.enqueue(17);
		queue.enqueue(18);
		queue.enqueue(19);
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
		queue.dequque();
	}

}
