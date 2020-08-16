package com.lrn.leetcode.google;

public class Q65ValidNumber {


    public boolean isNumber(String s) {
        if(s == null || s.isEmpty()) {
            return false;
        }
        // ignore white spaces at the starting.
        int idx=0, lastidx=s.length()-1;
        while(idx < s.length() && s.charAt(idx) == ' ') {
            idx++;
        }
        while(lastidx >=0 && s.charAt(lastidx) == ' ') {
            lastidx--;
        }
        if(idx == s.length()) {
            return false;
        }
        return isvalid(s,idx,lastidx, false,false);
    }

    public boolean isvalid(String s, int idx, int lasidx, boolean exp, boolean deci) {
        if(deci && idx > lasidx) {
            return true;
        }
        if( idx > lasidx || s.charAt(idx) == 'e') {
            return false;
        }
        boolean sign=false;
        while(idx<= lasidx) {
            char ch = s.charAt(idx);
            if(ch == ' ') {
                return false;
            } else if( ch == '-' || ch == '+') {
                if(sign || deci) {
                    return false;
                }
                sign = true;
            } else if(ch == '.') {
                if(deci) {
                    return false;
                }
                return isvalid(s, idx+1, lasidx, exp, true);
            } else if(ch == 'e') {
                if(exp) {
                    return false;
                }
                return isvalid(s, idx+1, lasidx, true,false);
            } else if(!Character.isDigit(ch)) {
                return false;
            }
            idx++;
        }
        return true;
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
        System.out.println((sol.isNumber(" 99e2.5")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber("53e2.5")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber(" --6")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber("-+3")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber("95a54e53")) ? "PASS":"FAIL");
        System.out.println(!(sol.isNumber(".")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber(".1")) ? "PASS":"FAIL");
        System.out.println((sol.isNumber("3.")) ? "PASS":"FAIL");
    }
}
