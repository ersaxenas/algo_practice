package com.lrn.leetcode.google;

import java.util.Stack;

public class Q42TrappingRainWater {
    /* https://leetcode.com/problems/trapping-rain-water/
     * PD: Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
     * assm: arr - +ve elem, non null
     * appr: dp
     *
     * */

    public int trap(int[] height) {
        int water = 0;
        if (height == null || height.length < 2) {
            return water;
        }

        /*prep left max array*/
        int maxseen = 0;
        int[] maxright = new int[height.length];
        for (int idx = height.length - 1; idx >= 0; idx--) {
            if (height[idx] > maxseen) {
                maxseen = height[idx];
            }
            maxright[idx] = maxseen;
        }
        LsUtil.printArray(maxright);
        int maxleft = 0;
        for (int idx = 0; idx < height.length; idx++) {
            water = water + Math.max(Math.min(maxleft, maxright[idx]) - height[idx], 0);
            if (height[idx] > maxleft) {
                maxleft = height[idx];
            }
        }
        return water;
    }

    public int trap2(int[] height) {
        int water = 0;
        if (height == null || height.length < 3) {
            return water;
        }
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        while (idx < height.length && height[idx] == 0) {
            idx++;
        } // SKIP  length

        while (idx < height.length) {
            while (!stack.isEmpty() && height[idx] >= height[stack.peek()]) {
                int topIdx = stack.pop();
                if (stack.isEmpty()) {
                    break;
                } else if(height[topIdx] == height[idx]) {
                    continue;
                }
                water = water + (idx - stack.peek() - 1) * (Math.min(height[stack.peek()], height[idx]) - height[topIdx]);
            }
            stack.push(idx);
            idx++;
        }
        return water;
    }

    public static void main(String[] args) {
        Q42TrappingRainWater sol = new Q42TrappingRainWater();
        System.out.println(sol.trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

}
