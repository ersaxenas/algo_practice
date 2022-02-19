package com.lrn.leetcode.weeklycomp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WContest202 {
    /*
     * 1550. Three Consecutive Odds
     * Given an integer array arr, return true if there are three consecutive odd numbers in the array. Otherwise, return false.
     * https://leetcode.com/contest/weekly-contest-202/problems/three-consecutive-odds/
     *
     * */
    class Q1550 {
        public boolean threeConsecutiveOdds(int[] arr) {
            if (arr == null || arr.length <= 2) {
                return false;
            }
            for (int idx = 2; idx < arr.length; idx++) {
                if (arr[idx] % 2 == 1 && arr[idx - 1] % 2 == 1 && arr[idx - 2] % 2 == 1) {
                    return true;
                }
            }
            return false;
        }
    }

    /*
     * 1551. Minimum Operations to Make Array Equal
     * https://leetcode.com/contest/weekly-contest-202/problems/minimum-operations-to-make-array-equal/
     *
     * */

    class Q1551 {

    }

    /*1552. Magnetic Force Between Two Balls
     * https://leetcode.com/contest/weekly-contest-202/problems/magnetic-force-between-two-balls/
     * binary search : https://leetcode.com/problems/magnetic-force-between-two-balls/discuss/794066/Simple-Explanation
     * */
    static class Q1552 {
        public int maxDistance(int[] position, int m) {
            Arrays.sort(position);
            int low = 1;
            int hi = position[position.length-1]; // since array is sorted then max possible distance can be first and last element
            while(low < hi) {
                int mid = low + (hi - low)/2;
                if(canPlace(position, m, mid)) {
                    low = mid+1;
                } else {
                    hi = mid;
                }
            }
            return low -1;
        }


        public boolean canPlace(int[] position, int noOfBalls, int minDistance) {
            int lastPos = position[0]; // first ball at 0 position - keep track of last position
            int idx=1;
            noOfBalls--; // we have placed first ball at 0 position
            while(idx < position.length && noOfBalls > 0) {
                /* if minDistance between last position and position at idx is greater then or equal to minDistance
                *  then place the ball else skip
                * */
                if(position[idx] - lastPos >= minDistance) {
                    lastPos = position[idx];
                    noOfBalls--;
                }
                idx++;
            }
            return noOfBalls == 0; /*if all balls have been placed then return true else false*/
        }

        public static void main(String[] args) {
            Q1552 sol = new Q1552();
            System.out.println(sol.maxDistance(new int[]{1, 2, 3, 4, 7}, 3));
            System.out.println(sol.maxDistance(new int[]{5,4,3,2,1,1000000000}, 2));
        }

    }


    public int minDays(int n) {
        return md(n, new HashMap<>());
    }

    public int md(int n, Map<Integer, Integer> cache) {
        if (n <= 0) {
            return 0;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int cnt3 = n;
        if (n % 3 == 0) {
            cnt3 = 1 + md(n - (2 * (n / 3)), cache);
        }
        int cnt2 = n;
        if (n % 2 == 0) {
            cnt2 = 1 + md(n / 2, cache);
        }

        int cnt1 = n;
        cnt1 = 1 + md(n - 1, cache);

        int res = Math.min(cnt3, Math.min(cnt2, cnt1));
        cache.put(n, res);
        return res;
    }
    /*https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/discuss/794351/Java-O(log2N)-beats-100-time-with-explanation
    * */
    HashMap<Integer, Integer> cache = new HashMap<>();
    public int minD(int n) {
       if(n <= 1) {
           return n;
       }
       if(cache.containsKey(n)) { return cache.get(n);}
       /*
        remember = n -n/2 = n/2 so if we choose to ear n/2 then n/2 will remain
       if we choose to eat 0 or more each day and eat n/2
        Ex. n = 10 then we can eat 5  => n%2 + minD(n/2) = 0 + minD(5)
        if n == 11 then eat 1 today and then ear 5 next day => n%2 + minD(n/2) == 1 + minD(5) */
       int d2 = n%2 + minD(n/2);
       /*
        remember = n - 2n/3 = n/3 so if we choose to ear 2n/3 then n/3 will remain
       if we choose to eat 0 or more each day and eat n/3
        Ex. n = 10 then we can eat 1 today and eat 6 next day  => n%3 + minD(n/3) = 1 + minD(9/3)
        if n == 11 then eat 2 in next 2 days and then eat 6  third day => n%3 + minD(n/3) == 2 + minD(9/3)
        if n == 12 then eat 8 today => n%3 + minD(n/3) == 0 + minD(12/3) */
       int d3 = n%3 + minD(n/3);
       /*
       * we add 1 because it takes one day to eat n/2 or 2n/3 apples.
       * When we call that min we have already performed either operation 2 or 3 which leaves us with n - n/2 = n/2 or n - 2n/3 = n/3 apples.
       * */
       cache.put(n, 1 + Math.min(d2,d3));
       return cache.get(n);
    }

    public static void main(String[] args) {
        WContest202 con = new WContest202();
        long tt = System.nanoTime();
        try {
            System.out.println(con.minD(16));
        } finally {
            tt = System.nanoTime() - tt;
            System.out.printf("tt = %dms%n", (tt / 1_000_000));
        }
        for (int idx = 1; idx < 20; idx++) {
            System.out.printf("N = %d, day1 = %d, day2= %d \n", idx, con.minDays(idx), con.minD(idx));
        }
    }
}
