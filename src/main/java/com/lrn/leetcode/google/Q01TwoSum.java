package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.HashMap;

public class Q01TwoSum {


    /* https://leetcode.com/problems/zigzag-conversion/
     * Problem definition : Given array of integers find sum equal to provided target sum
     * Assumptions : can use extra memory, array contains +ve ints from 0 < N < int.max, non null elements, best time solution, optimize for space complexity
     * test cases :
     *  1. [1,3,6] target = 7 result [0,2]
     *  2. [1,3,6] target = 10 result []
     *  3. [1]  target = 10 throw illegal argument exp
     *  4. [] target = 1 throw illegal arg exp
     *  5. [1,2,3] target =0 return []
     *  Approach:
     *   check for target 0 then return []
     *   check array null or len <= 0 then throw error
     *   check if target ==0 then return []
     *   Take hashmap key = arr elem, value = elem index
     *   loop : over array - i 0 < N < arr.len
     *         target_sum = target - element at i
     *         check if target sum exists in map then return i and map.get(target_sum)
     *         else add to map elem, i
     *   return empty array
     * */

    public static int[] twoSum1(int[] nums, int target) {
        if(target == 0) {
            return null;
        }
        if(nums == null || nums.length <= 1) {
            return null;
        }
        /*use this approach if elements are part of output. if output is suppose to contain indexes of elements then cannot sort array.*/
        Arrays.sort(nums);
        int fwdp =0, revp= nums.length-1;
        while(fwdp < revp) {
            int sum = nums[fwdp] + nums[revp];
            if(sum == target) {
                return new int[] {fwdp, revp};
            } else if(sum < target){
                fwdp++;
            } else {
                revp--;
            }
        }
        return null;
    }

    /*
     * Problem definition : Given array of integers find sum equal to provided target sum
     * Assumptions : can use extra memory, array contains +ve ints from 0 < N < int.max, non null elements, best time solution, optimize for space complexity
     * test cases :
     *  1. [1,3,6] target = 7 result [0,2]
     *  2. [1,3,6] target = 10 result []
     *  3. [1]  target = 10 throw illegal argument exp
     *  4. [] target = 1 throw illegal arg exp
     *  5. [1,2,3] target =0 return []
     *  Approach:
     *   check for target 0 then return []
     *   check array null or len <= 0 then throw error
     *   check if target ==0 then return []
     *   Take hashmap key = arr elem, value = elem index
     *   loop : over array - i 0 < N < arr.len
     *         target_sum = target - element at i
     *         check if target sum exists in map then return i and map.get(target_sum)
     *         else add to map elem, i
     *   return empty arr
     * */
    public static int[] twoSum2(int[] nums, int target) {
        if(target == 0) {
            return new int[]{};
        }
        if(nums == null || nums.length <= 1) {
            throw new IllegalArgumentException("array is null or has only one element");
        }
        /*use this approach if output is suppose to contain indexes of elements*/
        HashMap<Integer, Integer> cache = new HashMap<>();
        for(int idx=0; idx<nums.length; idx++) {
            int targetElem = target - nums[idx];
            if(cache.containsKey(targetElem)) { // found
                return new int[]{idx, cache.get(targetElem)};
            } else { // not found
                cache.put(nums[idx], idx);
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum2(new int[]{1,3,6}, 7)));
        System.out.println(Arrays.toString(twoSum2(new int[]{1,3,6}, 10)));
        System.out.println(Arrays.toString(twoSum2(new int[]{1,3,6}, 0)));
        //System.out.println(Arrays.toString(twoSum2(new int[]{1}, 10)));
        //System.out.println(Arrays.toString(twoSum2(new int[]{}, 10)));
    }
}
