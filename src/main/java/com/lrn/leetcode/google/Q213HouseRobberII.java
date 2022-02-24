package com.lrn.leetcode.google;

public class Q213HouseRobberII {
    /* https://leetcode.com/problems/house-robber-ii
    *https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
    *
    * */

    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        return Math.max(rob0(nums), rob1(nums));
    }

    public int rob0(int[] nums) {
        int premax=0, curmax=0;
        for(int idx=0; idx < nums.length-1; idx++) { // start at 0 and skip last house
            int tmp = curmax;
            curmax = Math.max(
                    premax + nums[idx], // rob house at idx + previous cash in hand
                    curmax // skip house at idx so just keep current cash in hand
            );
            premax = tmp;
        }
        return curmax;
    }
    public int rob1(int[] nums) {
        int premax=0, curmax=0;
        for(int idx=1; idx < nums.length; idx++) { // start at 1 and include last house
            int tmp = curmax;
            curmax = Math.max(
                    premax + nums[idx], // rob house at idx + previous cash in hand
                    curmax // skip house at idx so just keep current cash in hand
            );
            premax = tmp;
        }
        return curmax;
    }





    public static void main(String[] args) {
        Q213HouseRobberII sol = new Q213HouseRobberII();
        sol.rob(new int[]{2,3,2});
    }
}
