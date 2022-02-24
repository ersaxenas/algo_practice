package com.lrn.leetcode.weeklycomp;

import java.util.ArrayList;
import java.util.List;

public class BWContest42 {
    /*
    * 1700. Number of Students Unable to Eat Lunch
    * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/discuss/987311/JavaC%2B%2BPython-Array-Time-O(N)-Space-O(1)
    * */


    public int countStudents(int[] students, int[] sandwiches) {
        // how many different types of sandwiches we need based on student preferance
        int[] studentPrefCnt = {0,0};
        for(int idx=0; idx<students.length; idx++) {
            studentPrefCnt[students[idx]]++;
        }
        // for each students check a sandwich is available.
        //if sandwich is not available then it will remain on the top and no matter how may times students rotate
        // they will not take sandwich from top of the stack.
        int idx=0;
        while(idx < students.length) {
            if(studentPrefCnt[sandwiches[idx]] > 0) {
                studentPrefCnt[sandwiches[idx]]--;
            } else {
                break;
            }
            idx++;
        }
        return students.length - idx;
    }

    /*
    * 1701. Average Waiting Time
    *https://leetcode.com/contest/biweekly-contest-42/problems/average-waiting-time/
    * */
    public double averageWaitingTime(int[][] customers) {

        double tw =0;
        int st=0;
        for(int idx=0; idx<customers.length; idx++) {
            int[] cst = customers[idx];
            st = Math.max( st, cst[0]);
            double wt = cst[1];
            wt = wt + st - cst[0];
            tw = tw + wt;
            st = st + cst[1];
        }
        return tw/customers.length;
    }
    /*
    * pd: 1702. Maximum Binary String After Change
    * https://leetcode.com/contest/biweekly-contest-42/problems/maximum-binary-string-after-change/
    * https://www.youtube.com/watch?v=Of73lOvUFQM
    * */
    public String maximumBinaryString(String binary) {
        char[] str = binary.toCharArray();
        int zeros=0, firstZeroIndex =-1;
        StringBuilder sbr = new StringBuilder("1".repeat(str.length));
        for(int idx=0; idx<str.length; idx++) {
            if(str[idx]=='0') {
                if(firstZeroIndex == -1) {
                    firstZeroIndex = idx;
                }
                zeros++;
            }
        }
        if(firstZeroIndex == -1) {
            return binary;
        }
        int finalZeroLoc = firstZeroIndex + zeros -1;
        sbr.setCharAt(finalZeroLoc,'0');
        return sbr.toString();
    }

    /*
    * https://leetcode.com/contest/biweekly-contest-42/problems/minimum-adjacent-swaps-for-k-consecutive-ones/
    * pd: 1703. Minimum Adjacent Swaps for K Consecutive Ones
    * sol: https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/discuss/987607/O(n)-explanation-with-picture
    * hard to understand sol.
    * */

    public int minMoves(int[] nums, int k) {
       if(k <=1) {
           return 0;
       }
        List<Integer> indicesOfOne = new ArrayList<>();
        for(int idx=0; idx<nums.length; idx++) {
            if(nums[idx] ==1) {
                indicesOfOne.add(idx);
            }
        }
        int[] presum = new int[indicesOfOne.size()+1];
        presum[0] =0; // presum first elem is zero so when we cal left val at 0 index helps.
        for(int idx=0; idx<indicesOfOne.size(); idx++) {
            presum[idx+1] = indicesOfOne.get(idx) + presum[idx];
        }
        int minMove = Integer.MAX_VALUE;
        if(k%2==1) { // odd
            int radius = (k-1)/2;
            for(int idx = radius; idx< indicesOfOne.size() - radius; idx++) { // rem size of indicesOfOne
                int right = presum[idx+1+radius] - presum[idx+1]; // idx+1 because presum[0] == 0
                int left  = presum[idx] - presum[idx-radius];
                minMove = Math.min(minMove, right-left);
            }
            return minMove-(radius * (radius+1));
        } else {
            int radius = (k-1)/2;
            for(int idx = radius; idx< indicesOfOne.size() - radius-1; idx++) {// rem size of indicesOfOne -1 because we have 1 extra elem in case of even
                int right = presum[idx+1+radius+1] - presum[idx+1];// idx+1 because presum[0] == 0s
                int left  = presum[idx] - presum[idx-radius];
                minMove = Math.min(minMove, right-left-presum[idx]);
            }
            return minMove-radius * (radius+1) - (radius+1);
        }
    }


    public static void main(String[] args) {
        BWContest42 sol = new BWContest42();
//        System.out.println(sol.averageWaitingTime(new int[][]{{1, 2}, {2, 5}, {4, 3}}));
//        System.out.println(sol.minMoves(new int[]{1, 0, 0, 1, 0, 1}, 2));
        System.out.println(sol.minMoves(new int[]{1,0,0,1,0,1}, 2));
        System.out.println(sol.minMoves(new int[]{1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1}, 5));
    }






}
