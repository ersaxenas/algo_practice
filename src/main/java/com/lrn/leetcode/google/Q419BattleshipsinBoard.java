package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q419BattleshipsinBoard {
    /*
    * pd:Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
    * assm: best time sol, always a valid board, matrix size < 1000
    * appr: dfs
    * https://leetcode.com/problems/battleships-in-a-board/discuss/90902/Simple-Java-Solution
    * test cases:
    *
    *
    * */

    public int countBattleships(char[][] board) {
        int battleShips =0;
        final int rows = board.length;
        final int cols = board[0].length;
        boolean visited[][] = new boolean[rows][cols];
        for(int row = 0; row< rows; row++) {
            for(int col = 0; col< cols; col++) {
                if(!visited[row][col] && board[row][col] == 'X') {
                    battleShips++;
                    if(dfs(board, visited, row, col+1, true) == 0) {
                        dfs(board, visited, row+1, col, false);
                    }
                }
            }
        }
        return battleShips;
    }

    public int dfs(char[][] board, boolean[][] visited, int row, int col, boolean isHorizontal) {
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'X') {
            return 0;
        }
        visited[row][col] = true;
        if(isHorizontal) {
            return 1 + dfs(board, visited, row, col+1, isHorizontal);
        } else {
            return 1 + dfs(board, visited, row+1, col, isHorizontal);
        }
    }

    /* simply brillian solution.
    * Going over all cells, we can count only those that are the "first" cell of the battleship. First cell will be defined as the most top-left cell.
    * We can check for first cells by only counting cells that do not have an 'X' to the left and do not have an 'X' above them.
    * */
    public int countBattleships2(char[][] board) {
        int m = board.length;
        if (m==0) return 0;
        int n = board[0].length;

        int count=0;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == '.') continue;
                if (i > 0 && board[i-1][j] == 'X') continue;
                if (j > 0 && board[i][j-1] == 'X') continue;
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Q419BattleshipsinBoard sol = new Q419BattleshipsinBoard();
        char[][] board = new char[][] {
                {'X',' ',' ','X'}
        };
        System.out.println(sol.countBattleships(board));
        board = new char[][] {
                {'X',' ',' ','X'},
                {' ',' ',' ','X'},
                {' ',' ',' ','X'},
        };
        System.out.println(sol.countBattleships(board));
    }

}
