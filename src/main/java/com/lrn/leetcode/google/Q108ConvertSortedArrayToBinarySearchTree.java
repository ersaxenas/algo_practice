package com.lrn.leetcode.google;

public class Q108ConvertSortedArrayToBinarySearchTree {
    /*
    * pd: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
    * assm: arr len < 1000, -1000 < arr elem < 1000, best time sol
    * appr: like binary search find mid, mid will be root and then find root in the left and right part of the array.
    * test cases:
    *
    * */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 1) return new TreeNode(nums[0]);
        return buildTree(nums,0,nums.length-1);
    }

    public TreeNode buildTree(int[] nums, int start, int end) {
        if(start > end ) return null;
        int rootIdx = start + (int)Math.floor((end-start)/2);
        //System.out.println("start: "+ start + ", end: " + end + ", rootIdx: " + rootIdx);
        TreeNode node = new TreeNode(nums[rootIdx]);
        node.left = buildTree(nums, start, rootIdx-1);
        node.right = buildTree(nums, rootIdx+1, end);
        return node;
    }



}
