package com.lrn.cci.stackandqueue;

import java.util.ArrayList;

public class StackOfPlates {

	private final ArrayList<StackImplementation> stackOfStacks = new ArrayList<>();
	Integer sizeOfStack = 3;
	StackImplementation stack;
	Integer topStackIntex = 0;

	public StackOfPlates() {
		stack = new StackImplementation(3);
		stackOfStacks.add(stack);
		topStackIntex = 0;
	}

	public void push(final Integer item) {
		if (stack.isFull()) {
			addNewStack();
			stack.push(item);
			System.out.println("Element " + item + " pushed to stack " + topStackIntex);
		} else {
			stack.push(item);
			System.out.println("Element " + item + " pushed to stack " + topStackIntex);
		}
	}

	private void addNewStack() {
		System.out.println("add new stack");
		stack = new StackImplementation(sizeOfStack);
		stackOfStacks.add(stack);
		topStackIntex++;
	}

	public Integer pop() {
		if ((topStackIntex == 0) && stack.isEmpty()) {
			System.out.println("stack is empty");
			return -1;
		} else if (stack.isEmpty()) {
			stackOfStacks.remove(topStackIntex.intValue());
			topStackIntex--;
			stack = stackOfStacks.get(topStackIntex.intValue());
			return stack.pop();
		} else {
			return stack.pop();
		}
	}

	public Integer popAt(final int stackIndex) {
		Integer item = null;
		if (stackIndex > topStackIntex) {
			System.out.println("Invalid stack index. " + stackIndex);
			return item;
		} else if (stackIndex == topStackIntex) {
			return stack.pop();
		} else {
			item = popAtBottom(stackIndex, topStackIntex);
		}
		return item;
	}

	public Integer popAtBottom(final int stackIndex, final int topStackIntex) {

		if (stackIndex < topStackIntex) {
			Integer bottom = popAtBottom(stackIndex + 1, topStackIntex);
			StackImplementation stack = stackOfStacks.get(stackIndex);
			Integer newBottom = stack.popBottom();
			if (bottom != null) {
				stack.push(bottom);
			}
			return newBottom;
		} else {
			StackImplementation stack = stackOfStacks.get(stackIndex);
			Integer bottom = stack.popBottom();
			if (stack.isEmpty()) {
				this.topStackIntex--;
			}
			return bottom;
		}

	}

	public static void main(final String[] args) {
		StackOfPlates stack = new StackOfPlates();
		stack.push(11);
		stack.push(12);
		stack.push(13);
		stack.push(21);
		stack.push(22);
		stack.push(23);
		stack.push(31);
		stack.push(32);
		stack.push(33);
		stack.push(41);
		stack.push(44);
		stack.push(45);
		stack.push(51);

		System.out.println("POP at " + stack.popAt(1));

	}

}
