package com.lrn.leetcode.google;

public class Q152MaximumProductSubarray {
    /* https://leetcode.com/problems/maximum-product-subarray
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
    String logm = "%s<max: %d, imax: %d, imin: %d>";
    public int maxProduct(int[] nums) {
        int max=nums[0], imax=max,imin=max;
        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        System.out.println(String.format(logm,">>>>>",max,imax,imin));
        for(int idx=1; idx<nums.length; idx++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if(nums[idx] < 0) {
                int tmp = imax;
                imax=imin;
                imin=tmp;
                System.out.println(String.format(logm,"swap>",max,imax,imin));
            }
            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = Math.max(nums[idx],imax * nums[idx]);
            imin = Math.min(nums[idx], imin * nums[idx]);
            max = Math.max(imax,max);
            System.out.println(String.format(logm,">>>>>",max,imax,imin) + "  <"+nums[idx]+">");
        }
        return max;
    }

    public static void main(String[] args) {
        Q152MaximumProductSubarray sol = new Q152MaximumProductSubarray();
        System.out.println((sol.maxProduct(new int[]{2,2,2,-1,2,-1,2,-1,2,-1,2,-1,2}) == 6) ? "passed" : "failed");
//        System.out.println((sol.maxProduct(new int[]{2, 3, -2, 4}) == 6) ? "passed" : "failed");
//        System.out.println((sol.maxProduct(new int[]{-2,0,-1}) == 0) ? "passed" : "failed");
    }

}
