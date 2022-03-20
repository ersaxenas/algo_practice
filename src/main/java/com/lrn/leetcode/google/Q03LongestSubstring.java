package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Q03LongestSubstring {
    /*
    * problem def: Longest substring without repeating chars
    * Assumptions: String of large lenght, can use additional memory, best time solution.
    * Test case:
    * 1. "abcabcabc" res: 3
    * 2. "bbbb" res 1
    * 3. "" res 0
    * 4. "a" res  1
    * 5.  null res 0.
    * Appraoch : sliding window -
    * wins =0 wine=0 mapOfChars key=char value index {}, maxsize
    * iterate over string for each char
    *  if(char is present in mapOfChars)  then set wins to max of wins or mapOfchars.get - last seen index
    *   put latest index of char in mapOfChars
    *  maxsize = max of maxsize or wine-wins + 1
    * */

    public static int lenghtOfLongestSubstring(String str) {
        if(str == null || str.length() ==0) {
            return 0;
        }
        if(str.length()  == 1) {
            return 1;
        }
        int maxLen =0, wins=0;
        HashMap<Character, Integer> charMap = new HashMap<>();
        for(int wine=0; wine<str.length(); wine++) {
            char currChar = str.charAt(wine);
            if(charMap.containsKey(currChar)) {
               wins = Math.max(wins, charMap.get(currChar) +1);
            }
            charMap.put(currChar, wine);
            maxLen = Math.max(maxLen, (wine -wins) + 1);
        }
        return maxLen;
    }
    public static void main(String[] args) {
        System.out.println(3 == lenghtOfLongestSubstring("abcabcabc"));
        System.out.println(1 == lenghtOfLongestSubstring("bbbb"));
        System.out.println(0 == lenghtOfLongestSubstring(""));
        System.out.println(1 == lenghtOfLongestSubstring("a"));
        System.out.println(0 == lenghtOfLongestSubstring(null));
        System.out.println(3 == lenghtOfLongestSubstring("pwwkew"));
        System.out.println(2 == lenghtOfLongestSubstring("abba"));
    }
}
