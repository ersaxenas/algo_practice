package com.lrn.cci.stackandqueue;

import java.util.ArrayList;

public class StackImplementation {

	private StackNode<Integer> top;
	private final int allocatedSize;
	private int currentSize = 0;

	public StackImplementation(final int size) {
		this.allocatedSize = size;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean isFull() {
		return currentSize >= allocatedSize;
	}

	public boolean push(final Integer item) {
		if (isFull()) {
			System.out.println("Stack is full. Current size: " + currentSize + " and allocated size: " + allocatedSize);
			return false;
		}
		StackNode<Integer> node = new StackNode<>();
		node.setItem(item);
		if (top == null) {
			top = node;
		} else {
			node.setNext(top);
			top = node;
		}
		currentSize++;
		return true;
	}

	public Integer pop() {
		if (isEmpty()) {
			System.out.println("Stack is empty. Current size: " + currentSize + " and allocated size: " + allocatedSize);
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
			System.out.println("Stack is empty. Current size: " + currentSize + " and allocated size: " + allocatedSize);
			return null;
		}
		return top.getItem();
	}

	public Integer popBottom() {
		if (isEmpty()) {
			System.out.println("Stack is empty. Current size: " + currentSize + " and allocated size: " + allocatedSize);
			return null;
		}
		if (currentSize == 1) {
			return pop();
		}
		StackNode<Integer> stackNode = top;
		while (stackNode.getNext().getNext() != null) {
			stackNode = stackNode.getNext();
		}
		StackNode<Integer> stackNodeFirst = stackNode.getNext();
		stackNode.setNext(null);
		currentSize--;
		return stackNodeFirst.getItem();
	}

	public void printStack() {
		if (isEmpty()) {
			System.err.println("Stack is empty");
		}
		ArrayList<Integer> lst = new ArrayList<>();
		StackNode<Integer> node = top;
		while (node != null) {
			lst.add(node.getItem().intValue());
			node = node.getNext();
		}
		System.out.println("Stack : " + lst);
	}

	public static void main(final String[] args) {
		StackImplementation stack = new StackImplementation(10);
		stack.push(20);
		System.out.println("Peek:" + stack.peek());
		stack.push(44);
		System.out.println("Peek:" + stack.peek());
		stack.push(23);
		System.out.println("Peek:" + stack.peek());
		stack.push(65);
		System.out.println("Peek:" + stack.peek());
		stack.push(13);
		System.out.println("Peek:" + stack.peek());
		stack.push(65);
		System.out.println("Peek:" + stack.peek());
		stack.push(7);
		System.out.println("Peek:" + stack.peek());
		stack.push(28);
		System.out.println("Peek:" + stack.peek());
		stack.push(87);
		System.out.println("Peek:" + stack.peek());
		stack.push(17);
		System.out.println("Peek:" + stack.peek());
		stack.push(11);
		System.out.println("Peek:" + stack.peek());

		System.out.println("Pop:" + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop:" + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Peek:" + stack.peek());

		System.out.println("pop bottom : " + stack.popBottom());

		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println("pop bottom : " + stack.popBottom());
		System.out.println("pop bottom : " + stack.popBottom());
		System.out.println("pop bottom : " + stack.popBottom());
		System.out.println("pop bottom : " + stack.popBottom());
		System.out.println("pop bottom : " + stack.popBottom());

	}

}
