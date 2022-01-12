package com.lrn.leetcode.google;

public class Q28ImplementStrStr {

    /* 2021-12-10T16:02:10.863Z
     * https://leetcode.com/problems/implement-strstr/
     * pd: Implement strStr().Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Assm: str len 0 < N < int max, best time solution, consider white space
     * Aprc: iterate of over string j 0
     *       if( haystack ch at i = needle ch at j ) then look for needle in the string
     *       if not keep movin forward
     *       h e l l o , ll
     *       0 1 2 3 4
     *
     * test cases:
     * */
    public int strStr(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        if(l1 < l2) {
            return -1;
        } else if (l2 ==0){
            return 0;
        }
        for (int idx = 0; idx < haystack.length(); idx++) {
            for (int n = 0; ; n++) {
                if(n == needle.length()) {return idx;}
                if(idx + n == haystack.length()) {return -1;}
                if(haystack.charAt(idx+n) != needle.charAt(n)) {break;}
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q28ImplementStrStr q28ImplementStrStr = new Q28ImplementStrStr();
        System.out.println(q28ImplementStrStr.strStr("hello", "ll"));
        System.out.println(q28ImplementStrStr.strStr("aaaaa", "bba"));
        System.out.println(q28ImplementStrStr.strStr("a", "a"));
    }

}
