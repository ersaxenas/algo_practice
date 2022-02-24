package com.lrn.cci.treeandgraph;

public class BTreeNode<K extends Comparable<K>, V> {
	private K key;
	private V val;
	private BTreeNode<K, V> leftNode;
	private BTreeNode<K, V> rightNode;
	private int count;
	private BTreeNode<K, V> parent;

	public BTreeNode() {

	}

	public BTreeNode(final K key, final V val, final int count) {
		this.key = key;
		this.val = val;
		this.count = count;
	}

	public K getKey() {
		return key;
	}

	public void setKey(final K key) {
		this.key = key;
	}

	public V getVal() {
		return val;
	}

	public void setVal(final V val) {
		this.val = val;
	}

	public BTreeNode<K, V> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(final BTreeNode<K, V> leftNode) {
		this.leftNode = leftNode;
	}

	public BTreeNode<K, V> getRightNode() {
		return rightNode;
	}

	public void setRightNode(final BTreeNode<K, V> rightNode) {
		this.rightNode = rightNode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(final int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "BTreeNode [{key=" + key + ", val=" + val + ", count= " + count + "} leftNode->" + ((leftNode == null) ? " empty " : leftNode.getKey()) + ", rightNode->"
				+ ((rightNode == null) ? " empty " : rightNode.getKey()) + "]";
	}

	public BTreeNode<K, V> getParent() {
		return parent;
	}

	public void setParent(final BTreeNode<K, V> parent) {
		this.parent = parent;
	}

}
