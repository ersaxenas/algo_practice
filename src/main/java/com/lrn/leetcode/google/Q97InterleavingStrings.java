package com.lrn.leetcode.google;

public class Q97InterleavingStrings {
    /*
    * pd: https://leetcode.com/problems/interleaving-string
    * appr: https://leetcode.com/problems/interleaving-string/discuss/31879/My-DP-solution-in-C%2B%2B
    * assm:
    * test cases:
    *
    * */


    public boolean isInterleave(String s1, String s2, String s3) {

        if(s1.length() + s2.length() != s3.length()) return false;
        boolean[][] dp = new boolean[s2.length()+1][s1.length()+1];
        dp[0][0] = true;
        // for first row : s2 is empty
        for(int col=1; col<s1.length()+1; col++) {
            if(dp[0][col-1] && s1.charAt(col-1) == s3.charAt(col-1)) {
                dp[0][col] = true;
            }
        }
        // for first col : s1 is empty
        for(int row=1; row<s2.length()+1; row++) {
            if(dp[row-1][0] && s2.charAt(row-1) == s3.charAt(row-1)) {
                dp[row][0] = true;
            }
        }

        for(int row=1; row<s2.length()+1; row++) {
            for(int col=1; col<s1.length()+1; col++) {
                dp[row][col] = (dp[row-1][col] && s2.charAt(row-1) == s3.charAt(row+col-1)) ||
                        (dp[row][col-1] && s1.charAt(col-1) == s3.charAt(row+col-1));

            }
        }
        return dp[s2.length()][s1.length()];
    }

    public static void main(String[] args) {
       Q97InterleavingStrings sol = new Q97InterleavingStrings();
        System.out.println(sol.isInterleave("a", "", "a"));
    }
}
