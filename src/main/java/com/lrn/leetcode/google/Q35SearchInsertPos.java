package com.lrn.leetcode.google;

public class Q35SearchInsertPos {
    /* 2021-12-16T07:18:52.180Z 
    https://leetcode.com/problems/search-insert-position/
    * pd: Given a sorted array and a target value, return the index if the target is found.
    * If not, return the index where it would be if it were inserted in order.
    * You may assume no duplicates in the array.
    * assm: nodup or null values, best time sol, arr len 0 < N < int max
    * appr: binary search
    * */

    public int searchInsert(int[] nums, int target) {
      if(nums == null || nums.length ==0 || target < nums[0]) {
          return 0;
      }
      if(target > nums[nums.length-1]) {
          return nums.length;
      }
      int start =0, end = nums.length-1, mid=0;
      while(start <= end) {
          mid = start + (end -start)/2;
          int elem = nums[mid];
          if(target == elem) {
              return mid;
          } else if(target < elem) {
              end = mid-1;
          } else {
              start = mid+1;
          }
      }
      return end+1;
    }

    public static void main(String[] args) {
        Q35SearchInsertPos sol = new Q35SearchInsertPos();
        System.out.println(sol.searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(sol.searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(sol.searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(sol.searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

}
