package com.lrn.leetcode.weeklycomp;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.List;

public class WContest260 {

    /*
    *2016. Maximum Difference Between Increasing Elements
    * */
    public int maximumDifference(int[] nums) {
        int min=nums[0], diff=-1;
        for(int idx=1; idx < nums.length; idx++) {
            if(min < nums[idx]) {
                diff = Math.max(diff, nums[idx] - min);
            }
            min = Math.min(min, nums[idx]);
        }
        return diff;
    }
   /*2017. Grid Game
   1. first robot will always consume 00 and 1,n-1 col
   2. goal is to minimize the points consumed by robot2
   https://leetcode.com/problems/grid-game/discuss/1486340/C%2B%2BJavaPython-Robot1-Minimize-TopSum-and-BottomSum-of-Robot-2-Picture-Explained
    */
   public long gridGame(int[][] grid) {
       long topsum = 0, bottomsum=0, ans=Long.MAX_VALUE;
       for(int num: grid[0]) {
           topsum = topsum + num;
       }
       for(int col=0; col < grid[0].length; col++) {
           topsum = topsum - grid[0][col];
           ans = Math.min( ans, Math.max(topsum, bottomsum)); // robot 2 will consume either topsum or bottom sum
           bottomsum = bottomsum + grid[1][col];
       }

       return ans;
   }

   /*2018. Check if Word Can Be Placed In Crossword
   * https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/discuss/1486285/Python-3-working-with-strings
   * long sol.
   * */



    public static void main(String[] args) {
        WContest260 sol = new WContest260();
        int[][] grid = new int[][] {
                {2,5,4},
                {1,5,1},
        };
        sol.gridGame(grid);

    }


}
