package com.lrn.cci.stackandqueue;

public class QueueUsingStack {

	/* new elements go in the this stack */
	StackImplementation newElementsStack = new StackImplementation(10);
	/* Old elements stay in this stack */
	StackImplementation oldElementsStack = new StackImplementation(10);

	public void push(final Integer item) {
		System.out.println("Push :" + item);
		newElementsStack.push(item);
	}

	public Integer pop() {
		if (oldElementsStack.isEmpty()) {
			/* move elements from new stack to old stack in reverse order. */
			while (!newElementsStack.isEmpty()) {
				oldElementsStack.push(newElementsStack.pop());
			}
		}
		return oldElementsStack.pop();
	}

	public void enqueue(final Integer item) {
		push(item);
	}

	public Integer dequeue() {
		return pop();
	}

	public static void main(final String[] args) {
		QueueUsingStack queue = new QueueUsingStack();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		queue.enqueue(11);
		queue.enqueue(12);
		queue.enqueue(13);
		queue.enqueue(14);
		queue.enqueue(15);
		queue.enqueue(16);
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		queue.enqueue(17);
		queue.enqueue(18);
		queue.enqueue(19);
		queue.enqueue(20);
		queue.enqueue(21);
		queue.enqueue(22);
		queue.enqueue(23);
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
		System.out.println("Pop: " + queue.dequeue());
	}

}
