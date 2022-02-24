package com.lrn.cci.stackandqueue;

public class StackImplementationMin {

	private StackNode<Integer> top;
	private final int allocatedSize;
	private int currentSize = 0;

	public StackImplementationMin(final int size) {
		this.allocatedSize = size;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean isFull() {
		return currentSize >= allocatedSize;
	}

	public int push(final Integer item) {
		if (isFull()) {
			System.out.println("Stack is full. Current size: " + currentSize + " and allocated size: " + allocatedSize);
			return currentSize;
		}
		StackNode<Integer> node = new StackNode<>();
		node.setItem(item);
		node.setMinElem(item);
		if (top == null) {
			top = node;
		} else {
			node.setNext(top);
			/* if want to track minimum element */
			if (node.getItem() < top.getMinElem()) {
				node.setMinElem(item);
			} else {
				node.setMinElem(top.getMinElem());
			}
			top = node;

		}
		currentSize++;
		return currentSize;
	}

	public Integer pop() {
		if (isEmpty()) {
			System.out.println("Stack is full. Current size: " + currentSize + " and allocated size: " + allocatedSize);
			return null;
		}
		StackNode<Integer> node = top;
		top = top.getNext();
		node.setNext(null);
		currentSize--;
		return node.getItem();
	}

	public Integer peek() {
		if (isEmpty()) {
			System.out.println("Stack is full. Current size: " + currentSize + " and allocated size: " + allocatedSize);
			return null;
		}
		return top.getItem();
	}

	public Integer getMin() {
		if (isEmpty()) {
			System.out.println("Stack is full. Current size: " + currentSize + " and allocated size: " + allocatedSize);
			return null;
		}
		return top.getMinElem();
	}

	public static void main(final String[] args) {
		StackImplementationMin stack = new StackImplementationMin(10);
		stack.push(20);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(44);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(23);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(65);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(13);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(65);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(7);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(28);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(87);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(17);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		stack.push(11);
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());

		System.out.println("Pop:" + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop:" + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek() + ", min: " + stack.getMin());

	}

}
