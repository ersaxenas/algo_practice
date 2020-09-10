package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.List;

public class Q139WordBreak {
    /*
    * pd: iven a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
    * assm: no white space, str len < 1000, best time sol.
    * appr: recursion
    *       start from index 1 keep including char and see if word can be found in dict
    *  BU:
    *  Ex. [abc] [a b bc]
    *      0 to 3
    *      (0)  a -> present in dic, so dp[T,F,F]
    *      (1)  ab -> dic word a
    *                          -> a len is 1 so split string from last to 1 len -> 1-1 + 1 = substr(1,1+1) = b and b != a
    *                          -> b len is 1 so split string from last to 1 len -> 1-1 + 1 = substr(1,1+1) = b and b == b
    *                             then b last half of string ab so if first half is present in the string then ab is true;
    *                             dp[index - word.len] == TRUE THEN dp [T]
    *                             dp[T,T,F]
    *     (3) abc -> so we will try
    *                             a ->
    *                             b ->
    *                             bc -> will work
    * */


    public boolean wordBreakDPBU(String str, List<String> wordDict) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        boolean[] dp = new boolean[str.length()];
        for (int len = 0; len < str.length(); len++) { // for string of len 0 to str.length
            for (String word : wordDict) { // each word in dict
                if (
                        word.length() <= len + 1 && // check if word is not longer then current string substr(len+1)
                                str.substring(
                                        len - word.length() + 1, // here +1 since index start at 0
                                        len + 1
                                ).equals(word) // check if word is present in the string here we are splitting from end
                ) {
                    if (len - word.length() < 0) { // means word length is equal to current string len
                        dp[len] = true;
                    } else {
                        /* mean word length is less then current string length and we are splitting the string.
                         * [  aaa   bbb ]
                         *   |fh|  |lh|
                         *  last half (lh) is present in the string if first half is present then dp true;
                         */
                        dp[len] = dp[len - word.length()];
                    }
                    if(dp[len]){
                        break;
                    }
                }
            }
        }
        return dp[str.length()-1];
    }


    /*TLE*/
    public boolean wordBreak(String str, List<String> wordDict) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        for (int idx = 0; idx < wordDict.size(); idx++) {
            final String word = wordDict.get(idx);
            if (word.length() <= str.length() && str.startsWith(word)) {
                if (wordBreak(str.substring(word.length()), wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q139WordBreak sol = new Q139WordBreak();
        System.out.println(sol.wordBreakDPBU("dogs", Arrays.asList("dog", "s", "gs")));
        System.out.println(sol.wordBreakDPBU("cars", Arrays.asList("car", "ca", "rs")));
        System.out.println(sol.wordBreakDPBU("leetcode", Arrays.asList("leet", "code")));
        System.out.println(sol.wordBreakDPBU("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(sol.wordBreakDPBU("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }


}
