package com.lrn.leetcode.google;

import java.util.Arrays;

public class Q403FrogJump {
    /*
    * pd: A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
Note:
The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
    * assm: best time sol.
    * appr:
    * https://leetcode.com/problems/frog-jump/discuss/88824/Very-easy-to-understand-JAVA-solution-with-explanations
    * test cases:
    * [0,1,3,5,6,8,12,17] true
    * */

    public boolean canCross(int[] stones) {
       return canCrossRec2(stones,0,0, new boolean[stones.length][stones.length]);
    }

    public boolean canCrossRec1(int[] stones, int startIndex, int prevJump, boolean[][] dp) {
      if(startIndex == stones.length-1) {
          return true;
      }
      if(dp[startIndex][prevJump]) {
          return true;
      }
      for(int idx=startIndex+1; idx<stones.length; idx++) {
          int gap = stones[idx] - stones[startIndex];
          if(gap >= prevJump -1 && gap <= prevJump+1) {
              if(canCrossRec1(stones, idx, gap, dp)) {
                  dp[startIndex][prevJump] = true;
                  return true;
              }
          }
      }
      return false;
    }

    public boolean canCrossRec2(int[] stones, int startIndex, int prevJump, boolean[][] dp) {
      if(startIndex == stones.length-1) {
          return true;
      }
      if(dp[startIndex][prevJump]) {
          return true;
      }
      int idx1 = Arrays.binarySearch(stones, startIndex+1, stones.length, stones[startIndex] + prevJump -1);
      if(idx1 >=0 && canCrossRec2(stones, idx1, prevJump-1, dp)) {
             dp[startIndex][prevJump] = true;
             return true;
      }
      int idx2 = Arrays.binarySearch(stones, startIndex+1, stones.length, stones[startIndex] + prevJump);
      if(idx2 >=0 && canCrossRec2(stones, idx2, prevJump, dp)) {
             dp[startIndex][prevJump] = true;
             return true;
      }
      int idx3 = Arrays.binarySearch(stones, startIndex+1, stones.length, stones[startIndex] + prevJump+1);
      if(idx3 >=0 && canCrossRec2(stones, idx3, prevJump+1, dp)) {
             dp[startIndex][prevJump] = true;
             return true;
      }
      return false;
    }

    public static void main(String[] args) {
          Q403FrogJump sol = new Q403FrogJump();
        System.out.println(sol.canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(sol.canCross(new int[]{0,1,2,3,4,8,9,11}));
    }


}
