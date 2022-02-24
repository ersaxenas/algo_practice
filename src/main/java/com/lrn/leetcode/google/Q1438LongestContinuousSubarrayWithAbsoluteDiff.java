package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class Q1438LongestContinuousSubarrayWithAbsoluteDiff {
    /*
    * pd: Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
    * assm: array size < 10000, limit < 10000, best time sol
    * appr: sliding window : https://www.youtube.com/watch?v=LDFZm4iB7tA
    * test case:
    *
    * */

    public int longestSubarray(int[] nums, int limit) {
        int maxLen =0, ws=0;
        Deque<Integer> increasingOrderDeque = new ArrayDeque<>();
        Deque<Integer> decreasingOrderDeque = new ArrayDeque<>();
        for(int we=0; we<nums.length; we++) {
            // in order to maintain increasing order remove all the elem > nums[we]
            while(!increasingOrderDeque.isEmpty() && increasingOrderDeque.peekLast() > nums[we]) {
                increasingOrderDeque.pollLast();
            }
            increasingOrderDeque.add(nums[we]);
            // in order to maintain decreasing order remove all the elem < nums[we]
            while(!decreasingOrderDeque.isEmpty() && decreasingOrderDeque.peekLast() < nums[we]) {
                decreasingOrderDeque.pollLast();
            }
            decreasingOrderDeque.add(nums[we]);
            // exceeded the limit
            while (decreasingOrderDeque.peekFirst() - increasingOrderDeque.peekFirst() > limit) {
                // remove elem at ws -- shrink window
                if(increasingOrderDeque.peekFirst() == nums[ws]) increasingOrderDeque.pollFirst();
                if(decreasingOrderDeque.peekFirst() == nums[ws]) decreasingOrderDeque.pollFirst();
                ws++;
            }
            maxLen = Math.max(maxLen, we-ws+1);

        }
        return maxLen;
    }

    public static void main(String[] args) {
        Q1438LongestContinuousSubarrayWithAbsoluteDiff sol = new Q1438LongestContinuousSubarrayWithAbsoluteDiff();
        System.out.println(sol.longestSubarray(new int[]{8, 2, 4, 7}, 4));
    }

}
