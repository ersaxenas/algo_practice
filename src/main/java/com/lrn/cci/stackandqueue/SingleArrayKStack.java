package com.lrn.cci.stackandqueue;

import java.util.Arrays;

public class SingleArrayKStack {

	// @formatter:off
	/*
	 * this can also be achieved using a circular array where each stack element keeps track of previous element. Each time a new element is to be inserted then find an empty slot
	 * in the circular array and link it with previous element. Just keep track of the top element of the stack.
	 * This approach is more space efficient.
	 */
	// @formatter:on

	StackNode<Integer>[] stack = null;
	Integer[] stackTop = null;
	int stackSize = 0;

	public SingleArrayKStack(final int arraySize, final int noOfStacks) {
		// @formatter:off
		/*
		 * size = array size / no. of stacks -- divide array in no. of stacks passed
		 * [1,1,1,2,2,2,3,3,3]
		 */
		// @formatter:on
		stackSize = arraySize / noOfStacks;
		/* initialize array */
		stack = new StackNode[arraySize];
		/* initialize array keep track of top of the stacks */
		stackTop = new Integer[noOfStacks];
		for (int cnt = 0; cnt < noOfStacks; cnt++) {
			stackTop[cnt] = cnt * stackSize;
		}
	}

	public boolean isEmpty(final int stackNo) {
		int top = stackTop[stackNo - 1];
		return top == ((stackNo - 1) * stackSize);
	}

	boolean isFull(final int stackNo) {
		int top = stackTop[stackNo - 1];
		return top >= ((stackNo) * stackSize);
	}

	public void push(final Integer item, final int stackNo) {
		/* check if top not more then allocated size */
		if (isFull(stackNo)) {
			System.out.println("Stack " + stackNo + " is full. Stack top element index " + stackTop[stackNo - 1]);
		} else {
			/* get current top of the stack no. */
			int top = stackTop[stackNo - 1];
			int newTop = top + 1;
			StackNode<Integer> node = new StackNode<>();
			node.setItem(item);
			stack[newTop] = node;
			stackTop[stackNo - 1] = newTop;
		}
		System.out.println(Arrays.asList(stackTop));
		System.out.println(Arrays.asList(stack));
	}

	public StackNode<Integer> pop(final int stackNo) {
		StackNode<Integer> node = null;
		if (isEmpty(stackNo)) {
			System.out.println("Stack is " + stackNo + " empty.");
		} else {
			int top = stackTop[stackNo - 1];
			int nTop = top - 1;
			node = stack[top];
			stack[top] = null;
			if (nTop >= ((stackNo - 1) * stackSize)) {
				stackTop[stackNo - 1] = nTop;
			}
		}
		System.out.println(Arrays.asList(stackTop));
		System.out.println(Arrays.asList(stack));
		return node;
	}

	public StackNode<Integer> peek(final int stackNo) {
		StackNode<Integer> node = null;
		if (isEmpty(stackNo)) {
			System.out.println("stack " + stackNo + " is empty");
		} else {
			int top = stackTop[stackNo - 1];
			node = stack[top];
		}
		return node;
	}

	public static void main(final String[] args) {
		int k = 3, n = 10;

		SingleArrayKStack ks = new SingleArrayKStack(n, k);

		ks.push(15, 3);
		ks.push(30, 3);
		ks.push(45, 3);
		ks.push(60, 3);

		ks.push(17, 2);
		ks.push(34, 2);
		ks.push(51, 2);
		ks.push(68, 2);

		ks.push(11, 1);
		ks.push(22, 1);
		ks.push(33, 1);
		ks.push(44, 1);

		System.out.println("Popped element from stack 3 is " + ks.pop(3));
		System.out.println("Popped element from stack 3 is " + ks.pop(3));
		System.out.println("Popped element from stack 3 is " + ks.pop(3));
		System.out.println("Popped element from stack 3 is " + ks.pop(3));
		System.out.println("Popped element from stack 3 is " + ks.pop(3));
		System.out.println("Popped element from stack 3 is " + ks.pop(3));
		System.out.println("Popped element from stack 2 is " + ks.pop(2));
		System.out.println("Popped element from stack 2 is " + ks.pop(2));
		System.out.println("Popped element from stack 2 is " + ks.pop(2));
		System.out.println("Popped element from stack 2 is " + ks.pop(2));
		System.out.println("Popped element from stack 2 is " + ks.pop(2));
		System.out.println("Popped element from stack 2 is " + ks.pop(2));
		System.out.println("Popped element from stack 1 is " + ks.pop(1));
		System.out.println("Popped element from stack 1 is " + ks.pop(1));
		System.out.println("Popped element from stack 1 is " + ks.pop(1));
		System.out.println("Popped element from stack 1 is " + ks.pop(1));
		System.out.println("Popped element from stack 1 is " + ks.pop(1));
		System.out.println("Popped element from stack 1 is " + ks.pop(1));
		System.out.println("Popped element from stack 1 is " + ks.pop(1));
	}

}
