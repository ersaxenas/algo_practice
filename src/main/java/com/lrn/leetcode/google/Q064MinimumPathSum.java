package com.lrn.leetcode.google;

public class Q064MinimumPathSum {
    /*2021-12-31T10:32:06.411Z
    https://leetcode.com/problems/minimum-path-sum/
     * pd: Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     * assm; best time sol, non nulls
     * appr: dp bottom up
     *
     * test cases:
     * */

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int maxrow = grid.length, maxcol = grid[0].length;
        int[][] result = new int[maxrow][maxcol];
        result[0][0] = grid[0][0];
        for (int idx = 1; idx < maxrow; idx++) {
            result[idx][0] = result[idx - 1][0] + grid[idx][0];
        }
        for (int idx = 1; idx < maxcol; idx++) {
            result[0][idx] = result[0][idx - 1] + grid[0][idx];
        }
        for (int r = 1; r < maxrow; r++) {
            for (int c = 1; c < maxcol; c++) {
                result[r][c] = Math.min(result[r-1][c], result[r][c-1]) + grid[r][c];
            }
        }
        LsUtil.printArray(result);
       return result[maxrow-1][maxcol-1];
    }

    public static void main(String[] args) {
        Q064MinimumPathSum sol = new Q064MinimumPathSum();
        System.out.println(sol.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

}
