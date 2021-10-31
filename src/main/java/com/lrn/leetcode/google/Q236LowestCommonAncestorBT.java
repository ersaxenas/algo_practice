package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q236LowestCommonAncestorBT {

    /*
    * pd: Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
    * assm: node < int.max, p and q are present the tree,
    *
    * */

    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        /*
         * 1. not found
         * 2. found p
         * 3. found q
         * if either p or q found we just need that node
         * */
        if (node == null || node == p || node == q) {
            return node;
        }
        TreeNode left = lowestCommonAncestor(node.left, p, q); /*find p or q in the left subtree which ever comes first*/
        TreeNode right = lowestCommonAncestor(node.right, p, q); /*find p or q in the right subtree which ever comes first*/
        /*
         * left != null means we found p or q in the left subtree. right != null means we found p or q in the right subtree.
         * 1. if left && right both != null mean -> p and q are in the opposite subtrees so return current node;
         * 2. if left != null && right == null mean -> both nodes are not present in the right subtree and in the left subtree either p or q was found first
         *    so we return left since other node must be some where in the child subtree of left
         * 3. if left == null && right != null mean -> both nodes are not present in the left subtree and in the right subtree either p or q was found first
         *    so we return since other node must be some where in the child subtree of right
         * */
        if (left != null && right != null) {
            return node;
        }
        return (left != null) ? left : right;
    }


    public boolean find(TreeNode node, TreeNode p, List<TreeNode> treeNodeList) {
        if(node == null) {
            return false;
        }
        if(node == p ) {
            treeNodeList.add(node);
            return true;
        }
        if(find(node.left, p, treeNodeList) || find(node.left, p, treeNodeList)) {
           treeNodeList.add(node);
           return true;
        }
        return false;
    }


}
