package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Q30SubstringWithConcatenationOfAllWords {
    /*
    * You are given a string, s, and a list of words, words, that are all of the same length.
    * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
     * */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.isEmpty() || words.length ==0 || s.length() < words.length) {
            return result;
        }
        HashMap<String, Integer> freqmap = new HashMap<>();
        for(String w: words) {
            freqmap.put(w, freqmap.getOrDefault( w,0)+1);
        }
        int wcnt=words.length, wlen=words[0].length();
        for(int ws=0; ws<=(s.length() - (wcnt*wlen)); ws++) {
            int wordMatch = freqmap.size();
            HashMap<String, Integer> seen = new HashMap<>();
            for(int idx=0; idx<words.length; idx++) {
                int wstart = ws + (idx*wlen);
                String sbword = s.substring(wstart, (wstart+wlen));
                // check if word exists
                if(freqmap.containsKey(sbword)) {
                    seen.put(sbword, seen.getOrDefault(sbword,0)+1);
                } else {
                    break; // start with next index
                }
                // check for freq greater then required
                Integer seenfreq = seen.get(sbword);
                Integer reqfreq = freqmap.get(sbword);
                if(seenfreq > reqfreq) {
                    break; // freq is more then required
                } else if(seenfreq < reqfreq) {
                    continue;
                } else {
                    wordMatch--;
                }
                if(wordMatch ==0) {
                    result.add(ws);
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q30SubstringWithConcatenationOfAllWords q30SubstringWithConcatenationOfAllWords = new Q30SubstringWithConcatenationOfAllWords();
        System.out.println(q30SubstringWithConcatenationOfAllWords.findSubstring("abababab", new String[]{"a", "b", "a"}).stream().map(String::valueOf).collect(Collectors.joining()));

    }
}
