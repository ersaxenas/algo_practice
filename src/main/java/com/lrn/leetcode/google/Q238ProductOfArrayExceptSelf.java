package com.lrn.leetcode.google;

public class Q238ProductOfArrayExceptSelf {
    /*
     * pd: Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     * assm: non null elem, arr len < int max, best time sol
     * appr:
     *
     * test cases:
     *
     * */

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] res = new int[nums.length];
        res[0] = 1;
        int left = 1;
        for (int idx = 1; idx < nums.length; idx++) {
            left = left * nums[idx - 1];
            res[idx] = left;
        }
        int right = 1;
        for (int idx = nums.length - 2; idx >= 0; idx--) {
            right = right * nums[idx + 1];
            res[idx] = right * res[idx];
        }
        return res;
    }

    public static void main(String[] args) {
        Q238ProductOfArrayExceptSelf sol = new Q238ProductOfArrayExceptSelf();
        LsUtil.printArray(sol.productExceptSelf(new int[]{1,2,3,4}));
    }

}
