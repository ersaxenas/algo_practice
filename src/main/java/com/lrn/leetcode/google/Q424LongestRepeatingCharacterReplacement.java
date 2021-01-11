package com.lrn.leetcode.google;

public class Q420LongestRepeatingCharacterReplacement {
    /*
    * pd: Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
In one operation, you can choose any character of the string and change it to any other uppercase English character.
Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
Note:
Both the string's length and k will not exceed 104.
    * assm: string len < 10000, best time sol, no white spaces in string,
    * appr: dp
    * test cases:
    *
    * */

    public int characterReplacement(String s, int k) {

    }

    public int chreplaceRec(String s, int idx, int k, int len) {
        if(idx == s.length() || k == 0) {
            return len;
        }
        int len1 =0;
        if(idx > 0 && s.charAt(idx-1) != s.charAt(idx)) {
           len1 = Math.max(chreplaceRec(s,idx+1,k-1,len+1),chreplaceRec(s,idx+1,k,0));

        }
        int len2 = chreplaceRec(s, idx+1, k, len+1);
        return Math.max(len1,len2);
    }



}
