package com.lrn.leetcode.google;

import java.util.Deque;
import java.util.LinkedList;

public class Q226InvertBinaryTree {
    /*
    * pd: nvert a binary tree.
Example:
Input:
*     4
   /   \
  2     7
 / \   / \
1   3 6   9
    *Output:
     4
   /   \
  7     2
 / \   / \
9   6 3   1
    * assm: best time sol.
    * appr:
    * test cases
    * */

    /*iterative sol*/
    public TreeNode invertTreeItr(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
       return root;
    }

    /*recursive*/
    public TreeNode invertTree(TreeNode root) {
        invertTreeRec(root);
        return root;
    }

    public void invertTreeRec(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }
        TreeNode left = node.left;
        node.left = node.right;
        node.right = left;
        invertTree(node.left);
        invertTree(node.right);
    }


}
