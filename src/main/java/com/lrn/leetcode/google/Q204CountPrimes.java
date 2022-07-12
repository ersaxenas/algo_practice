package com.lrn.leetcode.google;

public class Q204CountPrimes {
    /*2022-07-03T08:32:31.066Z
    * pd: https://leetcode.com/problems/count-primes
    * assm: num < int max, best time sol
    * appr: https://leetcode.com/problems/count-primes/discuss/57588/My-simple-Java-solution
    * test cases:
    * */

    public int countPrimes(int n) {
        if(n < 2) return 0;
        boolean[] nonprime = new boolean[n]; // assume everything is prime
        int cnt=0;
        for(int idx=2; idx < n; idx++) {
            if(nonprime[idx] == false) { // if num at idx is not prime
                cnt++;
                // for idx -> idx * 2, idx * 3 .... < n
                for(int t = 2; t*idx < n; t++) {
                    nonprime[t*idx] = true;
                }
            }
        }
        return cnt;
    }
}
