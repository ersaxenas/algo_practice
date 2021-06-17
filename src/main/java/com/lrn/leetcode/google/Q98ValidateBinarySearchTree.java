package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q98ValidateBinarySearchTree {
    /*
    * pd: https://leetcode.com/problems/validate-binary-search-tree
    * appr: https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
    * */
    /*iterate over binary tree*/
    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode node = root;
        List<Integer> lst = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while(node != null || !stack.isEmpty()) {
             // keep going left
            while(node != null) {
                stack.push(node); // parents on stack
                node = node.left;
            }
            node = stack.pop();
            // visit node
            lst.add(node.val);
            // go right;
            node = node.right;
        }
        return lst;
    }
    /* Question : Kth Smallest Element in a BST */

    public int kthSmallest(TreeNode root, int K) {
        TreeNode node = root;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while(node != null || !stack.isEmpty()) {
               // keep going left
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // visit node
            if(--K ==0) {
                break; // stop at Kth node
            }
            // go right
            node = node.right;
        }
        return (node != null) ? node.val : -1;
    }

    /*validate binary search tree*/

    public boolean validateBST(TreeNode root) {
        TreeNode node = root, pre=null;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while(node != null || !stack.isEmpty()) {
            // keep going left
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(pre != null && pre.val >= node.val) {
                return false;
            }
            // go right
            pre = node;
            node = node.right;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        n5.left = n1;
        n5.right = n4;
        n4.left = n3;
        n4.right = n6;
        Q98ValidateBinarySearchTree sol = new Q98ValidateBinarySearchTree();
        System.out.println(sol.validateBST(n5));
    }



}
