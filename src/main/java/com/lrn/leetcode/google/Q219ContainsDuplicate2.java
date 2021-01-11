package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q219ContainsDuplicate2 {
    /*
    * pd: Given an array of integers and an integer k,
    * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
    * assm: arr len < 10000, elem are non null +ve int, best time sol
    * appr: iterate over array
    * test cases:
    * Input: nums = [1,2,3,1], k = 3 Output: true
    * Input: nums = [1,0,1,1], k = 1 Output: true
    * Input: nums = [1,2,3,1,2,3], k = 2 Output: false
    *
    * */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k ==0) {
            return false;
        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        int ws=0;
        for(int we=0; we<nums.length; we++) {
            if((we-ws) > k) {
                 int freq = freqMap.get(nums[ws])-1;
                 if(freq == 0) {
                     freqMap.remove(nums[ws]);
                 } else {
                     freqMap.put(nums[ws], freq);
                 }
                 ws++;
            }
            if(freqMap.containsKey(nums[we])) {
                return true;
            }
           freqMap.put(nums[we], freqMap.getOrDefault(nums[we],0)+1);
        }
       return false;
    }


    public static void main(String[] args) {
         Q219ContainsDuplicate2 sol = new Q219ContainsDuplicate2();
        System.out.println((sol.containsNearbyDuplicate(new int[]{2,2}, 3)) ? "passed" : "failed");
        System.out.println((sol.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3)) ? "passed" : "failed");
        System.out.println((sol.containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1)) ? "passed" : "failed");
        System.out.println(!(sol.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 1)) ? "passed" : "failed");
    }

}
