package com.lrn.leetcode.google;

public class Q905SortArrayByParity {
    /*
    * pd: https://leetcode.com/problems/sort-array-by-parity
    * assm: 1 < arr size < 1000, 1 < val < 10000, best time sol
    * appr: quick sort
    * test cases:
    *
    * */

    public int[] sortArrayByParity(int[] nums) {
        if(nums.length < 2) return nums;
        int odd =0, even = nums.length-1;
        while(odd < even) {
            while(odd < even && nums[odd] % 2 == 0 ) odd++; // look for even no.
            while(even > odd && nums[even] % 2 != 0 ) even--; // look for odd no.
            if(odd < even) {
                swap(nums, odd, even);
                odd++;
                even--;
            }
        }
        return nums;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }


}
