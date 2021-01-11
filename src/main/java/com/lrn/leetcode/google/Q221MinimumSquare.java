package com.lrn.leetcode.google;

public class Q221MinimumSquare {
    /*
    * pd: Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
    * assm: matix size < 100 x 100, best time sol
    * appr: use height approach
    * test cases:
    * Input:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Output: 4
    * */

    public int maximalSquare(char[][] matrix) {
       int max=0;
       if(matrix.length == 0 ) {
           return max;
       }
       int[][] nm = new int[matrix.length][matrix[0].length];
       for(int row=0; row<matrix.length; row++) {
           for(int col=0; col<matrix[0].length; col++) {
               if(row==0 || col ==0) {
                   nm[row][col] = Character.getNumericValue(matrix[row][col]);
               } else if(matrix[row][col] == '1') {
                   nm[row][col] = Math.min(Math.min(nm[row-1][col],nm[row][col-1]), nm[row-1][col-1])+1;
               }
               max = Math.max(max, nm[row][col]);
           }
       }
        return max*max;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][] {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        Q221MinimumSquare sol = new Q221MinimumSquare();
        System.out.println(sol.maximalSquare(matrix));
    }

}
