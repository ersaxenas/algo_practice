package com.lrn.leetcode.google;

import java.util.Arrays;

public class Q016Closest3Sum {
    /** 2021-11-25T13:09:41.016Z
     * https://leetcode.com/problems/3sum-closest/
     * PD: Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
     * Assumption : atleast one solution, arr contains non null elem, best time solution
     * Approach: sort the array
     *          iterate over array till length-2 (since we need 3 elements)
     *          not use two pointers approach to find two elements with sum equal to - A (element at index in the previous loop)
     * Time complexity : O(nlog n) - sorting + O(n^2) - two loops => O (n^2)
     */

    public int closest3sum(int[] nums, int target) {
        Arrays.sort(nums);
        int rsum = nums[0] + nums[1] + nums[nums.length - 1];
        for (int idx = 0; idx < nums.length - 2; idx++) {
            int lp = idx + 1, rp = nums.length - 1;
            while (lp < rp) {
                int sum = nums[idx] + nums[lp] + nums[rp];
                if (sum == target) {
                    return sum;
                } else if (sum > target) {
                    rp--;
                } else {
                    lp++;
                }
                if(Math.abs(sum - target) < Math.abs(rsum -target)) {
                    rsum = sum;
                }
            }
        }
        return rsum;
    }

    public static void main(String[] args) {
        Q16Closest3Sum q16Closest3Sum = new Q16Closest3Sum();
        System.out.println(q16Closest3Sum.closest3sum(new int[]{-1,2,1,-4}, 1));
        System.out.println(q16Closest3Sum.closest3sum(new int[]{1, 1, 1, 0}, -100));
    }


}

