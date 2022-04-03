package com.lrn.leetcode.google;

public class Q063UniquePaths2 {
    /*2021-12-31T10:28:34.307Z
    https://leetcode.com/problems/unique-paths-ii/submissions/+
     * pd:[is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     *n obstacle and empty space is marked as 1 and 0 respectively in the grid.
     * Note: m and n will be at most 100.
     * assm; best time sol.
     * appr: dp with memoization.
     *
     * */

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        return uniquePaths(obstacleGrid, 0, 0, new Integer[obstacleGrid.length][obstacleGrid[0].length]);
    }

    public int uniquePaths(int[][] matrix, int r, int c, Integer[][] cache) {
        if (r == matrix.length - 1 && c == matrix[0].length - 1) {
            return 1;
        }
        if (cache[r][c] != null) {
            return cache[r][c];
        }

        int pc1 = 0;
        if (r + 1 < matrix.length && matrix[r + 1][c] != 1) {
            pc1 = uniquePaths(matrix, r + 1, c, cache);
        }
        int pc2 = 0;
        if (c + 1 < matrix[0].length && matrix[r][c + 1] != 1) {
            pc2 = uniquePaths(matrix, r, c + 1, cache);
        }
        cache[r][c] = pc1 + pc2;
        return cache[r][c];
    }

    public int uniquePathBU(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0][0] == 1) {
            return 0;
        }
        int maxrow = matrix.length, maxcol = matrix[0].length;
        int path = 1;
        int[][] res = new int[maxrow][maxcol];
        for (int idx = 0; idx < maxrow; idx++) {
            if (matrix[idx][0] == 1) {
                path = 0;
            }
            res[idx][0] = path;
        }
        path = 1;
        for (int idx = 0; idx < maxcol; idx++) {
            if (matrix[0][idx] == 1) {
                path = 0;
            }
            res[0][idx] = path;
        }
        LsUtil.printArray(res);
        for (int r = 1; r < maxrow; r++) {
            for (int c = 1; c < maxcol; c++) {
                if(matrix[r][c] == 1) {
                    res[r][c] = 0;
                    continue;
                }
                res[r][c] = res[r - 1][c] + res[r][c - 1];
            }
        }
        LsUtil.printArray(res);
        return res[maxrow - 1][maxcol - 1];
    }

    public static void main(String[] args) {
        Q063UniquePaths2 sol = new Q063UniquePaths2();
        System.out.println(sol.uniquePathBU(new int[][]{{0, 0}, {1, 0}}));
//        System.out.println(sol.uniquePathBU(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

}
