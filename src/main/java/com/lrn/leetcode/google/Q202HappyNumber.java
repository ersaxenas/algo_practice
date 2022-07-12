package com.lrn.leetcode.google;

import java.util.HashSet;
import java.util.Set;

public class Q202HappyNumber {
    /*2022-07-02T09:25:08.500Z
    * pd: Write an algorithm to determine if a number n is "happy".
A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
Return True if n is a happy number, and False if not.
* Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
    * assm: non -ve number, best time sol
    * appr:
    * */

    public boolean isHappy1(int n) {
        if (n == 1) return true;
        if (n == 0) return false;
        int slow = n, fast = n;
       do {
           slow = squareSum(slow);
           fast = squareSum(squareSum(fast));
           if(fast == 1) return true;
       } while(slow != fast);
       return false;
    }

    public int squareSum(int n) {
        int nextnum = 0;
        while (n > 0) {
            nextnum = (int) (nextnum + Math.pow(n % 10, 2));
            n = n / 10;
        }
        return nextnum;
    }

    public boolean isHappy(int n) {
        return isHappyRec(n, new HashSet<>());
    }

    public boolean isHappyRec(int n, Set<Integer> cache) {
        if (n == 1) return true;
        if (n == 0 || cache.contains(n)) return false;
        cache.add(n);
        int nextnum = 0;
        while (n > 0) {
            nextnum = (int) (nextnum + Math.pow(n % 10, 2));
            n = n / 10;
        }
        return isHappyRec(nextnum, cache);
    }

    public static void main(String[] args) {
        Q202HappyNumber sol = new Q202HappyNumber();
        System.out.println(sol.isHappy1(19) ? "passed" : "failed");
        System.out.println(sol.isHappy1(10) ? "passed" : "failed");
        System.out.println(!sol.isHappy1(11) ? "passed" : "failed");
    }

}
