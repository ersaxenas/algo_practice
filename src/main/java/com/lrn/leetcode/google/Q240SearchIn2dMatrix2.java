package com.lrn.leetcode.google;

public class Q240SearchIn2dMatrix2 {
    /*
     * pd: Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * assm: non null element, no. of elem < int.max, best time sol
     * appr: https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66140/My-concise-O(m%2Bn)-Java-solution
     *
     * Test cases:
     * */

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length ==0) {
            return false;
        }
        int rows = matrix.length, cols = matrix[0].length;
        /*search  row*/
        int col = cols -1, row = 0;
        while(row < rows && col >=0) {
            if(matrix[row][col] == target) {
                return true;
            } else if(target < matrix[row][col]){
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q240SearchIn2dMatrix2 sol = new Q240SearchIn2dMatrix2();
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(sol.searchMatrix(matrix, 5));
        System.out.println(sol.searchMatrix(matrix, 20));
        matrix = new int[][] {{1,4},{2,5}};
        System.out.println(sol.searchMatrix(matrix, 2));

    }

}
