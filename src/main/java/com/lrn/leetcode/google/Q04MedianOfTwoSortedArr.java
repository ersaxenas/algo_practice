package com.lrn.leetcode.google;

import java.util.PriorityQueue;

public class Q04MedianOfTwoSortedArr {
    /**
     * Prob def: There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     * You may assume nums1 and nums2 cannot be both empty.
     */

    public double findMedian(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int maxLen = Math.max(nums1.length, nums2.length);
        for(int idx=0; idx< maxLen; idx++) {
            if(idx < nums1.length) {
                add(nums1[idx], maxHeap, minHeap);
            }
            if(idx < nums2.length) {
                add(nums2[idx], maxHeap, minHeap);
            }
        }
        return findMedian(maxHeap, minHeap);
    }

    public void add(int elem, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
       if(maxHeap.isEmpty() || elem < maxHeap.peek()) {
           maxHeap.offer(elem);
       } else {
           minHeap.offer(elem);
       }
       rebalance(maxHeap,minHeap);
    }

    public void rebalance(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if(maxHeap.size() > minHeap.size()+1) {
            minHeap.offer(maxHeap.poll());
        } else if(minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double findMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if(maxHeap.size() == minHeap.size()) {
            return (maxHeap.poll() + minHeap.poll()) / 2d;
        }
        return maxHeap.peek();
    }

    /*Approach 2: based on birnary search
    * x [..], y [..]
    * median formula => (x.len + y.len +1 ) /2 = partition x + partition y
    * binaryseach : low = 0 hi = x.len -1  : choose longest array for partition
    * partition x = low + hi / 2 , find y from median formula
    *            p                        p
    * [... xleft , xright...]  [... yleft , yright...]
    * if(xleft <= right && yleft <= xright) partiion found -> median = even : Max(xleft, yleft) + Min(xright, yright) / 2, odd : max(xleft, y left)
    * else if( xleft > yright) then hi = partition x -1
    * else low = partion x + 1
    * Test cases
    * X : [1,5,9, 23, 49] Y : [2,4,8,22] median = 8 - [1,2,4,8,9,23,49]
    * X : [80] Y : [1,2,3] median = 2.5
    * */


    public double findMedian2(int[] nums1, int[] nums2) {
         if(nums1.length < nums2.length) {
             return findMedian2(nums2, nums1);
         }
         int xlen = nums1.length, ylen = nums2.length, comblen = xlen+ylen;
         int low =0, hi = xlen;
         while(low <= hi) {
             int px = (low + hi) / 2;
             int py = ((comblen + 1) / 2) - px;

             int xleft = (px == 0) ? Integer.MIN_VALUE : nums1[px-1];
             int xright = (px == xlen) ? Integer.MAX_VALUE : nums1[px];

             int yleft = (py == 0) ? Integer.MIN_VALUE : nums2[py-1];
             int yright = (py == ylen) ? Integer.MAX_VALUE : nums2[py];

              if(xleft <= yright && yleft <= xright) {
                  if(comblen % 2 ==0) {
                      return ((Math.max(xleft, yleft) + Math.min(xright, yright)) / 2d);
                  } else {
                      return Math.max(xleft, yleft);
                  }
              } else if(xleft > yright) {
                  hi = px -1;
             } else {
                  low = px+1;
             }
          }
       throw new IllegalArgumentException("Arrays are not sored.");
    }


    public static void main(String[] args) {
        Q04MedianOfTwoSortedArr q4MedianOfTwoSortedArr = new Q04MedianOfTwoSortedArr();
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        System.out.println(q4MedianOfTwoSortedArr.findMedian2(nums1, nums2));
        nums1 = new int[]{1,2};
        nums2 = new int[]{3,4};
        System.out.println(q4MedianOfTwoSortedArr.findMedian2(nums1, nums2));
        nums1 = new int[]{1,5,9, 23, 49};
        nums2 = new int[]{2,4,8,22};
        System.out.println(q4MedianOfTwoSortedArr.findMedian2(nums1, nums2));
        nums1 = new int[]{80};
        nums2 = new int[]{1,2,3};
        System.out.println(q4MedianOfTwoSortedArr.findMedian2(nums1, nums2));
    }

}
