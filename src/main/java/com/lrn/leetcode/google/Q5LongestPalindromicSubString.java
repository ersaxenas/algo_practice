package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q5LongestPalindromicSubString {
    /*5. Longest Palindromic Substring : https://leetcode.com/problems/longest-palindromic-substring/*/
    /*Approach 1: using recursion*/
    public String findLongestPalindrome(String str) {
        if(str == null || str.trim().length() <= 1) {
            return str;
        }
        Map<String,String> cache = new HashMap<>();
        return findLongestPalindrome(str, 0, str.length()-1, cache);
    }
    public String findLongestPalindrome(String str, int start, int end, Map<String,String> cache) {
        if(start > end) {
           return null;
        }
        if(cache.containsKey(start+":"+end)) {
            return cache.get(start+":"+end);
        }
        if(isPalindrome(str, start,end)) return str.substring(start, end+1);
        String str1 = findLongestPalindrome(str,start+1, end, cache);
        String str2 = findLongestPalindrome(str,start, end-1,cache);
        String palindrome;
        if(str1 == null && str2 == null) {
            palindrome =  null;
        } else if(str1==null) {
            palindrome = str2;
        } else if(str2 == null) {
            palindrome = str1;
        } else {
            palindrome = (str1.length() > str2.length()) ? str1 : str2;
        }
        cache.put(start+":"+end, palindrome);
        return palindrome;
    }

    public boolean isPalindrome(String str, int start, int end) {
        while(start < end) {
            if(str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    /*
    * Approach : Start from idx 0 to N < string.len
    *  for each idx expand from idx and idx+1 to check for palindrome.
    *  Ex: ababa ->
    * iteration 1 : (a - expand and check for palindrome) (a,b expand and check)
    *              if palindrome store maxLen and start
    * iteration 2 : (b - expand and check for palindrome) (b,a expand and check)
    *              if palindrome store maxLen and start
    * -------------
    * check for palindrome : string , start ,end
    * check if char at start == char at end then repeat for start-1 and end+1
    * ex:  abbcbba
    *    start 3 and end 3 : c == c
    *    start 2 and end 4 : b == b
    *    ...
    *
    * */
    int maxLen = 0, start=0;
    public String findPalindrome2(String str) {
        if(str == null || str.length() <= 1) {
            return str;
        }
        maxLen=0; start =0;
        for(int idx=0; idx<str.length(); idx++) {
            expand(str,idx,idx);
            expand(str,idx,idx+1);
        }
      return str.substring(start, start+maxLen);
    }

    public void expand(String str, int left, int right) {
        while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--; right++;
        }
        if(maxLen < (right -left -1)) { // here -1 is because while when while loop ends anagram will be at left+1 and right-1
            maxLen = (right - left -1);
            start = left+1;
        }
    }

    public static void main(String[] args) {
        Q5LongestPalindromicSubString q5LongestPalindromicSubString = new Q5LongestPalindromicSubString();
        String str = "babad";
        System.out.println(q5LongestPalindromicSubString.findLongestPalindrome(str));
        System.out.println(q5LongestPalindromicSubString.findLongestPalindrome("cbbd"));
        System.out.println(q5LongestPalindromicSubString.findLongestPalindrome("aaa"));
        System.out.println(q5LongestPalindromicSubString.findLongestPalindrome("abcd"));
    }

}
