package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q144BinaryTreePreorderTraversal {
    /* https://leetcode.com/problems/binary-tree-preorder-traversal
    * pd:Given a binary tree, return the preorder traversal of its nodes' values.
    * Follow up: Recursive solution is trivial, could you do it iteratively?
    * appr: recursive easy
    *       iterative: node == root  stack []
    *       loop stack is not empty
    *       visit root, if right child present then add to stack
    *       go left, if node not null visit or stack pop
    * assm; no blank nodes, tree nodes < 1000, best time sol.
    * test cases:
    *                      50
    *                 /          \
    *            20                100
    *         /     \                   \
    *      10        15                  150
    *                 \
    *                  18
    *   ans : 50, 20, 10, 15, 18, 100 , 150
    * */

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode = root;
        while(currNode!= null || !stack.isEmpty()) {
            if(currNode!= null) {
                result.add(currNode.val);
                if(currNode.right!=null) {
                    stack.push(currNode.right);
                }
                currNode = currNode.left;
            } else {
                currNode = stack.pop();
            }
        }
        return result;
    }

    public List<Integer> preorderTraversalItr(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        TreeNode currNode = root;
        while(currNode != null || !deque.isEmpty()) {
            while(currNode != null) {
                result.add(currNode.val); // visit
                if(currNode.right != null) deque.push(currNode.right); // avoid using deque.add behaves like queue
                currNode = currNode.left;
            }
            if(!deque.isEmpty()) {
                currNode = deque.pop();
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode = root;
        while(currNode!= null || !stack.isEmpty()) {
            while(currNode!= null) { // keep going left
                stack.push(currNode);
                currNode = currNode.left;
            }
            currNode = stack.pop();
            result.add(currNode.val);
            currNode = currNode.right; // go to right
        }
        return result;
    }
    public void preorderTraversalRec(TreeNode node, List<Integer> result) {
        if(node == null) {
            return;
        }
        result.add(node.val);
        preorderTraversalRec(node.left,result);
        preorderTraversalRec(node.right,result);
    }

    public static void main(String[] args) {
        Q144BinaryTreePreorderTraversal sol = new Q144BinaryTreePreorderTraversal();
        TreeNode node50 = new TreeNode(50);
        TreeNode node20 = new TreeNode(20);
        TreeNode node100 = new TreeNode(100);
        TreeNode node150 = new TreeNode(150);
        TreeNode node10= new TreeNode(10);
        TreeNode node15 = new TreeNode(15);
        TreeNode node18 = new TreeNode(18);
        node50.left = node20;
        node50.right = node100;

        node20.left = node10;
        node20.right = node15;
        node15.right = node18;

        node100.right = node150;

        ArrayList<Integer> result = new ArrayList<>();
        sol.preorderTraversalRec(node50,result);
        LsUtil.printList(result);
        LsUtil.printList(sol.preorderTraversal(node50));
        LsUtil.printList(sol.inorderTraversal(node50));


    }
}
