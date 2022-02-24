package com.lrn.leetcode.weeklycomp;

import java.util.HashMap;
import java.util.Map;

public class WContest255 {


    /*1979. Find Greatest Common Divisor of Array*/
    public int findGCD(int[] nums) {
        int min = nums[0], max = nums[0];
        for(int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return gcd(min,max);
    }

    public int gcd(int num1, int num2) {
        if(num1 == num2) return num1;
        if(num1 > num2) return gcd(num1-num2, num2);
        return gcd(num1, num2-num1);
    }

    public static void main(String[] args) {
        WContest255 sol = new WContest255();

    }


}
