package com.lrn.leetcode.google;

public class Q67AddBinary {
    /*
     *
     * */

    public String addBinary(String a, String b) {
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }
        int maxl = Math.max(a.length(), b.length());
        int carry = 0;
        String res = "";
        for (int idx = 1; idx <= maxl; idx++) {
            char ch1 = (a.length() - idx >= 0) ? a.charAt(a.length() - idx) : 0;
            char ch2 = (b.length() - idx >= 0) ? b.charAt(b.length() - idx) : 0;
            int sum = carry;
            if (ch1 == '1' && ch2 == '1') {
                sum = sum + 10;
            } else if (ch1 == '1' || ch2 == '1') {
                sum = sum + 1;
            }
            if (sum == 2) {
                sum = 10;
            }
           res = (sum %10) + res;
            carry = sum / 10;
        }
        if(carry != 0) {
            res = "1" + res;
        }
       return  res;
    }

    public static void main(String[] args) {
        Q67AddBinary sol = new Q67AddBinary();
        System.out.println(sol.addBinary("1010", "1011"));
        System.out.println(sol.addBinary("1", "0"));
        System.out.println(sol.addBinary("0", "0"));
        System.out.println(sol.addBinary("1", "1"));
        System.out.println(sol.addBinary("10", "1"));
        System.out.println(sol.addBinary("11", "1"));
        System.out.println(sol.addBinary("101", "1"));
        System.out.println(sol.addBinary("111", "11"));
    }

}
