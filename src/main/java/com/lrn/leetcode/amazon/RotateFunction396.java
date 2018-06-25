package com.lrn.leetcode.amazon;

public class RotateFunction396 {



	public static void rotateArray(final int[] arr) {
		int no_of_rotation = arr.length;
		int N = arr.length;
		int maxSum = Integer.MIN_VALUE;
		for(int rotation =0; rotation< no_of_rotation; rotation++ ) {
			int startIndex = N-rotation;
			int sum = 0;
			System.out.print("[");
			for(int i=0; i<arr.length; i++) {
				if(startIndex == N) {
					startIndex =0;
				}
				sum = sum + (i*arr[startIndex]);
				startIndex++;
			}
			System.out.print(sum +"]\n");
			maxSum = Math.max(maxSum	, sum);
		}
		System.out.println("max sum " + maxSum);
	}


	public int  maxRotateFunction(final int[] arr) {
		if((arr==null) || (arr.length==0)) {
			return 0;
		}
		int no_of_rotation = arr.length;
		int N = arr.length;
		int maxSum = Integer.MIN_VALUE;
		for(int rotation =0; rotation< no_of_rotation; rotation++ ) {
			int startIndex = N-rotation;
			int sum = 0;
			for(int i=0; i<arr.length; i++) {
				if(startIndex == N) {
					startIndex =0;
				}
				sum = sum + (i*arr[startIndex]);
				startIndex++;
			}
			maxSum = Math.max(maxSum	, sum);
		}
		return maxSum;
	}


	public static void main(final String[] args) {
		//int[] arr = {4, 3, 2, 6};
		int[] arr = {-2147483648,-2147483648};
		rotateArray(arr);
	}

}
