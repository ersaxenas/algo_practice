package com.lrn.leetcode.google;

import java.util.HashMap;

public class Q087ScrambleString {
    /*2022-01-11T15:32:29.806Z
    * pd: https://leetcode.com/problems/scramble-string
    * assm: string len < 10000, string only contain lower case small letters and no white spaces, best time sol.
    * appr:
    *
    * test case:
    * */

    public boolean isScramble(String s1, String s2, HashMap<String, String> cache) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if(s1.equals(s2)) return true;
        if(s2.equals(cache.get(s1))) {
            return false;
        }

        int[] letters = new int[26];

        for(int idx=0; idx< s1.length(); idx++) {
            letters[s1.charAt(idx) - 'a']++;
            letters[s2.charAt(idx) - 'a']--;
        }
        for(int idx=0; idx<letters.length; idx++) {
            if(letters[idx] != 0) return false;
        }
        for(int cut=1; cut<s1.length(); cut++) {
            if(
                    isScramble(s1.substring(0,cut), s2.substring(0,cut), cache) &&
                    isScramble(s1.substring(cut), s2.substring(cut), cache)
            ) return true;
            if(
                    isScramble(s1.substring(0,cut), s2.substring(s2.length()-cut), cache) &&
                    isScramble(s1.substring(cut), s2.substring(0, s2.length()-cut), cache)
            ) return true;
        }
        cache.put(s1,s2);
       return false;
    }

    public static void main(String[] args) {
        Q087ScrambleString sol = new Q087ScrambleString();
        System.out.println(sol.isScramble("great", "rgeat", new HashMap<>()));
        System.out.println(sol.isScramble("abcde", "caebd", new HashMap<>()));
    }


}
