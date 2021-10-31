package com.lrn.leetcode.google;

public class Q137SingleNumber2 {
    /*
    * iven a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
    * sol : https://leetcode.com/problems/single-number-ii/discuss/43297/Java-O(n)-easy-to-understand-solution-easily-extended-to-any-times-of-occurance
    * The usual bit manipulation code is bit hard to get and replicate.
    * I like to think about the number in 32 bits and just count how many 1s are there in each bit, and sum %= 3 will clear it once it reaches 3.
    * After running for all the numbers for each bit, if we have a 1, then that 1 belongs to the single number,
    * we can simply move it back to its spot by doing ans |= sum << i;
    * This has complexity of O(32n), which is essentially O(n) and very easy to think and implement.
    * Plus, you get a general solution for any times of occurrence. Say all the numbers have 5 times, just do sum %= 5.
    * EX:
    *    3     3    3    2
    *  011   011  011   010
    * for bit at pos 0 : sum = 1 + 1 + 1 + 0 = 3, 3%3 = 0, so 0th the of the no repeating number is 0
    * for bit at pos 1 : sum = 1 + 1 + 1 + 1 = 4, 4%3 = 1, so 1th the of the no repeating number is 1
    * for bit at pos 2 : sum = 0 + 0 + 0 + 0 = 0, 0%3 = 0, so 2th the of the no repeating number is 0
    * .
    * .
    * till 32 bits
    * now number is 010 == 2
    * */


    public int singleNumber(int[] nums) {
        int ans =0;
        for(int idx=0; idx<32; idx++) {// for each bit from 0 to 32
            int sum=0;
            for(int num : nums) {
                if(((num >> idx) & 1) == 1) { // sum IDXth bit of each num
                    sum++;
                }
            }
            sum = sum%3; // when we modulo 3 then only bit by single no. will remain
            if(sum != 0) {// if sum is not 0 then we have bit which is 1 in the number which appears only once. so restore it to its original pos.
               // so sum is 1 now left shift it by IDX to move it to its correct position
               ans = ans | (sum << idx); // bitwise OR it the bit gets set in the ans
            }
        }
        return ans;
    }

}
