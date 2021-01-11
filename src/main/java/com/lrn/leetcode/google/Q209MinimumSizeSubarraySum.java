package com.lrn.leetcode.google;

public class Q209MinimumSizeSubarraySum {
    /*
    * pd: Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
Example:
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
    * assm: arr elem - +ve only, non null, arr size < 10000, best time sol
    * approach : sliding window
    * test cases:
    *  inp : s = 7, nums[2,3,1,2,4,3] out: 2
    *
    * */
    // complexity : O(n)
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null) {
            return 0;
        }
        int ws = 0, sum = 0, minlen = nums.length + 1;
        for (int we = 0; we < nums.length; we++) {
            sum = sum + nums[we];
            while (sum >= s) {
                minlen = Math.min(minlen, we - ws + 1);
                sum = sum - nums[ws++];
            }
        }
        return (minlen > nums.length) ? 0 : minlen;
    }

    public static void main(String[] args) {
        Q209MinimumSizeSubarraySum sol = new Q209MinimumSizeSubarraySum();
        System.out.println((2 == sol.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}) ? "passed" : "failed"));
    }

}
