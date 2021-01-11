package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Q259ThreeSumSmaller {
    /*
     * pd: Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
     * Follow up: Could you solve it in O(n2) runtime?
     * assm: arr len < 10000, non null elem, -1000 < elem < int max, best time sol
     * appr: two pointers - we only need to return count of such triplets so we can sort array
     * test cases:
     * Input: nums = [-2,0,1,3], target = 2 Output: 2
Explanation: Because there are two triplets which sums are less than 2: [-2,0,1][-2,0,3]
* Input: nums = [], target = 0 Output: 0
* Input: nums = [0], target = 0
Output: 0
     * */

    public int threeSumSmaller(int[] nums, int target) {
        int cnt = 0, left, right;
        if(nums == null) {
            return cnt;
        }
        Arrays.sort(nums);
        for (int idx = 0; idx < nums.length - 2; idx++) {
            left = idx + 1;
            right = nums.length - 1;
            while (left < right) {
                if (nums[idx] + nums[left] + nums[right] < target) {
                    cnt = cnt + (right - left);
                    left++;
                } else {
                    right--;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Q259ThreeSumSmaller sol = new Q259ThreeSumSmaller();
        System.out.println(sol.threeSumSmaller(new int[]{-2, 0, 1, 3}, 2) == 2 ? "pass" : "fail");
        System.out.println(sol.threeSumSmaller(new int[]{}, 0) == 0 ? "pass" : "fail");
        System.out.println(sol.threeSumSmaller(new int[]{0}, 0) == 0 ? "pass" : "fail");
    }

}
