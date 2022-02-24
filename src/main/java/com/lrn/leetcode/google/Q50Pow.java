package com.lrn.leetcode.google;

public class Q50Pow {
    /* 2021-12-25T07:55:44.131Z 
    https://leetcode.com/problems/powx-n/
    * pd: Implement pow(x, n), which calculates x raised to the power n (xn).
    * assm:
    * appr: recursive
    *       if n is even then result pow(x*x, n/2)
    *       if odd then x * power(x*x, n/2)
    * */

    public double myPow(double x, int n) {
        long N = n;
        if(N < 0) {
            N = -N;
            x = 1/x;
        } else if(N==0) {
            return 1;
        } else if(N == 1) {
            return x;
        }
        return ( N%2 ==0 ) ? myPow(x*x, (int)(N/2)) : x * myPow(x*x, (int)N/2);
    }

    public static void main(String[] args) {
        Q50Pow sol = new Q50Pow();
        System.out.println(sol.myPow(2.0, 10));
        System.out.println(sol.myPow(2.1, 3));
        System.out.println(sol.myPow(2.0, -2));
    }


}
