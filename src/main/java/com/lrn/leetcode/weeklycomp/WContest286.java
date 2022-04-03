package com.lrn.leetcode.weeklycomp;

import java.util.*;

public class WContest286 {


    /**
     * https://leetcode.com/contest/weekly-contest-286/problems/find-the-difference-of-two-arrays/
     * 2215. Find the Difference of Two Arrays
     *
     *     User Accepted: 9430
     *     User Tried: 9851
     *     Total Accepted: 9629
     *     Total Submissions: 14280
     *     Difficulty: Easy
     *
     * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
     *
     *     answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
     *     answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
     *
     * Note that the integers in the lists may be returned in any order.
     */

    static class ArrayDifference {
        public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
            Set<Integer> set1 = new HashSet<>(); // set for num1
            Set<Integer> set2 = new HashSet<>(); // set for num2
            int len1 = nums1.length, len2 = nums2.length;
            int maxlen = Math.max(len1, len2);
            //prepare set for each array
            for(int idx=0; idx < maxlen; idx++) {
                if(idx < len1) set1.add(nums1[idx]);// add elements to set1
                if(idx < len2) set2.add(nums2[idx]);// add elements to set2
            }
            for(int num: nums1) { // remove common elements so what's left is distinct
                if(set2.contains(num)) {
                    set1.remove(num);
                    set2.remove(num);
                }
            }
            //prepare the result
            ArrayList<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>(set1));
            result.add(new ArrayList<>(set2));
            return result;
        }
    }
    /*
    * https://leetcode.com/contest/weekly-contest-286/problems/minimum-deletions-to-make-array-beautiful/
    *
    * You are given a 0-indexed integer array nums. The array nums is beautiful if:

    nums.length is even.
    nums[i] != nums[i + 1] for all i % 2 == 0.

Note that an empty array is considered beautiful.

You can delete any number of elements from nums. When you delete an element, all the elements to the right of the deleted element will be shifted one unit to the left to fill the gap created and all the elements to the left of the deleted element will remain unchanged.

Return the minimum number of elements to delete from nums to make it beautiful.
    * */
    static class MakeBeautifulArraybyDel {
        public int minDeletion(int[] nums) {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for(int num: nums) {
                // skip element
                if(!stack.isEmpty() && stack.peek() == num && (stack.size()-1)%2 == 0 ) {
                    continue;
                }
                // add element
                stack.push(num);
            }
            // if stack size is not even than remove last element
            if(!stack.isEmpty() && stack.size()%2 != 0) {
                stack.pop();
            }
            return nums.length - stack.size();
        }
    }



}
