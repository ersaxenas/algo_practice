package com.lrn.leetcode.google;

import java.util.PriorityQueue;

public class Q215KthLargestElem {
    /*
    * pd: Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
    * assm: non null, k < arr len, arr len < 10000, best time sol
    * appr: minHeap
    * test cases:
    * Input: [3,2,1,5,6,4] and k = 2 Output: 5
    * Input: [3,2,3,1,2,4,5,5,6] and k = 4 Output: 4
    * */

    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length ==0 || k > nums.length) {
            return -1;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int elem: nums) {
            minHeap.add(elem);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
       return minHeap.peek();
    }

    public static void main(String[] args) {
        Q215KthLargestElem sol = new Q215KthLargestElem();
        System.out.println(sol.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(sol.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }


}
