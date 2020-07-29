package com.lrn.leetcode.google;

import java.util.PriorityQueue;

public class Q45JumpGame2 {
    /*
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     * Assm: arr elem are positive, non null
     *       best time sol
     * appr: bfs [2,3,1,1,4]
     *       level 1 -> 2
     *       level 2 -> 3,1
     *       level 3 -> 1,4
     *       at each leve calculate max
     * */

    public int jump(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }
       int idx=0, level=0, nextMaxIndex=0, maxReachableIndex=0;
       while(((maxReachableIndex - idx)+1) > 0) { // number of nodes between current index and maxReachableIndex > 0
            level++; //going to next level
            for(;idx<=maxReachableIndex && idx<nums.length; idx++) {
                nextMaxIndex = Math.max(nextMaxIndex,idx+nums[idx]);
                if(nextMaxIndex >=nums.length-1) {
                    return level;
                }
            }
            maxReachableIndex = nextMaxIndex;
       }
       return 0;
    }

    public static void main(String[] args) {
        Q45JumpGame2 sol = new Q45JumpGame2();
        System.out.println(sol.jump(new int[]{2, 3, 1, 1, 4}));
    }


}
