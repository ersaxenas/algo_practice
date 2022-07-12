package com.lrn.leetcode.google;

public class Q200NumberOfIslands {

    /*2022-07-02T08:32:19.513Z
     * pd: Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     * assm: grid only contains 0 or 1 and no nulls, no. of cell in grid < 10000, best time solution
     * appr: start with first of matrix if it 1 then run dfs from this cell and visit all 4 neighbours.
     *       Idea here is to start from a 1 and make all connecting cell with 1 value to 0.
     * test cases:
     *
     * */

    public int numIslands(char[][] grid) {
        int noOfRows = grid.length;
        int noOfCols = grid[0].length;
        int islands = 0;
        for (int row = 0; row < noOfRows; row++) {
            for (int col = 0; col < noOfCols; col++) {
                if (grid[row][col] == '1') {
                    islands++;
                    dfs(grid,row,col);
                }
            }
        }
        return islands;
    }

    public void dfs(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
    }
    int[][] direction = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    public void dfs2(char[][] grid, int row, int col) {
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != '1') return;
        grid[row][col] = 'x';
        for(int[] dir : direction) {
            dfs(grid, row+dir[0], col+dir[1]);
        }
    }

    public static void main(String[] args) {
         char[][] grid = new char[][] {
                 {'1','1','1','1','0'},
                 {'1','1','0','1','0'},
                 {'1','1','0','0','0'},
                 {'0','0','0','0','0'}
         };
         Q200NumberOfIslands sol = new Q200NumberOfIslands();
        System.out.println(sol.numIslands(grid)==1 ? "passed" : "failed");
    }

}
