package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q94BinaryTreeInorderTraversal {

/* 2022-01-13T07:09:44.719Z
* pd: Given a binary tree, return the inorder traversal of its nodes' values.
* Example:
* Input: [1,null,2,3]
   1
    \
     2
    /
   3
* Output: [1,3,2]
* Follow up: Recursive solution is trivial, could you do it iteratively?
* assm: tree node can be null, max tree elem 100, best time sol
* appr:
*   take currnode = root;
*   loop: stack is not empty or currnode s not null
*         loop : traverse to left most node and keep adding nodes to stack in the path
*         we will reach this step when currnode == null - means we have reached left node
*         now pop the node from stack which the left most node and add to result
*         now set currnode = right child
* Ez: [1,null,2,3] result [1,3,2]
* */

    // best
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
               while(curr != null) { // to left most node
                   stack.add(curr);
                   curr = curr.left;
               }
               curr = stack.pop();
               result.add(curr.val);
               curr = curr.right; // go right
        }
        return result;
    }



    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            if(stack.peek().left == null) {
                while(!stack.isEmpty()) {
                    TreeNode currNode = stack.pop();
                    result.add(currNode.val);
                    if(currNode.right != null) {
                        stack.add(currNode.right);
                        break;
                    }
                }
            } else {
                stack.add(stack.peek().left);
            }
        }
        return result;
    }
}



