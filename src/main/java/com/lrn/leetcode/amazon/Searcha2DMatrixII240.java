package com.lrn.leetcode.amazon;

public class Searcha2DMatrixII240 {

	public static boolean binarySearchMatix(final int[][] matrix, final int start, final int target) {
		/*get rows and columns of the matrix */
		int rows = matrix.length-1;
		int columns = matrix[0].length-1;
		/*search in columns*/
		int hi = columns, lo = start;
		boolean elemFound=false;
		/*row is fixed here i.e. start*/
		while(lo <= hi) {
			int mid = lo + ((hi-lo)/2);
			int elem = matrix[start][mid];
			if(target < elem ) {
				hi = mid -1;
			} else if(target > elem) {
				lo = mid + 1;
			} else {
				elemFound= true;
				break;
			}
		}
		if(elemFound) {
			return true;
		}
		/*search in rows*/
		/*column is fixed here i.e. start*/
		hi = rows;
		lo =start;
		while(lo <= hi) {
			int mid = lo + ((hi-lo)/2);
			int elem = matrix[mid][start];
			if(target < elem ) {
				hi = mid -1;
			} else if(target > elem) {
				lo = mid + 1;
			} else {
				elemFound= true;
				break;
			}
		}
		return elemFound;

	}

	/*search diagonally
	 * first search in row 0 and col 0..n
	 * then search in col 0 and row 0..n
	 * and start from row1 and col 1 repart previous two steps and so on.
	 * */
	public static boolean searchMatrix(final int[][] matrix, final int target) {
		if((matrix == null) || (matrix.length==0)) {
			return false;
		}
		System.out.println("searching for "+ target);
		/*remember to take minimum of matrix */
		int shortOfRowOrColumn = Math.min(matrix.length, matrix[0].length);
		for(int i=0; i<shortOfRowOrColumn;i++) {
			if(binarySearchMatix(matrix, i, target)) {
				System.out.println("Element found "+ target);
				return true;
			}

		}
		System.out.println("Element not found "+ target);
		return false;
	}




	public static void main(final String[] args) {
		//		int[][] arr = {
		//				{1,   4,  7, 11, 15},
		//				{2,   5,  8, 12, 19},
		//				{3,   6,  9, 16, 22},
		//				{10, 13, 14, 17, 24},
		//				{18, 21, 23, 26, 30}
		//		};
		int[][] arr = {{1,1}};
		searchMatrix(arr, 0);
		searchMatrix(arr, 8);
		searchMatrix(arr, 3);
		searchMatrix(arr, -33);
		searchMatrix(arr, 555);
		searchMatrix(arr, 0);
		searchMatrix(arr, 30);

	}

}
