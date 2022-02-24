package com.lrn.leetcode.google;

public class Q268MissingNumber {
    /*
    * pd: https://leetcode.com/problems/missing-number
    * assm: 1 < n < 10000, best time sol, 0 <= val < int max, best time sol
    * appr: cyclic sort
    * test cases
    * */

    public int missingNumber(int[] nums) {
        int idx=0;
        while( idx < nums.length ) {
            int elem = nums[idx];
            if(elem != idx && elem < nums.length) {
                nums[idx] = nums[elem];
                nums[elem] = elem;
                continue;
            }
            idx++;
        }
        for(idx=0; idx < nums.length; idx++) {
            if(idx != nums[idx]) return idx;
        }
        return nums.length;
    }

}
