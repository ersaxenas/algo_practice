package com.lrn.leetcode.google;

public class Q079WordSearch {
    /*2022-01-07T12:08:57.345Z
    https://leetcode.com/problems/word-search/
    * Given a 2D board and a word, find if the word exists in the grid.
    * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
    * assm: non null elem, best time sol
    * appr: brute force: start from each cell of matrix and try to visit four adjacent cell recursively.
    *       1. if row or col is less then 0 then abandon that path
    *       2. row is more then board.lenght or col is more then board[row].length then abandon the path
    *       3. if char doesn't match the the word at index then it abandon the path else continue
    *       4. if reached at of the word then success
    * TEST CASES:
    *  A B C E
    *  S F C S
    *  A D E E
    * WORD = ABFCS -> SUCCESS
    * WORD = ABFCSA -> FAILURE
    * */
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (dfs(board, row, col, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, int row, int col, String word, int idx, boolean[][] visited) {
        if (idx == word.length()) {
            return true;
        }
        if (row < 0 || row == board.length || col < 0 || col == board[row].length) {
            return false;
        }
        if (visited[row][col]) {
            return false;
        }
        if (word.charAt(idx) == board[row][col]) {
            visited[row][col] = true;
            int[][] cells = {{row, col - 1}, {row, col + 1}, {row - 1, col}, {row + 1, col}};
            for (int[] cell : cells) {
                if (dfs(board, cell[0], cell[1], word, idx + 1, visited)) {
                    return true;
                }
            }
            visited[row][col] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board =
                {
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                };
        Q079WordSearch sol = new Q079WordSearch();
        System.out.println(sol.exist(board, "ABCCED"));
        System.out.println(sol.exist(board, "SEE"));
        System.out.println(sol.exist(board, "ABCB"));

        board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}};
        System.out.println(sol.exist(board, "ABCESEEEFS"));

    }


}
