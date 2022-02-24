package com.lrn.leetcode.google;

import java.util.Stack;

public class Q104MaximumDepthOfBinaryTree {
    /*2022-01-30T18:23:16.804Z
    https://leetcode.com/problems/maximum-depth-of-binary-tree
    * pd: Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Note: A leaf is a node with no children.
Example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
    * assm: non null nodes, no. of nodes < int max, best time sol.
    * appr: DFS
    * */

    /*can iterate over tree but will need another stack to keep track of depth*/
    public int maxDepthnotworking(TreeNode root) {
        int maxd = 0;
        if (root == null) {
            return maxd;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                maxd = Math.max(maxd, stack.size());
                while (!stack.isEmpty()) {
                    node = stack.pop();
                    if(node.right != null) {
                        node = node.right;
                    }
                }
            }
        }
        return maxd;
    }

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.add(root);
        depthStack.add(1);
        int max = 0;
        TreeNode currNode = null;
        while(!nodeStack.isEmpty()) {
            currNode = nodeStack.pop();
            int depth = depthStack.pop();
            if(currNode.left == null && currNode.right == null) {
                max = Math.max(max,depth);
            }
            if(currNode.left != null) {
                nodeStack.push(currNode.left);
                depthStack.push(depth+1);
            }
            if(currNode.right != null) {
                nodeStack.push(currNode.right);
                depthStack.push(depth+1);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        Q104MaximumDepthOfBinaryTree sol = new Q104MaximumDepthOfBinaryTree();
        TreeNode n3 = new TreeNode(3);
        TreeNode n20 = new TreeNode(20);
        TreeNode n9 = new TreeNode(9);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);

        n20.left = n15;
        n20.right = n7;

        n3.right = n20;
        n3.left = n9;
        System.out.println(sol.maxDepth(n3));
    }

}


