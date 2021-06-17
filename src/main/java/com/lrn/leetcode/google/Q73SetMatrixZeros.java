package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q73SetMatrixZeros {
    /*https://leetcode.com/problems/set-matrix-zeroes
    * https://leetcode.com/problems/set-matrix-zeroes/discuss/26014/Any-shorter-O(1)-space-solution
    *
    * */

    public void setZeroes(int[][] matrix) {
        if(matrix == null) return;
        boolean isCol0Marked = false;
        for(int row=0; row<matrix.length; row++) {
            if(matrix[row][0] == 0) {
                isCol0Marked = true;// col 0 is marked 0
            }
            for(int col=1; col < matrix[0].length; col++) {
                if(matrix[row][col] == 0) { // found cell with 0
                    matrix[0][col] = 0; // mark first cell of row as 0
                    matrix[row][0] = 0; // mark first cell of col as 0
                }
            }
        }
        for(int row=matrix.length-1; row>=0; row--) {
            for(int col=matrix[0].length-1; col>=1; col--) {
                if(matrix[row][0] == 0 || matrix[0][col] ==0) { // if first cell of row or col is 0
                    matrix[row][col] = 0;
                }
            }
            if(isCol0Marked) matrix[row][0] = 0; // if col 0 is marked then set firs cell of the row as 0
        }
    }


}
