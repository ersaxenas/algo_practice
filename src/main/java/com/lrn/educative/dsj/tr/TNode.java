package com.lrn.educative.dsj.tr;

public class TNode {
    private Integer data;
    private TNode left;
    private TNode right;

    public TNode() {}

    public TNode(int data) {
        this.data = data;
    }

    public TNode(int data, TNode left) {
        this.data = data;
        this.left = left;
    }

    public TNode(int data, TNode left, TNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Integer getData() { return data; }
    public void setData(int data) { this.data = data; }
    public TNode getLeft() { return left; }
    public void setLeft(TNode left) { this.left = left; }
    public TNode getRight() { return right; }
    public void setRight(TNode right) { this.right = right; }
}
