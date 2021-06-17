package com.lrn.leetcode.google;

public class Q81SearchInRotatedArray2 {
    /*
    * pd: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/28202/Neat-JAVA-solution-using-binary-search
    * assm: arr len < 10000, arr is sorted in ascending order, best time sol
    * approach : binary search
    * test cases:
    *
    *
    * */

    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int start=0, end=nums.length-1, mid=0;
        while(start <= end) {
            mid = start + (end-start)/2;
            if(target == nums[mid]) {
                return true;
            }
            /*right sorted or left not sorted*/
            if( nums[mid] < nums[end] || nums[start] > nums[mid]) {
                if(target > nums[mid] && target <= nums[end]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            } else if(nums[start] < nums[mid] || nums[mid] > nums[end]) { // left sorted or right unsorted
                   if(target >= nums[start] && target < nums[mid]) {
                       end = mid-1;
                   } else {
                       start = mid+1;
                   }
            } else {
               end--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q81SearchInRotatedArray2 sol = new Q81SearchInRotatedArray2();

    }
}
