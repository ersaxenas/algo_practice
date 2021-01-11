package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q242ValidAnagram {
    /*
     * pd: Given two strings s and t , write a function to determine if t is an anagram of s.
     * assm: no space chars, str len < int.max, best time sol.
     * appr:
     *
     * test cases:
     * Input: s = "anagram", t = "nagaram" Output: true
     * Input: s = "rat", t = "car" Output: false
     * */

    //O(n) -- 18 ms
    public boolean isAnagram2(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int idx=0; idx<s.length(); idx++) {
            freqMap.put(s.charAt(idx), freqMap.getOrDefault(s.charAt(idx), 0) + 1);
            freqMap.put(t.charAt(idx), freqMap.getOrDefault(t.charAt(idx), 0) - 1);
        }
        for(char ch: freqMap.keySet()) {
            if(freqMap.get(ch) != 0) {return false;}
        }
      return true;
    }
   //O(n) -- 9 ms
    public boolean isAnagram(String s, String t) {

        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        for (char ch : t.toCharArray()) {
            final Integer freq = freqMap.get(ch);
            if(freq == null) {
                return false;
            }
            if(freq == 1) {
                freqMap.remove(ch);
            } else {
                freqMap.put(ch, freq-1);
            }
        }
        return freqMap.size() == 0;

    }

    public static void main(String[] args) {
        Q242ValidAnagram sol = new Q242ValidAnagram();
        System.out.println(sol.isAnagram("anagram", "nagaram"));
        System.out.println(sol.isAnagram("rat", "car"));
    }

}
