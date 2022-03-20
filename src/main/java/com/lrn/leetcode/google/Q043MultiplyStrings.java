package com.lrn.leetcode.google;

import java.util.Arrays;

public class Q043MultiplyStrings {

    /* 2021-12-24T11:13:46.765Z
    https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation

     * PD: Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
     * The length of both num1 and num2 is < 110.
     * Both num1 and num2 contain only digits 0-9.
     * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.
     * Assm: array non nulls, +ve only
     *       Best time solution
     * Appr: intuition
     *  `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
     * */

    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        int p1 = 0, p2 = 0;
        for (int d2 = num2.length() - 1; d2 >= 0; d2--) { // going in reverse order
            for (int d1 = num1.length() - 1; d1 >= 0; d1--) { // going reverse order
                p1 = d1 + d2;
                p2 = p1 + 1;
                int mul = res[p2] + ((num1.charAt(d1) - '0') * (num2.charAt(d2) - '0'));
                res[p2] = mul % 10;
                res[p1] += mul / 10;
            }
        }
        while (p1 < res.length && res[p1] == 0) {
            p1++;
        }
        StringBuilder sbr = new StringBuilder();
        for (int idx = p1; idx < res.length; idx++) {
            sbr.append(res[idx]);
        }
        return (sbr.length() == 0) ? "0" : sbr.toString();
    }

    public static void main(String[] args) {
        Q43MultiplyStrings sol = new Q43MultiplyStrings();
        System.out.println(sol.multiply("123", "0"));
    }

}
