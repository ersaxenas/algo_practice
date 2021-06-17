package com.lrn.leetcode.google;

public class Q112PathSum {
    /*
    * pd: https://leetcode.com/problems/path-sum/
    * assm: tree nodes < 1000, -1000 < node val < 1000, best time sol
    * appr: iterate over tree
    * test cases:
    * */

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return hasPathSumRec(root, targetSum, 0);
    }
    public boolean hasPathSumRec(TreeNode node, int targetSum, int sum) {
        if(node != null && node.left == null && node.right ==null && sum + node.val == targetSum) return true;
        if(node == null) return false;
        return hasPathSumRec(node.left, targetSum, sum + node.val) || hasPathSumRec(node.right, targetSum, sum + node.val);
    }
}
