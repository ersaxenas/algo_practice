package com.lrn.leetcode.google;

public class Q329LongestIncreasingPathMatrix {
    /*
    * pd: Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
    * assm: matrix max dim < 1000, 0 < elem < 1000, non null, best time sol.
    * appr: dp bottom up
    * test cases:
    * */

    int rows,cols;
    int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        rows = matrix.length;
        if(rows == 0) return 0;
        cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int max = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                max = Math.max(max, dfs(matrix, r,c,dp));
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int row, int col, int[][] dp) {
        if(dp[row][col] != 0) return dp[row][col];
        int maxlen = 1;
        for (int[] dir : direction) {
            int nr = row + dir[0];
            int nc = col + dir[1];
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || matrix[row][col] >= matrix[nr][nc]) {
                continue;
            }
            int len = 1 + dfs(matrix,nr, nc, dp);
            maxlen = Math.max(maxlen, len);
        }
        dp[row][col] = maxlen;
       return maxlen;
    }


    public static void main(String[] args) {
        Q329LongestIncreasingPathMatrix sol = new Q329LongestIncreasingPathMatrix();
        int[][] matrix = new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        System.out.println(sol.longestIncreasingPath(matrix));
        matrix = new int[][]{
                {3,4,5},
                {3,2,6},
                {2,2,1}
        };
        System.out.println(sol.longestIncreasingPath(matrix));
    }


}
