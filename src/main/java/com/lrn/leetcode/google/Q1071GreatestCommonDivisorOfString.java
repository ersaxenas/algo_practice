package com.lrn.leetcode.google;

public class Q1071GreatestCommonDivisorOfString {
    /*
    * pd: https://leetcode.com/problems/greatest-common-divisor-of-strings/
    * assm:  1 <= str len < 1000, str2 len <= str1 len, best time sol
    * appr: trick
    * test cases:
    * */


    public String gcdOfStrings(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1)) return "";
        int len = gcd(str1.length(), str2.length());
        return str1.substring(0,len);
    }

    public int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
}
