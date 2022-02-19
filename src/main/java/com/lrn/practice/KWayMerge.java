package com.lrn.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class KWayMerge {

    public List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> (a[0]+a[1]) - (b[0]+b[1]));
        for(int idx=0; idx<nums1.length && idx<k; idx++) {
            for(int jdx=0; jdx<nums2.length && jdx<k; jdx++) {
                if(minHeap.size() < k) {
                    minHeap.add(new int[]{nums1[idx],nums2[jdx]});
                } else {
                    final int[] top = minHeap.peek();
                    if(nums1[idx] + nums2[jdx] < top[0]+top[1]) { // sum at minheap top is less then next pair
                        break; // stop since we already have k elems in queue and now no point in adding a smaller elem
                    } else {
                        minHeap.poll();
                        minHeap.add(new int[] {nums1[idx], nums2[jdx]});
                    }
                }
            }
        }
        return new ArrayList<>(minHeap);
    }

    public int KthSamllestInSortedMatrixBinarySearch(int[][] matrix, int k) {
          int N = matrix.length;
          int start = matrix[0][0], end = matrix[N-1][N-1];
          while(start < end) {
              int[] range = new int[] {matrix[0][0], matrix[N-1][N-1]};
              int mid = start + (end -start) / 2;
              int cnt = countLessEqual(matrix, mid, range);

              if(cnt ==k) {
                  return range[0];
              }
              if(cnt < k) {
                  start = range[1];
              } else {
                  end = range[0];
              }
          }
         return start;
    }

    public int countLessEqual(int[][] matrix, int mid, int[] range) {
          int row=matrix.length-1, col =0, cnt=0;
          while(row >=0 && col < matrix[0].length) {
              if(matrix[row][col] < mid) {
                  range[0] = Math.max(range[0],matrix[row][col]);
                  cnt = cnt + row+1;
                  col++;
              } else {
                  range[1] = Math.min(range[1], matrix[row][col]);
                  row--;
              }
          }
         return cnt;
    }


    public int[] findSmallestRange(List<Integer[]> lists) {
        int rmin=Integer.MAX_VALUE, rmax=Integer.MIN_VALUE, rangeDiff =Integer.MAX_VALUE;
        int[] result = new int[2];
        // int[] -> listIdx, arrayIdx
        PriorityQueue<Integer[]> minHeap = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        for(int idx=0; idx< lists.size(); idx++) {
            minHeap.add(new Integer[] {idx, 0, lists.get(idx)[0]});
            rmax = Math.max(rmax, lists.get(idx)[0]);
        }
        while(minHeap.size() == lists.size()) {
            Integer[] minElem = minHeap.poll();
            rmin = minElem[2];
            if(rangeDiff > (rmax - rmin)) {
                rangeDiff = rmax - rmin;
                result[0] = rmin;
                result[1] = rmax;
                System.out.println("rmin :" + rmin + ",  rmax: " + rmax + ", rageDiff: " + rangeDiff);
            }
            if(minElem[1]+1 < lists.get(minElem[0]).length) {
                minElem[1]++;
                minElem[2] = lists.get(minElem[0])[minElem[1]];
                rmax = Math.max(rmax, minElem[2]);
                minHeap.add(minElem);
            }
        }
       return result;
    }


    public static void main(String[] args) {
        KWayMerge sol = new KWayMerge();
/*
        final List<int[]> kLargestPairs = sol.findKLargestPairs(new int[]{9, 8, 2}, new int[]{6, 3, 1}, 3);
        kLargestPairs.forEach( arr -> {
            System.out.println("<" + arr[0]+", "+ arr[1]+">");
        });
        int matrix[][] = { { 1, 4 }, { 2, 5 } };
        int result = sol.KthSamllestInSortedMatrixBinarySearch(matrix, 2);
        System.out.println("Kth smallest number is: " + result);

        int matrix1[][] = { { -5 } };
        result = sol.KthSamllestInSortedMatrixBinarySearch(matrix1, 1);
        System.out.println("Kth smallest number is: " + result);

        int matrix2[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
        result = sol.KthSamllestInSortedMatrixBinarySearch(matrix2, 5);
        System.out.println("Kth smallest number is: " + result);

        int matrix3[][] = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
        result = sol.KthSamllestInSortedMatrixBinarySearch(matrix3, 8);
        System.out.println("Kth smallest number is: " + result);
*/
        Integer[] l1 = new Integer[] { 1, 5, 8 };
        Integer[] l2 = new Integer[] { 4, 12 };
        Integer[] l3 = new Integer[] { 7, 8, 10 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int[] result = sol.findSmallestRange(lists);
        System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");

    }
}
