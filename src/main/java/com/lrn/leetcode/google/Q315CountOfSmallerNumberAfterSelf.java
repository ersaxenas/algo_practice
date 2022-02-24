package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q315CountOfSmallerNumberAfterSelf {
    /*
    * pd:
    *
    *
    * */


     static class IndexValPair {
      int idx, val;
      public static IndexValPair of(int idx, int val) {
          IndexValPair indexValPair = new IndexValPair();
          indexValPair.idx = idx;
          indexValPair.val = val;
          return indexValPair;
      }
    }
    public List<Integer> countSmaller(int[] nums) {
       List<Integer> result = new ArrayList<>();
       if(nums == null || nums.length ==0) {
           return result;
       }
       IndexValPair[] indexValPairsArr = new IndexValPair[nums.length];
       int[] smaller = new int[nums.length];
       for(int idx=0; idx<nums.length; idx++) {
           indexValPairsArr[idx] = IndexValPair.of(idx, nums[idx]);
       }
       mergeSort(indexValPairsArr, smaller);
       for(int idx=0; idx<smaller.length; idx++) {
           result.add(smaller[idx]);
       }
       return result;
    }
    private IndexValPair[] mergeSort(IndexValPair[] indexValPairArr, int[] smaller) {
        if (indexValPairArr.length <= 1) {
            return indexValPairArr;
        }
        int mid = indexValPairArr.length/2;
         IndexValPair[] leftArr = mergeSort(Arrays.copyOfRange(indexValPairArr, 0, mid), smaller);
         IndexValPair[] rightArr = mergeSort(Arrays.copyOfRange(indexValPairArr, mid, indexValPairArr.length), smaller);
         int left=0, right=0;
         while(left < leftArr.length || right < rightArr.length) {
             if(right == rightArr.length || (left < leftArr.length && leftArr[left].val <= rightArr[right].val)) {
                 indexValPairArr[left+right] = leftArr[left];
                 smaller[leftArr[left].idx] += right; // elements seen in right side array are less then current left size index.
                 left++;
             } else {
                 indexValPairArr[left+right] = rightArr[right];
                 right++;
             }
         }
         return indexValPairArr;
    }

    public static void main(String[] args) {
        Q315CountOfSmallerNumberAfterSelf sol = new Q315CountOfSmallerNumberAfterSelf();
        LsUtil.printList(sol.countSmaller(new int[]{5, 2, 6, 1}));
    }

}
