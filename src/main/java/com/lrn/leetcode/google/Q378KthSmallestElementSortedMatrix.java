package com.lrn.leetcode.google;

public class Q378KthSmallestElementSortedMatrix {
    /*
    * pd: Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.
    * assm: n < 1000, non null elements, best time sol
    * appr:
    * test cases:
    * */

    public int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length ==0) {
            return -1;
        }
        int N = matrix.length;
        int start = matrix[0][0], end = matrix[N-1][N-1];
        while(start < end) {
            int mid = start + (end-start) /2; // mid value
            // find no. of elements less then mid value
            int count = 0;
            int col = N-1; // last col
            for(int row=0; row < N; row++) {// find out elements <= mid value
                while(col >=0 && matrix[row][col] > mid) {
                    col--; // value at row, col is > then mid value so move to prev col
                }
                count = count + (col +1); // +1 since 0 is the first col
            }
            if(count < k) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    public static void main(String[] args) {

    }
}
