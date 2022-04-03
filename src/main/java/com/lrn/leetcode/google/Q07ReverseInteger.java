package com.lrn.leetcode.google;

public class Q07ReverseInteger {
    /*Reverse Integer https://leetcode.com/problems/reverse-integer/
    * assumptions: best time solution
    * Appraoch: take the last digit out of num by taking modulo 10
    * add to new num = new num * 10 + last digit
    * now check for over flow : if integer over flow occurred then num will not match previous num
    * (new num - last digit) / 10 == prevnum
    * test cases :
    * 1234 return 4321
    * 0 return 0
    * -1 return -1
    * 987654321 return 0;
    * */
    public int reverseInteger(int num) {
        int newNum = 0, prevNum;
        while(num != 0 ) {
            prevNum = newNum;
            int lasDigit = (num%10);
            newNum = newNum * 10 + lasDigit;
            if((newNum - lasDigit)/10 != prevNum) {
                return 0;
            }
            num = num / 10;
        }
        return newNum;
    }

    public static void main(String[] args) {
      Q07ReverseInteger q7ReverseInteger = new Q07ReverseInteger();
        System.out.println(q7ReverseInteger.reverseInteger(1534236469));
        System.out.println(q7ReverseInteger.reverseInteger(-2147483412));
    }
}
