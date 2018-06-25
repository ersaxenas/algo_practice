package com.lrn.leetcode.amazon;

import java.util.List;

public class SetMatrixZeroes73 {

	public void setZeroes(final int[][] matrix) {
		int no_row = matrix.length;
		int no_col = matrix[0].length;
		/*zero rows*/
		List<Integer> lstrow = new java.util.ArrayList<>();
		List<Integer> lstcol = new java.util.ArrayList<>();
		/*rows and columns to be marked as zero*/
		for(int row=0; row<no_row; row++) {
			for(int col=0; col<no_col; col++) {
				if(matrix[row][col] == 0) {
					lstrow.add(row);
					lstcol.add(col);
				}
			}
		}
		/*make rows zero*/
		for(int row: lstrow) {
			for(int col=0; col<no_col; col++) {
				matrix[row][col]=0;
			}
		}
		/*make col zero*/
		for(int col: lstcol) {
			for(int row=0; row<no_row; row++) {
				matrix[row][col] =0;
			}
		}

	}

	public static void main(final String[] args) {
		int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
		//int[][] matrix = {{1},{0}};
		SetMatrixZeroes73 obj = new SetMatrixZeroes73();
		obj.setZeroes(matrix);
	}

}
