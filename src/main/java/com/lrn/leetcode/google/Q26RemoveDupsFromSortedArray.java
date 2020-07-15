package com.lrn.leetcode.google;

public class Q26RemoveDupsFromSortedArray {

    /*
    * pd: Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
    * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
    * Assm: best time solution, arr len 0 < n < int max, arr doest not contain any nulls
    * Appr: take two index p1 =0 , p2=0
    *       p1 points to place where elem can be swapped
    *       p2 keeps moving forward to
    *       when arr[p2] != arr[p2+1] then swap elem and p1++, p2++
    *       when arr[p2] == arr[p2+1] then swap elem and p2++
    *
    * time: O(n) space o(1)
    *
    * */

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length < 2) {
            return 1;
        }
        int p1=1, p2=1;
        while(p2 < nums.length) {
           if(nums[p2] != nums[p2-1]) {
               nums[p1] = nums[p2];
               p1++;
           }
           p2++;
        }
       return p1;
    }

}
