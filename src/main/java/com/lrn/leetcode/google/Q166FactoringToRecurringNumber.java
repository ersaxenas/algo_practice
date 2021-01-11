package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q165FactoringToRecurringNumber {
    /*
    * pd: Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
If the fractional part is repeating, enclose the repeating part in parentheses.
If multiple answers are possible, return any of them.
Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:
Input: numerator = 2, denominator = 1
Output: "2"
Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"
Example 4:
Input: numerator = 4, denominator = 333
Output: "0.(012)"
Example 5:
Input: numerator = 1, denominator = 5
Output: "0.2"
Constraints:
-231 <= numerator, denominator <= 231 - 1
denominator != 0
    * assm: num < int range, denominator != 0
    * appr:
    *
    * */

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder buff = new StringBuilder();
        String sign = (numerator < 0 == denominator < 0) ? "" : "-";
        buff.append(sign);
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        buff.append(num/den);
        long rem = num % den;
        if(rem ==0) {
            return buff.toString();
        }
        buff.append(".");
        Map<Long, Integer> patternIndex = new HashMap<>();
        while(rem != 0) {
            if(patternIndex.containsKey(rem)) {
                final Integer idx = patternIndex.get(rem);
                return buff.substring(0,idx) + "(" + buff.substring(idx) +")";
            } else {
                patternIndex.put(rem, buff.length());
            }
            rem = rem*10;
            buff.append(rem/den);
            rem = rem % den;
        }
       return buff.toString();
    }

    public static void main(String[] args) {
       
    }


}