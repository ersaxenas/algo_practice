package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Q15ThreeSum {
    /*
     * https://leetcode.com/problems/3sum/
     * PD: Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     * ASSMP: input will not have non-nulls, best time solution
     * Appraoch: A+B+C = 0 so B+C= -A
     *          iterate over arrays elem
     *          elem1 - look for two elems in the array of sum == -A
     * */
    public List<List<Integer>> threesum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int idx = 0; idx <= nums.length - 3; idx++) {
            // avoid dups
            if( idx!=0 && nums[idx] == nums[idx-1]) {
                continue;
            }
            int curElem = nums[idx];
            List<List<Integer>> tmp = findPairWithTargetSum(nums, curElem, idx + 1);
            res.addAll(tmp);
        }
        return res;
    }

    public List<List<Integer>> findPairWithTargetSum(int[] nums, int curElem, int start) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        int lp=start, rp=nums.length-1;
        while(lp < rp) {
            int sum = nums[lp] + nums[rp] + curElem;
            if(sum == 0) {
                res.add(Arrays.asList(curElem, nums[lp],nums[rp]));
                lp++; rp--;
                while(lp < rp && (nums[lp] == nums[lp-1])) lp++;
                while(lp < rp && (nums[rp] == nums[rp+1])) rp--;
            } else if(sum < 0) {
                lp++;
            } else {
                rp--;
            }
        }
        return res;
    }



    public static void main(String[] args) {
        Q15ThreeSum q15ThreeSum = new Q15ThreeSum();
        List<List<Integer>> threesum = q15ThreeSum.threesum(new int[]{-1, 0, 1, 2, -1, -4});
        threesum.forEach(System.out::println);
    }


}
