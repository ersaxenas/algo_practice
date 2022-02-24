package com.lrn.leetcode.google;

public class Q410SplitArrayLargestSum {
    /*
    * pd: Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
Write an algorithm to minimize the largest sum among these m subarrays.
    * assm: elem < 1000, arrlen < 10000, best time sol, pieces <= arr.len
    * appr: binary search
    * test cases:
    * Input: nums = [7,2,5,10,8], m = 2 output = 18
    * */

    public int splitArray(int[] nums, int m) {
       int maxNum = 0;
       int sum=0;
       for(int num: nums) {
           maxNum = Math.max(maxNum, num);
           sum = sum + num;
       }
       // range is maxNum to sum -> single elem with max value to whole array
       int low = maxNum, hi = sum;
       while(low < hi) {
           int mid = low + (hi - low) /2; // sum of sub array
           // how many times arrays can be split in to contiguous pieces so that sum of sub arrays <= mid
           final int pieces = findPieces(nums, mid);
           /*rem: order is imp here. first check if pieces are greater then m */
           if(pieces > m) { // pieces are less then m so by reducing  the target sum we can increase the no. of pieces
               low = mid+1;
           } else { // pieces are larger then m so by increasing the target sum we can reduce the no. of pieces
               hi = mid;
           }
       }
       return low;
    }

    public int findPieces(int[] nums, int maxSum) {
        int curSum =0;
        int pieces =1;
        for(int num: nums) {
            if(curSum + num > maxSum) {
                curSum= 0;
                pieces++;
            }
            curSum = curSum + num;
        }
        return pieces;
    }

    public static void main(String[] args) {
        Q410SplitArrayLargestSum sol = new Q410SplitArrayLargestSum();
        System.out.println(sol.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }


}
