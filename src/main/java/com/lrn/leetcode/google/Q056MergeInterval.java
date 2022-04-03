package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;

public class Q056MergeInterval {
    /* 2021-12-27T13:12:48.272Z 
    https://leetcode.com/problems/merge-intervals/
    * PD: Given a collection of intervals, merge all overlapping intervals.
    * assm: non negative, non null, two dimentional array with two elem only
    * appr: merger interval tech.
    * test cases:
    * Input: [[1,3],[2,6],[8,10],[15,18]]
    * Output: [[1,6],[8,10],[15,18
    * * */

   public int[][] merge(int[][] intervals) {
       if(intervals == null || intervals.length < 2)  {
           return intervals;
       }
       Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
       ArrayList<int[]> res = new ArrayList<>();
       int pis= intervals[0][0], pie = intervals[0][1];
       for(int idx=1;idx< intervals.length; idx++) {
           int[] ci = intervals[idx];
           if(ci[0] <=pie) { // overlap
               pis = Math.min(pis, ci[0]);
               pie= Math.max(pie, ci[1]);
           } else {
               int[] inter = new int[] {pis, pie};
               res.add(inter);
               pis = ci[0];
               pie = ci[1];
           }
       }
       res.add(new int[] {pis, pie});
       return res.toArray(new int[res.size()][]);
   }

    public static void main(String[] args) {
      Q056MergeInterval sol = new Q056MergeInterval();
      LsUtil.printArray(sol.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));

    }

}
