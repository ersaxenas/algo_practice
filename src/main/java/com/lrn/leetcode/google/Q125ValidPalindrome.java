package com.lrn.leetcode.google;

public class Q125ValidPalindrome {
    /*2022-02-09T09:16:40.301Z
    * pd : https://leetcode.com/problems/valid-palindrome/
    * assm: string len < 10000, str will not be null, best time sol
    * appr: recursive
    * test cases:
    * Input: s = "A man, a plan, a canal: Panama" Output: true
    * Input: s = "race a car" Output: false
    * */

    public boolean isPalindrome(String s) {
        /*start comparing from start and end of string*/
        return isPalindromeRecursive(s,0,s.length()-1);
    }

    public boolean isPalindromeRecursive(String s, int start, int end) {
        /*if char at start is not letter or digit then skip char*/
        while(start < s.length() && !Character.isLetterOrDigit(s.charAt(start))) {
            start++;
        }
        /*if char at end is not letter or digit then skip char*/
        while(end >= 0 && !Character.isLetterOrDigit(s.charAt(end))) {
            end--;
        }
        /*start and end index meet or cross : seen all the chars in the string*/
        if(start >= end ) return true;
        /*compare char at start and end if match then compare next chars */
        if(Character.toUpperCase(s.charAt(start)) == Character.toUpperCase(s.charAt(end))) {
            return isPalindromeRecursive(s, start+1, end-1); /*recursive call to compare next chars*/
        }
        /*If char at start and end do not match then return false*/
        return false;
    }

    public static void main(String[] args) {
       Q125ValidPalindrome sol = new Q125ValidPalindrome();
        System.out.println(sol.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(sol.isPalindrome("race a car"));
        System.out.println(sol.isPalindrome("a."));
    }

}
