package com.lrn.leetcode.google;

import java.util.PriorityQueue;

public class Q295FindMedianFromDataStream {
    /*
    * pd: Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
For example,
[2,3,4], the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5
Design a data structure that supports the following two operations:
void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
    * assm: no of max elem < INT.MAX, non null elem, 0 < N < 100, best time sol, median will be double
    * appr: two heaps
    * Test cases:
    * Input 1 output 1
    * Input 2 output 1.5
    * Input 3 output 2
    * */

    /**
     * initialize your data structure here.
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((e1, e2) -> e2 - e1);

    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
        } else {
            if (num > maxHeap.peek()) {
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
        }
        rebalance();
    }

    private void rebalance() {
        if(maxHeap.size() > minHeap.size()+1) {
            minHeap.add(maxHeap.poll());
        }
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (!maxHeap.isEmpty()) {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2d;
            }
        }
        return 0d;
    }

    public static void main(String[] args) {
       Q295FindMedianFromDataStream sol = new Q295FindMedianFromDataStream();
        sol.addNum(1);
        System.out.println(sol.findMedian() == 1 ? "pass" : "fail");
        sol.addNum(2);
        System.out.println(sol.findMedian() == 1.5 ? "pass" : "fail");
        sol.addNum(3);
        System.out.println(sol.findMedian() == 2 ? "pass" : "fail");

    }


}
