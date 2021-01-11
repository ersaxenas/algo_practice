package com.lrn.leetcode.google;

public class Q387FirstUniqueCharacterString {
    /*
    * pd: Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
    * assm: string len < 10000, best time sol, only lower case a to z char
    * appr:
    * test cases:
    * */

    public int firstUniqueChar(String s) {
        if(s == null || s.isEmpty()) {
            return -1;
        }
        int[] freq = new int[26];
        for(int ch: s.toCharArray()) {
            freq[ch -'a']++;
        }
        for(int ch: s.toCharArray()) {
            if(freq[ch-'a'] == 1) {
                return s.indexOf(ch);
            }
        }
       return -1;
    }

    public static void main(String[] args) {
        Q387FirstUniqueCharacterString sol = new Q387FirstUniqueCharacterString();
        System.out.println(sol.firstUniqueChar("leetcode"));
        System.out.println(sol.firstUniqueChar("loveleetcode"));
    }


}
