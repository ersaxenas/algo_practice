package com.lrn.leetcode.google;

public class Q41FirstMissingPositive {
    /* 2021-12-24T10:59:45.911Z
    https://leetcode.com/problems/first-missing-positive/
     * pd : Given an unsorted integer array, find the smallest missing positive integer.
     * assm: array no empty, no nulls
     *       best time solutions
     * Appr: cycling sort
     *       1 -> 0
     *       2 -> 1
     *       3 -> 4
     *       if num > arr.len discard and num < 0 discard
     * */

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int idx=0;
        while(idx < nums.length) {
            int elem = nums[idx];
            if(elem < nums.length && elem > 0 && elem != nums[elem-1]) {
               swap(nums, idx, elem-1);
               continue;
             }
            idx++;
        }
        Integer misnum = null;
        idx=0;
        while(idx < nums.length) {
            if(idx+1 != nums[idx]) {
                misnum = idx+1;
                break;
            }
            idx++;
        }
        if(misnum == null) {
            misnum = nums.length+1;
        }
        return misnum;
    }

    void swap(int[] nums, int a , int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        Q41FirstMissingPositive sol = new Q41FirstMissingPositive();
        System.out.println(sol.firstMissingPositive(new int[]{1}));
        System.out.println(sol.firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(sol.firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(sol.firstMissingPositive(new int[]{7,8,9,11,12}));
    }

}
