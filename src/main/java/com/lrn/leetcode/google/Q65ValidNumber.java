package com.lrn.leetcode.google;

public class Q65ValidNumber {
    /*https://leetcode.com/problems/valid-number/*/

    public boolean isNumber(String s) {
        if(s == null || s.isEmpty()) {
            return false;
        }
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numSeen = false;
        // sign +- can only come at beginning of string 0 or after e
        String str = s.trim();
        for(int idx=0; idx<str.length(); idx++) {
            char ch = str.charAt(idx);
            if(ch >= '0' && ch <= '9') {
                numSeen = true;
            } else if(ch == '.') {
               if(eSeen || pointSeen) { // . cannot come after e and multiple points are not allowed
                   return false;
               }
               pointSeen = true;
            } else if( ch == 'e' || ch == 'E') {
               if(eSeen || !numSeen) { // e can only come after num and come multiple times
                  return false;
               }
               eSeen = true;
               numSeen = false; // after e we expect to see num once again
            } else if( ch == '+' || ch == '-') {
                // sign can only come at 0 index and can after e
                    if( idx != 0 && str.charAt(idx-1) != 'e') {
                        return false;
                    }
            } else {
                return false;
            }
        }
        return numSeen;
    }


    public static void main(String[] args) {
        Q65ValidNumber sol = new Q65ValidNumber();
        System.out.println((sol.isNumber("0")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber(" 0.1")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber("abc")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber("1 a")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber("0e10")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber("-90e3")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber(" 1e")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber("e3")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber(" 6e-1")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber(" 99e2.5")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber("53e2.5")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber(" --6")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber("-+3")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber("95a54e53")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber(".")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber(".1")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber("3.")) ? "PASS":"FAIL");
    }
}
