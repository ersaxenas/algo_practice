package com.lrn.leetcode.google;

import java.math.BigInteger;

public class Q172FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        if(n <= 0) return 0;
        BigInteger fact = factorial(n);
        return trailing(fact);
    }

    public BigInteger factorial(int n) {
        BigInteger fact = BigInteger.ONE;
        while(n > 0) {
            fact = fact.multiply(BigInteger.valueOf(n));
            n--;
        }
        return fact;
    }

    public int trailing(BigInteger num) {
        int cnt=0;
        while( num.compareTo(BigInteger.ZERO) > 0 && (num.mod(BigInteger.TEN).equals(BigInteger.ZERO))) {
            cnt++;
            num = num.divide(BigInteger.TEN);
        }
        return cnt;
    }

    public static void main(String[] args) {
        Q172FactorialTrailingZeroes sol = new Q172FactorialTrailingZeroes();
        System.out.println(sol.trailingZeroes(5));
    }
}
