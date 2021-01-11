package com.lrn.leetcode.google;

import com.lrn.cci.likedlist.Palindrome;

import java.util.HashMap;
import java.util.HashSet;

public class Q416PartitionEqualSubsetSum {
    /*
    * PD: Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
    * assm: array size < 1000, 1 < elem < 1000, best time sol
    * appr: dp
    * test cases:
    * Input = [1,5,11,5] output = true
    * */

    public boolean canPartition(int[] nums) {
        int sum =0;
        for(int num: nums) {
            sum = sum + num;
        }
        if(sum %2 != 0) {
            return false;
        }
       return canPartition(nums, 0, sum/2, new HashMap<>());
    }

    public boolean canPartition(int[] nums, int idx, int sum, HashMap<String, Boolean> cache) {
        // base case
        if(sum < 0 || idx >= nums.length) {
            return false;
        }
        if(sum == 0) {
            return true;
        }
        final String key = idx + ":" + sum;
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        if (canPartition(nums, idx + 1, sum - nums[idx], cache) || canPartition(nums, idx + 1, sum, cache)) {
            cache.put(key,true);
            return true;
        }
        cache.put(key,false);
        return false;
    }

    public static void main(String[] args) {
        Q416PartitionEqualSubsetSum sol = new Q416PartitionEqualSubsetSum();
        System.out.println(sol.canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(sol.canPartition(new int[]{1, 2, 3, 5}));
    }


}
