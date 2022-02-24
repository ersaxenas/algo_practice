package com.lrn.leetcode.google;

public class Q307RangeSumQueryMutable {
    /*
    *pd: Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
The update(i, val) function modifies nums by updating the element at index i to val.
    * assm: non null elem, array size < 10000, 0 < elem < 10000
    * appr: segment tree
    * test cases:
    *
    * */
    static class SegmentTreeNode {
        int val, start, end;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int val, int start, int end) {
            this.val = val;
            this.start = start;
            this.end = end;
        }
    }


    static class NumArrayRange {
        SegmentTreeNode root;
        int[] nums;
        public NumArrayRange(int[] nums) {
          if(nums == null || nums.length == 0) {
              return;
          }
          this.nums = nums;
          root = new SegmentTreeNode(0, 0, nums.length-1);
          buildTree(nums, 0, nums.length-1, root);
        }

        public void update(int i, int val) {
          if(root == null || i >= nums.length) return;
          int difference = val - nums[i];
          nums[i] = val;
          updateTree(root, i, difference);
        }

        public void updateTree(SegmentTreeNode node, int index, int difference) {
            if(index < node.start || index > node.end) return; // out of range
            node.val = node.val + difference;
            if(node.start == node.end) return;
            updateTree(node.left, index, difference);
            updateTree(node.right, index, difference);
        }

        public int sumRange(int i, int j) {
            if(root == null) {
                return 0;
            }
         return sumRange(root, i,j);
        }

        public int sumRange(SegmentTreeNode node, int rangeStart, int rangeEnd) {
             if(rangeStart == node.start && rangeEnd == node.end) {
                 return node.val;
             }
             int mid = node.start + (node.end - node.start) / 2;
             if(rangeEnd <= mid)  { // search in left side tree for range
                 return sumRange(node.left, rangeStart, rangeEnd);
             } else if(rangeStart >= mid+1) {
                 return sumRange(node.right, rangeStart, rangeEnd);
             } else {
                 return sumRange(node.left, rangeStart, mid) + sumRange(node,mid+1, rangeEnd);
             }
        }

        public int buildTree(int[] nums, int start, int end, SegmentTreeNode node) {
            if(start == end) {
                node.val = nums[start];
                return node.val;
            }
            node.start=start;
            node.end = end;
            int mid = start + (end - start) / 2;
            node.left = new SegmentTreeNode(0, start, mid);
            node.right = new SegmentTreeNode(0, mid+1, end);
            node.val = buildTree(nums, start, mid, node.left) + buildTree(nums, mid+1, end, node.right);
            return node.val;
        }


    }

    public static void main(String[] args) {
        NumArrayRange numArrayRange = new NumArrayRange(new int[] {1,3,5,7,9,11});
        System.out.println(numArrayRange.sumRange(2,5));
        numArrayRange.update(2,6);
        System.out.println(numArrayRange.sumRange(2,5));
    }

}
