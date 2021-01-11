package com.lrn.leetcode.google;

public class Q278FirstBadVersion {
    /*
    * pd: You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
    * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
    * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
    * assm: n < 10000, best time sol
    * appr: binary search
    * Test cases:
    *  Input: n = 5, bad = 4 Output: 4
    * */
    int badversion = 0;

    public Q278FirstBadVersion(int badversion) {
        this.badversion = badversion;
    }

    public int firstBadVersion(int n) {

        int start =1, end = n, mid;
        while(start < end) {
            mid = start + (end - start) / 2;
            if(isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        return end;
    }

    boolean isBadVersion(int version) {
        return version >= badversion;
    }

    public static void main(String[] args) {
        Q278FirstBadVersion sol = new Q278FirstBadVersion(4);
        System.out.println(new Q278FirstBadVersion(4).firstBadVersion(5) == 4  ? "pass" : "fail");
        System.out.println(new Q278FirstBadVersion(1).firstBadVersion(1) == 1  ? "pass" : "fail");
        System.out.println(new Q278FirstBadVersion(10).firstBadVersion(100) == 10  ? "pass" : "fail");
    }

}
