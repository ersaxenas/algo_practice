package com.lrn.practice;

import com.lrn.leetcode.google.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class TreeBfs {

    public TreeNode findSuccessor(TreeNode root, int key) {
        TreeNode prev = null, curNode = root;
        if (root == null || (root.left == null || root.right == null)) {
            return prev;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            if (prev != null && prev.val == key) {
                return curNode;
            }
            if (curNode.left != null) queue.add(curNode.left);
            if (curNode.right != null) queue.add(curNode.right);
            prev = curNode;
        }
        return null;
    }

    public void connectLevelOrderSibling(TreeNode root) {
        TreeNode prev = null, curNode = root;
        if (root == null || (root.left == null || root.right == null)) {
            return;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int idx = 0; idx < queueSize; idx++) {
                curNode = queue.poll();
                if (curNode.left != null) queue.add(curNode.left);
                if (curNode.right != null) queue.add(curNode.right);
                if (prev == null) {
                    prev = curNode;
                } else {
                  prev.next = curNode;
                }
                prev = curNode;
            }
            prev = null;
        }
    }

    public static void main(String[] args) {
        TreeBfs sol = new TreeBfs();
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        TreeNode result = sol.findSuccessor(root, 12);
        if (result != null)
            System.out.println(result.val + " ");
        result = sol.findSuccessor(root, 9);
        if (result != null)
            System.out.println(result.val + " ");
    }


}
