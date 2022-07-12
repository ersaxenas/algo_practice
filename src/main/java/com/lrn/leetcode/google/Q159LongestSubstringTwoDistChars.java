package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q159LongestSubstringTwoDistChars {
    /*2022-04-24T18:04:06.955Z 
    https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
    * pd: Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
    * test cases:
    * Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
    *Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
    * assm: non white space chars, string len < 1000, best time sol
    * appr: sliding window
    *
    * */

    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        int ws=0, maxlen=0;
        for(int we=0; we < s.length(); we++) {
            char ch = s.charAt(we);
            freqMap.put(ch, freqMap.getOrDefault(ch,0)+1);
            while(freqMap.size() > 2) {
                char wsch = s.charAt(ws++);
                int freq = freqMap.get(wsch)-1;
                if(freq <= 0) {
                    freqMap.remove(wsch);
                } else {
                    freqMap.put(wsch, freq);
                }
            }
            maxlen = Math.max(maxlen, (we-ws+1));
        }
        return maxlen;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int maxlen =0;
        int ws=0;
        Map<Character, Integer> charFreqMap = new HashMap<>();
        for(int we=0; we < s.length(); we++) {
            final char ch = s.charAt(we);
            charFreqMap.put(ch, charFreqMap.getOrDefault(ch,0)+1);
            if(charFreqMap.size()<=2) {
                maxlen = Math.max(maxlen,(we-ws+1));
            } else {
                while(charFreqMap.size() > 2 && ws <= we) {
                    char chAtWs = s.charAt(ws);
                    int freq = charFreqMap.get(chAtWs)-1;
                    if(freq == 0) {
                        charFreqMap.remove(chAtWs);
                    } else {
                        charFreqMap.put(chAtWs,freq);
                    }
                    ws++;
                }
            }
        }
        return maxlen;
    }


}
