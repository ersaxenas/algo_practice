package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q18_4Sum {
    /*
    * https://leetcode.com/problems/4sum/
    * PD: Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
    * Assmp: input - non null, will have atleast one solution | best time solution
    *  Approach: Sort arrays
    * run two for loops
    * loop 1 -> 0 to len - 3
    * loop 2 -> start = loop1 idx+1 to N -2
    *   now use two pointer approach to find pair with sum = targetsum - (elem1 + elem2)
    * */
    public List<List<Integer>> foursum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);
        for(int idx1=0; idx1<nums.length-3; idx1++) {
            if(idx1 != 0 && nums[idx1] == nums[idx1-1]) {continue;}
            for(int idx2=idx1+1; idx2 < nums.length -2; idx2++) {
                if(idx2 != idx1+1 && nums[idx2] == nums[idx2-1]) {continue;}
                int lp=idx2+1, rp = nums.length-1;
                while(lp < rp) {
                    int sum = nums[idx1] + nums[idx2] + nums[lp] + nums[rp];
                    if(sum == target) {
                        result.add(Arrays.asList(nums[idx1],nums[idx2],nums[lp],nums[rp]));
                        lp++; rp--;
                        while(lp < rp && (nums[lp] == nums[lp-1])) {lp++;}
                        while(lp < rp && (nums[rp] == nums[rp+1])) {rp--;}
                    } else if(sum < target) {
                        lp++;
                    } else {
                        rp--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q18_4Sum q184Sum = new Q18_4Sum();
        List<List<Integer>> foursum = q184Sum.foursum(new int[]{0,0,0,0}, 0);
        foursum.forEach(list -> {
            StringBuilder sbr = new StringBuilder();
            list.forEach(elem -> sbr.append(elem).append(" "));
            System.out.println(sbr);
        });
    }

}
