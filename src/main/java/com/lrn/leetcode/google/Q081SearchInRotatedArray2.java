package com.lrn.leetcode.google;

public class Q081SearchInRotatedArray2 {
    /* 2022-01-07T12:17:29.430Z
     * pd: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
     * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/28202/Neat-JAVA-solution-using-binary-search
     * assm: arr len < 10000, arr is sorted in ascending order, best time sol
     * approach : binary search
     * test cases:
     *
     *
     * */

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0, end = nums.length - 1, mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return true;
            }
            /*If we know for sure right side is sorted or left side is unsorted*/
            if (nums[mid] < nums[end] || nums[start] > nums[mid]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums[start] < nums[mid] || nums[mid] > nums[end]) { // If we know for sure left side is sorted or right side is unsorted
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                /*If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
                any of the two sides won't change the result but can help remove duplicate from
                consideration, here we just use end-- but left++ works too
                */
                end--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q081SearchInRotatedArray2 sol = new Q081SearchInRotatedArray2();

    }
}
