package com.lrn.leetcode.google;

public class Q190ReverseBits {
    /*
    * pd: https://leetcode.com/problems/reverse-bits
    * assm:
    * appr: https://leetcode.com/problems/reverse-bits/discuss/54738/Sharing-my-2ms-Java-Solution-with-Explanation
    * test cases:
    * */

    public int reverseBits(int n) {
        if(n == 0) return 0;
        int result = 0;
        for(int idx=0; idx < 32; idx++) {
            result = result << 1;
            if((n & 1) == 1) result = result + 1;
            n = n >> 1;
        }
        return result;
    }

}
