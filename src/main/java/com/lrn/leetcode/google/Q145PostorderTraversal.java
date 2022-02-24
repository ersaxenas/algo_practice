package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q145PostorderTraversal {
    /*
    * pd: https://leetcode.com/problems/binary-tree-postorder-traversal
    * assm: treenode < 1000, best time sol
    * appr: https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
    * test cases:
    * */

    // iterative all three
    // left - root - right
    public List<Integer> inorderTraversalItr(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        TreeNode currNode = root;
        while(currNode != null || !deque.isEmpty()) {
            while(currNode != null) {
                deque.push(currNode); // put on stack and visit left child
                currNode = currNode.left;
            }
            if(!deque.isEmpty()) {
                currNode = deque.pop();
                result.add(currNode.val); // add after visiting to left child
                currNode = currNode.right; // go right;
            }
        }
        return result;
    }
    // root - left - right
    public List<Integer> preorderTraversalItr(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        TreeNode currNode = root;
        while(currNode != null || !deque.isEmpty()) {
            while(currNode != null) {
                deque.push(currNode);
                result.add(currNode.val); // add before visiting left child
                currNode = currNode.left;
            }
            if(!deque.isEmpty()) {
                currNode = deque.pop();
                currNode = currNode.right;// go right
            }
        }
        return result;
    }
    // left - right - root
    public List<Integer> postorderTraversalItr(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if(root == null) return result;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        TreeNode currNode = root;
        while(currNode != null || !deque.isEmpty()) {
            while(currNode != null) {
                deque.push(currNode); // put on stack
                result.addFirst(currNode.val); // reverse of preorder
                currNode = currNode.right; // reverse of preorder
            }
            if(!deque.isEmpty()) {
                currNode = deque.pop();
                currNode = currNode.left; // reverse of preorder
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        post(root, result);
        return result;
    }

    public void post(TreeNode node, List<Integer> result) {
        if(node == null) return;
        post(node.left, result);
        post(node.right, result);
        result.add(node.val);
    }

    public static void main(String[] args) {
        Q145PostorderTraversal sol = new Q145PostorderTraversal();
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


        LsUtil.printList(sol.inorderTraversalItr(node50));
        LsUtil.printList(sol.preorderTraversalItr(node50));
        LsUtil.printList(sol.postorderTraversalItr(node50));
    }





}
