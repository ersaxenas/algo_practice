package com.lrn.leetcode.google;

public class Q132PalindromePartitioning2 {
    /*
    * pd: Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.
    * assm: non null and no white spaces and lower case letters only, string len < 1000, best time sol
    * appr: DP
    * test cases:
    * Input: aab output: 1
    * input: a   output 0
    * input: ab output 1
    * */

    public int minCut(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] noOfCuts = new int[s.length()];
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for (int len = 0; len < s.length(); len++) {
            int minCuts = len; // starting with worst case here: a single char is considered as palindrome so worst case a string will have be to cut in len-1 times to make it palindrome
            for (int cut = 0; cut <= len; cut++) {
                if (s.charAt(cut) == s.charAt(len) // first and last char of string (cut to len) is matching so this string can be palindrome
                        &&
                        ( // check if rest of char between cut to len make a palindrome string
                                cut + 1 > len - 1 // if cut+1 and len-1 cross each other means there are no more chars in between cut and len
                                        ||
                                        isPalindrome[cut + 1][len - 1] // if there are more char between cut and len then we must have seen this string in previous iterations.
                        )
                ) {
                    isPalindrome[cut][len] = true;
                    if (cut == 0) {
                        // if cut started at index 0 means string is palindrome starting from cut to len
                        minCuts = 0;
                    } else {
                        // if cut is starting at index n and string from n to len is palindrome so how many cuts are required to make string starting from 0 to n-1 palindrome
                        minCuts = Math.min(minCuts, noOfCuts[cut - 1]+1);
                    }
                }
            }
            noOfCuts[len] = minCuts;
        }
        return noOfCuts[s.length() - 1];
    }


    public static void main(String[] args) {
        Q132PalindromePartitioning2 sol = new Q132PalindromePartitioning2();
        System.out.println(sol.minCut("aab") == 1 ? "passed" : "failed");
        System.out.println(sol.minCut("a") == 0 ? "passed" : "failed");
        System.out.println(sol.minCut("ab") == 1 ? "passed" : "failed");
    }

}
