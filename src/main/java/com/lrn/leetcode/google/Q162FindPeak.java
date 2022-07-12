package com.lrn.leetcode.google;

public class Q162FindPeak {
    /*2022-05-04T06:43:16.334Z
    * pd: A peak element is an element that is greater than its neighbors.
Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that nums[-1] = nums[n] = -∞.
    * assm: non null elem, arr size < 10000, best time sol
    * appr: binary search
    *       if mid elem > mid +1 elem - mean peak can be at mid or at some index before mid so go to first half
    *       else (mid elem < mid +1 elem) peak is after mid
    * Test cases:
    * I: [1, 2, 3, 1] O: 2 index of 3
    * I: [1,2,1,3,5,6,4] O: 1 index of 2 or 5 index of 6
    * */

    public int findPeakElement(int[] nums) {
      if(nums == null || nums.length ==0) {
          return -1;
      }
      int start=0, end=nums.length-1, mid;
      while(start < end) {
          mid = start + (end-start)/2;
          if(nums[mid] > nums[mid+1]) {
              end = mid;
          } else {
              start = mid+1;
          }
      }
      return start;
    }

    public static void main(String[] args) {
        Q162FindPeak sol = new Q162FindPeak();
        System.out.println(sol.findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(sol.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));

    }

}
