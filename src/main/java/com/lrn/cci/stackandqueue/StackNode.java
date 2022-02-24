package com.lrn.cci.stackandqueue;

public class StackNode<T> {

	private T item;
	private StackNode<T> next;
	private StackNode<T> prev;
	private Integer prevSlot;
	private Integer nextSlot;
	private Integer minElem;

	public T getItem() {
		return item;
	}

	public void setItem(final T item) {
		this.item = item;
	}

	public StackNode<T> getNext() {
		return next;
	}

	public void setNext(final StackNode<T> next) {
		this.next = next;
	}

	public StackNode<T> getPrev() {
		return prev;
	}

	public void setPrev(final StackNode<T> prev) {
		this.prev = prev;
	}

	public Integer getPrevSlot() {
		return prevSlot;
	}

	public void setPrevSlot(final Integer prevSlot) {
		this.prevSlot = prevSlot;
	}

	public Integer getNextSlot() {
		return nextSlot;
	}

	public void setNextSlot(final Integer nextSlot) {
		this.nextSlot = nextSlot;
	}

	public Integer getMinElem() {
		return minElem;
	}

	public void setMinElem(final Integer minElem) {
		this.minElem = minElem;
	}

	@Override
	public String toString() {
		return item.toString();
	}

}