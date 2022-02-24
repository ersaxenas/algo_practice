package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class Q45JumpGame2 {
    /*2021-12-25T07:23:05.880Z
      https://leetcode.com/problems/jump-game-ii/
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     * Assm: arr elem are positive, non null
     *       best time sol
     * appr: bfs [2,3,1,1,4]
     *       level 1 -> 2
     *       level 2 -> 3,1
     *       level 3 -> 1,4
     *       at each level calculate max
     * */

    public int jump(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }
       int idx=0, level=0, nextMaxIndex=0, maxReachableIndex=0;
       while(((maxReachableIndex - idx)+1) > 0) { // number of nodes between current index and maxReachableIndex > 0
            level++; //going to next level
            for(;idx<nums.length && idx<=maxReachableIndex; idx++) {
                nextMaxIndex = Math.max(nextMaxIndex,idx+nums[idx]);
                if(nextMaxIndex >=nums.length-1) {
                    return level;
                }
            }
            maxReachableIndex = nextMaxIndex;
       }
       return 0;
    }

    // from q55 easy to understand bfs
    public boolean canJump2(int[] nums) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, nums[0]});
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int idx=0; idx<size; idx++) {
                int[] elem = queue.poll();
                for(int i=1; i<=elem[1]; i++) {
                    if(elem[0] + i == nums.length) {
                        return true;
                    }
                    if(elem[0] + i < nums.length) {
                        queue.add(new int[] {elem[0] +i, nums[elem[0]+i]});
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q45JumpGame2 sol = new Q45JumpGame2();
        System.out.println(sol.jump(new int[]{2, 3, 1, 1, 4}));
    }


}
