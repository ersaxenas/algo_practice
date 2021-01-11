package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Q334IncreasingTripletSubsequence {
    /*
    * pd: Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
Formally the function should:
Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
    * assm: non null elem, 0 < N < int.max, arr size < 10000, best time sol.
    * appr:
    *
    * test cases:
    * Input: [1,2,3,4,5] Output: true
    * Input: [5,4,3,2,1] Output: false
    * */
    public boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length <3) {
            return false;
        }
        int rangeStart = Integer.MAX_VALUE, rangeEnd = Integer.MAX_VALUE;
        for(int num: nums) {
            if(num <= rangeStart) {
                rangeStart = num;
            } else if( num <= rangeEnd) {
                rangeEnd = num;
            } else {
                return true;
            }
        }
       return false;
    }


    public static void main(String[] args) {
        Q334IncreasingTripletSubsequence sol = new Q334IncreasingTripletSubsequence();
        System.out.println(sol.increasingTriplet(new int[]{2,1,5,0,3}));
        System.out.println(sol.increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        System.out.println(sol.increasingTriplet(new int[]{5,4,3,2,1}));
        System.out.println(sol.increasingTriplet(new int[]{5,4,3,10,11}));
    }



}
