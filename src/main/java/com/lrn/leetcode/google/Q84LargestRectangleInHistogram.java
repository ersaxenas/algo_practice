package com.lrn.leetcode.google;

import java.util.Stack;

public class Q84LargestRectangleInHistogram {
    /* https://leetcode.com/problems/largest-rectangle-in-histogram/
    * pd : Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
    * */

    public int largestRecArea(int[] heights) {
        int maxarea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int idx=0; idx<=heights.length; idx++) {
            int h = (idx == heights.length) ? 0 : heights[idx];
            while(!stack.isEmpty() && h < heights[stack.peek()]) {
                int currBarHeight = heights[stack.pop()]; // calculate area of current bar
                int prevIndex = (stack.isEmpty()) ? -1 : stack.peek(); // to calculate bar width
                int area = currBarHeight * (idx - prevIndex -1);
                maxarea = Math.max(maxarea, area);
            }
            stack.push(idx);
        }
        return maxarea;
    }

    public static void main(String[] args) {
        Q84LargestRectangleInHistogram sol = new Q84LargestRectangleInHistogram();
        System.out.println(sol.largestRecArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
