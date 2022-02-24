package com.lrn.practice;

import com.lrn.leetcode.google.LsUtil;

public class ModifiedBinarySearch {
    /**/

    public int[] findRange(int[] arr, int key) {
        int[] result = new int[] {-1,-1};
        result[0] = search(arr, key, true);
        if(result[0] != -1) {
            result[1] = search(arr, key, false);
        }
        return result;
    }

    public int search(int[] arr, int key, boolean findFirst) {
        int start = 0, end = arr.length - 1, mid = 0, keyIdx = -1;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                keyIdx = mid;
                if (findFirst) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return keyIdx;
    }

    public int findMaxOfBiotonicSeq(int[] arr) {
        int start =0, end =arr.length-1, mid=0;
        while(start < end) {
            mid = start + (end-start) /2;
            if(arr[mid] < arr[mid+1]) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
        return arr[start];
    }

    public static void main(String[] args) {
        ModifiedBinarySearch sol = new ModifiedBinarySearch();
/*
        LsUtil.printArray(sol.findRange(new int[]{4, 6, 6, 6, 9}, 6));
        LsUtil.printArray(sol.findRange(new int[] { 1, 3, 8, 10, 15}, 10));
        LsUtil.printArray(sol.findRange(new int[] {1, 3, 8, 10, 15}, 12));
*/
        System.out.println(sol.findMaxOfBiotonicSeq(new int[]{1, 3, 8, 12, 4, 2}));
        System.out.println(sol.findMaxOfBiotonicSeq(new int[]{3, 8, 3, 1 }));
        System.out.println(sol.findMaxOfBiotonicSeq(new int[]{1, 3, 8, 12}));
        System.out.println(sol.findMaxOfBiotonicSeq(new int[]{10, 9, 8}));
    }


}
