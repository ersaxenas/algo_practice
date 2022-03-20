package com.lrn.leetcode.google;

import java.util.HashSet;
import java.util.Set;

public class Q130SurroundedRegion {

    /*2022-03-06T09:18:28.617Z
    * pd:
    * assm:
    *
    * appr:
    *
    * //We will use boundary DFS to solve this problem
      // Let's analyze when an 'O' cannot be flipped,
      // if it has atleast one 'O' in it's adjacent, AND ultimately this chain of adjacent 'O's is connected to some 'O' which lies on boundary of board
      //consider these two cases for clarity :
        O's won't be flipped          O's will be flipped
        [X O X X X]                   [X X X X X]
        [X O O O X]                   [X O O O X]
        [X O X X X]                   [X O X X X]
        [X X X X X]                   [X X X X X]
      So we can conclude if a chain of adjacent O's is connected some O on boundary then they cannot be flipped
    //Steps to Solve :
    //1. Move over the boundary of board, and find O's
    //2. Every time we find an O, perform DFS from it's position
    //3. In DFS convert all 'O' to '#'      (why?? so that we can differentiate which 'O' can be flipped and which cannot be)
    //4. After all DFSs have been performed, board contains three elements,#,O and X
    //5. 'O' are left over elements which are not connected to any boundary O, so flip them to 'X'
    //6. '#' are elements which cannot be flipped to 'X', so flip them back to 'O'
    *
    *
    *
    * */

    public void solve(char[][] board) {
        int rows = board.length, cols=board[0].length;
        if(rows <= 2 || cols <= 2) return;
        for(int col=0; col<cols; col++) {
            if(board[0][col] == 'O') dfs(0,col,board);
            if(board[rows-1][col] == 'O') dfs(rows-1,col,board);
        }
        for(int row=1; row<rows-1; row++) {
            if(board[row][0] == 'O') dfs(row,0,board);
            if(board[row][cols-1] == 'O') dfs(row,cols-1,board);
        }
        LsUtil.printArray2d(board);
        for(int row=0; row<rows; row++) {
            for(int col=0; col<cols; col++) {
                if(board[row][col] == 'O') board[row][col] = 'X';
                if(board[row][col] == '#') board[row][col] = 'O';
            }
        }
        LsUtil.printArray2d(board);
    }

    public void dfs(int row, int col, char[][] board) {
        if (row < 0 || row >= board.length  || col < 0 || col >= board.length || board[row][col] != 'O') return;
        board[row][col] = '#';
        dfs(row-1,col,board);
        dfs(row+1,col,board);
        dfs(row,col-1,board);
        dfs(row,col+1,board);
    }

    public static void main(String[] args) {
        char[][] arr = {
                {'X', 'O', 'X', 'O', 'O', 'O', 'O'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'X', 'O', 'O', 'O', 'O', 'X', 'O'},
                {'O', 'O', 'O', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'X', 'O', 'O', 'O', 'O', 'O'}
         };
         Q130SurroundedRegion sol = new Q130SurroundedRegion();
         sol.solve(arr);
    }
}