package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class Q239SlindingWinMax {
    /*
     * pd: You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
     * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
     * assm: arr len < int.max, non null elem, k < nums.len ,best time sol
     * appr:
     *
     * test cases:
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3 Output: [3,3,5,5,6,7]
     * */

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0 || k > nums.length) {
            return null;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((e1, e2) -> e2 - e1);
        for (int num : nums) {
            if (maxHeap.size() < k) {
                maxHeap.add(num);
            }
        }
        int[] res = new int[nums.length - k + 1];
        res[0] = maxHeap.peek();
        int i = 1;
        for (int idx = k; idx < nums.length; idx++) {
            maxHeap.remove(nums[idx - k]);
            maxHeap.add(nums[idx]);
            res[i++] = maxHeap.peek();
        }
        LsUtil.printArray(res);
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        int idx = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int we = 0; we < nums.length; we++) {
            int elem = nums[we];
            // index at queue head is less then window start limit
            // so if we are at index we then window start will be we-k+1
            if (!queue.isEmpty() && queue.peek() < (we - k + 1)) {
                queue.poll(); // remode from head
            }
            // remove all the element smaller then element at we from tail (not head)
            while (!queue.isEmpty() && elem >= nums[queue.peekLast()]) { // is element at we greater then last element in queue
                queue.pollLast(); // remove from tail
            }
            queue.add(we); // add index
            if ((we - k + 1) >= 0) { // window established
                res[idx++] = nums[queue.peek()];
            }
        }
        LsUtil.printArray(res);
        return res;
    }

    public static void main(String[] args) {
        Q239SlindingWinMax sol = new Q239SlindingWinMax();
        sol.maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);

        sol.maxSlidingWindow(new int[]{1}, 1);
        sol.maxSlidingWindow(new int[]{1, -1}, 1);
        sol.maxSlidingWindow(new int[]{9, 11}, 2);
    }


}
