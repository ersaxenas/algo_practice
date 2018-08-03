package com.lrn.practice;

public class StackUsingArray implements MyStack {
	/*array for stack*/
	Integer[] stack;
	/*index of last element at the stack*/
	int n=0;

	public StackUsingArray(final int size) {
		stack = new Integer[size];
	}

	@Override
	public Integer peek() {
		if(!isEmpty()) {
			return stack[n-1];
		}
		return null;
	}

	@Override
	public Integer pop() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		Integer item = stack[--n];
		stack[n]=null;
		return item;
	}

	@Override
	public boolean push(final Integer item) {
		if(n>=stack.length) {
			System.out.println("Stack is full");
			return false;
		}
		stack[n++] = item;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	public static void main(final String[] args) {
		StackUsingArray stackArr = new StackUsingArray(3);
		System.out.println(stackArr.peek());
		System.out.println(stackArr.pop());
		System.out.println(stackArr.push(1));
		System.out.println(stackArr.pop());
		System.out.println(stackArr.pop());
		System.out.println(stackArr.peek());
		System.out.println(stackArr.push(1));
		System.out.println(stackArr.push(2));
		System.out.println(stackArr.push(3));
		System.out.println(stackArr.push(4));
		System.out.println(stackArr.push(4));
		System.out.println(stackArr.peek());
		System.out.println(stackArr.pop());
		System.out.println(stackArr.peek());
		System.out.println(stackArr.pop());
		System.out.println(stackArr.peek());
		System.out.println(stackArr.pop());
		System.out.println(stackArr.peek());
		System.out.println(stackArr.pop());
		System.out.println(stackArr.peek());
		System.out.println(stackArr.pop());
		System.out.println(stackArr.peek());

	}




}
