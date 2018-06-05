package com.lrn.cci.stringsandarrays;

public class MatrixRotation {

	public void rotateMatrix(final String[][] matrix, final int size) {
		int N = size;
		for (int i = 0; i < (N / 2); i++) {
			for (int j = i; j < (N - 1 - i); j++) {
				String x = matrix[i][j];
				/* element size-layer */
				matrix[i][j] = matrix[N - 1 - j][i];
				matrix[N - 1 - j][i] = matrix[N - 1 - i][N - 1 - j];
				matrix[N - 1 - i][N - 1 - j] = matrix[j][N - 1 - i];
				matrix[j][N - 1 - i] = x;
				// System.out.println(i + "," + j + "=" + (N - 1 - j) + "," + i + ":" + (N - 1 - j) + "," + i + "=" + (N - 1 - i) + "," + (N - 1 - j) + ":" + (N - 1 - i) + ","
				// + (N - 1 - j) + "=" + j + "," + (N - 1 - i) + ":" + j + "," + (N - 1 - i) + "=" + i + "," + j);

			}
		}
	}

	public void printArray(final String[][] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(" | ");
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " | ");
			}
			System.out.println("");
		}
	}

	public static void main(final String[] args) {
		String[][] array = new String[7][7];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				array[i][j] = i + "," + j;
			}
		}
		MatrixRotation matrixRotation = new MatrixRotation();
		System.out.println("original array");
		matrixRotation.printArray(array);
		matrixRotation.rotateMatrix(array, array.length);
		System.out.println("rotated array");
		matrixRotation.printArray(array);

	}

}
