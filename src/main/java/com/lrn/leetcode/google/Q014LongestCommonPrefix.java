package com.lrn.leetcode.google;

public class Q014LongestCommonPrefix {
    /* 2021-11-25T12:24:41.721Z
    * https://leetcode.com/problems/longest-common-prefix/
    * PD: Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string "".
    * Assumptions: Input is a-z small case chars only, Array size 0 < int Max, array contain only non values,Best time solutions
    * Approach: Start with prefix = arr[0]
    *          now starting with arr[1] to N try to find common prefix between prefix and arr[N].
    *          if prefix reaches "" or null return ""
    *          When loop end return prefix.
    *
    * Test case: Input: ["flower","flow","flight"] Output: "fl"
    * Input: ["dog","racecar","car"] Output: ""
    * */

    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        if(strs == null || strs.length == 0) {
            return prefix;
        }
        if(strs.length ==1) {
            return strs[0];
        }
        prefix = strs[0];
        for(int idx=1; idx<strs.length; idx++) {
                prefix = findPrefix(prefix, strs[idx]);
                if(prefix.length() == 0) {
                    break;
                }
        }
        return prefix;
    }
    public String findPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int idx=0;
        for(;idx<len; idx++) {
            if(str1.charAt(idx) != str2.charAt(idx)) {
                 break;
            }
        }
        return str1.substring(0,idx);
    }

    public static void main(String[] args) {
        Q014LongestCommonPrefix q14LongestCommonPrefix = new Q014LongestCommonPrefix();
        System.out.println(q14LongestCommonPrefix.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(q14LongestCommonPrefix.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }


}
