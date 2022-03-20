package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q140WordBreak2 {

   /*2022-03-20T11:31:02.261Z
   *HARD: https://leetcode.com/problems/word-break-ii/
   *
   * */

    // using backtracking
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        // buffer initialized with string
        backtrack(new StringBuilder(s), s.length(), result, wordDict);
        return result;
    }

    public void backtrack(StringBuilder buffer, int end,  List<String> result, List<String> wordDict) {
        if(end  == 0 ) {
            System.out.println("adding: " + buffer);
            result.add(buffer.toString());
        } else if( end > 0 ) {
            for(String word: wordDict) {
                if(end-word.length() < 0) continue;// word len is larger than string on the left
                String tst = buffer.substring(end-word.length() ,end);
                if(tst.equals(word)) {
                    System.out.println(end-word.length()+" <> "+ end + " : " + tst+ " <> "+ word);
                    if(end - word.length() != 0 ) buffer.insert(end-word.length(), " "); // add space for word
                    backtrack(buffer, end-word.length(), result, wordDict);
                    if(end - word.length() != 0 ) buffer.deleteCharAt(end-word.length());
                }
            }
        }
    }
    public static void main(String[] args) {
        Q140WordBreak2 sol = new Q140WordBreak2();
        sol.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
    }

}
