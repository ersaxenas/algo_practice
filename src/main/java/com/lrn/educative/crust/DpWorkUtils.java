package com.lrn.educative.crust;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DpWorkUtils {
    /*Fibonacci Numbers*/
    static class FibonacciGen {
        public Integer fibonacci1(int N) {
            if (N == 0 || N == 1) {
                return 1;
            }
            return fibonacci1(N - 1) + fibonacci1(N - 2);
        }

        public Double fibonacci2(double N, HashMap<Double, Double> cache) {
            if (N == 0 || N == 1) {
                return 1d;
            }
            if (cache.containsKey(N)) {
                return cache.get(N);
            }
            double res = fibonacci2(N - 1, cache) + fibonacci2(N - 2, cache);
            cache.put(N, res);
            return res;
        }

        public Double fibonacci3(double N) {
            if (N == 0 || N == 1) {
                return 1d;
            }
            double prv1 = 0, prv2 = 1, res = 0;
            for (int idx = 0; idx < N; idx++) {
                res = prv1 + prv2;
                prv1 = prv2;
                prv2 = res;
            }
            return prv2;
        }

        public static void main(String[] args) {
            FibonacciGen fibonacciGen = new FibonacciGen();
            System.out.println(fibonacciGen.fibonacci3(1000));
        }
    }

    /*Largest Sum Subarray*/
    static class LargestSumSubArray {
        public Integer findSum(Integer[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }
            int currentMax = Integer.MIN_VALUE, globalMax = Integer.MIN_VALUE;
            for (int idx = 0; idx < arr.length; idx++) {

                int currElem = arr[idx];
                if (currentMax < 0) {
                    currentMax = currElem;
                } else {
                    currentMax = currentMax + currElem;
                }

                if (globalMax < currentMax) {
                    globalMax = currentMax;
                }
            }
            return globalMax;
        }

        public Integer findSumRecursive2(Integer[] arr, int currIndex, int maxSum, int currSum, HashMap<Integer, Integer> cache) {
            //base case
            if (currIndex == arr.length) {
                return maxSum;
            }
            if (cache.containsKey(currIndex)) {
                return cache.get(currIndex);
            }
            // recursive
            int currElem = arr[currIndex];
            int newCurrSum = 0;
            if (currElem > 0) { // +ve
                if (currSum < 0) { // currsum is -ve
                    newCurrSum = currElem;
                } else { // currsum is +ve
                    newCurrSum = currSum + currElem;
                }
            } else { //-ve
                newCurrSum = currElem;
            }
            int max = findSumRecursive2(arr, currIndex + 1, Math.max(maxSum, newCurrSum), newCurrSum, cache);
            cache.put(currIndex, max);
            return max;
        }

        public Integer findSumDpWithMemoization(Integer[] arr) {
            HashMap<Integer, Integer> cache = new HashMap<>();
            return findSumRecursive2(arr, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, cache);
        }

        public static void main(String[] args) {
            LargestSumSubArray largestSumSubArray = new LargestSumSubArray();
            Integer[] v = new Integer[]{-4, 2, -5, 1, 2, 3, 6, -5, 1};
            System.out.println("Sum of largest subarray: " + largestSumSubArray.findSum(v));
            System.out.println("Sum of largest subarray dp: " + largestSumSubArray.findSumDpWithMemoization(v));
        }
    }

    /*MaxSum Subsequence - Nonadjacent Elements*/
    static class MaxSumSubSeqNonAdj {
        public Integer findSumDp(Integer[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }
            HashMap<Integer, Integer> cache = new HashMap<>();
            return findSumDpRecursive(arr, 0, cache);
        }

        public Integer findSumDpRecursive(Integer[] arr, int currIndex, Map<Integer, Integer> cache) {
            // base
            if (currIndex >= arr.length) {
                return 0;
            }
            if (cache.containsKey(currIndex)) {
                return cache.get(currIndex);
            }
            // recursive case
            int currElem = arr[currIndex];
            // include
            int sum1 = currElem + findSumDpRecursive(arr, currIndex + 2, cache);
            // exclude or skip
            int sum2 = findSumDpRecursive(arr, currIndex + 1, cache);

            int max = Math.max(sum1, sum2);
            cache.put(currElem, max);
            return max;
        }

        public Integer findSumDPBottomUp(Integer[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }
            Integer[] dp = new Integer[arr.length];
            Arrays.fill(dp, arr[0]);
            for (int idx = 1; idx < arr.length; idx++) {
                int currElem = arr[idx];
                // exclude curr element
                int sum1 = 0;
                if ((idx - 2) >= 0) {
                    sum1 = currElem + dp[idx - 2];
                }
                int sum2 = Math.max(currElem, dp[idx - 1]);
                dp[idx] = Math.max(sum1, sum2);
            }
            return dp[arr.length - 1];
        }

        public static void main(String[] args) {
            MaxSumSubSeqNonAdj maxSumSubSeqNonAdj = new MaxSumSubSeqNonAdj();
            Integer[] v = new Integer[]{1, -1, 6, -4, 2, 2};
            System.out.println("Max sum of nonadjacent subsequence dp memoization: " + maxSumSubSeqNonAdj.findSumDp(v));
            System.out.println("Max sum of nonadjacent subsequence : bottomup: " + maxSumSubSeqNonAdj.findSumDp(v));
        }
    }

    /*Find Combinations for Game Scoring*/
    static class ScoringGame {
        public Integer scoreWaysBruteForce(int N) {
            if (N < 0) {
                return 0;
            }
            if (N == 0) {
                return 1;
            }
            return scoreWaysBruteForce(N - 1) + scoreWaysBruteForce(N - 2) + scoreWaysBruteForce(N - 4);
        }

        public Integer scoreWaysMemoization(int N, Integer[] cache) {
            if (N < 0) {
                return 0;
            }
            if (N == 0) {
                return 1;
            }
            if (cache[N] != null) {
                return cache[N];
            }
            int score = scoreWaysBruteForce(N - 1) + scoreWaysBruteForce(N - 2) + scoreWaysBruteForce(N - 4);
            cache[N] = score;
            return score;
        }

        public void shiftElementsLeftByOne(Integer[] arr) {
            if (arr.length < 2) {
                return;
            }
            for (int idx = 1; idx < arr.length; idx++) {
                arr[idx - 1] = arr[idx];
            }
        }

        public Integer scoreWaysBottomUp(int N) {
            Integer[] arr = new Integer[4];
            Arrays.fill(arr, 0);
            arr[3] = 1;
            for (int idx = 0; idx < N; idx++) {
                int sum = arr[3] + arr[2] + arr[0];
                shiftElementsLeftByOne(arr);
                arr[3] = sum;
            }
            return arr[3];
        }

        public static void main(String[] args) {
            ScoringGame scoringGame = new ScoringGame();
            System.out.println("BruteForce: Number of ways score 5 can be reached = " + scoringGame.scoreWaysBruteForce(6));
            for (int i = 0; i < 10; i++) {
                System.out.println("Memoization: Number of ways score " + i + " can be reached = " + scoringGame.scoreWaysBruteForce(i));
                System.out.println("BottomUp: Number of ways score " + i + " can be reached = " + scoringGame.scoreWaysBottomUp(i));
            }
        }
    }

    /*Coin Changing Problem*/
    static class CoinChange {
        public Integer findWaysBruteForce(int sum) {
            Integer[] coinArr = new Integer[]{1, 2, 5};
            return findWaysMemoization(sum, 0, coinArr, new Integer[sum + 1][coinArr.length]);
        }

        public Integer findWaysBruteForce(int sum, int index, Integer[] coinArr) {
            //System.out.println(String.format("sum : %d, index: %d", sum, index));
            if (sum == 0) {
                return 1;
            }
            if (sum < 0) {
                return 0;
            }
            if (index == coinArr.length) {
                return 0;
            }
            // include
            int way1 = findWaysBruteForce(sum - coinArr[index], index, coinArr);
            // exclude
            int way2 = findWaysBruteForce(sum, index + 1, coinArr);
            return way1 + way2;
        }

        public Integer findWaysMemoization(int sum, int index, Integer[] coinArr, Integer[][] cache) {
            //System.out.println(String.format("sum : %d, index: %d", sum, index));
            if (sum == 0) {
                return 1;
            }
            if (sum < 0) {
                return 0;
            }
            if (index == coinArr.length) {
                return 0;
            }
            if (cache[sum][index] != null) {
                //System.out.println(String.format("cache hit sum: %d, index :%d", sum, index));
                return cache[sum][index];
            }
            // include
            int way1 = findWaysMemoization(sum - coinArr[index], index, coinArr, cache);
            // exclude
            int way2 = findWaysMemoization(sum, index + 1, coinArr, cache);
            int wayTotal = way1 + way2;
            cache[sum][index] = wayTotal;
            return wayTotal;
        }

        public Integer findWaysBottomUp(int sum, Integer[] coinArr) {
            Integer[] solution = new Integer[sum + 1];
            Arrays.fill(solution, 0);
            solution[0] = 1;
            for (int coinIndex = 0; coinIndex < coinArr.length; coinIndex++) {
                for (int arrIndex = coinArr[coinIndex]; arrIndex < sum + 1; arrIndex++) {
                    solution[arrIndex] = solution[arrIndex] + solution[arrIndex - coinArr[coinIndex]];
                }
            }

            return solution[solution.length - 1];
        }

        public static void main(String[] args) {
            CoinChange coinChange = new CoinChange();
            for (int idx = 1; idx <= 7; idx++) {
                System.out.println("Memoizatin: " + idx + ": " + coinChange.findWaysBruteForce(idx));
                System.out.println("BottomUp : " + idx + ": " + coinChange.findWaysBottomUp(idx, new Integer[]{1, 2, 5}));
            }
        }
    }

    /*Levenshtein Distance:
     * Given two strings, compute the Levenshtein distance between them, meaning the minimum number of edits required to convert one string to the other.
     * */
    static class LevenshteinDistance {
        public Integer findDistanceBruteForce(String str1, String str2) {
            int maxLen = Math.max(str1.length(), str2.length());
            return maxLen - findCommonStringBruteForce(str1, str2, 0, 0);
        }

        public Integer findCommonStringBruteForce(String str1, String str2, int idxStr1, int idxStr2) {
            // base case
            if (idxStr1 == str1.length() || idxStr2 == str2.length()) {
                return 0;
            }
            // recursive case
            if (str1.charAt(idxStr1) == str2.charAt(idxStr2)) { // found common char
                return 1 + findCommonStringBruteForce(str1, str2, idxStr1 + 1, idxStr2 + 1);
            }
            // common char not found at idxstr1 and idxstr2
            // skip char from str1
            int cnt1 = findCommonStringBruteForce(str1, str2, idxStr1 + 1, idxStr2);
            int cnt2 = findCommonStringBruteForce(str1, str2, idxStr1, idxStr2 + 1);
            int max = Math.max(cnt1, cnt2); // return maximum common chars found
            return max;
        }

        public Integer findDistanceMemoization(String str1, String str2) {
            int maxLen = Math.max(str1.length(), str2.length());
            return maxLen - findCommonStringMemoization(str1, str2, 0, 0, new Integer[str1.length()][str2.length()]);
        }

        public Integer findCommonStringMemoization(String str1, String str2, int idxStr1, int idxStr2, Integer[][] cache) {
            // base case
            if (idxStr1 == str1.length() || idxStr2 == str2.length()) {
                return 0;
            }
            if (cache[idxStr1][idxStr2] != null) {
                return cache[idxStr1][idxStr2];
            }
            // recursive case
            if (str1.charAt(idxStr1) == str2.charAt(idxStr2)) { // found common char
                return 1 + findCommonStringBruteForce(str1, str2, idxStr1 + 1, idxStr2 + 1);
            }
            // common char not found at idxstr1 and idxstr2
            // skip char from str1
            int cnt1 = findCommonStringBruteForce(str1, str2, idxStr1 + 1, idxStr2);
            int cnt2 = findCommonStringBruteForce(str1, str2, idxStr1, idxStr2 + 1);
            int max = Math.max(cnt1, cnt2); // return maximum common chars found
            cache[idxStr1][idxStr2] = max;
            return max;
        }

        public Integer findDistanceBottomUp(String str1, String str2) {
            int[][] cache = new int[str1.length() + 1][str2.length() + 1];
            for (int idx = 0; idx < str1.length(); idx++) {
                cache[idx][0] = 0;
            }
            for (int idx = 0; idx < str2.length(); idx++) {
                cache[0][idx] = 0;
            }
            for (int str1Idx = 1; str1Idx <= str1.length(); str1Idx++) {
                for (int str2Idx = 1; str2Idx <= str2.length(); str2Idx++) {
                     if(str1.charAt(str1Idx -1) == str2.charAt(str2Idx-1)) {
                         cache[str1Idx][str2Idx] = 1 + cache[str1Idx-1][str2Idx-1];
                     } else {
                         cache[str1Idx][str2Idx] = Math.max(cache[str1Idx-1][str2Idx], cache[str1Idx][str2Idx-1]);
                     }
                }
            }
            return Math.max(str2.length(), str2.length()) - cache[str1.length()][str2.length()];
        }

        public static void main(String[] args) {
            LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
            String str1 = "ant", str2 = "ant";
            System.out.println(String.format("BruteForce: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceBruteForce(str1, str2)));
            System.out.println(String.format("Memoization: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceMemoization(str1, str2)));
            System.out.println(String.format("Tabular: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceBottomUp(str1, str2)));
            str1 = "test";
            str2 = "text";
            System.out.println(String.format("BruteForce: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceBruteForce(str1, str2)));
            System.out.println(String.format("Memoization: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceMemoization(str1, str2)));
            System.out.println(String.format("Tabular: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceBottomUp(str1, str2)));
            str1 = "ant";
            str2 = "aunt";
            System.out.println(String.format("BruteForce: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceBruteForce(str1, str2)));
            System.out.println(String.format("Memoization: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceMemoization(str1, str2)));
            System.out.println(String.format("Tabular: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceBottomUp(str1, str2)));
            str1 = "min";
            str2 = "max";
            System.out.println(String.format("BruteForce: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceBruteForce(str1, str2)));
            System.out.println(String.format("Memoization: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceMemoization(str1, str2)));
            System.out.println(String.format("Tabular: Levenshtein distance between %s, %s is %d", str1, str2, levenshteinDistance.findDistanceBottomUp(str1, str2)));
        }
    }


}
