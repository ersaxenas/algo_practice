package com.lrn.leetcode.google;

public class Q303RangeSumQuery {
    /*
    * pd: Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
Implement the NumArray class:
NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int i, int j) Return the sum of the elements of the nums array in the range [i, j] inclusive (i.e., sum(nums[i], nums[i + 1], ... , nums[j]))
    * assm: non null elem, arr len < int.max, -100 <= elem < 10000, best time sol
    * appr: math.
    *       0   1    2   3   4
    *      [a1, a2, a3, a4, a5]
    *       0   1      2       3          4        5
    *      [0, a1,  a1+a2, a1+a2+a3, a1+a2+a3+a4, a1+a2+a3+a4+a5]
    *  range 0 - 3
    *      a1+a2+a3+a4 - 0
    * range  1 - 3
    *      a1+a2+a3+a4 - a1 = a2+a3+a4
    * test cases:
    * Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]
    * */

    static class NumArray {
        int[] sum;
        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            sum[0] = 0;
            for (int idx = 0; idx < nums.length; idx++) {
             sum[idx+1] = sum[idx] + nums[idx];
            }
        }

        public int sumRange(int i, int j) {
           return sum[j+1] - sum[i];
        }
    }

    public static void main(String[] args) {
        NumArray sol = new NumArray(new int[] {1,2,3,4,5});
        System.out.println(sol.sumRange(0, 3));
        System.out.println(sol.sumRange(1, 3));
    }

}
