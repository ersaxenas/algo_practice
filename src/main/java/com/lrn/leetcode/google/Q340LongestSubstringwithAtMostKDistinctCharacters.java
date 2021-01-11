package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q340LongestSubstringwithAtMostKDistinctCharacters {
    /*
    * pd: Given a string, find the length of the longest substring T that contains at most k distinct characters.
    * assm: no white space chars, string len < 10000, only english small letters, best time sol.
    * appr: sliding window
    * test cases:
    * */

    public int longestSubStr(String s, int K) {
        if(s == null || s.length() == 0 || K ==0) {
            return 0;
        }
        int ws=0, maxlen=0;
        Map<Character, Integer> charIndex = new HashMap<>();
        for(int we=0; we<s.length(); we++) {
            char wech = s.charAt(we);
            charIndex.put(wech, charIndex.getOrDefault(wech,0)+1);
            while(charIndex.size() > K ) {
                char ch = s.charAt(ws);
                int freq = charIndex.getOrDefault(ch,0)-1;
                if(freq <=0) {
                    charIndex.remove(ch);
                } else {
                    charIndex.put(ch, freq);
                }
                ws++;
            }
            maxlen = Math.max(maxlen, we-ws+1);
        }
        return maxlen;
    }

    public static void main(String[] args) {
        Q340LongestSubstringwithAtMostKDistinctCharacters sol = new Q340LongestSubstringwithAtMostKDistinctCharacters();
        System.out.println(sol.longestSubStr("abaccc", 2));
        System.out.println(sol.longestSubStr("bacc", 2));
        System.out.println(sol.longestSubStr("aba", 1));
        System.out.println(sol.longestSubStr("eceda", 2));
        System.out.println(sol.longestSubStr("aaaaa", 3));
        System.out.println(sol.longestSubStr("aaaaa", 3));
    }

}
