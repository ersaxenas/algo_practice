package com.lrn.leetcode.google;

import java.util.LinkedList;
import java.util.Queue;

public class Q116PopulatingNextRightPointers {
    /* https://leetcode.com/problems/populating-next-right-pointers-in-each-node
     * pd: You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
     * struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * Initially, all next pointers are set to NULL.
     * Follow up:
     * You may only use constant extra space.
     * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
     * assm: non null elem, no. of nodes < 10000, best time solution
     * appr: BFS
     * Test cases:
     * Input: root = [1,2,3,4,5,6,7]
     * Output: [1,#,2,3,#,4,5,6,7,#]
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
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qs = queue.size();
            Node prenode = null;
            for (int idx = 0; idx < qs; idx++) {
                Node currNode = queue.poll();
                if (prenode != null) {
                    prenode.next = currNode;
                }
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
                prenode = currNode;
            }
        }
      return root;
    }

}
