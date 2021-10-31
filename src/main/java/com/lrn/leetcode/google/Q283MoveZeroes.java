package com.lrn.leetcode.google;

public class Q283MoveZeroes {
    /*
    * pd: https://leetcode.com/problems/move-zeroes
    * assm: arr len < 10000, -100 < num < 100, best time sol
    * appr: similar to quick sort
    * test cases:
    * */

    public void moveZeroes(int[] nums) {
        int p=0;
        for(int idx=0; idx < nums.length; idx++) {
            if(nums[idx] == 0) {
                continue;
            }
            swap(nums, p, idx);
            p++;
        }
        while( p < nums.length) {
            nums[p] = 0;
            p++;
        }
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}
