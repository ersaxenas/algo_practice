package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q131PalindromePartitioning {
    /* https://leetcode.com/problems/palindrome-partitioning
    * pd: Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
Example:
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
    * assm: no white space in between, string size < 1000, best time sol.
    * appr: backtracking
    *       "aab" -> split only if substring is palindrome
    *          a -> [ab]
    *                -> a [b]
    *                   -> b []
    *          aa [b]
    *            -> b []
    * */

    public List<List<String>> partition(String s) {
       List<List<String>> result = new ArrayList<>();
       if(s==null || s.isEmpty()) {
           return result;
       }
       backtrack(result, new ArrayList<>(), s.trim(), 0);
       return result;
    }

    public void backtrack(List<List<String>> result, List<String> tmplist, String str, int index) {
        if(index >= str.length()) {
            result.add(new ArrayList<>(tmplist));
        } else {
            for(int idx=index; idx<str.length(); idx++) {
                String subs = str.substring(index, idx+1);
                if(isPalindrome(subs)) {
                    tmplist.add(subs);
                    backtrack(result, tmplist, str, idx+1);
                    tmplist.remove(tmplist.size()-1);
                }
            }
        }
    }
    public boolean isPalindrome(String str) {
        if(str == null || str.length() == 0) {
            return false;
        }
        if(str.length() == 1) {
            return true;
        }
        int start = 0, end = str.length()-1;
        while(start < end) {
            if(str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        Q131PalindromePartitioning sol = new Q131PalindromePartitioning();
        LsUtil.printListOfList(sol.partition("aab"));
    }

}
