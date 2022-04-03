package com.lrn.leetcode.google;

public class Q048RotateImage {

    /* 2021-12-25T07:29:03.398Z 
    https://leetcode.com/problems/rotate-image/
     * pd: You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * assm: array is of N*N
     *  appr:
     *  clockwise -> reverse rows
     *  swap over symmetry -> draw a line from 00 to nn then swap rest of the elements
     * */
    /*
     * clockwise rotate
     * first reverse up to down, then swap the symmetry
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     */

    /*
     * anticlockwise rotate
     * first swap the symmetry  then reverse up to down
     * 1 2 3     1 4 7     3 6 9
     * 4 5 6  => 2 5 8  => 2 5 8
     * 7 8 9     3 6 9     1 4 7
     */

    public void rotate(int[][] matrix) {
        LsUtil.printArray(matrix);
        reverserows(matrix);
        swap(matrix);
        LsUtil.printArray(matrix);
    }

    public void swap(int[][] matrix) {
        for (int row = 0; row < matrix[0].length; row++) {
            for (int col = row + 1; col < matrix[0].length; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }
    }

    public void reverserows(int[][] matrix) {
        int start = 0, end = matrix[0].length - 1;
        while (start < end) {
            int[] tmp = matrix[start];
            matrix[start] = matrix[end];
            matrix[end] = tmp;
            start++;
            end--;
        }
    }

    public void rotateAntiClockWise(int[][] matrix) {
        LsUtil.printArray(matrix);
        swap(matrix);
        reverserows(matrix);
        LsUtil.printArray(matrix);
    }

    public static void main(String[] args) {
        Q048RotateImage sol = new Q048RotateImage();
        sol.rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        sol.rotateAntiClockWise(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }

}
