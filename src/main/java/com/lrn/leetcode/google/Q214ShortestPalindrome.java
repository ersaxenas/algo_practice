package com.lrn.leetcode.google;

public class Q214ShortestPalindrome {
    /*
    * pd: Given a string s, you can convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
    *Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"
Example 2:
Input: s = "abcd"
Output: "dcbabcd"
    * assm: string lend < 1000, no white space chars, only small case letters
    * appr: KMP
    * https://www.youtube.com/watch?v=4jY57Ehc14Y, https://www.youtube.com/watch?v=V5-7GzOfADQ
    * Test cases:
    * time complexity : N + N
    * */

    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String revs = s + "#" + new StringBuilder(s).reverse();
        int[] lps = getTable(revs);
        return new StringBuilder(s.substring(lps[lps.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String str) {
        // lps - largest possible suffix
        int[] lps = new int[str.length()];
        int len = 0;
        int idx = 1;
        while (idx < str.length()) {
            if (str.charAt(idx) == str.charAt(len)) {
                lps[idx] = len + 1;
                len++;
                idx++;
            } else {
                if (len != 0) {
                    len = len - 1;
                } else {
                    lps[len] = 0;
                    idx++;
                }
            }
        }
        return lps;
    }

    public int[] getTable2(String str) {
        // lps - largest possible suffix
        int[] lps = new int[str.length()];
        int len = 0;
        int idx = 1;
        while (idx < str.length()) {
            if (str.charAt(idx) == str.charAt(len)) {
                lps[idx] = len + 1;
                len++;
                idx++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[len] = 0;
                    idx++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        Q214ShortestPalindrome sol = new Q214ShortestPalindrome();
        LsUtil.printArray(sol.getTable("ababbbabbaba"));
        LsUtil.printArray(sol.getTable2("ababbbabbaba"));
//        System.out.println(sol.shortestPalindrome("abcd"));
//        System.out.println(sol.shortestPalindrome("aacecaaa"));
//        System.out.println(sol.shortestPalindrome("ababbbabbaba"));
    }


}
