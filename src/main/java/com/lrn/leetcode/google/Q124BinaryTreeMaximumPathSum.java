package com.lrn.leetcode.google;

public class Q124BinaryTreeMaximumPathSum {
    /* 2022-02-09T09:12:11.902Z
    https://leetcode.com/problems/binary-tree-maximum-path-sum
     * Given a non-empty binary tree, find the maximum path sum.
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
     * The path must contain at least one node and does not need to go through the root.
     * assm: non null nodes, node values -10000 < N < 10000, no. of nodes < 10000, best time sol.
     * appr: dfs -
     *       at each node x we calculate max left tree sum and max right tree sum
     *       1). max tree sum at this node x will be  = x.val + left tree sum + right tree sum
     *       2). max sum branch at this node x will be = x.val + max(left tree sum , right tree sum)
     *  Ex:
     *                  10
     *               /      \
     *             5         20
     *                     /    \
     *                   15     30
     *                          /
     *                         25
     *       at node 25: left tree sum =0, right tree sum =0, max tree sum = 25 + 0 + 0 = 25, max branch sum = 25 + max(0,0) = 25 (return)
     *       at node 30: left tree sum =25, right tree sum =0, max tree sum = 30 + 25 + 0 = 55, max branch sum = 30 + max(25,0) = 55 ( return)
     *       at node 15: left tree sum =0, right tree sum =0, max tree sum = 15 + 0 + 0 = 15, max branch sum = 15 + max(0,0) = 15 (return)
     *       at node 20: left tree sum =15, right tree sum =55, max tree sum = 20 + 15 + 55 = 90, max branch sum = 20 + max(55,15) = 75 ( return)
     *       .
     *       .
     * Test cases: [1,2,3] and 6
     * */
    int maxTreeSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxTreeSum = Integer.MIN_VALUE;
        maxSumRec(root);
        return maxTreeSum;
    }

    public int maxSumRec(TreeNode node) {
        // base caes
        if (node == null) {
            return 0;
        }
        // recursive
        int leftSubTreeMaxSum = maxSumRec(node.left);
        leftSubTreeMaxSum = Math.max(leftSubTreeMaxSum,0); // ignore -ve sum
        int rightSubTreeMaxSum = maxSumRec(node.right);
        rightSubTreeMaxSum = Math.max(rightSubTreeMaxSum,0); // ignore -ve sum
        int maxSumAtNode = node.val + leftSubTreeMaxSum + rightSubTreeMaxSum;
        maxTreeSum = Math.max(maxTreeSum, maxSumAtNode);
        return node.val + Math.max(leftSubTreeMaxSum, rightSubTreeMaxSum); // you can only go from this node to left or right to form a path
    }

    public static void main(String[] args) {
        Q124BinaryTreeMaximumPathSum sol = new Q124BinaryTreeMaximumPathSum();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        System.out.println(sol.maxPathSum(n1));

    }
}
