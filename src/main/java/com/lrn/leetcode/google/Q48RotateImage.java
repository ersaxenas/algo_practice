package com.lrn.leetcode.google;

public class Q47RotateImage {

    /*
     * pd: You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * assm: array is of N*N
     *  appr:
     *  clockwise -> reverse rows
     *  swap over symmetry -> drow a line from 00 to nn then swap rest of the elements
     * */

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
        }
    }

    public static void main(String[] args) {
        
    }

}
