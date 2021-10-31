package com.lrn.leetcode.google;

public class Q167TwoSum2InputArraySorted {
    /*
    * pd: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    * assm: arr len < 10000, -1000 < elem < 1000, best time sol, always a sol
    * appr: two pointers
    * test cases:
    * */

    public int[] twoSum(int[] numbers, int target) {
        int p1=0, p2 = numbers.length-1;
        while(p1 < p2) {
            long sum = numbers[p1] + numbers[p2];
            if( sum == target) return new int[] {p1+1, p2+1};
            if( sum < target ) {
                p1++;
            } else {
                p2--;
            }
        }
        return new int[] {p1+1, p2+1};
    }

}
