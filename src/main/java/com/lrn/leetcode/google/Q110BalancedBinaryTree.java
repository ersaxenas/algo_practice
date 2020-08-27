package com.lrn.leetcode.google;

public class Q110BalancedBinaryTree {
    /*
    * pd: Given a binary tree, determine if it is height-balanced.
    * For this problem, a height-balanced binary tree is defined as:
    * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
    * assm: non null elem, no. of nodes < 10000, best time sol
    * appr: recursive
    *       at each node height  == height of left subtree + height of right subtree + 1
    *       at any node if abs(height of left subtree - height of right subtree) > 1 then tree is not height balanced.
    *       if tree is not height balanced then return -1;
    * */

    public boolean isBalanced(TreeNode node) {
        if(node == null) {
            return true;
        }
        return getHeight(node) != -1;
    }

    public int getHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int lh = getHeight(node.left);
        if(lh == -1) {
            return -1;
        }
        int rh = getHeight(node.right);
        if(rh == -1) {
            return -1;
        }
        return (Math.abs(lh - rh) > 1) ? -1 : (1+Math.max(lh,rh));
    }

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n20 = new TreeNode(20);
        TreeNode n9 = new TreeNode(9);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);

        n20.left = n15;
        n20.right = n7;

        n3.right = n20;
        n3.left = n9;
        Q110BalancedBinaryTree sol = new Q110BalancedBinaryTree();
        System.out.println(sol.isBalanced(n3));
    }


}
