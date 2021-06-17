package com.lrn.leetcode.google;

public class Q100SameTree {
    /*
    * pd: https://leetcode.com/problems/same-tree/
    * assm: best time sol, tree nodes < 10000, tree values < Int.max
    * approach: tree traversal recursive
    * test cases:
    * */

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val) return isSameTree(p.left,q.left) && isSameTree(p.right, q.right);
        return false;
    }

}
