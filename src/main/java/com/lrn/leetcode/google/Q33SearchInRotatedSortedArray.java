package com.lrn.leetcode.google;

public class Q33SearchInRotatedSortedArray {
    /* 2021-12-16T06:50:22.601Z 
     https://leetcode.com/problems/search-in-rotated-sorted-array/
    * pd: Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).
* assm : nodups, no nulls, best time solution
* appr: binary search
*       event after rotating one part of array will be sorted.
*
    * */

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            int emid = nums[mid];
            if (emid == target) {
                return mid;
            }
            if (nums[start] <= emid) { // left side is sorted
                if (target >= nums[start] && target < emid) {
                    end = mid - 1; // search left
                } else {
                    start = mid + 1; // search right
                }
            } else { // right side is sorted
                if (target > emid && target <= nums[end]) {
                    start = mid + 1; // search right
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public int srch(int[] A, int target) {
        int lo = 0;
        int hi = A.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] == target) return mid;

            if (A[lo] <= A[mid]) {
                if (target >= A[lo] && target < A[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > A[mid] && target <= A[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return A[lo] == target ? lo : -1;
    }

    public static void main(String[] args) {
        Q33SearchInRotatedSortedArray sol = new Q33SearchInRotatedSortedArray();
        System.out.println(sol.search(new int[] {3,1}, 1));
    }

}
