package com.lrn.leetcode.google;

import java.util.List;

public class LsUtil {
    public static void printArray(final Integer[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static  <E> void printArray2d(final E[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j].toString() + " | ");
            }
            System.out.println();
        }
    }

    public static  void printArray2d(final boolean[][] array) {
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void printArray(final Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            System.out.print(array[i] + " | ");
        }
        System.out.println();
    }

    public static <E> void printArray(final E[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            System.out.print(array[i].toString() + " | ");
        }
        System.out.println();
    }

    public static void printArray(final int[][] array) {
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void printArray(final int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            System.out.print(array[i] + " | ");
        }
        System.out.println();
    }

    public static <E> void printList(final List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(" | ");
            System.out.print(list.get(i).toString() + " | ");
        }
        System.out.println();
    }
    public static <E> void printListOfList(final List<List<E>> list) {
        for(List<E> child: list) {
            System.out.print(" <");
            for (int i = 0; i < child.size(); i++) {
                System.out.print(" | ");
                System.out.print(child.get(i).toString() + " | ");
            }
            System.out.print("> \n");
        }
        System.out.println();
    }
}
