package com.lrn.practice;

import com.lrn.leetcode.google.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class TreeDfs {

    public int countPaths(TreeNode root, int S) {
      return countPaths(root, S, new LinkedList<>());
    }

    public int countPaths(TreeNode node, int S, List<Integer> lst) {
        if(node == null) {
            return 0;
        }
        int paths = 0, sum =0;
        lst.add(node.val);
        for(int idx=lst.size()-1; idx>=0; idx--) {
            sum = sum + lst.get(idx);
            if(sum == S) {
                paths++;
            }
        }
        paths += countPaths(node.left, S, lst);
        paths += countPaths(node.right, S, lst);
        lst.remove(lst.size()-1);
        return paths;
    }

    int pMaxSum =0;
    public int pathMaxSum(TreeNode root) {
         pMaxSum = Integer.MIN_VALUE;
         pathMaxSumRec(root);
         return pMaxSum;
    }

    public int pathMaxSumRec(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int leftSum = pathMaxSumRec(node.left);
        int rightSum = pathMaxSumRec(node.right);
        leftSum = Math.max(leftSum,0); // ignore -ve path sum as we are interested in max sum
        rightSum = Math.max(rightSum,0);
        int curMax = Math.max(rightSum, leftSum) + node.val;
        pMaxSum = Math.max(pMaxSum, leftSum+rightSum+node.val);
        return curMax;
    }


    public static void main(String[] args) {
        TreeDfs sol = new TreeDfs();
/*
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + sol.countPaths(root, 11));
*/

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Maximum Path Sum: " + sol.pathMaxSum(root));

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println("Maximum Path Sum: " + sol.pathMaxSum(root));

        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        System.out.println("Maximum Path Sum: " + sol.pathMaxSum(root));

    }


}
