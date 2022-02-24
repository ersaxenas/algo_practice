package com.lrn.educative.gci.heap;

import java.util.PriorityQueue;

public class TwoHeapTech {
    /*This pattern uses two Heaps to solve these problems; A Min Heap to find the smallest element and a Max Heap to find the biggest element.*/
    /*
     *       max heap              -       min heap
     *  tail  e < e < e  head      -   head e < e < e tail
     * */
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
        //maxHeap size == minHeap size + 1
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
}
