package com.lrn.leetcode.backtracking;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.List;

public class Q131PalindromePartitioning {
    /*
    * pd: Given a string s, partition s such that every substring of the partition is a palindrome.
    * Return all possible palindrome partitioning of s.
    * assm:
    * appr: backtracking
    *
    * */

   public List<List<String>> partition(String s) {
     List<List<String>> result = new ArrayList<>();
     if(s == null || s.isEmpty()) {
         return result;
     }
     backtrack(result, new ArrayList<>(), s.trim(), 0);
     return result;
   }

   public void backtrack(List<List<String>> result, List<String> tmplist, String s, int start) {
       if(start >= s.length()) {
          result.add(new ArrayList<>(tmplist));
       } else {
           for(int idx=start; idx<s.length(); idx++) {
               String substring = s.substring(start, idx+1);
               if(isPalindrome(substring)) {
                  tmplist.add(substring);
                  backtrack(result, tmplist, s, idx+1);
                  tmplist.remove(tmplist.size()-1);
               }
           }
       }
   }

   public boolean isPalindrome(String s) {
       if(s == null || s.isEmpty()) {
           return false;
       }
       if(s.length() == 1){
           return true;
       }
       int start=0, end = s.length()-1;
       while(start < end) {
           if(s.charAt(start) != s.charAt(end)) {
               return false;
           }
           start++; end--;
       }
       return true;
   }

    public static void main(String[] args) {
        Q131PalindromePartitioning sol = new Q131PalindromePartitioning();
        LsUtil.printListOfList(sol.partition("efe"));
    }

}
