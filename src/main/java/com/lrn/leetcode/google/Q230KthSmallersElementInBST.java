package com.lrn.leetcode.google;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q230KthSmallersElementInBST {
    /*
    * pd: Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
    * assm: non null elem, total nodes < int.max, best time sol
    * appr:
    * test cases:
    * Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
    *Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
*
    * */

    public int kthSmallest(TreeNode root, int k) {
        if(root == null) {
            return -1;
        }
        int cnt = 0;
        TreeNode node = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            cnt++;
            if(cnt == k) { return node.val;}
            node = node.right;
        }
        return -1;
    }

    public static void main(String[] args) {
        Q230KthSmallersElementInBST sol = new Q230KthSmallersElementInBST();
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        n1.right = n2;
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        n3.left = n1;
        n3.right = n4;
        System.out.println(sol.kthSmallest(n3, 2));
    }

}
