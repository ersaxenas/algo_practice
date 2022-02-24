package com.lrn.leetcode.google;

public class Q53MaximumSubarray {
    /* 2021-12-27T12:59:55.413Z 
    https://leetcode.com/problems/maximum-subarray/
     * pd: Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * assm: arr elem non null
     *       best time sol
     * appr: sliding window
     *       -ve ws = we
     *        +ve include elem in window , keep including in the window till sum is >=0
     * Test cases:
     * */

    public int maxSubArray(int[] nums) {
        int maxSum = 0;
        if (nums == null || nums.length == 0) {
            return maxSum;
        }
        int sum = nums[0];
        maxSum = sum;
        for (int idx = 1; idx < nums.length; idx++) {
            int elem = nums[idx];
            if (sum < 0) {
                sum = elem;
            } else {
              sum = sum + elem;
            }
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Q53MaximumSubarray sol = new Q53MaximumSubarray();
        System.out.println(sol.maxSubArray(new int[]{1, 2}));
        System.out.println(sol.maxSubArray(new int[]{-2, -1}));
        System.out.println(sol.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

}
