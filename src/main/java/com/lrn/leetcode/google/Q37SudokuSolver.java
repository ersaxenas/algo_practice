package com.lrn.leetcode.google;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Q37SudokuSolver {
    /* https://leetcode.com/problems/sudoku-solver/
     * pd: Write a program to solve a Sudoku puzzle by filling the empty cells.
     * A sudoku solution must satisfy all of the following rules:
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * Empty cells are indicated by the character '.'.
     * appr: backtracking
     *
     * */

    String rowf = "row-%d-%d";
    String colf = "col-%d-%d";
    String bkf = "block-%d%d-%d";

    public void solveSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                  char ch = board[row][col];
                  if(ch != '.') {
                      seen.add(String.format(rowf,row,Character.getNumericValue(ch)));
                      seen.add(String.format(colf,col,Character.getNumericValue(ch)));
                      seen.add(String.format(bkf,row/3,col/3,Character.getNumericValue(ch)));
                  }
            }
        }
        solverec(board,0,0,seen);
    }

    public boolean solverec(char[][] board, int row, int col, Set<String> seen) {
           //base
           if(row>=9) {
               return true;
           }
          //recursive
          int nc = (col<8) ? col+1 : 0;
          int nr = (col == 8) ? row+1 : row;
          char ch = board[row][col];
          if(ch == '.') {
             for(int cidx=1; cidx<=9;cidx++) {
                 String rv = String.format(rowf, row, cidx);
                 String cv = String.format(colf, col, cidx);
                 String bv = String.format(bkf, row / 3, col / 3, cidx);
                 if(seen.contains(rv) || seen.contains(cv) || seen.contains(bv)) {
                     continue;
                 }
                 seen.add(rv);
                 seen.add(cv);
                 seen.add(bv);
                 board[row][col] = Character.forDigit(cidx,10);
                 if(solverec(board, nr, nc, seen)) {
                     return true;
                 }
                 board[row][col] = '.';
                 seen.remove(rv);
                 seen.remove(cv);
                 seen.remove(bv);
             }
             return false;
          }
          return solverec(board,nr,nc,seen);
    }

    public static void main(String[] args) {
        char[][] sudoku = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        Q37SudokuSolver sol = new Q37SudokuSolver();
        long start = System.nanoTime();
        sol.solveSudoku(sudoku);
        long end = System.nanoTime();
        System.out.println((end-start)/1000000);
    }

}
