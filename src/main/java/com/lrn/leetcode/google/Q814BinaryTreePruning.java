package com.lrn.leetcode.google;

public class Q814BinaryTreePruning {
    /*
    * pd: https://leetcode.com/problems/binary-tree-pruning
    * assm: tree nodes < 1000, best time sol
    * appr: tree iteration
    * test cases:
    * */

    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return root;
        pruneTreeRec(root);
        return  pruneTreeRec(root) ? root : null;
    }

    public boolean pruneTreeRec(TreeNode node) {
        if(node == null) return false;
        boolean left = pruneTreeRec(node.left);
        boolean right = pruneTreeRec(node.right);
        if(!left) node.left = null;
        if(!right) node.right = null;
        return left || right || node.val ==1;
    }


}
