package com.lrn.leetcode.google;

public class Q153FindMinimumInRotatedSortedArray {
    /*
    * pd: Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
Find the minimum element.
You may assume no duplicate exists in the array.
    * assm: non null, array size < 1000, best time sol
    * appr: binary search
    *
    * */

    public int findMin(int[] nums) {
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = start + (end-start)/2;
            int midElem = nums[mid];
            if(nums[start] <= midElem && midElem <= nums[end]) {
                return nums[start];
            }
            if(nums[start] <= midElem) { // left is sorted
                start = mid+1;
            } else {
                end = mid;
            }
        }
      return nums[end];
    }

    public static void main(String[] args) {
        Q153FindMinimumInRotatedSortedArray sol = new Q153FindMinimumInRotatedSortedArray();
        System.out.println(sol.findMin(new int[]{0, 1, 2, 4, 5, 6, 7}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{1, 2, 4, 5, 6, 7, 0}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{2, 4, 5, 6, 7, 0, 1}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{5, 6, 7, 0, 1, 2, 4}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{6, 7, 0, 1, 2, 4, 5}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{7, 0, 1, 2, 4, 5, 7}) == 0 ? "passed" : "failed");
    }
}
