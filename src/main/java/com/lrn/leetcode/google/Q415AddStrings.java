package com.lrn.leetcode.google;

public class Q415AddStrings {
    /*
    * pd: Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
Note:
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
    * assm: num1 & num1 len < 4, best time sol, only digits 0 to 9, no leading zeros
    * appr:
    * test cases:
    * */
    
    
    public String addStrings(String num1, String num2) {
        String sum = new String();
        int carry=0;
        int len = Math.max(num1.length(), num2.length());
        final int nl1 = num1.length();
        final int nl2 = num2.length();
        int idx=1;
        for(; idx<=len; idx++) {
            int ch1 = (nl1 -idx >= 0) ? num1.charAt(nl1-idx) - '0' : 0;
            int ch2 = (nl2 -idx >= 0) ? num2.charAt(nl2-idx) - '0' : 0;
            int tsum = ch1+ch2 + carry;
            sum =  (tsum%10) + sum;
            carry = tsum/10;
        }
        if(carry > 0) {
            sum = carry +sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        Q415AddStrings sol = new Q415AddStrings();
        System.out.println(sol.addStrings("1999", "22222"));
    }




}
