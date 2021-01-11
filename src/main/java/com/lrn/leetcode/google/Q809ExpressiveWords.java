package com.lrn.leetcode.google;

import java.util.LinkedList;

public class Q809ExpressiveWords {
    /*
    * pd: Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
Given a list of query words, return the number of words that are stretchy.
    * assm: best time sol, string lenth < 10000, Array len < 10000, arr elem len < 10000, only lower case letters
    * appr:
    *
    *
    * test cases:
    * Input:
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation:
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
    * */

    public int expressiveWords(String S, String[] words) {
        if (S == null || S.isEmpty() || words == null || words.length == 0) {
            return -1;
        }
        int res=0;
        for(String word: words) {
            if(isStreachy(S,word)) {
                res++;
            }
        }
        return res;
    }


    public boolean isStreachy(String S, String word) {
        if(word == null) {
            return false;
        }
        int p1 =0, p2 =0;
        while(p1 < S.length() && p2 < word.length()) {
              if(S.charAt(p1) != word.charAt(p2)) {
                  return false;
              }
              int len1 = getRepeatedLen(S, p1);
              int len2 = getRepeatedLen(word, p2);
              if((len1 < 3 && len1 != len2) || len1 < len2) {
                  return false;
              }
              p1 += len1;
              p2 += len2;
        }
        return p1 == S.length() && p2 == word.length();
    }

    public int getRepeatedLen(String str, int idx) {
        int end=idx;
        while(end < str.length() && str.charAt(idx) == str.charAt(end)) {
            end++;
        }
        return end - idx;
    }

    static class CPair {
        char ch;
        int occurance;

        public CPair(char ch, int occurance) {
            this.ch = ch;
            this.occurance = occurance;
        }
    }

    public int expressiveWords2(String S, String[] words) {
        if (S == null || S.isEmpty() || words == null || words.length == 0) {
            return 0;
        }
        int res =0;
        LinkedList<CPair> charOccrList = new LinkedList<>();
        CPair cPair = new CPair(S.charAt(0),0);
        charOccrList.add(cPair);
        for(int idx=0; idx < S.length(); idx++) {
            if(charOccrList.getLast().ch == S.charAt(idx)) {
                cPair.occurance++;
            } else {
                cPair = new CPair(S.charAt(idx),1);
                charOccrList.addLast(cPair);
            }
        }
        for(String word: words) {
            if(wordChek(charOccrList, word)) {
                res++;
            }
        }
        return res;
    }

    private boolean wordChek(LinkedList<CPair> charOccrList, String word) {
        int idx=0;
        for(CPair cPair: charOccrList) {
            if(idx >= word.length() || word.charAt(idx) != cPair.ch) {
                return false;
            }
            final Integer charOccurrence = cPair.occurance;
            int startIdx = idx;
            while(idx < word.length() && cPair.ch == word.charAt(idx)) {
                idx++;
            }
            if((charOccurrence <3 && (idx - startIdx) != charOccurrence) || (idx-startIdx) > charOccurrence) {
              return false;
            }
        }
        return idx >= word.length();
    }

    public static void main(String[] args) {
        Q809ExpressiveWords sol = new Q809ExpressiveWords();
        System.out.println(sol.expressiveWords("sass", new String[]{"sa"}));
        System.out.println(sol.expressiveWords("aaa", new String[]{"aaaa"}));
        System.out.println(sol.expressiveWords2("aaa", new String[]{"aaaa"}));
        System.out.println(sol.expressiveWords2("abcd", new String[]{"abc"}));
        System.out.println(sol.expressiveWords2("heeelloooo", new String[]{"hello", "hi", "helo"}));

    }

}
