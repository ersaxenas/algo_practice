package com.lrn.leetcode.google;

public class Q156BinaryTreeUpsideDown {
    /*2022-04-22T07:38:24.495Z
    * pd: https://leetcode.com/problems/binary-tree-upside-down/
    * assm:
    * appr:
    * test cases:
    *
    * */

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return moveChild(root);
    }

    public TreeNode moveChild(TreeNode node) {
        if(node == null || node.left == null ) return node;
        TreeNode left = node.left, right = node.right;
        node.left = null;
        node.right = null;
        TreeNode newRoot = moveChild(left);
        left.left = right;
        left.right = node;
        return newRoot;
    }



}
