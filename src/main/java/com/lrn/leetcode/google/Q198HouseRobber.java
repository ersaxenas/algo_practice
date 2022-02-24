package com.lrn.leetcode.google;

import java.util.Arrays;

public class Q198HouseRobber {
    /* https://leetcode.com/problems/house-robber
    * pd: You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
Constraints:
0 <= nums.length <= 100
0 <= nums[i] <= 400
    * assm: non null +ve elem only and are less < 400, array len < 1000, best time sol,
    * appr: dp
    * https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
    * */

    public int robTopDown(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return robRec(nums, nums.length - 1);
    }

    public int robRec(int[] nums, int idx) {
        if (idx < 0) {
            return 0;
        }
        return Math.max(
                nums[idx] + robRec(nums, idx - 2), // select current and current -2
                robRec(nums, idx - 1) // skip current and select idx-1
        );
    }

    public int robTopDownMem(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] cache = new int[nums.length+1];
        Arrays.fill(cache, -1);
        return robRecMem(nums, nums.length - 1, cache);
    }

    public int robRecMem(int[] nums, int idx, int[] cache) {
        if (idx < 0) {
            return 0;
        }
        if(cache[idx] != -1)  {
            return cache[idx];
        }
        final int maxsum = Math.max(
                nums[idx] + robRec(nums, idx - 2), // select current and current -2
                robRec(nums, idx - 1) // skip current and select idx-1
        );
        cache[idx] = maxsum;
        return maxsum;
    }

    public int robBottomUpv1(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int[] cache = new int[nums.length+1];
        cache[0] = 0;
        cache[1] = nums[0];
        for(int idx=1; idx<nums.length; idx++) {
            cache[idx+1] = Math.max(
                    nums[idx] + cache[idx - 1], // select current and current -2
                    cache[idx] // skip current and select idx-1
            );
        }
        return cache[nums.length];
    }

    public static void main(String[] args) {
        Q198HouseRobber sol = new Q198HouseRobber();
        System.out.println(sol.robTopDown(new int[]{1, 2, 3, 1}) == 4 ? "TOP DOWN: passed" : "TOP DOWN: failed");
        System.out.println(sol.robTopDown(new int[]{2,7,9,3,1}) == 12 ? "TOP DOWN: passed" : "TOP DOWN: failed");

        System.out.println(sol.robTopDownMem(new int[]{1, 2, 3, 1}) == 4 ? "MEM: passed" : "MEM: failed");
        System.out.println(sol.robTopDownMem(new int[]{2,7,9,3,1}) == 12 ? "MEM: passed" : "MEM: failed");

        System.out.println(sol.robBottomUpv1(new int[]{1, 2, 3, 1}) == 4 ? "BOTTOM UP: passed" : "BOTTOM UP: failed");
        System.out.println(sol.robBottomUpv1(new int[]{2,7,9,3,1}) == 12 ? "BOTTOM UP: passed" : "BOTTOM UP: failed");
    }

}
