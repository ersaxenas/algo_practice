package com.lrn.leetcode.google;

public class LsUtil {
    public static void printArray(final Integer[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " | ");
            }
            System.out.println("");
        }
    }

    public static  <E> void printArray(final E[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j].toString() + " | ");
            }
            System.out.println("");
        }
    }

    public static void printArray(final Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            System.out.print(array[i] + " | ");
        }
        System.out.println("");
    }

    public static <E> void printArray(final E[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            System.out.print(array[i].toString() + " | ");
        }
        System.out.println("");
    }

    public static void printArray(final int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " | ");
            }
            System.out.println("");
        }
    }

    public static void printArray(final int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            System.out.print(array[i] + " | ");
        }
        System.out.println("");
    }
}
