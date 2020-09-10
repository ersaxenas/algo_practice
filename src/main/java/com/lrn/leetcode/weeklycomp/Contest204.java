package com.lrn.leetcode.weeklycomp;

public class Contest204 {
    /*
    https://leetcode.com/contest/weekly-contest-204/problems/detect-pattern-of-length-m-repeated-k-or-more-times/
    * 1566. Detect Pattern of Length M Repeated K or More Times
Given an array of positive integers arr,  find a pattern of length m that is repeated k or more times.
A pattern is a subarray (consecutive sub-sequence) that consists of one or more values, repeated multiple times consecutively without overlapping. A pattern is defined by its length and the number of repetitions.
Return true if there exists a pattern of length m that is repeated k or more times, otherwise return false.
    * */

    static class Q1566 {
        /*better*/
        public boolean containsPattern(int[] arr, int m, int k) {
            int count = 0;
            for (int idx = 0; idx < arr.length - m; idx++) {
                if (arr[idx] == arr[idx + m]) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == (k - 1) * m) {
                    return true;
                }
            }
            return false;
        }

        public boolean containsPattern2(int[] arr, int m, int k) {
            if (arr == null || arr.length == 0 || arr.length < k * m) {
                return false;
            }

            int idx1 = m - 1, idx2 = m * 2 - 1, match = 0;
            while (idx2 < arr.length) {
                if (isMatch(arr, idx1, idx2, m)) {
                    match = (match == 0) ? match + 2 : match + 1;
                    if (match >= k) {
                        return true;
                    }
                    idx1 = idx1 + m;
                    idx2 = idx2 + m;
                } else {
                    match = 0;
                    idx1++;
                    idx2++;
                }

            }
            return false;
        }

        public boolean isMatch(int[] arr, int idx1, int idx2, int m) {
            while (idx1 >= 0 && idx2 >= 0 && arr[idx1--] == arr[idx2--] && m > 0) {
                m--;
            }
            return m == 0;
        }

        public static void main(String[] args) {
            Q1566 sol = new Q1566();
            System.out.println(sol.containsPattern(new int[]{1, 2, 4, 4, 4, 4}, 1, 3));
        }
    }

    /*
    * 1567. Maximum Length of Subarray With Positive Product
Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
Return the maximum length of a subarray with positive product.
    *
    * */
    static class Q1567 {
        public int getMaxLen(int[] nums) {
            int noOfNeg = 0, firstNeg = -1, zeropos = -1, maxlen = 0;
            for (int idx = 0; idx < nums.length; idx++) {
                if (nums[idx] == 0) {
                    zeropos = idx;// save 0 index
                    // reset
                    firstNeg = -1;
                    noOfNeg = 0;
                    continue; // skip the index
                }
                if (nums[idx] < 0) { // -ve number
                    if (firstNeg == -1) {
                        firstNeg = idx;
                    }
                    noOfNeg++;
                }
                // calculate maxlen
                if (noOfNeg % 2 == 0) { // even no. of -ve elements
                    maxlen = Math.max(maxlen, (idx - zeropos));
                } else { // odd no. of -ve elem
                    maxlen = Math.max(maxlen, (idx - firstNeg));
                }

            }
            return maxlen;
        }

        public static void main(String[] args) {
            Q1567 sol = new Q1567();
            System.out.println(sol.getMaxLen(new int[]{-1, -2, -3, 0, 1}));
            System.out.println(sol.getMaxLen(new int[]{-1, 2}));
        }
    }

}
