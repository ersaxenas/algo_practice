package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q054SpiralMatrix {

    /* 2021-12-27T13:00:22.168Z 
    https://leetcode.com/problems/spiral-matrix/ 
    */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length ==0 || matrix[0].length ==0) {
            return  result;
        }
        int rowbegin=0, rowend=matrix.length-1;
        int colbegin=0, colend=matrix[0].length-1;
        while(rowbegin <= rowend && colbegin <= colend) {
               // top row - > left to right
               for(int col=colbegin; col<=colend; col++) {
                   result.add(matrix[rowbegin][col]);
               }
               rowbegin++;
               // right col -> top to down
               for(int row=rowbegin; row<=rowend; row++) {
                   result.add(matrix[row][colend]);
               }
               colend--;
               // last row -> right to left
               if(rowbegin <= rowend) {
                   for(int col=colend; col>=colbegin; col--) {
                       result.add(matrix[rowend][col]);
                   }
               }
               rowend--;
               // first col -> down to up
               if(colbegin <= colend) {
                   for(int row=rowend; row>=rowbegin; row--) {
                       result.add(matrix[row][colbegin]);
                   }
               }
               colbegin++;
        }
        return result;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        // not working
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length ==0 || matrix[0].length ==0) {
            return  result;
        }
        int N = matrix.length, M = matrix[0].length;
        int startcell=0;
        while(startcell <= N/2) {
            int row = startcell, col= startcell;
            while(col < (M-startcell)) {
                result.add(matrix[row][col++]);
            }
            col--;
            row++;
            while(row < (N-startcell)) {
                result.add(matrix[row++][col]);
            }
            row--;
            col--;
            while(col >= startcell) {
                result.add(matrix[row][col--]);
            }
            col++;
            row--;
            while(row > startcell) {
                result.add(matrix[row--][col]);
            }
            startcell++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Q054SpiralMatrix sol = new Q054SpiralMatrix();
        LsUtil.printList(sol.spiralOrder2(matrix));
        matrix = new int[][]{{1}, {2}, {3}};
        LsUtil.printList(sol.spiralOrder2(matrix));
        matrix = new int[][]{{1}, {2}, {3},{4}, {5}, {6},{7}, {8}, {9},{10}};
        LsUtil.printList(sol.spiralOrder2(matrix));
        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7,8}, {9, 10, 11, 12}};
        LsUtil.printList(sol.spiralOrder2(matrix));
        LsUtil.printList(sol.spiralOrder2(new int[][]{}));
    }


}
