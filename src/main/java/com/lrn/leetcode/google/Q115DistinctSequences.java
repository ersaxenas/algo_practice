package com.lrn.leetcode.google;

public class Q115DistinctSequences {
    /*2022-02-04T11:44:26.202Z
     * pd: https://leetcode.com/problems/distinct-subsequences
     * assm: string no white spaces, string len < 1000,
     * appr: DP bottom up
     * An example:
S: [acdabefbc] and T: [ab]
first we check with a:
           *  *
      S = [acdabefbc]
mem[1] = [0111222222]
then we check with ab:
               *  * ]
      S = [acdabefbc]
mem[1] = [0111222222]
mem[2] = [0000022244]
And the result is 4, as the distinct subsequences are:

      S = [a   b    ]
      S = [a      b ]
      S = [   ab    ]
      S = [   a   b ]
     *
     *
     * test cases :
     * input "rabbbit", "rabbit" output 3
     * input "acdabefbc", "ab" output 4
     * */


    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int col = 0; col < s.length(); col++) {// first row all 1
            dp[0][col] = 1;
        }
        // first col is already zero since dp is an array of int
        for (int row = 1; row <= t.length(); row++) {
            for (int col = 1; col <= s.length(); col++) {
                if (t.charAt(row - 1) == s.charAt(col - 1)) { // match
                    dp[row][col] = dp[row][col - 1] + dp[row - 1][col - 1];
                } else {
                    dp[row][col] = dp[row][col - 1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }

    public static void main(String[] args) {
        Q115DistinctSequences sol = new Q115DistinctSequences();
        System.out.println(sol.numDistinct("acdabefbc", "ab"));
        System.out.println(sol.numDistinct("rabbbit", "rabit"));
    }

}
