package com.lrn.leetcode.google;

public class Q152MaximumProductSubarray {
    /*
    * pd: Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product
    * Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
    * assm: non null elem, arr len < 1000, best time sol
    * appr: trick
    * https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly-simplest-solution-with-O(n)-time-complexity
    *
    * */

    public int maxProduct(int[] nums) {
        int max=nums[0], imax=max,imin=max;
        for(int idx=1; idx<nums.length; idx++) {
            if(nums[idx] < 0) {
                int tmp = imax;
                imax=imin;
                imin=tmp;
            }
            imax = Math.max(nums[idx],imax * nums[idx]);
            imin = Math.min(nums[idx], imin * nums[idx]);
            max = Math.max(imax,max);
        }
        return max;
    }

    public static void main(String[] args) {
        Q152MaximumProductSubarray sol = new Q152MaximumProductSubarray();
        System.out.println((sol.maxProduct(new int[]{2, 3, -2, 4}) == 6) ? "passed" : "failed");
        System.out.println((sol.maxProduct(new int[]{-2,0,-1}) == 0) ? "passed" : "failed");
    }

}
