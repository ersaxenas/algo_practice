package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q290WordPattern {
    /*
     * pd: https://leetcode.com/problems/word-pattern
     * assm: pattern len < 1000, string len < 1000, contains only lower case english char with white space only, best time sol
     * appr:
     * test cases:
     * */

    public boolean wordPattern2(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;
        // idea is to map word to idx and char to idx
        Map<String, Integer> indexmap = new HashMap<>();
        for (int idx = 0; idx < words.length; idx++) {
            if (indexmap.getOrDefault("-" + words[idx], -1).intValue() == indexmap.getOrDefault("" + pattern.charAt(idx), -1).intValue()) {
                indexmap.put("-" + words[idx], idx);
                indexmap.put("" + pattern.charAt(idx), idx);
            } else {
                return false;
            }
        }
        return true;
    }

    // brute force
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;
        Map<String, Character> wordtochar = new HashMap<>();
        Map<Character, String> chartoword = new HashMap<>();
        char[] chars = pattern.toCharArray();
        for (int idx = 0; idx < chars.length; idx++) {
            if (chartoword.containsKey(chars[idx])) {
                if (!words[idx].equals(chartoword.get(chars[idx]))) {
                    return false;
                }
            }
            if (wordtochar.containsKey(words[idx])) {
                if (wordtochar.get(words[idx]) != chars[idx]) {
                    return false;
                }
            } else {
                chartoword.put(chars[idx], words[idx]);
                wordtochar.put(words[idx], chars[idx]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q290WordPattern sol = new Q290WordPattern();


        System.out.println(sol.wordPattern("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd", "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t"));
        System.out.println(sol.wordPattern("abc", "b c a"));
        System.out.println(sol.wordPattern("abba", "dog cat cat dog"));
        System.out.println(sol.wordPattern("abba", "dog cat cat fish"));
        System.out.println(sol.wordPattern("aaaa", "dog cat cat dog"));
        System.out.println(sol.wordPattern("abba", "dog dog dog dog"));
        System.out.println("----------------------------");
        System.out.println(sol.wordPattern2("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd", "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t"));
        System.out.println(sol.wordPattern2("abc", "b c a"));
        System.out.println(sol.wordPattern2("abba", "dog cat cat dog"));
        System.out.println(sol.wordPattern2("abba", "dog cat cat fish"));
        System.out.println(sol.wordPattern2("aaaa", "dog cat cat dog"));
        System.out.println(sol.wordPattern2("abba", "dog dog dog dog"));
    }


}
