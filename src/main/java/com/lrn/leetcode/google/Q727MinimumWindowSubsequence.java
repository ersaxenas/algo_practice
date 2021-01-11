package com.lrn.leetcode.google;

public class Q727MinimumWindowSubsequence {
    /*
    * pd: Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
    * assm: string
    * appr: dp
    * test cases:s
    * */

    public String minWindow(String S, String T) {
        int m = T.length(), n = S.length();
        int[][] dp = new int[m+1][n+1];
        // 0th elem represent empty string
        for(int col=0; col<=n; col++) {
            dp[0][col]= col+1;
        }
        LsUtil.printArray(dp);
        // row 1 to m,  col 0 is 0
        for(int row=1; row<=m; row++) {
           for(int col=1; col<=n; col++) {
               if(T.charAt(row-1) == S.charAt(col-1)) {
                   dp[row][col] = dp[row-1][col-1]; // since char matched we take the index of last char matched before this char in both S and T().
               } else {
                    dp[row][col] = dp[row][col-1]; // since char didnt we take index of previous col.
               }
           }
        }
        LsUtil.printArray(dp);
        int minLen = n+1, start=0;
        // last char of T
        for(int col=1; col<=n; col++) {
            if(dp[m][col] != 0) { // T found in S
                int len = col - dp[m][col] + 1;
                if(len < minLen) {
                    minLen = len;
                    start = dp[m][col]-1;
                }
            }
        }
       return (minLen == n+1) ? "" : S.substring(start, start+minLen);
    }

    public static void main(String[] args) {
        Q727MinimumWindowSubsequence sol = new Q727MinimumWindowSubsequence();
        System.out.println(sol.minWindow("abcdebdcde", "bcde"));
    }

}
