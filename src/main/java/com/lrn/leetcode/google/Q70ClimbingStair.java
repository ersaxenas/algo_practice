package com.lrn.leetcode.google;

import java.util.concurrent.atomic.AtomicInteger;

public class Q70ClimbingStair {
   /* https://leetcode.com/problems/climbing-stairs/
   https://leetcode.com/problems/climbing-stairs/solution/
   * pd: You are climbing a stair case. It takes n steps to reach to the top.
   * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
   * assm:
   * appr: 2 steps
   *       backtracking
   *       1 -> 1 -> reached top sol 1
   *       1 -> 2 -> reached top + 1  not a sol so go back
   *       2 -> 1 -> reached top sol 1
   *       2 -> 2 -> reached top +1 not a sol so go back
   *       total sol = 2
   * test cases:
   * stairs 2, ways 2
   * stairs 3, ways 3
   * */

    public int climbStairs(int n) {
      return backtrack(n,0);
    }

    public int backtrack(int N, int stepsTaken) {
        int cnt=0;
        if(stepsTaken == N) {
            cnt++;
        } else if(stepsTaken < N) {
            cnt = backtrack(N, stepsTaken+1) +  backtrack(N, stepsTaken+2);
        }
        return cnt;
    }

    public int climbStairs2(int n) {
        if(n <= 2) {return n;}
        int a=1, b=2, tmp=0;
        for(int idx=2; idx<n; idx++) {
            tmp = b;
            b = a + b;
            a = tmp;
        }
        return b;
    }

    public static void main(String[] args) {
        Q70ClimbingStair sol = new Q70ClimbingStair();
        for(int idx=1; idx<=10; idx++) {
            System.out.println(sol.climbStairs(idx) + " : " + sol.climbStairs2(idx));
        }
    }

}
