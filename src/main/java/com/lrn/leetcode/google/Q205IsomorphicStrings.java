package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q205IsomorphicStrings {
    /* (Trick is to match each char at an index to an int)
    * pd: Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
    * assm: non null, no white space and lower case chars only, String len < 1000, best time sol
    * appr: use map to map chars and if a chars is being mapped some other char then return false
    *       no. of distinct chars in both strings must be same and length of both the strings must be same.
    * Test cases:
    * input : egg, add output: true
    * input : foo, bar output: false
    * input : paper, title output: true
    * */

    public boolean isIsomorphic1(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for (int idx = 0; idx < s.length(); idx++) {
            if (m1[s.charAt(idx)] != m2[t.charAt(idx)]) {
                return false;
            }
            m1[s.charAt(idx)] = idx + 1;
            m2[t.charAt(idx)] = idx + 1;

        }
        return true;
    }


    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        Map<Character, Character> charMap = new HashMap<>();
        Set<Character> seen = new HashSet<>();
        for (int idx = 0; idx < s.length(); idx++) {
            Character ch1 = s.charAt(idx);
            Character ch2 = t.charAt(idx);
            if ((charMap.containsKey(ch1) && !(charMap.get(ch1) == ch2))) {
                return false;
            } else if (!charMap.containsKey(ch1) && seen.contains(ch2)) {
                return false;
            } else {
                charMap.put(ch1, ch2);
                seen.add(ch2);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q205IsomorphicStrings sol = new Q205IsomorphicStrings();
        System.out.println(sol.isIsomorphic1("egg", "add") ? "passed" : "failed");
        System.out.println(!sol.isIsomorphic1("foo", "bar") ? "passed" : "failed");
        System.out.println(sol.isIsomorphic1("paper", "title") ? "passed" : "failed");
        System.out.println(!sol.isIsomorphic1("ab", "aa") ? "passed" : "failed");
    }

}
