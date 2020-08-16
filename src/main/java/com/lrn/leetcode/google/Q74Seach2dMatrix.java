package com.lrn.leetcode.google;

public class Q74Seach2dMatrix {
    /*
     * pd: Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * assm: matrix has no null elem, best time sol
     * appr: treat matrix an 1d array
     *      start = 0 , end = row * col -1
     *      now element location in an array for ex:
     *      4x4 matrix
     *      start =0 , end = 4x4-1 = 15
     *      now lets find location of elem at index 9: (remember since start index is 0)
     *      row = 9/col = 9/4 = 2, col = 9%col = 1 = 1 so 9th element will be at [2,1] position
     *      we can use binary search
     * Test cases:
     * [1,   3,  5,  7]
     * [10, 11, 16, 20]
     * [23, 30, 34, 50]
     * target = 3 ans: true
     * target 13  ans: false
     * */

     public boolean searchMatrix(int[][] matrix, int target) {
         boolean result = false;
         if(matrix == null || matrix.length==0 || matrix[0].length ==0) {
             return result;
         }
         int rows= matrix.length, cols= matrix[0].length;
         int start =0, end = rows*cols -1;
         while(start <= end) {
                int mid = start + (end-start)/2;
                int midelem = matrix[mid/cols][mid%cols];
                if(target == midelem) {
                    return true;
                } else if(target < midelem) {
                    end = mid -1;
                } else {
                    start = mid + 1;
                }
         }
        return result;
     }

    public static void main(String[] args) {
        Q74Seach2dMatrix sol = new Q74Seach2dMatrix();
        int[][] matrix = {{1,3,5,6},{10,11,16,20},{23,30,34,50}};
        System.out.println(sol.searchMatrix(matrix, 3));
        System.out.println(sol.searchMatrix(matrix, 11));
        System.out.println(sol.searchMatrix(matrix, 30));
        System.out.println(sol.searchMatrix(matrix, 100));
        System.out.println(sol.searchMatrix(matrix, 12));
        System.out.println(sol.searchMatrix(matrix, 13));
    }

}
