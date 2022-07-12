package com.lrn.leetcode.google;

public class Q189RotateArray {
    /*2022-06-12T22:16:13.684Z
    * pd: https://leetcode.com/problems/rotate-array/
    * assm: arr len < 1000, best time sol
    * appr:
    * test cases:
    * */

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end) {
        int tmp;
        while(start < end) {
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
