package com.lrn.leetcode.google;

import java.util.Random;

public class Q384ShuffleAnArray {
    /*
    * pd: Given an integer array nums, design an algorithm to randomly shuffle the array.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the integer array nums.
int[] reset() Resets the array to its original configuration and returns it.
int[] shuffle() Returns a random shuffling of the array.
*    assm: array size < 10000, best time sol
*   appr:
* test cases:
*
    * */

    int[] numsOriginal;
    Random random = new Random();
    public Q384ShuffleAnArray(int[] nums) {
        numsOriginal = nums.clone();
    }
    public int[] reset() {
      return numsOriginal;
    }

    public int[] shuffle() {
        int[] nums = numsOriginal.clone();
        for(int idx=1; idx<nums.length; idx++) {
           int ridx = random.nextInt(idx+1);
           swap(idx,ridx, nums);
        }
        return nums;
    }

    public void swap(int a, int b, int[] nums) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }


}
