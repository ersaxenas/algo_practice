package com.lrn.leetcode.google;

public class Q69SqrtX {
    /* https://leetcode.com/problems/sqrtx/
    * pd: Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
    * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
    * assm: best time solution, x is +ve
    * appr: binary search
    *       start from 1 to X -> start --- end
    *        loop  mid = start + (ene-start)/2
    *             if mid * mid == x or mid == x/mid then return mid
    *             if mid * mid > x or mid > x/mid then end = mid -1
    *             else start = mid + 1
    *       return end;
    * test cases:
    *  input : 4, output 2
    *  input : 8, output 2
    *  input : 0, output 0
    *  input : 1, output 1
    * */

    public int mySqrt(int x) {
        if(x <=1) { return x;}
        int start = 1, end = x, mid =0;
        while(start <= end) {
            mid = start + (end-mid)/2;
            if(mid == x/mid) { return mid;}
            else if(mid > x/mid) { end = mid-1;}
            else { start = mid+1;}
        }
        return end;
    }

    public static void main(String[] args) {
        Q69SqrtX sol = new Q69SqrtX();
        System.out.println(sol.mySqrt(4));
        System.out.println(sol.mySqrt(8));
        System.out.println(sol.mySqrt(0));
        System.out.println(sol.mySqrt(10));
        System.out.println(sol.mySqrt(11));
        System.out.println(sol.mySqrt(12));
        System.out.println(sol.mySqrt(13));
        System.out.println(sol.mySqrt(14));
        System.out.println(sol.mySqrt(15));
        System.out.println(sol.mySqrt(16));
        System.out.println(sol.mySqrt(17));
        System.out.println(sol.mySqrt(18));
        System.out.println(sol.mySqrt(19));
        System.out.println(sol.mySqrt(1085817232));
    }


}
