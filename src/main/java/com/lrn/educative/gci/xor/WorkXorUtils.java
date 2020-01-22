package com.lrn.educative.gci.xor;

public class WorkXorUtils {
    /*Given an array of n-1 integers in the range from 1 to n, find the one number that is missing from the array.*/
    static class MissingNumFromArray {
        public Integer find(int[] arr) {
            assert arr!=null &&  arr.length > 1;
            Integer s1 =0;
            // sum
            for (int idx = 0; idx <= arr.length+1; idx++) {
                s1 = s1 ^ idx;
            }
            // XOR of all value in arr
            int x2 = arr[0];
            for (int idx = 1; idx < arr.length; idx++) {
                x2 = x2 ^ arr[idx];
            }
            return s1 ^ x2;
        }
        public static void main(String[] args) {
            MissingNumFromArray missingNumFromArray = new MissingNumFromArray();
            int[] arr = new int[] { 1, 5, 2, 6, 4 };
            System.out.print("Missing number is: " + missingNumFromArray.find(arr));
        }
    }
    /*In a non-empty array of numbers, every number appears exactly twice except two numbers that appear only once. Find the two numbers that appear only once.*/
    static class FindTwoNonDupInArray {
        public int[] find(int[] arr) {
            int n1xn2 =0;
            for (int num: arr) {
                n1xn2 = n1xn2 ^ num;
            }
            System.out.println("n1xn2 = " + n1xn2);
            int rightMostBit = 1;
            while((rightMostBit & n1xn2) == 0) {
                rightMostBit = rightMostBit << 1;
            }
            System.out.println("rightMostBit = " + rightMostBit);
            int num1=0, num2=0;
            for (int num: arr) {
                if((rightMostBit & num) != 0) {
                    num1 = num1 ^ num;
                } else {
                    num2 = num2 ^ num;
                }
            }
            return new int[] {num1,num2};
        }

        public static void main(String[] args) {
            FindTwoNonDupInArray findTwoNonDupInArray = new FindTwoNonDupInArray();
            int[] arr = new int[] { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
            int[] result = findTwoNonDupInArray.find(arr);
            System.out.println("Single numbers are: " + result[0] + ", " + result[1]);

            arr = new int[] { 2, 1, 3, 2 };
            result = findTwoNonDupInArray.find(arr);
            System.out.println("Single numbers are: " + result[0] + ", " + result[1]);
        }
    }
    /*Complement of Base 10 Number*/
    static class ComplementBaseTen {
        public Integer bitwiseComplement(int num) {
            // find bit count -- shift right
            int bitCount =0, n=num;
            while(n > 0) {
                bitCount++;
                n= n >> 1;
            }

            int allBitSet = (int) (Math.pow(2, bitCount) -1);
            return num ^ allBitSet;
        }

        public static void main(String[] args) {
            ComplementBaseTen complementBaseTen = new ComplementBaseTen();
            System.out.println("Bitwise complement is: " + complementBaseTen.bitwiseComplement(8));
            System.out.println("Bitwise complement is: " + complementBaseTen.bitwiseComplement(10));
        }
    }

    /*Given a binary matrix representing an image, we want to flip the image horizontally, then invert it.
To flip an image horizontally means that each row of the image is reversed. For example, flipping [0, 1, 1] horizontally results in [1, 1, 0].
To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [1, 1, 0] results in [0, 0, 1].*/
    static class FlipAndInvertMatrix {
        public void flipAndInvert(int[][] arr) {
            assert arr != null;
            int col = arr[0].length;
            for (int[] row : arr) {
                for (int idx = 0; idx < ((col + 1) / 2); idx++) {
                    int tmp = row[idx] ^ 1; // invert
                    row[idx] = row[col - 1 -idx] ^ 1; // flip and invert
                    row[col - 1 - idx] = tmp;
                }
            }
            print(arr);
        }
        public void print(int[][] arr) {
            for(int i=0; i < arr.length; i++) {
                for (int j=0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {
            FlipAndInvertMatrix flipAndInvertMatrix = new FlipAndInvertMatrix();
            int[][] arr = {{1, 0, 1}, {1, 1, 1}, {0, 1, 1}};
            flipAndInvertMatrix.flipAndInvert(arr);
            System.out.println();

            int[][]arr2 = {{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}};
            flipAndInvertMatrix.flipAndInvert(arr2);
        }
    }


}
