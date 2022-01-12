package com.lrn.leetcode.google;

public class Q88MergedSortedArray {
    /*2022-01-11T15:51:22.251Z
     * pd: Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * Note:
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
     * assm: non null elem, best time sol
     * appr:
     * test case:
     * nums1 [1,2,3,0,0,0]
     * nums2 [2,5,6]
     * result [1,2,2,3,4,5,6]
     * */

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int p1 = m - 1, p2 = n - 1, idx = nums1.length - 1;
        while (p1 >= 0 && p2 >= 0) {
            int elem1 = nums1[p1];
            int elem2 = nums2[p2];
           if(elem1>= elem2) {
               nums1[idx--] = elem1;
               p1--;
           } else {
               nums1[idx--] = elem2;
               p2--;
           }
        }
        while(p2 >=0) {
            nums1[idx--] = nums2[p2];
            p2--;
        }
        LsUtil.printArray(nums1);
    }

    public static void main(String[] args) {
        Q88MergedSortedArray sol = new Q88MergedSortedArray();
        sol.merge(new int[] {1,2,3,0,0,0}, 3, new int[] {2,5,6}, 3);
        sol.merge(new int[] {1,2,3,0}, 3, new int[] {2}, 1);
        sol.merge(new int[] {0}, 0, new int[] {2}, 1);
    }
}
