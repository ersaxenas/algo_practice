package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q279PerfectSquare {
    /* https://leetcode.com/problems/perfect-squares/discuss/71495/An-easy-understanding-DP-solution-in-Java
    * pd: Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
    * assm: n < 1000, best time sol.
    * appr: backtracking
    * test cases:
    * Input: n = 12 Output: 3 Explanation: 12 = 4 + 4 + 4.
    * Input: n = 13 Output: 2 Explanation: 13 = 4 + 9.
    * */

    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n); // max elem y
        dp[0] = 0;
        dp[1] = 1;
        for(int idx=1; idx<=n; idx++) {
            for(int sq=1; sq*sq <= idx; sq++) {
                dp[idx] = Math.min(dp[idx], dp[idx-sq*sq] +1);
            }
        }
       return dp[n];
    }

    public static void main(String[] args) {
        Q279PerfectSquare sol = new Q279PerfectSquare();
        System.out.println(sol.numSquares(12) == 3 ? "pass" : "fail");
        System.out.println(sol.numSquares(13) == 2 ? "pass" : "fail");
        System.out.println(sol.numSquares(50) == 2 ? "pass" : "fail");
    }

}
