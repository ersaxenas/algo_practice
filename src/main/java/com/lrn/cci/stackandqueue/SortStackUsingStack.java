package com.lrn.cci.stackandqueue;

public class SortStackUsingStack {
	StackImplementation primaryStack = new StackImplementation(100);
	StackImplementation secondaryStack = new StackImplementation(100);

	public Integer pop() {
		return primaryStack.pop();
	}

	public Integer peek() {
		return primaryStack.peek();
	}

	public boolean isEmpty() {
		return primaryStack.isEmpty();
	}

	public boolean isFull() {
		return primaryStack.isFull();
	}

	public void push(final Integer item) {
		if (primaryStack.isFull()) {
			System.out.println("stack is full");
		} else if (primaryStack.isEmpty()) {
			primaryStack.push(item);
		} else {
			while (!primaryStack.isEmpty()) {
				Integer top = primaryStack.peek();
				/* if item is greater then element on stack move top to secondary else stop */
				if (item > top) {
					secondaryStack.push(primaryStack.pop());
				} else {
					break;
				}
			}
			secondaryStack.push(item);
			while (!secondaryStack.isEmpty()) {
				primaryStack.push(secondaryStack.pop());
			}
		}
		primaryStack.printStack();
	}

	public static void main(final String[] args) {
		SortStackUsingStack obj = new SortStackUsingStack();
		obj.push(10);
		obj.push(5);
		obj.push(50);
		obj.push(30);
		obj.push(86);
		obj.push(345);
		obj.push(34);
		obj.push(87);
		obj.push(23);
		obj.push(54);
		obj.push(214);
		obj.push(84);
		obj.push(95);
		obj.push(36);
		obj.push(15);
		obj.push(75);
		obj.push(46);
		obj.push(45);
		obj.push(63);
		obj.push(27);
		obj.push(10);
		obj.push(92);
		obj.push(84);
		obj.push(67);
		obj.push(28);
	}

}
