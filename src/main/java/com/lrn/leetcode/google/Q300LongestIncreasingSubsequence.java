package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q300LongestIncreasingSubsequence {
    /*
     * pd: Given an unsorted array of integers, find the length of longest increasing subsequence.
     * assm: non null, arr len < int.max, elem are int min to + int amx
     * appr: dp
     * test cases:
     *
     * */

    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length ==0) {
            return 0;
        }
        return longestSeqRec(nums, 0,1, 1, new HashMap<>());

    }

    public int longestSeqRec(int[] nums, int prvidx ,int idx, int cmax, Map<String,Integer> dp) {
        if (idx == nums.length) {
            return cmax;
        }
        if(dp.containsKey(prvidx+"-"+idx+"-"+cmax)) {
            return dp.get(prvidx+"-"+idx+"-"+cmax);
        }
        // include
        int xmax = 0;
        if (nums[prvidx] < nums[idx]) {
            xmax = longestSeqRec(nums, idx,idx + 1, cmax + 1, dp);
        }
        // exclude
        int ymax = longestSeqRec(nums, idx, idx + 1, 1,dp);
        int zmax = longestSeqRec(nums, prvidx, idx + 1, cmax,dp);

        final int max = Math.max(Math.max(xmax, ymax), zmax);
        dp.put(prvidx+"-"+idx+"-"+cmax, max);
        return max;
    }

    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int size=0;
        for(int currElem: nums) {
            int start =0, end = size; // here end it at size not at nums.len
            while(start != end) {
                int mid = start + (end-start) /2;
                if (currElem > tails[mid]) {
                    start = mid+1; // not including mid
                } else {
                    end = mid; // including mid
                }
            }
            tails[start] = currElem;
            if(start == size) size++;
        }
        return size;
    }


    public static void main(String[] args) {
        Q300LongestIncreasingSubsequence sol = new Q300LongestIncreasingSubsequence();
        //System.out.println(sol.lengthOfLIS2(new int[]{1,2,3,-1,4,5}));
        System.out.println(sol.lengthOfLIS2(new int[]{4,10,4,3,11,9}));
        System.out.println(sol.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }


}
