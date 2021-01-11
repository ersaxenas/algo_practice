package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Q337HouseRobber3 {
    /*
    * pd: The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
Determine the maximum amount of money the thief can rob tonight without alerting the police.
    * assm: best time sol, max tree nodes < 10000, 0 < value of tree nodes < 10000
    * appr: DP
    * test cases:
    * */

    /*APPROACH 1*/
    public int rob(TreeNode root) {
        return robHouse(root, false);
    }

    Map<TreeNode, Integer> parentRob = new HashMap<>();
    Map<TreeNode, Integer> parentNotRob = new HashMap<>();

    public int robHouse(TreeNode node, boolean isParentRobbed) {
       if(node == null) {
           return 0;
       }
       int sum =0;
       if(isParentRobbed) { // true == then cannot rob children
           if(parentRob.containsKey(node)) {
               return parentRob.get(node);
           }
           sum = robHouse(node.left, false) + robHouse(node.right , false);
           parentRob.put(node, sum);
       } else { // false == 1. rob the node and not rob the children 2. not rob this node
           if(parentNotRob.containsKey(node)){
               return parentNotRob.get(node);
           }
           int sumRob = node.val + robHouse(node.left, true) + robHouse(node.right, true);
           int sumNotRob = robHouse(node.left, false) + robHouse(node.right, false);
           sum = Math.max(sumRob, sumNotRob);
           parentNotRob.put(node, sum);
       }
        return sum;
    }

    /*bottom up*/
    public int robn(TreeNode root) {
       int[] result = rob2(root);
       return Math.max(result[0], result[1]);
    }
    public int[] rob2(TreeNode root) {
        if(root == null) {
            return new int[]{0,0};
        }
        int[] left = rob2(root.left);
        int[] right = rob2(root.right);
        int[] result = new int[2];
        result[0] = root.val + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return result;
    }



    public static void main(String[] args) {
        TreeNode nn1 = new TreeNode(3);
        TreeNode nn2 = new TreeNode(2);
        TreeNode nn3 = new TreeNode(3);
        nn1.left = nn2;
        nn1.right = nn3;
        TreeNode nn4 = new TreeNode(3);
        TreeNode nn5 = new TreeNode(1);
        nn2.right = nn4;
        nn3.right = nn5;
        Q337HouseRobber3 sol = new Q337HouseRobber3();
        System.out.println(sol.rob(nn1));
    }

}
