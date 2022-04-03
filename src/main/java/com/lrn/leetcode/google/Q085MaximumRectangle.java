package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.Stack;

public class Q085MaximumRectangle {
    /* 2022-01-09T12:13:55.624Z
     * PD: Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     * assm: non null elem, bests time sol
     * appr: https://leetcode.com/problems/maximal-rectangle/discuss/122456/Easiest-solution-build-on-top-of-leetcode84
     *       for each row calculate height and then calculate height
     * ["1","0","1","0","0"]
     * ["1","0","1","1","1"]
     * ["1","1","1","1","1"]
     * ["1","0","0","1","0"]
     * bar height:
     * at starting at row 0 - 1 0 1 0 0
     * bar graph :
     *  0    0
     * -------------
     *  0  1  2  3  4
     * 
     * at row 1 - 2 0 2 1 1
     * bar graph :
     *  0     0
     *  0     0  0  0
     * --------------
     *  0  1  2  3  4
     *
     * at row 2 - 3 1 3 2 2
     * bar graph :
     *  0     0
     *  0     0  0  0
     *  0  0  0  0  0
     * --------------
     *  0  1  2  3  4
     *
     * at row 3 - 4 0 0 3 0
     * bar graph :
     *  0
     *  0        0
     *  0        0
     *  0        0
     * --------------
     *  0  1  2  3  4
     *
     * At each row calculate area and update max area
     * */

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        /*build height array and calculate max area for each row*/
        int[] height = new int[matrix[0].length];
        Arrays.fill(height, 0);
        int maxArea = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == '1') {
                    height[col]++;
                } else {
                    height[col] = 0;
                }
            }
            int marearow = calculateMaxAreaOfHistogram(height);
            maxArea = Math.max(maxArea, marearow);
        }
        return maxArea;
    }

    public int calculateMaxAreaOfHistogram(int[] nums) {
        int marea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int idx = 0; idx <= nums.length; idx++) {
            int heightOfNextBar = (idx == nums.length) ? 0 : nums[idx];
            /*if height of next bat is less then current bar on the top of the stack then take out the bar at teh top from
             * stack and calculate area*/
            while (!stack.isEmpty() && heightOfNextBar < nums[stack.peek()]) {
                int topBarIdx = stack.pop();
                int topBarHeight = nums[topBarIdx];
                // calculate width of the bar
                int prevBarIdx = (stack.isEmpty()) ? -1 : stack.peek();
                int width = idx - prevBarIdx - 1;
                int area = topBarHeight * width;
                marea = Math.max(area, marea);
            }

            stack.push(idx);
        }
        return marea;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'},
        };
        Q085MaximumRectangle sol = new Q085MaximumRectangle();
        System.out.println(sol.maximalRectangle(matrix));

    }

}
