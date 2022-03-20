package com.lrn.leetcode.google;

public class Q066PlusOne {

    /*2021-12-31T10:39:21.776Z
    https://leetcode.com/problems/plus-one/
     * pd: Given a non-empty array of digits representing a non-negative integer, increment one to the integer.
     * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single digit.
     * You may assume the integer does not contain any leading zero, except the number 0 itself.
     * assm: best time sol, non null, non -ve elems
     * appr: take sum = 1
     *       iterate over array
     *       if sum <= 0 then break;
     *       at each step sum + arr elem
     *       now arr[idx] = sum % 10
     *       and sum = sum / 10
     * test cases:
     * [1,2,3] -> [1,2,4]
     * [1,2,9] -> [1,3,0]
     * [9,9] -> [1,0,0]
     * */


    public int[] plusOne2(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        for (int idx = digits.length - 1; idx >= 0; idx--) {
            if(digits[idx] < 9) {
                digits[idx]++;
                return digits;
            }
            digits[idx] = 0;
        }
        int[] ndigits = new int[digits.length+1];
        ndigits[0]=1;
        return ndigits;
    }

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int sum = 1;
        for (int idx = digits.length - 1; idx >= 0; idx--) {
            if(sum <= 0 ) { break;}
            sum = sum + digits[idx];
            digits[idx] = sum % 10;
            sum = sum / 10;
        }
        if(sum > 0) {
            int[] ndigits = new int[digits.length+1];
            ndigits[0] = sum;
            for(int idx=0; idx< digits.length; idx++) {
                ndigits[idx+1] = digits[idx];
            }
            return ndigits;
        }
        return digits;
    }





    public static void main(String[] args) {
        Q66PlusOne sol = new Q66PlusOne();
        LsUtil.printArray(sol.plusOne(new int[]{1,2,3}));
        LsUtil.printArray(sol.plusOne(new int[]{1,2,9}));
        LsUtil.printArray(sol.plusOne(new int[]{9,9}));
        LsUtil.printArray(sol.plusOne(new int[]{2,9,9}));
    }

}
