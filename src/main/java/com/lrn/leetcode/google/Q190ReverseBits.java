package com.lrn.leetcode.google;

public class Q190ReverseBits {
    /*2022-06-18T09:19:32.710Z
    * pd: https://leetcode.com/problems/reverse-bits
    * assm:
    * appr: https://leetcode.com/problems/reverse-bits/discuss/54738/Sharing-my-2ms-Java-Solution-with-Explanation
    * test cases:
    * */

    public int reverseBits(int n) {
        if(n == 0) return 0;
        int result = 0;
        /*there are 32 bits in integer*/
        for(int idx=0; idx < 32; idx++) { // for each bit
            /*
            shift bit left each time so last bit will move to left side
            since we start with 0 (result =0) first shift will just shift 0
             */
            result = result << 1;
            /*
            read last bit of n by using & operation
            if last bit is one then set last bit to 1 by adding 1
             */
            if((n & 1) == 1) result = result + 1;
            /*now remove last bit from n by right shifting it*/
            n = n >> 1;
        }
        return result;
    }

}
