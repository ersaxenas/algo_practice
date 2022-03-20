package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q055JumpGame {
    /* 2021-12-27T13:08:53.910Z
    https://leetcode.com/problems/jump-game/
     *PD: Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * assm; arr non null elem, only +ve elem includes 0
     * appr: bfs
     * Test cases
     * */

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int idx = 0, nextReachable = 0, nextMaxReachable = 0;
        while ((nextMaxReachable - idx) + 1 > 0) {
            while (idx < nums.length && idx <= nextMaxReachable) {
                nextReachable = Math.max(nextReachable, idx + nums[idx]);
                if (nextReachable >= nums.length - 1) {
                    return true;
                }
                nextMaxReachable = nextReachable;
                idx++;
            }
        }
        return false;
    }

    // bfs
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
          Q55JumpGame sol = new Q55JumpGame();
        System.out.println(sol.canJump2(new int[]{2, 3, 1, 1, 4}));
        System.out.println(sol.canJump2(new int[]{3,2,1,0,4}));
    }


}
