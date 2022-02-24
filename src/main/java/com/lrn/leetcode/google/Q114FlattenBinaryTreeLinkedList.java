package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q114FlattenBinaryTreeLinkedList {
    /*2022-02-04T11:32:22.515Z
    * pd:Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
    * assm: tree size < 1000 nodes, best time sol,
    * appr:
    *
    * test cases:
    *
    * */

    public void flatten(TreeNode node) {
        if (node == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode currNode = node;
        while(currNode != null ) {
              if(currNode.right!= null) {
                  stack.push(currNode.right);
              }
              if(currNode.left != null) {
                  TreeNode tmp = currNode.left;
                  currNode.right = tmp;
                  currNode.left = null;
                  currNode = tmp;
              } else {
                 if(!stack.isEmpty()) {
                     currNode.right = stack.pop();
                     currNode = currNode.right;
                 } else {
                     currNode = null;
                 }
              }
        }
    }

    //post order traversal
    TreeNode flattenedSubTree;
    public void flatternRec(TreeNode node) {
        if(node == null) {
            return;
        }
        flatternRec(node.right);
        flatternRec(node.left);
        node.right = flattenedSubTree;
        node.left = null;
        flattenedSubTree = node;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n5;
        n2.left = n3;
        n2.right = n4;
        n5.right = n6;
        Q114FlattenBinaryTreeLinkedList sol = new Q114FlattenBinaryTreeLinkedList();
        sol.flatten(n1);

    }

}
