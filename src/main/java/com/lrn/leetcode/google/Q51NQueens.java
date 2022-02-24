package com.lrn.leetcode.google;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q51NQueens {
    /* 2021-12-27T12:56:17.134Z 
    https://leetcode.com/problems/n-queens/
     * pd: The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
     * ass: NxN matrix,
     *      best time sol
     * appr: backtracking
     *       can't place a queen on a cell (x,y) if Qrow == x or Qcol == y or Qrow = x - abs(y - Qcol)
     * */

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 0, n);
        return result;
    }

    public void backtrack(List<List<String>> result, List<Integer> tmplist, int row, int N) {
        if (row == N) {
            ArrayList<String> res = new ArrayList<>();
            for (int col : tmplist) {
                char[] arr = new char[N];
                Arrays.fill(arr, '.');
                arr[col] = 'Q';
                res.add(new String(arr));
            }
            result.add(res);
        } else {
            for (int col = 0; col < N; col++) {
                if (canPlaceQueen2(tmplist, row, col)) {
                    tmplist.add(col);
                    backtrack(result, tmplist, row + 1, N);
                    tmplist.remove(tmplist.size() - 1);
                }
            }
        }
    }

    public boolean canPlaceQueen(List<Integer> queenList, int row, int col) {
        for (int idx = 0; idx < queenList.size(); idx++) {
            int qcol = queenList.get(idx);
            if (qcol == col || idx == (row - Math.abs(col - qcol))) {
                return false;
            }
        }
        return true;
    }
    /*
    * for each cell (row, col) write a = row + col and b = row - col
    * ex:
    *       0          1        2        3        4        5       6        7
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *  0    0 , 0   1 ,-1    2 , -2   3 , -3   4, -4    5, -5    6, -6    7, -7
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *  1    1, 1    2, 0      3, -1,  4, -2    5, -3    6, -4     7, -5   8, -6
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *  2    2, 2    3, 1      4, 0,   5, -1    6, -2    7, -3    8 , -4   9, -5
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *  3    3, 3    4, 2      5, 1     6, 0    7 , -1   8 , -2   9 , -3   10 ,-4
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *  4    4, 4    5, 3      6,  2    7, 1    8,  0    9,  -1   10, -2   11, -3
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *  5    5, 5    6, 4      7, 3     8, 2    9, 1     10, 0    11, -1   12, -2
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *  6    6, 6    7, 5      8, 4     9, 3    10, 2,   11 ,1    12, 0    13, -1
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *  7    7, 7    8, 6      9, 5    10, 4    11, 3    12, 2    12, 1    14, 0
    *    |--------|--------|--------|--------|--------|--------|--------|--------|
    *
    *   now all diagonal cells will have either a or b in common
    *   for ex: cell 0 3 has -3  common on one diagonal and 3 on other diagonal
    *   for ex: cell 3 3 has 6 common on one diagonal and 0 on other diagonal
    * */
    public boolean canPlaceQueen2(List<Integer> queenList, int row, int col) {
        for (int idx = 0; idx < queenList.size(); idx++) {
            int qcol = queenList.get(idx);
            if (qcol == col || ((row + col) == (idx+qcol)) || ((row - col) == (idx - qcol)) ) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q51NQueens sol = new Q51NQueens();
        LsUtil.printListOfList(sol.solveNQueens(4));
    }
}
