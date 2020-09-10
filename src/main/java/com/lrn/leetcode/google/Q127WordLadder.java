package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Q127WordLadder {
    /*
    * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
    * assm; non null elem, no. of words < 100, best time sol
    * appr: bfs
    * */


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.add(beginWord);
        Queue<String> queue = new LinkedList<>();
        if (wordSet.contains(endWord)) {
            queue.add(endWord);
            wordSet.remove(endWord);
        } else {
            return 0;
        }
        int dist = 1;
        while (!queue.isEmpty()) {
            int qs = queue.size();
            for (int idx = 0; idx < qs; idx++) {
                String wd = queue.poll();
                List<String> prevWords = new ArrayList<>();
                for (String word : wordSet) {
                    if (wordCharDiff(wd, word) == 1) {
                        if (word.equals(beginWord)) {
                            return dist + 1;
                        }
                        prevWords.add(word);
                    }
                }
                wordSet.removeAll(prevWords);
                queue.addAll(prevWords);
            }
            dist++;
        }
        return 0;
    }

    public int wordCharDiff(String word1, String word2) {
        int diff = 0;
        for (int idx = 0; idx < word1.length(); idx++) {
            if (word1.charAt(idx) != word2.charAt(idx)) {
                diff++;
            }
        }
        return diff;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> sq = new HashSet<>(), eq = new HashSet<>(), visited = new HashSet<>(), wordset = new HashSet<>(wordList), nextlevelq = null;
        if (!wordset.contains(endWord)) {
            return 0;
        }
        sq.add(beginWord);
        eq.add(endWord);
        int len=2;/*len =2 since we have already considered begin word and end word*/
        while (!sq.isEmpty()) {
            nextlevelq = new HashSet<>(); /* all the element of the next level will go here */
            for (String currLevelWord : sq) { /*for each word in the current level queue*/
                for (int idx = 0; idx < currLevelWord.length(); idx++) { /* generate all possible combination of words by replacing one char at each position*/
                    char[] wordchars = currLevelWord.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) { /*for char from a to z*/
                        if (wordchars[idx] == ch) {
                            continue;
                        }
                        wordchars[idx] = ch;
                        String neighbour = new String(wordchars);
                        if (eq.contains(neighbour)) { /*found node in the other set so meeting point found*/
                            return len;
                        }
                        if (wordset.contains(neighbour) && visited.add(neighbour)) {
                            nextlevelq.add(neighbour);
                        }
                    }
                }
            }
            sq = (nextlevelq.size() < eq.size()) ? nextlevelq : eq;
            eq = (sq == nextlevelq) ? eq : nextlevelq;
            len++;
        }
        return 0;
    }


    public static void main(String[] args) {
        Q127WordLadder sol = new Q127WordLadder();
        System.out.println(sol.wordCharDiff("lose", "lest"));
        System.out.println(sol.ladderLength2("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(sol.ladderLength2("leet", "code", Arrays.asList("lest", "leet", "lose", "code", "lode", "robe", "lost")));
    }


}
