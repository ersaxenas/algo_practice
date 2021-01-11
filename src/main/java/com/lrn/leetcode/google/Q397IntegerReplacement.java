package com.lrn.leetcode.google;

public class Q397IntegerReplacement {
    /*
    * pd: Given a positive integer n, you can apply one of the following operations:
If n is even, replace n with n / 2.
If n is odd, replace n with either n + 1 or n - 1.
Return the minimum number of operations needed for n to become 1.
    * assm: 1 < n < 10000, best time sol
    * appr:
    *
    * test cases:
    * */
    // TLE
    public int integerReplacement(int n) {
        int N =n;
        int cnt=0;
        while(N != 1) {
            if((N & 1) == 0) { // even - check if lsb is 0
                N = N/2;
            } else {
                if(N == 3 || Integer.bitCount(N-1) < Integer.bitCount(N+1)) {
                    N = N -1;
                } else {
                    N = N +1;
                }
            }
            cnt++;
        }
        return cnt;
    }
   // DIFFICULT TO UNDERSTAND
    public int integerReplacement2(int n) {
        int N =n;
        int cnt=0;
        while(N != 1) {
            if((N & 1) == 0) { // even - check if lsb is 0
                N >>>= 1;
            } else {
                if(N == 3 || ((N >>> 1) & 1) == 0) {
                    N = N -1;
                } else {
                    N = N +1;
                }
            }
            cnt++;
        }
        return cnt;
    }
    //https://leetcode.com/problems/integer-replacement/discuss/87928/Java-12-line-4(5)ms-iterative-solution-with-explanations.-No-other-data-structures.
    public int integerReplacement3(int n) {
        if (n == Integer.MAX_VALUE) return 32;
        int N =n;
        int cnt=0;
        while(N > 1) {
            if((N %2 ) == 0) { // even - check if lsb is 0
                N = N/2;
            } else {
                if(((N+1) % 4 == 0) && N != 3) {
                    N = N +1;
                } else {
                    N = N -1;
                }
            }
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Q397IntegerReplacement sol = new Q397IntegerReplacement();
        System.out.println(sol.integerReplacement(2147483647));
        System.out.println(sol.integerReplacement(3));
        System.out.println(sol.integerReplacement(8));
        System.out.println(sol.integerReplacement(7));
        System.out.println(sol.integerReplacement(4));
    }

}
