package com.lrn.leetcode.google;

public class Q75SortColor {
    /*2022-01-06T10:42:34.822Z
    https://leetcode.com/problems/sort-colors
     * pd: Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * Note: You are not suppose to use the library's sort function for this problem.
     * assm: best time sol, non null elem
     * appr: take two pointers p0 - 0 will go to pointer p2 - will go to this pointer
     *       now iterate over array
     *       if idx == 0 swap with p0
     *       if idx == 2 swap with p1
     *       make sure p0 is pointing to elem > 0 and p1 is pointing to elem < 2
     * Test cases:
     * Input: [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     * */

    public void sortColor(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int p0 = 0, p1 = nums.length - 1, index = 0;
        /*index is always >= p0*/
        while (index <= p1) {
            int currelem = nums[index];
            if (currelem < 1) {
                /*swap index and p0*/
                nums[index] = nums[p0];
                nums[p0] = currelem;
                p0++;
                index++;
            } else if (currelem > 1) {
                /*swap index with p1*/
                nums[index] = nums[p1]; // move p1 elem to index
                nums[p1] = currelem; // move currelem to p1
                p1--;
            } else {
                index++;
            }
        }
    }

    public static void main(String[] args) {
        Q75SortColor sol = new Q75SortColor();
        int[] nums = {2, 0, 2, 1, 1, 0};
        sol.sortColor(nums);
        LsUtil.printArray(nums);
        nums = new int[] {2,0,1};
        sol.sortColor(nums);
        LsUtil.printArray(nums);
    }

}
