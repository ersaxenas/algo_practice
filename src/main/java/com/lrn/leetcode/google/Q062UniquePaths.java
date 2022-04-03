package com.lrn.leetcode.google;

public class Q062UniquePaths {
    /*2021-12-31T10:20:36.833Z
    https://leetcode.com/problems/unique-paths/
     * pd: A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there?
     * assm: best time sol, no cell are blocked
     * appr: dp
     *
     *
     * */

    public int uniquePaths(int m, int n) {
        if (m <= 0 && n <= 0) {
            return 0;
        }
        if (m == 0 || n == 0) {
            return 1;
        }
        return move(0, m - 1, 0, n - 1, new Integer[m][n]);
    }

    public int move(int row, int maxrow, int col, int maxCol, Integer[][] cache) {
        if (row == maxrow && col == maxCol) {
            return 1;
        }
        if (cache[row][col] != null) {
            return cache[row][col];
        }
        // go down
        int cnt1 = 0;
        if (row < maxrow) {
            cnt1 = move(row + 1, maxrow, col, maxCol, cache);
        }
        // go right
        int cnt2 = 0;
        if (col < maxCol) {
            cnt2 = move(row, maxrow, col + 1, maxCol, cache);
        }
        cache[row][col] = cnt1 + cnt2;
        return cache[row][col];
    }

    public int uniquePathBU(int m, int n) {
        if (m <= 0 && n <= 0) {
            return 0;
        }
        int row = m, col = n;
        Integer[][] paths = new Integer[m][n];
        for (int idx = 0; idx < row; idx++) {
            paths[idx][0] = 1;
        }
        for (int idx = 0; idx < col; idx++) {
            paths[0][idx] = 1;
        }
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                paths[r][c] = paths[r-1][c] + paths[r][c-1];
            }
        }
        return paths[row-1][col-1];
    }

    public static void main(String[] args) {
        Q062UniquePaths sol = new Q062UniquePaths();
        System.out.println(sol.uniquePathBU(7, 3));
    }

}
