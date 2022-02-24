package com.lrn.leetcode.google;

public class Q9PalindromeNumber {
    /* https://leetcode.com/problems/palindrome-number/
    * Palindrome Number: Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
    * Assumptions: +/-ve int, best time solution
    * Approach1: If even no. of digits -> fist half of the int == revers of second half of the int
    *            If odd no. of digits -> fist half of the int == revers of second half of the int and last digit removed
    * Approach 2: num of digits in a number = log10(num) +1
    *             run loop for number of dig / 2 and compare most significant digit with least significant digit if not equal then num is not palindrome
    *  Test case:
    * input : 1001 -> 10 == 01 -> 10  ans= true
    * input : 1101 -> 11 == 01 -> 10  ans= false
    * input : 10301 -> 10 == 301 -> 103 -> 10 == true
    * input : 11301 -> 11 == 311 -> 113 -> 11 == false
    *
    *
    * */

    static class PalindromeNum{
        public boolean isPalindrome1(int num) {
            if(num < 0 || (num!=0 && num%10==0)) {
                return false;
            }
            int secNum = 0;
            while(num > secNum) {
                secNum = secNum * 10 + num %10;
                num = num / 10;
            }
            return num == secNum || num == secNum/10;
        }

        public boolean isPalindrome2(int num) {
            if(num <0 || (num!=0 && num%10 ==0)) {return false;}
            int numOfdigits = (int) (Math.floor(Math.log10(num)) +1);
            long numMask = (long) Math.pow(10, numOfdigits-1);
            for(int idx=0; idx<numOfdigits/2; idx++) {
                if(num/numMask != num%10) {return false;}
                num = (int) (num % numMask);
                num = num / 10;
                numMask = numMask / 100;
            }
            return true;
        }

        public static void main(String[] args) {
           PalindromeNum palindromeNum = new PalindromeNum();
            System.out.println(true == palindromeNum.isPalindrome2(0));
            System.out.println(true == palindromeNum.isPalindrome2(1001));
            System.out.println(true == palindromeNum.isPalindrome2(10301));
            System.out.println(true == palindromeNum.isPalindrome2(121));
            System.out.println(false == palindromeNum.isPalindrome2(-121));
            System.out.println(false == palindromeNum.isPalindrome2(10));
            System.out.println(false == palindromeNum.isPalindrome2(1101));
            System.out.println(false == palindromeNum.isPalindrome2(11301));
        }

    }



}
