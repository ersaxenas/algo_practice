package com.lrn.leetcode.google;

import java.util.Arrays;

public class Q306AdditiveNumber {
    /*
    * pd: Additive number is a string whose digits can form additive sequence.
A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
    * assm: no white space char, no sign at the beginning, str len < 10000, best time sol
    * appr: brute force
    * test cases:
    * Input: "112358" Output: true
    * */

    public boolean isAdditiveNumber(String num) {
         int len = num.length();
         for(int i=1; i<=len/2; i++) {
             for(int j=1; Math.max(i,j) <= (len-i-j); j++) {
                 if(isValid(i,j,num)) return true;
             }
         }
         return false;
    }

    private boolean isValid(int i, int j, String num) {
        if(num.charAt(0) == '0' && i>1) return false;
        if(num.charAt(i) == '0' && j>1) return false;
        Long num1 = Long.parseLong(num.substring(0,i));
        Long num2 = Long.parseLong(num.substring(i,i+j));
        String sum;
        for(int start = i+j; start < num.length(); start = start + sum.length()) { // not a fixed increment loop
            num2 = num2 + num1; // now stores sum
            sum = num2.toString(); // sum is now num2
            // check for pattern - start with - if rest of the num has sum then proceed ahead else return flase
            if(!num.startsWith(sum, start)) return false;
            // now num2 become num1 == sum -> second no.
            // num1 should become num2 so
            num1 = num2 - num1;
            // from here loop continues a
            // now once again we have num1 and num2 and start is not at positioned at the start + sum.len
            // so calculate new sum and just check for start with in string
        }
        return true;
    }


    public static void main(String[] args) {
        Q306AdditiveNumber sol = new Q306AdditiveNumber();
        System.out.println(sol.isAdditiveNumber("112358"));
        System.out.println(sol.isAdditiveNumber("101"));
    }

}
