package com.lrn.leetcode.google;

public class Q250CountUnivalueSubtrees {
    /*
    * pd: Given the root of a binary tree, return the number of uni-value subtrees.A uni-value subtree means all nodes of the subtree have the same value.
    * assm: int node value, tree nodes < int.max, best time sol
    * appr: tree iteration
    * test cases:
    * Input: root = [5,1,5,5,5,null,5] Output: 4
    * */

    public int countUnivalSubtrees(TreeNode root) {
       int[] cnt = new int[1];
       countUnivalRec(root, cnt);
       return cnt[0];
    }

    public boolean countUnivalRec(TreeNode node, int[] cnt) {
        if(node == null) {
            return true;
        }
        boolean left = countUnivalRec(node.left, cnt);
        boolean right = countUnivalRec(node.right, cnt);
        if(left && right) {
            if(node.left != null && node.left.val != node.val) {
                return false;
            }
            if(node.right != null && node.right.val != node.val) {
                return false;
            }
            cnt[0]++;
            return true;
        }

        return false;
    }

}
