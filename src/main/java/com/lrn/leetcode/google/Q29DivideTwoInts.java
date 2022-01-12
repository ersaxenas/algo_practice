package com.lrn.leetcode.google;

public class Q29DivideTwoInts {
    /* 2021-12-10T16:23:33.462Z 
     * https://leetcode.com/problems/divide-two-integers/
     * PD: Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator. Return the quotient after dividing dividend by divisor.
     * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
     * assm: best time solution
     * appr: we cannot use * / and % but we can use + or -
     *       dividend = x * divisor or = (divisor + divisor ... x times)
     * Test cases:
     * 10 , 2 =  5
     * 11 , 2 =  5
     * 1 , 2 =  0
     * -10 , 2 = -5
     * -10 , -2 = 5
     * 10 , -2 = -5
     * 10 , 0 = int max
     * 0 , 10 = 0
     * */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        boolean isPos = true;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            isPos = false;
        }
        long absdividend = Math.abs((long)dividend);
        long absdivisor = Math.abs((long)divisor);
        if (absdividend == absdivisor) return (isPos) ? 1 : -1;
        if (absdividend < absdivisor || dividend == 0) {
            return 0;
        }
        long ans = divideRec(absdividend, absdivisor);
        if(ans > Integer.MAX_VALUE) return (isPos) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        return (int) ((isPos) ? ans : -ans);

    }

    public long divideRec(long dividend, long divisor) {
        // base case
        if (dividend < divisor) {
            return 0;
        }
        // recursive
        long sum = divisor;
        long mul = 1;
        while ((sum + sum) < dividend) {// multiply by 2, 4, 8 for performance
            sum = sum + sum;
            mul = mul + mul;
        }
        return mul + divideRec((dividend - sum), divisor);
    }

    public static void main(String[] args) {
        Q29DivideTwoInts q29DivideTwoInts = new Q29DivideTwoInts();
        System.out.println(q29DivideTwoInts.divide(-2147483648,-1));
//        System.out.println(q29DivideTwoInts.divide(10,2));
//        System.out.println(q29DivideTwoInts.divide(11,2));
//        System.out.println(q29DivideTwoInts.divide(1,2));
//        System.out.println(q29DivideTwoInts.divide(-10, 2));
//        System.out.println(q29DivideTwoInts.divide(-10, -2));
//        System.out.println(q29DivideTwoInts.divide(10, -2));
//        System.out.println(q29DivideTwoInts.divide(10, 0));
//        System.out.println(q29DivideTwoInts.divide(0, 10));
//        System.out.println(q29DivideTwoInts.divide(0, 10));
    }


}
