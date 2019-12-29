package com.lrn.cci.stringsandarrays;

import java.util.ArrayList;
import java.util.Random;

public class MatrixRowAndColumnZero {

	public void setToZero(final int[][] array) {
		ArrayList<Integer> rows = new ArrayList<>();
		ArrayList<Integer> columns = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] == 0) {
					rows.add(i);
					columns.add(j);
				}
			}
		}
		rows.forEach(row -> {
			for (int j = 0; j < array[row].length; j++) {
				array[row][j] = 0;
			}
		});

		columns.forEach(column -> {
			for (int i = 0; i < array.length; i++) {
				array[i][column] = 0;
			}
		});

	}

	public void printArray(final int[][] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(" | ");
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " | ");
			}
			System.out.println("");
		}
	}

	public static void main(final String[] args) {
		MatrixRowAndColumnZero obj = new MatrixRowAndColumnZero();
		int[][] array = new int[7][7];
		Random randomFunction = new Random();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				array[i][j] = randomFunction.nextInt(10);
			}
		}

		System.out.println("original array");
		obj.printArray(array);
		obj.setToZero(array);
		System.out.println("rotated array");
		obj.printArray(array);
	}

}
