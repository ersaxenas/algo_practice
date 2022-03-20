package com.lrn.leetcode.weeklycomp;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class WContest282 {


    /**
     * https://leetcode.com/contest/weekly-contest-282/problems/counting-words-with-a-given-prefix/
     * 2185. Counting Words With a Given Prefix
     * <p>
     * User Accepted: 10461
     * User Tried: 10792
     * Total Accepted: 10790
     * Total Submissions: 14232
     * Difficulty: Easy
     * <p>
     * You are given an array of strings words and a string pref.
     * <p>
     * Return the number of strings in words that contain pref as a prefix.
     * <p>
     * A prefix of a string s is any leading contiguous substring of s.
     */

    static class RunningSumArray {
        public int prefixCount(String[] words, String pref) {
            char[] prefixCharArray = pref.toCharArray(); /* Char array for prefix*/
            int cnt = 0; /* Count to trac prefix match*/
            for (String word : words) { /* check for each word*/
                if (isPrefix(word.toCharArray(), prefixCharArray)) {
                    cnt++; /*Prefix found so increment count*/
                }
            }
            return cnt; /*return count*/
        }

        boolean isPrefix(char[] word, char[] prefixCharArray) {
            /*if word length is less than prefix length : return false */
            if (word.length < prefixCharArray.length) return false;
            /*start comparing chars */
            for (int idx = 0; idx < prefixCharArray.length; idx++) {
                if (word[idx] != prefixCharArray[idx]) return false; /* chars didn't match at idx : return false*/
            }
            return true;
        }
    }
    /*
    * 2186. Minimum Number of Steps to Make Two Strings Anagram II
    * */
    static class ToStringsAnagram {
        public int minSteps(String s, String t) {
            int steps = 0;
            int[] scharr = new int[26];
            int[] tcharr = new int[26];
            for(char ch: s.toCharArray()) {
                scharr[ch - 'a']++;
            }
            for(char ch: t.toCharArray()) {
                tcharr[ch - 'a']++;
            }
            for(int idx =0 ; idx < 26; idx++) {
                steps = steps + Math.abs(scharr[idx] - tcharr[idx]);
            }
            return steps;
        }
    }

}
