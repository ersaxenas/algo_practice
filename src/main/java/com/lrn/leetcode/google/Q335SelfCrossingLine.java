package com.lrn.leetcode.google;

public class Q335SelfCrossingLine {
    /*
    * pd:You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.
Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
    * assm: arr len < 10000, best time sol, 0 < n < 1000 elem value
    * appr: https://leetcode.com/problems/self-crossing/discuss/729133/How-to-explain-to-interviewer-335.-Self-Crossing
    * test cases:
    * Input: [2,1,1,2] Output: true
    * */

    public boolean isSelfCrossing(int[] x) {
        if(x == null || x.length < 3) return false;
        for(int idx=3; idx<x.length; idx++) {
            if(x[idx-1] <= x[idx-3] && x[idx] >= x[idx-2]) return true;
            if(idx>=4 && x[idx-1] == x[idx-3] && x[idx] + x[idx-4] >= x[idx-2]) return true;
            if(idx>=5 && x[idx-2] >= x[idx-4] && x[idx-2] <= x[idx-4] + x[idx] && x[idx-1] <= x[idx-3] && x[idx-3] <= x[idx-5]+x[idx-1]) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Q335SelfCrossingLine sol = new Q335SelfCrossingLine();
        System.out.println(sol.isSelfCrossing(new int[]{3,3,3,2,1,1}));
        System.out.println(sol.isSelfCrossing(new int[]{2,1,1,2}));
        System.out.println(sol.isSelfCrossing(new int[]{1,2,3,4}));
        System.out.println(sol.isSelfCrossing(new int[]{1,1,1,1}));
    }
}
