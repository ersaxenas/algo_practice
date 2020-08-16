package com.lrn.leetcode.google;

public class Q55JumpGame {
    /*
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

    public static void main(String[] args) {
          Q55JumpGame sol = new Q55JumpGame();
        System.out.println(sol.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(sol.canJump(new int[]{3,2,1,0,4}));
    }


}
