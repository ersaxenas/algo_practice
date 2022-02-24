package com.lrn.educative.crust;

public class BinaryTreeNode {
    public Integer data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode next;
    public BinaryTreeNode parent;
    public int count;

    public BinaryTreeNode(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode getNext() {
        return next;
    }

    public void setNext(BinaryTreeNode next) {
        this.next = next;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return (data != null) ? "["+data+"]" : "[]";
    }
}
