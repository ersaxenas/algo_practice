package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class Q127WordLadder {
    /* https://leetcode.com/problems/word-ladder
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

    //https://leetcode.com/problems/word-ladder/discuss/40711/Two-end-BFS-in-Java-31ms.

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int dist = 1;
        while (!queue.isEmpty()) {
               int qsize = queue.size();
               for(int idx=0; idx<qsize; idx++) {
                   String word = queue.poll();
                   for(String child: getNextLevel(word, dict)) {
                       if(endWord.equals(child)) return dist+1;
                       if(!visited.contains(child)) {
                           queue.add(child);
                           visited.add(child);
                       }
                   }
               }
               dist++;
        }
        return 0;
    }
    /*best time complexity - double bfs. */
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        Set<String> beginset = new HashSet<>(); // starting side set (left side)
        beginset.add(beginWord);
        Set<String> endset = new HashSet<>();// end side set ( right side )
        endset.add(endWord);
        ArrayDeque<String> queue = new ArrayDeque<>();
        int dist = 1;
        Set<String> tmp;
        while (!beginset.isEmpty() && !endset.isEmpty()) {
               queue.addAll(beginset); // add elem from selected set to queue ( starting with begin set )
               beginset.clear(); // clear selected set;
               while(!queue.isEmpty()) { // BFS get next level
                   String word = queue.poll();
                   for(String child: getNextLevel(word, dict)) {
                       if(endset.contains(child)) return dist+1; // found child in next node so return distance;
                       if(!visited.contains(child)) {
                           visited.add(child);
                           beginset.add(child);
                       }
                   }
               }
               dist++;
               if(beginset.size() > endset.size()) {
                   /*switch sets -- choose smaller set of two
                   * smaller set of X will say will save N nodes in the next level
                   * and chances of finding next level node in larger set Y is higher.
                   * */
                  tmp = beginset;
                  beginset = endset;
                  endset = tmp;
               }
        }
        return 0;
    }


    public List<String> getNextLevel(String word, Set<String> dict) {
        List<String> wordList = new ArrayList<>();
        char[] wchar = word.toCharArray();
        for (int idx = 0; idx < wchar.length; idx++) {
            char och = wchar[idx];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (och == ch) continue;
                wchar[idx] = ch;
                String nword = new String(wchar);
                if (dict.contains(nword)) {
                    wordList.add(nword);
                }
            }
            wchar[idx] = och;
        }
        return wordList;
    }

    //--------------------------------------------------------------
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
        int len = 2;/*len =2 since we have already considered begin word and end word*/
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
        System.out.println(sol.ladderLength4("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(sol.ladderLength4("leet", "code", Arrays.asList("lest", "leet", "lose", "code", "lode", "robe", "lost")));
    }


}
