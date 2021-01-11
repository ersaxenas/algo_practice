package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q308RangeSumQuery2DMutable {
    /*
     * pd: Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
     * assm:
     * appr: fenwik tree or binary index tree https://www.youtube.com/watch?v=uSFzHCZ4E-8
     *
     * */

    static class NumMatrix {
        int[][] tree; // bit tree, sumNums(0->i) will be stored at tree(i+1), tree is reference by Length
        int[][] nums; // a deep clone of the input matrix. otherwise matrix might be updated by other process
        int rows;
        int cols;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0) return;
            rows = matrix.length;
            cols = matrix[0].length;
            tree = new int[rows + 1][cols + 1]; // 00 of tree remains 0
            // prep deep clone of matrix
            nums = new int[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    update(r, c, matrix[r][c]);
                }
            }
        }

        public void update(int row, int col, int val) {
            if (rows == 0 || cols == 0 || row < 0 || row > rows || col < 0 || col > cols) return;
            int oldVal = nums[row][col]; // keep old value
            nums[row][col] = val; // store new value
            int delta = val - oldVal;
            // now update tree
            for (int i = row + 1; i <= rows; i = i + (i & (-i))) {
                for (int j = col + 1; j <= cols; j = j + (j & (-j))) {
                    tree[i][j] += delta;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (rows == 0 || cols == 0 || row1 < 0 || row1 > rows || col1 < 0 || col1 > cols || row2 < 0 || row2 > rows || col2 < 0 || col2 > cols) return 0;
            return sum(row2, col2) - sum(row1 - 1, col2) - sum(row2, col1 - 1) + sum(row1 - 1, col1 - 1);
        }

        public int sum(int row, int col) {
            int s = 0;
            for (int i = row + 1; i > 0; i = i - (i & (-i))) {
                for (int j = col + 1; j > 0; j = j - (j & (-j))) {
                    s = s + tree[i][j];
                }
            }
            return s;
        }
    }

    // easy to understand sol. https://leetcode.com/problems/range-sum-query-2d-mutable/discuss/75852/15ms-easy-to-understand-java-solution
    static class NumMatrix2 {

        private int[][] colSums;
        private int[][] matrix;
        int rows, cols;

        public NumMatrix2(int[][] matrix) {
            if (matrix == null
                    || matrix.length == 0
                    || matrix[0].length == 0) {
                return;
            }

            this.matrix = matrix;
            rows = matrix.length;
            cols = matrix[0].length;
            colSums = new int[rows + 1][cols]; // height of each col. at row 0 height of col is 0
            for (int row = 1; row <= rows; row++) {
                for (int col = 0; col < cols; col++) {
                    colSums[row][col] = colSums[row - 1][col] + matrix[row - 1][col];
                }
            }
        }

        //time complexity for the worst case scenario: O(m)
        public void update(int row, int col, int val) {
            int diff = val - matrix[row][col];
            for (int ridx = row + 1; ridx <= rows; ridx++) {
                colSums[ridx][col] = colSums[ridx][col] + diff;
            }
            matrix[row][col] = val;
        }

        //time complexity for the worst case scenario: O(n)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res =0;
            for(int c=col1; c<=col2; c++) {
                res = res + colSums[row2+1][c] - colSums[row1][c];
            }
            return res;
        }

    }


    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(nums);

//        for (int i = 1; i < 10; i++) {
//            List<Integer> lst = new ArrayList<>();
//            for (int idx = i; idx <= 100; idx = idx + (idx & (-idx))) {
//                lst.add(idx);
//            }
//            LsUtil.printList(lst);
//        }
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        numMatrix.update(3, 2, 2);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println("done");
        int[][] nums2 = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        NumMatrix2 numMatrix2 = new NumMatrix2(nums);
        System.out.println(numMatrix2.sumRegion(2, 1, 4, 3));
        numMatrix2.update(3, 2, 2);
        System.out.println(numMatrix2.sumRegion(2, 1, 4, 3));
        System.out.println("done");
    }
}
