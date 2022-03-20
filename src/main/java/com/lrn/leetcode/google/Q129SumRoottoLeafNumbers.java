package com.lrn.leetcode.google;

public class Q129SumRoottoLeafNumbers {
    /*2022-02-27T15:33:42.133Z
    * pd:  https://leetcode.com/problems/sum-root-to-leaf-numbers
    * assm: tree nodes are less then 1000, 0 < node val < 1000, tree depth < 10, root leaf num is < int.max, total sum < int max
    * appr: dfs
    * test cases:
    * */


    int max =0;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return max;
    }

    public int dfs2(TreeNode node, int num) {
        if(node == null) return 0;
        num = num*10 + node.val;
        if(node.left == null && node.right == null) {
            return num;
        }
        return dfs2(node.left, num) + dfs2(node.right, num);
    }

    public void dfs(TreeNode node, int num) {
        if(node == null) return;
        num = num*10 + node.val;
        if(node.left == null && node.right == null) {
            max = max + num;
        }
        dfs(node.left, num);
        dfs(node.right, num);
    }
}
