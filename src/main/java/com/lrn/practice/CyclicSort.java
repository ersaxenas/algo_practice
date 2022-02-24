package com.lrn.practice;

import com.lrn.leetcode.google.LsUtil;

public class CyclicSort {
    public void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public void csort(int[] arr) {
        int idx=0;
        while(idx<arr.length) {
              int elem = arr[idx];
              if(arr[elem-1] != elem) {
                  swap(arr, idx, elem-1);
                  continue;
              }
              idx++;
        }
    }

    public static void main(String[] args) {
        CyclicSort sol = new CyclicSort();
        int[] arr = new int[] { 3, 1, 5, 4, 2 };
        sol.csort(arr);
        LsUtil.printArray(arr);

        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        sol.csort(arr);
        LsUtil.printArray(arr);

        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        sol.csort(arr);
        LsUtil.printArray(arr);
    }
}
