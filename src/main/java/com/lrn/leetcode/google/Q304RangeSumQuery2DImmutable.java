package com.lrn.leetcode.google;

public class Q304RangeSumQuery2DImmutable {
    /*
     * pd: Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
     * assm: non null elem, -1000 <= elem <= 1000, row1 <= row2 adn col1 <= col2, best time sol
     * appr: same as q 303
     * test cases:
     *
     * */

    static class NumMatrix {
        /*just like sol in 303*/
        int[][] sum;
        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            sum = new int[matrix.length][matrix[0].length + 1];
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    sum[row][col + 1] = sum[row][col] + matrix[row][col];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int msum = 0;
            for (int row = row1; row <= row2; row++) {
                msum = msum + sum[row][col2 + 1] - sum[row][col1];
            }
            return msum;
        }
    }

    static class NumMatrix2 {
        /*similar to 303 but efficient : taken from lc sol. */
        int[][] dp;
        public NumMatrix2(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            dp = new int[matrix.length+1][matrix[0].length + 1];
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    dp[row+1][col+1] = dp[row+1][col] + dp[row][col+1] + matrix[row][col] - dp[row][col];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // remember in dp fist row and col is 0 so row2+1 and col2+1
            return dp[row2+1][col2+1] - dp[row1][col2+1] - dp[row2+1][col1] + dp[row1][col1];
        }
    }

    public static void main(String[] args) {
        NumMatrix sol = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(sol.sumRegion(2, 1, 4, 3));
        System.out.println(sol.sumRegion(1, 1, 2, 2));
        System.out.println(sol.sumRegion(1, 2, 2, 4));

    }

}
