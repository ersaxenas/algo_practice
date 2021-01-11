package com.lrn.leetcode.google;

public class Q424LongestRepeatingCharacterReplacement {
    /*
    * pd: Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
In one operation, you can choose any character of the string and change it to any other uppercase English character.
Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
Note:
Both the string's length and k will not exceed 104.
    * assm: string len < 10000, best time sol, no white spaces in string,
    * appr: dp
    * test cases:
    *
    * */

    public int characterReplacement(String s, int k) {
       int ws=0, mostFreqCharSeenInWindow=0, maxLen=0;
       int[] freq = new int[26];
       for(int we=0; we<s.length(); we++) {
           final int ch = s.charAt(we) - 'A';
           freq[ch]++;
           mostFreqCharSeenInWindow = Math.max(mostFreqCharSeenInWindow, freq[ch]);
           if((we-ws+1) - mostFreqCharSeenInWindow > k) {
               freq[s.charAt(ws)-'A']--;
               ws++;
           }
           maxLen = Math.max(maxLen, (we-ws)+1);
       }
       return maxLen;
    }



    public static void main(String[] args) {
        Q424LongestRepeatingCharacterReplacement sol = new Q424LongestRepeatingCharacterReplacement();
        System.out.println(sol.characterReplacement("ABAB", 2));
        System.out.println(sol.characterReplacement("AABABBA", 1));
    }

}
