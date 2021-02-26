package com.lrn.leetcode.google;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode add(ListNode node) {
        this.next = node;
        return node;
    }

    @Override
    public String toString() {
        return "["+ val + "]";
    }

    public ListNode add(int value) {
        ListNode node = new ListNode(value);
        this.next = node;
        return node;
    }

    public void print() {
        ListNode node = this;
        System.out.println();
        while(node != null) {
            System.out.print(node.val +" -> ");
            node = node.next;
        }
        System.out.println();
    }

    public void printList(ListNode node) {
        StringBuilder sbr = new StringBuilder();
        while(node != null) {
            sbr.append(node.val).append("->");
            node = node.next;
        }
        System.out.println("["+sbr.toString()+"]");
    }
}
