package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q113PathSum2 {
    /*
    * pd: https://leetcode.com/problems/path-sum-ii
    * assm: tree nodes < 1000, -1000 <  node val < 1000, best time sol, if possible best memory sol
    * appr: backtracking and tree iteration
    * test cases:
    * */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        pathSumRec(root, targetSum, 0, new ArrayList<>(), result);
        return result;
    }

    public void pathSumRec(TreeNode node, int targetSum, int sum, List<Integer> tmp,List<List<Integer>> result) {
        if(node == null) return;
        tmp.add(node.val);
        if(node != null && node.left == null && node.right == null && sum + node.val == targetSum ) {
            result.add(new ArrayList<>(tmp));
        }
        pathSumRec(node.left, targetSum, sum+node.val, tmp, result);
        pathSumRec(node.right, targetSum, sum+node.val, tmp, result);
        tmp.remove(tmp.size()-1);
    }


}
