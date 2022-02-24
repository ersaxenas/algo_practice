package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q269AlienDictionary {
    /*
    * pd: There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
    * assm: array len < 10000, best time sol.
    * appr: topological sort
    * Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
*
    * */

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, Integer> degree = new HashMap<>();
        Map<Character, Set<Character>> characterSetMap = new HashMap<>();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                degree.putIfAbsent(ch, 0);
                characterSetMap.putIfAbsent(ch, new HashSet<>());
            }
        }
        for (int idx1 = 0; idx1 < words.length - 1; idx1++) { // compare chars of two words and prepare map
            String word1 = words[idx1];
            String word2 = words[idx1 + 1];
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            int minLen = Math.min(word1.length(), word2.length());
            for (int idx2 = 0; idx2 < minLen; idx2++) {
                char parent = word1.charAt(idx2);
                char child = word2.charAt(idx2);
                if (word1.charAt(idx2) != word2.charAt(idx2)) {
                    final Set<Character> characters = characterSetMap.get(parent);
                    if (!characters.contains(child)) {
                        characters.add(child);
                        degree.put(child, degree.get(child) + 1);
                    }
                    break;
                }
            }
        }
        Queue<Character> queue = new ArrayDeque<>();
        for (Character ch : degree.keySet()) {
            final Integer deg = degree.get(ch);
            if (deg == 0) {
                queue.add(ch);
            }
        }

        StringBuilder buffer = new StringBuilder();
        while (!queue.isEmpty()) {
            final Character source = queue.poll();
            buffer.append(source);
            for (Character child : characterSetMap.get(source)) {
                final int deg = degree.get(child) - 1;
                degree.put(child, deg);
                if (deg == 0) {
                    queue.add(child);
                }
            }
        }

        if (buffer.length() != degree.size()) {
            return "";
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Q269AlienDictionary sol = new Q269AlienDictionary();
        System.out.println(sol.alienOrder(new String[]{"wrt","wrf","er","ett","rftt","te"}).equals("wertf") ? "pass" : "fail");
        System.out.println(sol.alienOrder(new String[]{"za", "zb", "ca", "cb"}).equals("azbc") ? "pass" : "fail");
        System.out.println(sol.alienOrder(new String[]{"abc", "ab"}).equals("") ? "pass" : "fail");
        System.out.println(sol.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}).equals("wertf") ? "pass" : "fail");
        System.out.println(sol.alienOrder(new String[]{"z", "x"}).equals("zx") ? "pass" : "fail");
        System.out.println(sol.alienOrder(new String[]{"z", "x", "z"}).equals("") ? "pass" : "fail");
    }


}
