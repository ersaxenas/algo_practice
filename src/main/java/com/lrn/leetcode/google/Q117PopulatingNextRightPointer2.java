package com.lrn.leetcode.google;

public class Q117PopulatingNextRightPointer2 {
    /* https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii
     * pd:
     *
     *
     * */

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node currNode = root, prev = null, next = null;
        while (currNode != null) { // loop for each level
            while (currNode != null) { // loop to connect next level nodes
                if (currNode.left != null) { // if left child is present
                    if (prev != null) {
                        prev.next = currNode.left; // connect prev to left node
                    } else {
                        next = currNode.left; // save first node of next level
                    }
                    prev = currNode.left;
                }
                if (currNode.right != null) { // if right child is present
                    if (prev != null) {
                        prev.next = currNode.right; // connect prev to right node
                    } else {
                        next = currNode.right; // save first node of next level
                    }
                    prev = currNode.right;
                }
                currNode = currNode.next; // move to next node
            }
            currNode = next;
            prev = null;// reset
            next = null;//reset
        }
        return root;
    }


}
