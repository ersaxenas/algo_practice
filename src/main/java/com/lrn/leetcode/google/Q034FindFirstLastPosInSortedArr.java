package com.lrn.leetcode.google;

import java.util.Arrays;

public class Q034FindFirstLastPosInSortedArr {

    /* 2021-12-16T07:17:26.423Z 
    https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    * PD: Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
    * Your algorithm's runtime complexity must be in the order of O(log n).
    * If the target is not found in the array, return [-1, -1].
    * assm: array - non null, 0 < n < int max     ,best time solution
    * appr: binary search : when element is found continue searching on left for min and right for max
    * test cases:
    * [5,7,7,8,8,10] , 8 ans = [3,4]
    * [5,7,7,8,8,10] , 6 ans = [-1,-1]
    * */

    public int[] searchRange(int[] nums, int target) {
       int[] res = new int[2];
       Arrays.fill(res, -1);
       if(nums == null || nums.length == 0) {
           return res;
       }
       if(nums.length == 1) {
        return (target == nums[0]) ? new int[]{0,0} : res;
       }
       res[0] = findRange(nums,target, true);
       if(res[0] != -1) {
           res[1] = findRange(nums,target, false);
       }
       return res;
    }

    public int findRange(int[] nums, int target, boolean findfirst) {
        int start=0, end = nums.length-1, mid=0, keyindex=-1;
        while(start <= end) {
            mid = start+ (end-start)/2;
            int elem = nums[mid];
            if(target == elem) {
                keyindex = mid;
                // to find next occurrance if any
                if(findfirst) {
                    end = mid -1;
                } else {
                    start = mid+1;
                }
            } else if( target < elem) {
                end = mid -1;
            } else {
                start = mid + 1;
            }
        }
        return keyindex;
    }

    public static void main(String[] args) {
        Q34FindFirstLastPosInSortedArr sol = new Q34FindFirstLastPosInSortedArr();
        LsUtil.printArray(sol.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        LsUtil.printArray(sol.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7));
        LsUtil.printArray(sol.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        LsUtil.printArray(sol.searchRange(new int[]{1}, 6));
        LsUtil.printArray(sol.searchRange(new int[]{1}, 1));
        LsUtil.printArray(sol.searchRange(new int[]{2,2}, 3));
    }

}
