package com.lrn.leetcode.google;

public class Q059SpiralMatrix2 {
    /*2021-12-28T13:18:01.065Z
    https://leetcode.com/problems/spiral-matrix-ii/
    * pd: Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
    * assm;
    * appr:
    *
    * test cases : 3
    *  1 2 3
    *  8 9 4
    *  7 6 5
    * */

    public int[][] matrix(int n) {
        int[][] result = new int[n][n];;
        if(n <=0) {
            return result;
        }
        if(n==1) {
            return new int[][]{{1}};
        }
        int cnt=1;
        int br=0, er=n-1, bc=0, ec=n-1;
        while(br <=er && bc <= ec) {
            //  row -> left to right
            for(int idx=bc; idx<=ec && cnt<=(n*n); idx++) {
                result[br][idx] = cnt++;
            }
            br++;
            // col -> top - down
            for(int idx=br; idx<=er;idx++) {
                result[idx][ec] = cnt++;
            }
            ec--;
            // row - right to left
            for(int idx=ec; idx>=bc; idx--) {
                result[er][idx] = cnt++;
            }
            er--;
            // col -> bottom to up
            for(int idx=er; idx>=br; idx--) {
                result[idx][bc] = cnt++;
            }
            bc++;
        }
       return result;
    }

    public static void main(String[] args) {
        Q059SpiralMatrix2 sol = new Q059SpiralMatrix2();
        LsUtil.printArray(sol.matrix(0));
        LsUtil.printArray(sol.matrix(1));
        LsUtil.printArray(sol.matrix(2));
        LsUtil.printArray(sol.matrix(3));
    }

}
