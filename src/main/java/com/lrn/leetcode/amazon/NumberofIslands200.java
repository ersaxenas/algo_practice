package com.lrn.leetcode.amazon;

public class NumberofIslands200 {

	/*
	 * function finds number of islands
	 */
	public int numIslands(final char[][] grid) {
		if((grid == null) || (grid.length ==0)) {
			return 0;
		}
		int no_rows = grid.length;
		int no_cols = grid[0].length;
		int no_of_islands = 0;
		/*scan the grid for 1's*/
		for(int row=0; row<no_rows; row++) {
			for(int col=0; col<no_cols; col++) {
				if(grid[row][col] == '1') {
					no_of_islands++;
					dfs(grid, row,col);
				}
			}
		}
		return no_of_islands;
	}
	/*
	 * function uses DFS and marks all the connected nodes to 0.
	 *
	 */
	public void dfs(final char[][] grid, final int row, final int col) {
		int no_row = grid.length;
		int no_col = grid[0].length;
		/*recursion base condition*/
		if((row<0) || (col<0) || (row >= no_row) || (col >= no_col) || (grid[row][col] == '0')) {
			return;
		}
		/*make current cell 0*/
		grid[row][col] = '0';
		/*make 4 the adjacent cells 0*/
		/*horizontally*/
		dfs(grid, row, col-1);
		dfs(grid, row, col+1);
		/*vertically*/
		dfs(grid, row-1,col);
		dfs(grid, row+1,col);
	}



	public static void main(final String[] args) {
		char [][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
		NumberofIslands200 obj = new NumberofIslands200();
		System.out.println(obj.numIslands(grid));
	}

}
