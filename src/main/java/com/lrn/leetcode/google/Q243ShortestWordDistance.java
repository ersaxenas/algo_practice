package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.List;

public class Q243ShortestWordDistance {
    /*
    * pd: Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
    * assm: non null elem, list size < int max, both words are not same and are present in the list
    * appr: iterte over array and find indexes for each string. return difference
    * Test cases:
    * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
    * Input: word1 = “coding”, word2 = “practice” Output: 3
    * Input: word1 = "makes", word2 = "coding" Output: 1
    * */

    public int shortestDistance(String[] words, String word1, String word2) {
        if(words == null || words.length ==0 || word1 == null || word2 == null || word1.equals(word2)) {
            return 0;
        }
        int s1=-1, s2=-1, dist= words.length;
        for(int idx=0; idx<words.length; idx++) {
            if (word1.equals(words[idx])) {
                s1 = idx;
            }
            if (word2.equals(words[idx])) {
                s2 = idx;
            }
            if(s1 != -1 && s2 != -1) {
                dist = Math.min(dist, Math.abs(s1-s2));
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Q243ShortestWordDistance sol = new Q243ShortestWordDistance();
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println((sol.shortestDistance(words, "coding", "practice") == 3) ? "passed" : "failed");
        System.out.println((sol.shortestDistance(words, "makes", "coding") == 1) ? "passed" : "failed");
    }
}
