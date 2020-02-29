package com.lrn.educative.recur;

public class WorkUtilFund {
    static class Factorial {
        public int factorial(int num) {
            // base case
            if(num ==1) {
                return 1;
            }
            // recursive
            return num * factorial(num-1);
        }

        public static void main(String[] args) {
            System.out.println(new Factorial().factorial(5));
        }
    }
}
