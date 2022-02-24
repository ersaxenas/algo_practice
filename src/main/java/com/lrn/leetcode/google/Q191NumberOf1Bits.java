package com.lrn.leetcode.google;

public class Q191NumberOf1Bits {
    /*
    * pd: https://leetcode.com/problems/number-of-1-bits
    * assm: best time sol.
    * appr:
    * test cases:
    * */

    public int hammingWeight(int n) {
        if( n == 0) return 0;
        int cnt=0;
        for(int idx=0; idx < 32; idx++) {
            if((n & 1) == 1) cnt++;
            n = n >> 1;
        }
        return cnt;
    }
}
