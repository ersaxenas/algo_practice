package com.lrn.educative.crust;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MathWorkUtils {
    static class KthPermutation {
        public Integer factorial(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            return n * factorial(n - 1);
        }

        public void findKthPermutation(List<Integer> lst, int K, StringBuilder sbr) {
            if (lst.isEmpty()) {
                return;
            }
            int N = lst.size();
            int blockSize = factorial(N - 1);
            int selectedBlock = (K - 1) / blockSize;

            sbr.append(lst.get(selectedBlock));
            lst.remove(selectedBlock);
            // element cannot be in the blocks before selected block
            // so remove those elems.
            int newK = K - (selectedBlock * blockSize);
            findKthPermutation(lst, newK, sbr);
        }

        public static void main(String[] args) {
            KthPermutation kthPermutation = new KthPermutation();
            List<Integer> lst = new ArrayList<>();
            for (int idx = 1; idx <= 4; idx++) {
                lst.add(idx);
            }
            StringBuilder sbr = new StringBuilder();
            kthPermutation.findKthPermutation(lst, 8, sbr);
            System.out.println("8th permutation of 1234 = " + sbr);
        }
    }

    /*Integer Division Without Using * or / */
    static class IntegerDivision {
        public Integer divide(Integer dividend, Integer divisor) {
            if (divisor <= 0) {
                return -1;
            }
            if (divisor == 1) {
                return dividend;
            }
            if (dividend == divisor) {
                return 1;
            }
            if (dividend < divisor) {
                return 0;
            }
            int quotient = 1;
            int temp = divisor;

            while (temp < dividend) {
                quotient <<= 1;
                temp <<= 1;
            }
            if (temp > dividend) {
                temp >>= 1;
                quotient >>= 1;
                return quotient + divide(dividend - temp, divisor);
            }
            return quotient;
        }

        public static void main(String[] args) {
            IntegerDivision integerDivision = new IntegerDivision();
            System.out.println("7/2 = " + integerDivision.divide(7, 2));
            System.out.println("5/4 = " + integerDivision.divide(5, 4));
            System.out.println("1/3 = " + integerDivision.divide(1, 3));
            System.out.println("40/5 = " + integerDivision.divide(40, 5));
            System.out.println("40/4 = " + integerDivision.divide(40, 4));
        }
    }

    /* Pythagorean Triplets a*a + b*b = c*c */
    static class PythagoreanSum {
        public List<List<Integer>> findSum(Integer[] arr) {
            Arrays.sort(arr);
            List<List<Integer>> result = new ArrayList<>();
            if (arr == null || arr.length < 3) {
                return result;
            }
            for (int idx = 0; idx < arr.length; idx++) {
                int start = 0, end = arr.length - 1;
                int cSq = arr[idx] * arr[idx];
                while (start < end) {
                    if (idx == start || arr[start] == 0) {
                        start++;
                        continue;
                    }
                    if (idx == end || arr[end] == 0) {
                        end--;
                        continue;
                    }
                    int aSq = arr[start] * arr[start];
                    int bSq = arr[end] * arr[end];
                    int sum = aSq + bSq;
                    if (sum > cSq) {
                        end--;
                    } else if (sum < cSq) {
                        start++;
                    } else {
                        ArrayList<Integer> lst = new ArrayList<>();
                        lst.add(arr[start]);
                        lst.add(arr[end]);
                        lst.add(arr[idx]);
                        result.add(lst);
                        break;
                    }
                }
            }
            return result;
        }

        public static void main(String[] args) {
            PythagoreanSum pythagoreanSum = new PythagoreanSum();
            Integer[] l1 = {4, 16, 1, 2, 3, 5, 6, 8, 25, 10};
            System.out.println("Original: " + l1);
            List<List<Integer>> result = pythagoreanSum.findSum(l1);
            result.forEach(elem -> {
                System.out.println("Pythagorean triplets: " + elem);
            });
        }
    }

    /*All Possible Combinations for a Given Sum*/
    static class SumCombination {
        public List<List<Integer>> findSum(int targetSum) {
            List<List<Integer>> lst = new ArrayList<>();
            findSumBruteForce(1, 0, targetSum, new ArrayList<>(), lst);
            return lst;
        }

        public void findSumBruteForce(Integer elem, int sum, int targetSum, ArrayList<Integer> elemList, List<List<Integer>> result) {
            // base case
            if (targetSum == sum) {
                System.out.println(elemList);
                result.add(new ArrayList<>(elemList));
                return;
            }

            if (elem > targetSum || elemList.size() > targetSum) {
                return;
            }

            // recursive case
            // include
            elemList.add(elem);
            findSumBruteForce(elem, sum + elem, targetSum, elemList, result);
            elemList.remove(elemList.size() - 1);
            // exclude elem
            Integer nextElem = elem + 1;
            elemList.add(nextElem);
            findSumBruteForce(elem, sum + elem + 1, targetSum, elemList, result);
            elemList.remove(elemList.size() - 1);
        }

        public List<List<Integer>> findSum2(int targetSum) {
            List<List<Integer>> lst = new ArrayList<>();
            findSumBruteForce2(1, 0, targetSum, new ArrayList<>(), lst);
            return lst;
        }

        public void findSumBruteForce2(Integer elem, Integer sum, Integer targetSum, ArrayList<Integer> elemList, List<List<Integer>> result) {
            //base case
            if (sum.equals(targetSum)) {
                System.out.println(elemList);
                result.add(new ArrayList<>(elemList));
                return;
            }
            if (elemList.size() > targetSum || sum > targetSum) {
                return;
            }
            //recursive
            for (int idx = elem; idx <= targetSum; idx++) {
                int tempSum = sum + idx;
                if (tempSum <= targetSum) {
                    elemList.add(idx);
                    findSumBruteForce2(idx, tempSum, targetSum, elemList, result);
                    elemList.remove(elemList.size() - 1);
                } else {
                    return;
                }
            }
        }

        public static void main(String[] args) {
            SumCombination sumCombination = new SumCombination();
            sumCombination.findSum(4);
            sumCombination.findSum2(4);
        }
    }

    /*Find Missing Number*/
    static class MissingNumber {
        public Integer findMissingNumber(List<Integer> elemList) {
            int n = elemList.size() + 1;
            int seriesSum = (n * (n + 1)) / 2;
            int arrSum = 0;
            for (int elem : elemList) {
                arrSum += elem;
            }
            return seriesSum - arrSum;
        }

        public static void main(String[] args) {
            MissingNumber missingNum = new MissingNumber();
            List<Integer> list = Arrays.asList(3, 7, 1, 2, 8, 4, 5);
            System.out.println("Original = " + list);
            int missingNumber = missingNum.findMissingNumber(list);
            System.out.println("The missing number is " + missingNumber);
        }
    }

    /*Print All Permutations of a String*/
    static class StringPermutation {
        public List<String> findPermutation(String str) {
            List<String> result = new ArrayList<>();
            Queue<List<Character>> queue = new LinkedList<>();
            Character el = str.charAt(0);
            List<Character> characterList = new ArrayList<>();
            characterList.add(el);
            queue.add(characterList);

            for (int idx = 1; idx < str.length(); idx++) {
                Character ch = str.charAt(idx);
                int currQueueSize = queue.size();
                for (int qcnt = 0; qcnt < currQueueSize; qcnt++) {
                    List<Character> polledQueue = queue.poll();
                    int polledQueueSize = polledQueue.size();
                    for (int i = 0; i <= polledQueueSize; i++) {
                        ArrayList<Character> newLst = new ArrayList<>(polledQueue);
                        newLst.add(i, ch);
                        queue.add(new ArrayList<>(newLst));
                    }
                }
            }
            queue.forEach(lst -> {
                StringBuilder sbr = new StringBuilder();
                lst.forEach(sbr::append);
                result.add(sbr.toString());
            });
            return result;
        }

        public static void main(String[] args) {
            StringPermutation stringPermutation = new StringPermutation();
            String input = "bad";
            System.out.println("All permutations of " + input);
            List<String> result = stringPermutation.findPermutation(input);
            System.out.println(Arrays.toString(result.toArray()));
        }
    }

    /*Find All Subsets of a Set*/
    static class AllSubsets {
        public List<List<Integer>> findAllSubSets(Integer[] arr) {
            List<List<Integer>> lst = new LinkedList<>();
            lst.add(new ArrayList<>());

            for (Integer elem : arr) {
                int currLstSize = lst.size();
                for (int idx = 0; idx < currLstSize; idx++) {
                    ArrayList<Integer> newLst = new ArrayList<>(lst.get(idx));
                    newLst.add(elem);
                    lst.add(newLst);
                }
            }
            return lst;
        }

        public static void main(String[] args) {
            AllSubsets allSubsets = new AllSubsets();
            Integer[] myints = new Integer[]{2, 5, 7};
            List<List<Integer>> result = allSubsets.findAllSubSets(myints);
            for (int i = 0; i < result.size(); ++i) {
                System.out.print("{ ");
                for (Integer it : result.get(i)) {
                    System.out.print(it + " ");
                }
                System.out.println("}");
            }
        }
    }

    /*Is String a Valid Number?*/
    static class ValidNumber {
        enum STATE {START, INTEGER, DECIMAL, AFTER_DECIMAL, UNKNOWN}

        ;

        public STATE getNextState(STATE currState, char ch) {

            switch (currState) {
                case START:
                    // SAME AS INTEGER CASE
                case INTEGER:
                    if (ch >= '0' && ch <= '9') {
                        return STATE.INTEGER;
                    } else if (ch == '.') {
                        return STATE.DECIMAL;
                    } else {
                        return STATE.UNKNOWN;
                    }
                case AFTER_DECIMAL:
                case DECIMAL:
                    if (ch >= '0' && ch <= '9') {
                        return STATE.AFTER_DECIMAL;
                    } else {
                        return STATE.UNKNOWN;
                    }
            }
            return STATE.UNKNOWN;
        }

        public boolean isValidNumber(String str) {
            if (str == null || str.trim().length() == 0) {
                return false;
            }
            String numStr = str;
            if (numStr.startsWith("+") || numStr.startsWith("-")) {
                numStr = numStr.substring(1);
            }
            STATE currState = STATE.START;
            for (char ch : numStr.toCharArray()) {
                currState = getNextState(currState, ch);
                if (currState == STATE.UNKNOWN) {
                    return false;
                }
            }
            if (currState == STATE.DECIMAL) {
                return false;
            }
            return true;
        }

        public static void main(String[] args) {
            ValidNumber validNumber = new ValidNumber();
            System.out.println("Is the number valid 4.325? " + validNumber.isValidNumber("4.325"));
            System.out.println("Is the number valid 1.1.1? " + validNumber.isValidNumber("1.1.1"));
            System.out.println("Is the number valid 222? " + validNumber.isValidNumber("222"));
            System.out.println("Is the number valid 22.? " + validNumber.isValidNumber("22."));
            System.out.println("Is the number valid 0.1? " + validNumber.isValidNumber("0.1"));
            System.out.println("Is the number valid 22.22.? " + validNumber.isValidNumber("22.22."));
            System.out.println("Is the number valid 1.? " + validNumber.isValidNumber("1."));
        }
    }

    /*Calculate Power of a Number*/
    static class PowerFunction {
        public Double power(double x, int n) {
            if (x == 0 || n == 0) {
                return 1d;
            }
            if (n == 1) {
                return x;
            }
            boolean isNegativePower = false;
            if (n < 0) {
                isNegativePower = true;
                n = n * -1; // convert to +ve
            }
            double res = powerRecursive(x, n);
            if (isNegativePower) {
                return 1 / res;
            }
            return res;
        }

        public Double powerRecursive(double x, int n) {
            if (n == 0) {
                return 1d;
            }
            if (n == 1) {
                return x;
            }
            double res = powerRecursive(x, n / 2);
            if (n % 2 == 0) { // even
                return res * res;
            } else {
                return x * res * res;
            }
        }

        public static void main(String[] args) {
            PowerFunction powerFunction = new PowerFunction();
            System.out.println("Power(0, 0) = " + Double.toString(powerFunction.power(0, 0)));
            System.out.println("Power(2, 5) = " + Double.toString(powerFunction.power(2, 5)));
            System.out.println("Power(3, 4) = " + Double.toString(powerFunction.power(3, 4)));
            System.out.println("Power(1.5, 3) = " + Double.toString(powerFunction.power(1.5, 3)));
            System.out.println("Power(2, -2) = " + Double.toString(powerFunction.power(2, -2)));
        }
    }

    /*Calculate Square Root of a Number*/
    /*binary search approach*/
    static class SquareRootFunction {
        double EPSILON = 0.000000000000001;

        public Double squareRoot(double num) {
            if (num == 0) {
                return num;
            }
            double low = 0, high = 1 + num / 2;
            while (low < high) { // binary search
                double mid = low + ((high - low) / 2);
                double sqr = mid * mid;
                // we can't do a == b for doubles because
                // of rounding errors, so we use error threshold
                // EPSILON. Two doubles a and b are equal if
                //  abs(a-b) <= EPSILON
                double diff = Math.abs(num - sqr);
                if (diff <= EPSILON) {
                    return mid;
                } else if (sqr < num) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
            return -1d;
        }

        public static void main(String[] args) {
            SquareRootFunction squareRootFunction = new SquareRootFunction();
            double[] arr = {16, 17, 2.25};
            for (double i : arr) {
                System.out.println("Square root of " + i + " is " + squareRootFunction.squareRoot(i));
            }
        }
    }

}
