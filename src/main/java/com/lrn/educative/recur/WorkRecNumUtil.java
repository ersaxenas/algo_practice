package com.lrn.educative.recur;

public class WorkRecNumUtil {
    static class PowerFun {
        public int power(int num, int pow) {
            // base case
            if(pow == 0) {
                return 1;
            }
            // recursive
            return num * power(num, pow -1);
        }

        public static void main(String[] args) {
            System.out.println(new PowerFun().power(4,3));
        }
    }
    static class SumTillNFun {
        public int sumAll(int num) {
            // base case
            if(num == 1) {
                return 1;
            }
            // recursive
            return num + sumAll(num -1);
        }

        public static void main(String[] args) {
            System.out.println(new SumTillNFun().sumAll(5));
        }
    }
    static class ModFun {
        public int mod(int dividend, int divisor) {
            // guard condition
            if(divisor == 0) {
                return -1;
            }
            // base case
            if(dividend < divisor) {
                return dividend;
            }
            // recursive
            return mod(dividend-divisor, divisor);
        }

        public static void main(String[] args) {
            System.out.println(new ModFun().mod(27,0));
            System.out.println(new ModFun().mod(14,4));
        }
    }
    static class FibonacciFun {
        public int fibonacci(int n) {
            // base case
            if(n <= 1) {
                return n;
            }
            // recursive
            return fibonacci(n-1) + fibonacci(n-2);
        }

        public static void main(String[] args) {
            System.out.println(new FibonacciFun().fibonacci(6));
        }
    }
    static class GreatestCommonDivisorFun {
        public int gcdRecur(int num1, int num2) {
            // base case
            if(num1 == num2) {
                return num1;
            }
            // recursive
            if(num1 > num2) {
                 return gcdRecur(num1-num2, num2);
            }
            return gcdRecur(num1, num2-num1);
        }

        public static void main(String[] args) {
            System.out.println(new GreatestCommonDivisorFun().gcdRecur(56,42));
        }
    }
    static class PrimeNumCheckFun {
        public boolean checkPrime(int num, int i) {
            // base case
            if(num < 2) {
                return false; // since 0 and 1 are not prime number
            }
            if(i == 1) {
                return true;
            }
            if(num % i == 0) {
                return false; // number can be divided from other number
            }
            // recursive
            return checkPrime(num, i-1);
        }

        public static void main(String[] args) {
            int nn = 24;
            System.out.println(new PrimeNumCheckFun().checkPrime(nn,nn/2));
        }
    }
    static class GenerateBinaryFun {
        public int toBinary(int num) {
            // base case
            if(num == 0) {
                return num;
            }
            // recursive
            return num % 2 + 10 *  toBinary(num/2);
        }

        public static void main(String[] args) {
            int nn = 24;
            System.out.println(new GenerateBinaryFun().toBinary(20));
        }
    }
}
