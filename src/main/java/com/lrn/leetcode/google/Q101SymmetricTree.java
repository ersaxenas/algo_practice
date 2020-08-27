package com.lrn.leetcode.google;

import java.util.Stack;

public class Q101SymmetricTree {
     /*
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3


But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
     *
     * */

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricRec(root.left, root.right);
    }

    public boolean isSymmetricRec(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetricRec(left.left, right.right) && isSymmetricRec(left.right, right.left);
    }

    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root.left);
        stack.add(root.right);
        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            stack.push(node1.left);
            stack.push(node2.right);

            stack.push(node1.right);
            stack.push(node2.left);
        }
        return true;
    }

    public static void main(String[] args) {
        Q101SymmetricTree sol = new Q101SymmetricTree();
        TreeNode node1 = new TreeNode(1);
        TreeNode node21 = new TreeNode(2);
        TreeNode node22 = new TreeNode(2);
        TreeNode node31 = new TreeNode(3);
        TreeNode node32 = new TreeNode(3);
        TreeNode node41 = new TreeNode(4);
        TreeNode node42 = new TreeNode(4);
        node1.left = node21;
        node1.right = node22;
        node21.left = node31;
        node21.right = node41;
        node22.left = node42;
        node22.right = node32;

        System.out.println(sol.isSymmetric(node1));
        System.out.println(sol.isSymmetricIterative(node1));
        node21.left = null;
        System.out.println(sol.isSymmetric(node1));
        System.out.println(sol.isSymmetricIterative(node1));

    }


}
