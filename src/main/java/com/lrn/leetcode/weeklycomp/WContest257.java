package com.lrn.leetcode.weeklycomp;

public class QWContest257 {
 /* https://leetcode.com/contest/weekly-contest-257/problems/count-special-quadruplets/
 * 1995. Count Special Quadruplets
 * */
 public int countQuadruplets(int[] nums) {
     int cnt = 0;
     for(int idx=nums.length-1; idx >= 3; idx--) {
         if(findnum(nums, idx-1, 1, nums[idx])) cnt++;
     }
     return cnt;
 }

    public boolean findnum(int[] nums, int end, int n, int target) {
        if(end < 0 ) return false;
        if(target == 0 && n == 4) return true;
        for(int idx=end; idx >= 0; idx--) {
            if(findnum(nums, idx-1, n+1, target-nums[idx])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        
    }




}
