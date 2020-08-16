package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q76MinimumWindowSubstring {
    /*
     * pd: Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
     * assm: string of len 100, best time sol.
     * appr: DP
     *
     * test case:
     * Input: S = "ADOBECODEBANC", T = "ABC"
     * Output: "BANC"
     * */

    public String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return "";
        }
        if(s.equals(t)) {
            return s;
        }
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        Map<Character, Integer> wFreqMap = new HashMap<>();
        int ws = 0;
        String minSub = "";
        int len=s.length()+1;
        for (int we = 0; we < s.length(); we++) {
            char curch = s.charAt(we);
            if (freqMap.containsKey(curch)) {
                    wFreqMap.put(curch, wFreqMap.getOrDefault(curch, 0) + 1);
            }
            while (isMatch(freqMap, wFreqMap)) {
                if ((we - ws) + 1 < len) {
                    minSub = s.substring(ws, we + 1);
                    len = minSub.length();
                }
                char wsch = s.charAt(ws);
                if(wFreqMap.containsKey(wsch)) {
                    Integer freq = wFreqMap.get(wsch);
                    if(freq-1 > 0) {
                        wFreqMap.put(wsch, freq-1);
                    } else {
                       wFreqMap.remove(wsch);
                    }
                }
                ws++;
            }
        }
       return minSub;
    }

    boolean isMatch(Map<Character, Integer> freqMap, Map<Character, Integer> wFreqMap) {
        if (freqMap.size() != wFreqMap.size()) {
            return false;
        }
        for (char ch : freqMap.keySet()) {
            if (!wFreqMap.containsKey(ch)) {
                return false;
            }
            if (wFreqMap.get(ch) < freqMap.get(ch)  ) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q76MinimumWindowSubstring sol = new Q76MinimumWindowSubstring();
        System.out.println(sol.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
        System.out.println(sol.minWindow("acbbaca", "aba"));
        System.out.println(sol.minWindow("abc", "ac"));
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC"));
    }


}
