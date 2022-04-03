package com.lrn.leetcode.google;

import java.util.HashSet;

public class Q036ValidSudoku {
    /* 2021-12-16T07:20:23.860Z
        https://leetcode.com/problems/valid-sudoku/
     * pd: Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * assm: best time sol
     * appr: each row can have 1 to 9 elements so assign row id and num row-0-1
     *       same for column col-0-1
     *       same for block block-0-0-1
     * */

    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seenset = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char ch = board[row][col];
                if((ch != '.') /* not empty */
                         && (!seenset.add("row-"+row+"-"+ch) /* num is not already present in row*/
                        || !seenset.add("col-"+col+"-"+ch) /* num is not already present in col*/
                        || !seenset.add("block-"+row/3+""+col/3+"-"+ch) ) /* num is not already present in the block */
                ) {
                   return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] sudoku = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        Q036ValidSudoku sol = new Q036ValidSudoku();
        System.out.println(sol.isValidSudoku(sudoku));
    }

}
