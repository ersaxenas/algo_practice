package com.lrn.leetcode.google;

public class Q136SingleNumber {
    /*2022-03-16T20:39:33.705Z
    * pd: https://leetcode.com/problems/single-number
    * assm: arr len < 1000, 0 < num < 1000, best time sol
    * appr: xor
    * test cases
    * */
    /*sol in the 136 will also work here*/
    public int singleNumber(int[] nums) {
        int ans =0;
        for(int num: nums) ans = ans ^ num;
        return ans;
    }

}
