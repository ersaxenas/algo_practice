package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Q173BinarySearchTreeIterator {

    /*
    *
    *
    *
    * */
    static class BSTIterator {
        Stack<TreeNode> stack = new Stack<>();
        public BSTIterator(TreeNode root) {
            goLeft(root);
        }

        public void goLeft(TreeNode node) {
             while(node != null) {
                 stack.push(node);
                 node = node.left;
             }
        }

        /** @return the next smallest number */
        public int next() {
           if(hasNext()) {
               final TreeNode node = stack.pop();
               goLeft(node.right);
               return node.val;
           }
           throw new IllegalStateException("no more elements to visit");
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
         return !stack.isEmpty();
        }
    }



    public List<TreeNode> inorder(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) { // go left
              while(cur != null) {
                  stack.push(cur);
                  cur = cur.left;
              }
              cur = stack.pop();
              result.add(cur);
              cur = cur.right;
        }
        return result;
    }

    public static void main(String[] args) {
        Q173BinarySearchTreeIterator sol = new Q173BinarySearchTreeIterator();
        TreeNode n100 = new TreeNode(100);
        TreeNode n50 = new TreeNode(50);
        TreeNode n5 = new TreeNode(5);
        TreeNode n70 = new TreeNode(70);
        TreeNode n200 = new TreeNode(200);
        TreeNode n250 = new TreeNode(250);
        TreeNode n300 = new TreeNode(300);

        n100.left = n50;
        n100.right = n200;

        n50.left = n5;
        n50.right = n70;

        n200.left = n250;
        n200.right = n300;

        System.out.println(sol.inorder(n100).stream().map(elem -> String.valueOf(elem.val)).collect(Collectors.joining(" - ")));
        BSTIterator bstIterator = new BSTIterator(n100);
        while(bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }



}
