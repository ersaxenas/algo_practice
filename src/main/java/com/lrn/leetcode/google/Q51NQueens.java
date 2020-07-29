package com.lrn.leetcode.google;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q51NQueens {
    /*
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
                if (canPlaceQueen(tmplist, row, col)) {
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

    public static void main(String[] args) {
        Q51NQueens sol = new Q51NQueens();
        LsUtil.printListOfList(sol.solveNQueens(4));
    }
}
