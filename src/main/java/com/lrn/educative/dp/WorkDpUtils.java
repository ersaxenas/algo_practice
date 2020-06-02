package com.lrn.educative.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WorkDpUtils {
    public static void printArray(final Integer[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " | ");
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

    /*
    * Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack which has a capacity ‘C’.
    * The goal is to get the maximum profit from the items in the knapsack.
    * Each item can only be selected once, as we don’t have multiple quantities of any item.
    * Items: { Apple, Orange, Banana, Melon }
Weights: { 2, 3, 1, 4 }
Profits: { 4, 5, 3, 7 }
Knapsack capacity: 5
    * */
    static class MaximumProfit {
        public int maxProfitBrt(int[] weights, int[] profits, int capacity, int currIndex) {
            // base
            if (capacity == 0 || currIndex == profits.length) {
                return 0;
            }
            // recursion
            int profit1 = 0;
            int currWt = weights[currIndex];
            int currPr = profits[currIndex];
            // include
            if (currWt <= capacity) {
                profit1 = currPr + maxProfitBrt(weights, profits, (capacity - currWt), currIndex + 1);
            }
            //exclude
            int profit2 = maxProfitBrt(weights, profits, capacity, currIndex + 1);
            return Math.max(profit1, profit2);
        }

        // top down with memoization
        public int maxProfitWithMemoization(int[] weights, int[] profits, int capacity, int currIndex, Integer[][] dp) {
            // base
            if (capacity == 0 || currIndex == profits.length) {
                return 0;
            }
            if (dp[capacity][currIndex] != null) {
                return dp[capacity][currIndex];
            }
            // recursion
            int profit1 = 0;
            int currWt = weights[currIndex];
            int currPr = profits[currIndex];
            // include
            if (currWt <= capacity) {
                profit1 = currPr + maxProfitBrt(weights, profits, (capacity - currWt), currIndex + 1);
            }
            //exclude
            int profit2 = maxProfitBrt(weights, profits, capacity, currIndex + 1);
            final int maxProfit = Math.max(profit1, profit2);
            dp[capacity][currIndex] = maxProfit;
            return maxProfit;
        }

        public int maximumProfitTopDown(int[] weights, int[] profits, int capacity) {
            Integer[][] dp = new Integer[capacity + 1][profits.length];
            return maxProfitWithMemoization(weights, profits, capacity, 0, dp);
        }

        // bottom up tabular approach
        public int maximumProfitBottomUp(int[] weights, int[] profits, int capacity) {
            Integer[][] dp = new Integer[capacity + 1][profits.length];
            // first col where capacity is zero
            for (int idx = 0; idx < profits.length; idx++) {
                dp[0][idx] = 0;
            }
            // for first row - only one item in consideration
            for (int idx = 1; idx < capacity + 1; idx++) {
                if (weights[0] < capacity) {
                    dp[idx][0] = weights[0];
                } else {
                    dp[idx][0] = 0;
                }
            }
            // now rest of the table
            for (int idx1 = 1; idx1 < capacity + 1; idx1++) { //capacity 1 -> N
                for (int idx2 = 1; idx2 < profits.length; idx2++) { // weight array index 1 -> N
                    // exclude
                    int profit1 = dp[idx1][idx2 - 1];
                    // include
                    int profit2 = 0;
                    int currWt = weights[idx2];
                    int currPr = profits[idx2];
                    if (currWt <= idx1) {
                        profit2 = currPr + dp[idx1 - currWt][idx2 - 1];
                    }
                    dp[idx1][idx2] = Math.max(
                            profit1, /*exclude*/
                            profit2 /*include*/
                    );
                }
            }
            printSelectedElements(weights, profits, capacity, dp);
            return dp[capacity][profits.length - 1];
        }

        public void printSelectedElements(int[] weights, int[] profits, int capacity, Integer[][] dp) {
            int totalProfit = dp[capacity][profits.length - 1];
            int cap = capacity;
            for (int idx = profits.length - 1; idx > 0; idx--) {
                if (totalProfit != dp[cap][idx - 1]) {
                    System.out.println("selected wt : " + weights[idx]);
                    totalProfit = totalProfit - profits[idx];
                    cap = cap - weights[idx];
                }
            }
            if (totalProfit != 0) {
                System.out.println("Selected wt: " + weights[0]);
            }
        }


        public static void main(String[] args) {
            int[] profits = {1, 6, 10, 16};
            int[] weights = {1, 2, 3, 5};
            MaximumProfit maximumProfit = new MaximumProfit();
            int maxProfit = maximumProfit.maxProfitBrt(weights, profits, 7, 0);
            System.out.println("Total knapsack profit (brute force) ---> " + maxProfit);
            maxProfit = maximumProfit.maxProfitBrt(weights, profits, 6, 0);
            System.out.println("Total knapsack profit (brute force) ---> " + maxProfit);

            maxProfit = maximumProfit.maximumProfitTopDown(weights, profits, 7);
            System.out.println("Total knapsack profit (memoization) ---> " + maxProfit);
            maxProfit = maximumProfit.maximumProfitTopDown(weights, profits, 6);
            System.out.println("Total knapsack profit (memoization) ---> " + maxProfit);

            maxProfit = maximumProfit.maximumProfitBottomUp(weights, profits, 7);
            System.out.println("Total knapsack profit (tabular) ---> " + maxProfit);
            maxProfit = maximumProfit.maximumProfitBottomUp(weights, profits, 6);
            System.out.println("Total knapsack profit (tabular) ---> " + maxProfit);
        }
    }

    /*Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both the subsets is equal.*/
    static class EqualSumSubset {
        public boolean findSubsetBrt(int[] arr) {
            int k = 0;
            for (int elem : arr) {
                k = k + elem;
            }
            if (k % 2 != 0) {
                return false; // sum is odd so cant have two equal subset ex. no to equal sum subset produce odd sum
            }
            return findSubsetBrt(arr, k / 2, 0);
        }

        public boolean findSubsetBrt(int[] arr, int K, int index) {
            // base
            if (K == 0) {
                return true;
            }
            if (index == arr.length) {
                return false;
            }
            //recursion
            int currElem = arr[index];
            // include
            if (currElem <= K) {
                boolean result = findSubsetBrt(arr, K - currElem, index + 1);
                if (result) {
                    return true;
                }
            }
            // exclude
            return findSubsetBrt(arr, K, index + 1);
        }

        // top to down approach
        public boolean findSubsetToDown(int[] arr) {
            int K = 0;
            for (int elem : arr) {
                K = K + elem;
            }
            if (K % 2 != 0) {
                return false;
            }
            K = K / 2;
            Boolean[][] dp = new Boolean[K + 1][arr.length];
            return findSubsetMemoization(arr, K, 0, dp);
        }

        public boolean findSubsetMemoization(int[] arr, int K, int index, Boolean[][] dp) {
            // base case
            if (K == 0) {
                return true;
            }
            if (index == arr.length) {
                return false;
            }
            if (dp[K][index] != null) {
                return dp[K][index];
            }
            // recursive
            int currElem = arr[index];
            // include
            if (currElem <= K) {
                boolean result = findSubsetMemoization(arr, K - currElem, index + 1, dp);
                if (result) {
                    dp[K][index] = true;
                    return true;
                }
            }
            final boolean result = findSubsetMemoization(arr, K, index + 1, dp);
            dp[K][index] = result;
            return result;
        }

        // bottom to up
        public boolean findSubsetTabular(int[] arr) {
            int K = 0;
            for (int elem : arr) {
                K = K + elem;
            }
            if (K % 2 != 0) {
                return false;
            }
            K = K / 2;
            Boolean[][] dp = new Boolean[arr.length][K + 1];
            for (int idx = 0; idx < arr.length; idx++) {
                dp[idx][0] = true;
            }
            for (int idx = 1; idx < K + 1; idx++) {
                if (arr[0] == idx) {
                    dp[0][idx] = true;
                } else {
                    dp[0][idx] = false;
                }
            }
            // for tabular
            for (int idx = 1; idx < arr.length; idx++) {
                for (int sum = 1; sum < K + 1; sum++) {

                    // exclude
                    boolean result = dp[idx - 1][sum];
                    if (result) {
                        dp[idx][sum] = true;
                    } else if (arr[idx] <= sum) {
                        //include
                        dp[idx][sum] = dp[idx - 1][sum - arr[idx]];
                    } else {
                        dp[idx][sum] = false;
                    }
                }
            }
            return dp[arr.length - 1][K];
        }


        public static void main(String[] args) {
            EqualSumSubset equalSumSubset = new EqualSumSubset();
            int[] num = {1, 2, 3, 4};
            System.out.println("Brt: " + equalSumSubset.findSubsetBrt(num));
            System.out.println("Memoization: " + equalSumSubset.findSubsetToDown(num));
            System.out.println("Tabular: " + equalSumSubset.findSubsetTabular(num));
            System.out.println();
            num = new int[]{1, 1, 3, 4, 7};
            System.out.println("Brt: " + equalSumSubset.findSubsetBrt(num));
            System.out.println("Memoization: " + equalSumSubset.findSubsetToDown(num));
            System.out.println("Tabular: " + equalSumSubset.findSubsetTabular(num));
            System.out.println();
            num = new int[]{2, 3, 4, 6};
            System.out.println("Brt: " + equalSumSubset.findSubsetBrt(num));
            System.out.println("Memoization: " + equalSumSubset.findSubsetToDown(num));
            System.out.println("Tabular: " + equalSumSubset.findSubsetTabular(num));
            System.out.println();
            num = new int[]{1, 1, 1, 9};
            System.out.println("Brt: " + equalSumSubset.findSubsetBrt(num));
            System.out.println("Memoization: " + equalSumSubset.findSubsetToDown(num));
            System.out.println("Tabular: " + equalSumSubset.findSubsetTabular(num));
        }
    }

    /*Given a set of positive numbers, determine if there exists a subset whose sum is equal to a given number ‘S’.*/
    static class SubsetOfSum {

        public Boolean findSubsetBrute(int[] arr, int sum) {
            return findSubsetBrute(arr, sum, 0);
        }

        public Boolean findSubsetBrute(int[] arr, int cSum, int index) {
            // base case
            if (cSum == 0) {
                return true;
            }
            if (index == arr.length) {
                return false;
            }

            // recursion
            int currElem = arr[index];
            // include
            if (currElem <= cSum) {
                boolean result = findSubsetBrute(arr, cSum - currElem, index + 1);
                if (result) {
                    return true;
                }
            }
            // exclude
            return findSubsetBrute(arr, cSum, index + 1);
        }

        // bottom to top
        public boolean findSubsetTabular(int[] arr, int sum) {
            Boolean[][] dp = new Boolean[arr.length][sum + 1];
            // for sum = 0
            for (int idx = 0; idx < arr.length; idx++) {
                dp[idx][0] = true;
            }
            // for first element in the array
            for (int s = 1; s <= sum; s++) {
                if (arr[0] == s) {
                    dp[0][s] = true;
                } else {
                    dp[0][s] = false;
                }
            }
            // for rest of the elements in the array and for each sum
            for (int idx = 1; idx < arr.length; idx++) {
                for (int s = 1; s <= sum; s++) {
                    // exclude
                    boolean result = dp[idx - 1][s];
                    if (result) {
                        dp[idx][s] = true;
                    } else if (arr[idx] <= s) { // include
                        dp[idx][s] = dp[idx - 1][s - arr[idx]];
                    } else {
                        dp[idx][s] = false;
                    }
                }
            }
            return dp[arr.length - 1][sum];
        }

        public static void main(String[] args) {
            SubsetOfSum subsetOfSum = new SubsetOfSum();
            int[] num = {1, 2, 3, 7};
            System.out.println("BruteForce: " + subsetOfSum.findSubsetBrute(num, 6));
            System.out.println("Bottom to Top: " + subsetOfSum.findSubsetTabular(num, 6));
            num = new int[]{1, 2, 7, 1, 5};
            System.out.println("BurteForce: " + subsetOfSum.findSubsetBrute(num, 10));
            System.out.println("Bottom to top: " + subsetOfSum.findSubsetTabular(num, 10));
            num = new int[]{1, 3, 4, 8};
            System.out.println("BurteForce: " + subsetOfSum.findSubsetBrute(num, 6));
            System.out.println("Bottom to top: " + subsetOfSum.findSubsetTabular(num, 6));
        }
    }

    /*Given a set of positive numbers, partition the set into two subsets with a minimum difference between their subset sums.*/
    static class MinDiffSubset {
        public int findMinDiffBrf(int[] arr) {
            return findMinDiffBrf(arr, 0, 0, 0);
        }

        public int findMinDiffBrf(int[] arr, int currentIndex, int sum1, int sum2) {
            // base case
            if (currentIndex == arr.length) {
                return Math.abs(sum1 - sum2);
            }
            // recursive
            int currElem = arr[currentIndex];
            int diff1 = findMinDiffBrf(arr, currentIndex + 1, sum1 + currElem, sum2);
            int diff2 = findMinDiffBrf(arr, currentIndex + 1, sum1, sum2 + currElem);
            return Math.min(diff1, diff2);
        }

        // top to bottom
        public int findMinDiffMemoization(int[] arr) {
            int sum = 0;
            for (int elem : arr) {
                sum = sum + elem;
            }
            Integer[][] dp = new Integer[arr.length][sum + 1];
            return findMinDiffMemoization(arr, 0, 0, 0, dp);
        }

        public int findMinDiffMemoization(int[] arr, int currentIndex, int sum1, int sum2, Integer[][] dp) {
            // base case
            if (currentIndex == arr.length) {
                return Math.abs(sum1 - sum2);
            }
            if (dp[currentIndex][sum1] != null) {
                return dp[currentIndex][sum1];
            }
            // recursive
            int currElem = arr[currentIndex];
            int diff1 = findMinDiffBrf(arr, currentIndex + 1, sum1 + currElem, sum2);
            int diff2 = findMinDiffBrf(arr, currentIndex + 1, sum1, sum2 + currElem);
            final int minDiff = Math.min(diff1, diff2);
            dp[currentIndex][sum1] = minDiff;
            return minDiff;
        }

        // bottom to up
        public int findMinDiffTabular(int[] arr) {
            int sum = 0;
            for (int elem : arr) {
                sum = sum + elem;
            }
            Boolean[][] dp = new Boolean[arr.length][(sum / 2) + 1];
            // for sum 0
            for (int idx = 0; idx < arr.length; idx++) {
                dp[idx][0] = true;
            }
            // for element at 0 index
            for (int s = 1; s <= sum / 2; s++) {
                if (arr[0] == s) {
                    dp[0][s] = true;
                } else {
                    dp[0][s] = false;
                }
            }
            for (int s = 1; s <= sum / 2; s++) {
                for (int idx = 1; idx < arr.length; idx++) {
                    int currElem = arr[idx];
                    if (dp[idx - 1][s]) {// exclude
                        dp[idx][s] = dp[idx - 1][s];
                    } else if (currElem <= s) {// include
                        dp[idx][s] = dp[idx - 1][s - currElem];
                    } else {
                        dp[idx][s] = false;
                    }
                }
            }

            // take last row start from end and find true
            int sum1 = 0;
            for (int s = sum / 2; s >= 0; s--) {
                if (dp[arr.length - 1][s]) {
                    sum1 = s;
                    break;
                }
            }
            int sum2 = sum - sum1;
            return Math.abs(sum1 - sum2);
        }


        public static void main(String[] args) {
            MinDiffSubset minDiffSubset = new MinDiffSubset();
            int[] num = {1, 2, 3, 9};
            System.out.println("Brute Force: " + minDiffSubset.findMinDiffBrf(num));
            System.out.println("Memoization: " + minDiffSubset.findMinDiffMemoization(num));
            System.out.println("Tabular: " + minDiffSubset.findMinDiffTabular(num));
            num = new int[]{1, 2, 7, 1, 5};
            System.out.println("Brute Force: " + minDiffSubset.findMinDiffBrf(num));
            System.out.println("Memoization: " + minDiffSubset.findMinDiffMemoization(num));
            System.out.println("Tabular: " + minDiffSubset.findMinDiffTabular(num));
            num = new int[]{1, 3, 100, 4};
            System.out.println("Brute Force: " + minDiffSubset.findMinDiffBrf(num));
            System.out.println("Memoization: " + minDiffSubset.findMinDiffMemoization(num));
            System.out.println("Tabular: " + minDiffSubset.findMinDiffTabular(num));
        }
    }

    /*Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.*/
    static class SubsetOfSumCount {
        public int countSubsetsBrf(int[] arr, int sum) {
            return countSubsetsBrf(arr, sum, 0);
        }

        public int countSubsetsBrf(int[] arr, int sum, int currentIndex) {
            // base case
            if (sum == 0) {
                return 1;
            }
            if (arr.length == 0 || currentIndex == arr.length) {
                return 0;
            }

            // recursion
            // include
            int currElem = arr[currentIndex];
            int count1 = countSubsetsBrf(arr, sum - currElem, currentIndex + 1);
            // exclude
            int count2 = countSubsetsBrf(arr, sum, currentIndex + 1);
            return count1 + count2;
        }

        // top to bottom
        public int countSubsetsMemoization(int[] arr, int sum) {
            Integer[][] dp = new Integer[arr.length][sum + 1];
            return countSubsetsMemoization(arr, sum, 0, dp);
        }

        public int countSubsetsMemoization(int[] arr, int sum, int currentIndex, Integer[][] dp) {
            // base case
            if (sum == 0) {
                return 1;
            }
            if (arr.length == 0 || currentIndex == arr.length) {
                return 0;
            }
            if (dp[currentIndex][sum] != null) {
                return dp[currentIndex][sum];
            }

            // recursion
            // include
            int currElem = arr[currentIndex];
            int count1 = 0;
            if (currElem <= sum) {
                count1 = countSubsetsMemoization(arr, sum - currElem, currentIndex + 1, dp);
            }
            // exclude
            int count2 = countSubsetsMemoization(arr, sum, currentIndex + 1, dp);
            final int subsetCount = count1 + count2;
            dp[currentIndex][sum] = subsetCount;
            return subsetCount;
        }

        // bottom to up
        public int findSubsetTabular(int[] arr, int sum) {
            Integer[][] dp = new Integer[arr.length][sum + 1];
            // for sum 0
            for (int idx = 0; idx < arr.length; idx++) {
                dp[idx][0] = 1;
            }
            // for arr[0] element - single element
            for (int s = 1; s <= sum; s++) {
                if (arr[0] == s) {
                    dp[0][s] = 1;
                } else {
                    dp[0][s] = 0;
                }
            }
            for (int idx = 1; idx < arr.length; idx++) {
                for (int s = 1; s <= sum; s++) {
                    int currElem = arr[idx];
                    // exclude
                    int count1 = 0, count2 = 0;
                    if (dp[idx - 1][s] != null) {
                        count1 = dp[idx - 1][s];
                    }
                    //include
                    if (currElem <= s) {
                        count2 = dp[idx - 1][s - currElem];
                    }
                    dp[idx][s] = count1 + count2;

                }
            }
            //printArray(dp);
            return dp[arr.length - 1][sum];
        }

        public static void main(String[] args) {
            SubsetOfSumCount subsetOfSumCount = new SubsetOfSumCount();
            int[] num = {1, 1, 2, 3};
            System.out.println("Bruteforce: " + subsetOfSumCount.countSubsetsBrf(num, 4));
            System.out.println("Mamoization: " + subsetOfSumCount.countSubsetsMemoization(num, 4));
            System.out.println("Tabular: " + subsetOfSumCount.findSubsetTabular(num, 4));
            num = new int[]{1, 2, 7, 1, 5};
            System.out.println("Bruteforce: " + subsetOfSumCount.countSubsetsBrf(num, 9));
            System.out.println("Memoization: " + subsetOfSumCount.countSubsetsMemoization(num, 9));
            System.out.println("Tabular: " + subsetOfSumCount.findSubsetTabular(num, 9));
        }
    }

    /* unbounded knapsack */
    /*Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset of these items which will give us maximum profit such that their cumulative weight is not more than a given number ‘C’. We can assume an infinite supply of item quantities; therefore, each item can be selected multiple times.*/
    static class MaximumProfitUnbounded {
        public Integer maxProfitBruteForce(int[] weights, int[] profits, int capacity) {
            if (capacity <= 0 || weights.length != profits.length) {
                return 0;
            }
            return maxProfitBruteForce(weights, profits, capacity, 0);
        }

        public Integer maxProfitBruteForce(int[] weight, int[] profits, int capacity, int currentIndex) {
            // base case
            if (capacity <= 0 || currentIndex == profits.length) {
                return 0;
            }
            // recursive
            int currentElementWt = weight[currentIndex];
            int currentElementProfit = profits[currentIndex];
            // include
            int profit1 = 0;
            if (currentElementWt <= capacity) {
                profit1 = currentElementProfit + maxProfitBruteForce(weight, profits, capacity - currentElementWt, currentIndex);
            }
            // exclude
            int profit2 = maxProfitBruteForce(weight, profits, capacity, currentIndex + 1);
            int maxProfit = Math.max(profit1, profit2);
            return maxProfit;
        }

        // TOP to down
        public Integer maxProfitMemoization(int[] weights, int[] profits, int capacity) {
            if (capacity <= 0 || weights.length != profits.length) {
                return 0;
            }
            Integer[][] dp = new Integer[profits.length][capacity + 1];
            return maxProfitMemoization(weights, profits, capacity, 0, dp);
        }

        public Integer maxProfitMemoization(int[] weight, int[] profits, int capacity, int currentIndex, Integer[][] dp) {
            // base case
            if (capacity <= 0 || currentIndex == profits.length) {
                return 0;
            }
            if (dp[currentIndex][capacity] != null) {
                return dp[currentIndex][capacity];
            }
            // recursive
            int currentElementWt = weight[currentIndex];
            int currentElementProfit = profits[currentIndex];
            // include
            int profit1 = 0;
            if (currentElementWt <= capacity) {
                profit1 = currentElementProfit + maxProfitBruteForce(weight, profits, capacity - currentElementWt, currentIndex);
            }
            // exclude
            int profit2 = maxProfitBruteForce(weight, profits, capacity, currentIndex + 1);
            int maxProfit = Math.max(profit1, profit2);
            dp[currentIndex][capacity] = maxProfit;
            return maxProfit;
        }

        // bottom to up
        public Integer maxProfitTabular(int[] weights, int[] profits, int capacity) {
            Integer[][] dp = new Integer[profits.length][capacity + 1];
            // for capacity == 0
            for (int idx = 0; idx < profits.length; idx++) {
                dp[idx][0] = 0;
            }
            // for all weights and capacity
            for (int idx = 0; idx < profits.length; idx++) {
                for (int cap = 1; cap < capacity + 1; cap++) {
                    int currentWt = weights[idx];
                    int currentWtProfit = profits[idx];
                    // exclude
                    int profit1 = 0;
                    if (idx > 0 && dp[idx - 1][cap] != null) {
                        profit1 = dp[idx - 1][cap];
                    }
                    int profit2 = 0;
                    // include
                    if (currentWt <= cap) {
                        profit2 = currentWtProfit + dp[idx][cap - currentWt];
                    }
                    dp[idx][cap] = Math.max(profit1, profit2);
                }
            }
            printArray(dp);
            printSelectedItems(weights, profits, capacity, dp);
            return dp[profits.length - 1][capacity];
        }

        public void printSelectedItems(int[] weights, int[] profits, int capacity, Integer[][] dp) {
            int totalProfit = dp[profits.length - 1][capacity];
            int cap = capacity;
            for (int idx = profits.length; idx > 0; idx--) {
                while (totalProfit != dp[idx - 1][cap]) {
                    System.out.println("Item at index " + idx + " is included.");
                    totalProfit = totalProfit - profits[idx];
                    cap = cap - weights[idx];
                }
            }
            while (totalProfit != 0) {
                totalProfit = totalProfit - profits[0];
                System.out.println("Item at index " + 0 + " is included.");
            }
        }

        public static void main(String[] args) {
            MaximumProfitUnbounded maximumProfitUnbounded = new MaximumProfitUnbounded();
            int[] profits = {15, 50, 60, 90};
            int[] weights = {1, 3, 4, 5};
            int capacity = 8;
            int maxProfit = maximumProfitUnbounded.maxProfitBruteForce(weights, profits, capacity);
            System.out.println("BruteForce: " + maxProfit);
            maxProfit = maximumProfitUnbounded.maxProfitMemoization(weights, profits, capacity);
            System.out.println("Memoization: " + maxProfit);
            maxProfit = maximumProfitUnbounded.maxProfitTabular(weights, profits, capacity);
            System.out.println("Tabular: " + maxProfit);

            int[] profits2 = {15, 20, 50};
            int[] weights2 = {1, 2, 3};
            capacity = 10;
            maxProfit = maximumProfitUnbounded.maxProfitBruteForce(weights2, profits2, capacity);
            System.out.println("BruteForce: " + maxProfit);
            maxProfit = maximumProfitUnbounded.maxProfitMemoization(weights2, profits2, capacity);
            System.out.println("Memoization: " + maxProfit);
            maxProfit = maximumProfitUnbounded.maxProfitTabular(weights2, profits2, capacity);
            System.out.println("Tabular: " + maxProfit);
        }
    }

    /*Given a rod of length ‘n’, we are asked to cut the rod and sell the pieces in a way that will maximize the profit. We are also given the price of every piece of length ‘i’ where ‘1 <= i <= n’.*/
    static class RodCutter {
        public Integer maxPriceBruteForce(int[] lengths, int[] prices, int rodLen) {
            return maxPriceBruteForce(lengths, prices, rodLen, 0);
        }

        public Integer maxPriceBruteForce(int[] lengths, int[] prices, int rodLen, int currentIndex) {
            // base case
            if (currentIndex == lengths.length || rodLen <= 0) {
                return 0;
            }
            if (lengths.length != prices.length) {
                return 0;
            }
            // recursive
            int currentElem = lengths[currentIndex];
            int currentElemPrice = prices[currentIndex];
            // include
            int price1 = 0;
            if (currentElem <= rodLen) {
                price1 = currentElemPrice + maxPriceBruteForce(lengths, prices, rodLen - currentElem, currentIndex);
            }
            // exclude
            int price2 = maxPriceBruteForce(lengths, prices, rodLen, currentIndex + 1);
            int maxPrice = Math.max(price1, price2);
            return maxPrice;
        }

        public Integer maxPriceTabular(int[] lengths, int[] prices, int rodLen) {

            Integer[][] dp = new Integer[prices.length][rodLen + 1];
            // for length 0
            for (int idx = 0; idx < prices.length; idx++) {
                dp[idx][0] = 0;
            }

            for (int idx = 0; idx < prices.length; idx++) {
                for (int rlen = 1; rlen < rodLen + 1; rlen++) {
                    int currLen = lengths[idx];
                    int currPrice = prices[idx];
                    // exclude
                    int price1 = 0;
                    if (idx > 0 && dp[idx - 1][rlen] != null) {
                        price1 = dp[idx - 1][rlen];
                    }
                    // include
                    int price2 = 0;
                    if (currLen <= rlen) {
                        price2 = currPrice + dp[idx][rlen - currLen];
                    }
                    dp[idx][rlen] = Math.max(price1, price2);
                }
            }
            int maxPrice = dp[prices.length - 1][rodLen];
            printSelectedElement(lengths, prices, rodLen, dp);
            return maxPrice;
        }

        public void printSelectedElement(int[] lengths, int[] prices, int rodLen, Integer[][] dp) {
            int totalPrice = dp[prices.length - 1][rodLen];
            int rdLen = rodLen;
            for (int idx = prices.length - 1; idx > 0; idx--) {
                while (totalPrice != dp[idx - 1][rdLen]) {
                    int currElemLen = lengths[idx];
                    int price = prices[idx];
                    System.out.println("Selected len : " + currElemLen + ", price : " + price);
                    totalPrice = totalPrice - price;
                    rdLen = rdLen - currElemLen;
                }
            }
            if (totalPrice != 0) {
                System.out.println("Selected len : " + lengths[0] + ", price : " + prices[0]);
                totalPrice = totalPrice - prices[0];
            }
        }

        public static void main(String[] args) {
            RodCutter rc = new RodCutter();
            int[] lengths = {1, 2, 3, 4, 5};
            int[] prices = {2, 6, 7, 10, 13};
            int maxProfit = rc.maxPriceBruteForce(lengths, prices, 5);
            System.out.println("BruteForce : " + maxProfit);
            maxProfit = rc.maxPriceTabular(lengths, prices, 5);
            System.out.println("Tabular : " + maxProfit);
        }
    }

    /*Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the total number of distinct ways to make up that amount.*/
    static class MakeUpAmount {
        public Integer findCountBruteForce(int[] denominations, int Sum) {
            return findCountBruteForce(denominations, Sum, 0);
        }

        public Integer findCountBruteForce(int[] denominations, int sum, int currentIndex) {
            //base case
            if (sum == 0) {
                return 1;
            }
            if (currentIndex == denominations.length) {
                return 0;
            }
            // recursive
            int currentDenom = denominations[currentIndex];
            // include
            int count1 = 0;
            if (currentDenom <= sum) {
                count1 = findCountBruteForce(denominations, sum - currentDenom, currentIndex);
            }
            // exclude
            int count2 = findCountBruteForce(denominations, sum, currentIndex + 1);
            int totalCount = count1 + count2;
            return totalCount;
        }

        public Integer findCountTabular(int[] denominations, int Sum) {
            Integer[][] dp = new Integer[denominations.length][Sum + 1];
            // for sum == 0
            for (int idx = 0; idx < denominations.length; idx++) {
                dp[idx][0] = 1;
            }
            for (int idx = 0; idx < denominations.length; idx++) {
                for (int s = 1; s <= Sum; s++) {
                    int currentDeno = denominations[idx];
                    //exclude
                    int count1 = 0;
                    if (idx > 0 && dp[idx - 1][s] != null) {
                        count1 = dp[idx - 1][s];
                    }
                    // include
                    int count2 = 0;
                    if (currentDeno <= s) {
                        count2 = dp[idx][s - currentDeno];
                    }
                    dp[idx][s] = count1 + count2;
                }
            }
            int countforSum = dp[denominations.length - 1][Sum];
            return countforSum;
        }

        public static void main(String[] args) {
            MakeUpAmount makeUpAmount = new MakeUpAmount();
            int[] denominations = {1, 2, 3};
            System.out.println("Bruteforce: " + makeUpAmount.findCountBruteForce(denominations, 5));
            System.out.println("Tabular: " + makeUpAmount.findCountTabular(denominations, 5));
        }
    }

    /*Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the minimum number of coins needed to make up that amount.*/
    static class CoinsCounter {
        public Integer findCoinsForSumBruteForce(int[] denominations, int sum) {
            return findCoinsForSumBruteForce(denominations, sum, 0, 0);
        }

        public Integer findCoinsForSumBruteForce(int[] denominations, int sum, int currentIndex, int coins) {
            // base case
            if (sum == 0) {
                return coins;
            }
            if (currentIndex == denominations.length) {
                return null;
            }
            int currentDenom = denominations[currentIndex];
            //include
            Integer coinCount1 = null;
            if (currentDenom <= sum) {
                coinCount1 = findCoinsForSumBruteForce(denominations, sum - currentDenom, currentIndex, coins + 1);
            }
            // exclude
            Integer countCount2;
            countCount2 = findCoinsForSumBruteForce(denominations, sum, currentIndex + 1, coins);
            Integer minCoinCount;
            if (coinCount1 != null && countCount2 != null) {
                minCoinCount = Math.min(coinCount1, countCount2);
            } else if (coinCount1 != null) {
                minCoinCount = coinCount1;
            } else {
                minCoinCount = countCount2;
            }
            return minCoinCount;
        }

        public Integer findCoinsForSumMemoization(int[] denominations, int sum) {
            Integer[][] dp = new Integer[denominations.length][sum + 1];
            return findCoinsForSumMemoization(denominations, sum, 0, 0, dp);
        }

        public Integer findCoinsForSumMemoization(int[] denominations, int sum, int currentIndex, int coins, Integer[][] dp) {
            // base case
            if (sum == 0) {
                return coins;
            }
            if (currentIndex == denominations.length) {
                return null;
            }
            if (dp[currentIndex][sum] != null) {
                return dp[currentIndex][sum];
            }

            int currentDenom = denominations[currentIndex];
            //include
            Integer coinCount1 = null;
            if (currentDenom <= sum) {
                coinCount1 = findCoinsForSumBruteForce(denominations, sum - currentDenom, currentIndex, coins + 1);
            }
            // exclude
            Integer coinCount2;
            coinCount2 = findCoinsForSumBruteForce(denominations, sum, currentIndex + 1, coins);
            Integer minCoinCount;
            if (coinCount1 != null && coinCount2 != null) {
                minCoinCount = Math.min(coinCount1, coinCount2);
            } else if (coinCount1 != null) {
                minCoinCount = coinCount1;
            } else {
                minCoinCount = coinCount2;
            }
            dp[currentIndex][sum] = minCoinCount;
            return minCoinCount;
        }

        public Integer findCoinsTabular(int[] denomination, int sum) {
            Integer[][] dp = new Integer[denomination.length][sum + 1];
            for (int idx = 0; idx < denomination.length; idx++) {
                dp[idx][0] = 0;
            }
            for (int idx = 0; idx < denomination.length; idx++) {
                for (int s = 1; s <= sum; s++) {
                    int currentDenom = denomination[idx];
                    // include
                    Integer coinCount1 = Integer.MAX_VALUE;
                    if (currentDenom <= s) {
                        coinCount1 = dp[idx][s - currentDenom];
                    }
                    // exclude
                    Integer coinCount2 = Integer.MAX_VALUE;
                    if (idx > 0) {
                        coinCount2 = dp[idx - 1][s];
                    }
                    Integer minCoinCount;
                    if (coinCount1 != null && coinCount2 != null) {
                        minCoinCount = Math.min(coinCount1, coinCount2);
                    } else if (coinCount1 != null) {
                        minCoinCount = coinCount1;
                    } else {
                        minCoinCount = coinCount2;
                    }
                    dp[idx][s] = minCoinCount;
                }
            }
            return dp[denomination.length - 1][sum];
        }

        public static void main(String[] args) {
            CoinsCounter cc = new CoinsCounter();
            int[] denominations = {1, 2, 3};
            System.out.println("BruteForce: " + cc.findCoinsForSumBruteForce(denominations, 5));
            System.out.println("Memoization: " + cc.findCoinsForSumMemoization(denominations, 5));
            System.out.println("Tabular: " + cc.findCoinsTabular(denominations, 5));
            System.out.println("BruteForce: " + cc.findCoinsForSumBruteForce(denominations, 11));
            System.out.println("Memoization: " + cc.findCoinsForSumMemoization(denominations, 11));
            System.out.println("Tabular: " + cc.findCoinsTabular(denominations, 11));
            System.out.println("BruteForce: " + cc.findCoinsForSumBruteForce(denominations, 7));
            System.out.println("Memoization: " + cc.findCoinsForSumMemoization(denominations, 7));
            System.out.println("Tabular: " + cc.findCoinsTabular(denominations, 7));
            denominations = new int[]{3, 5};
            System.out.println("BruteForce: " + cc.findCoinsForSumBruteForce(denominations, 7));
            System.out.println("Memoization: " + cc.findCoinsForSumMemoization(denominations, 7));
            System.out.println("Tabular: " + cc.findCoinsTabular(denominations, 7));
        }
    }

    /*We are given a ribbon of length ‘n’ and a set of possible ribbon lengths. Now we need to cut the ribbon into the maximum number of pieces that comply with the above-mentioned possible lengths. Write a method that will return the count of pieces.*/
    static class MaximumRibbonCut {
        public Integer countRibbonPiecesBruteForce(int[] ribbonLens, int len) {
            return countRibbonPieces(ribbonLens, 0, len, 0);
        }

        public Integer countRibbonPieces(int[] ribbonLens, int currentIndex, int ribbonLen, int pieces) {
            // base
            if (ribbonLen == 0) {
                return pieces;
            }
            if (currentIndex == ribbonLens.length) {
                return 0;
            }
            // recursion
            int currentLen = ribbonLens[currentIndex];
            // include
            int pCount1 = 0;
            if (currentLen <= ribbonLen) {
                pCount1 = countRibbonPieces(ribbonLens, currentIndex, ribbonLen - currentLen, pieces + 1);
            }
            // exclude
            int pCount2 = countRibbonPieces(ribbonLens, currentIndex + 1, ribbonLen, pieces);
            return Math.max(pCount1, pCount2);
        }

        public Integer countRibbonPiecesTabular(int[] ribbonLens, int len) {
            Integer[][] dp = new Integer[ribbonLens.length][len + 1];
            for (int idx = 0; idx < ribbonLens.length; idx++) {
                dp[idx][0] = 0;
            }
            for (int idx = 0; idx < ribbonLens.length; idx++) {
                for (int l = 1; l <= len; l++) {
                    int currlen = ribbonLens[idx];
                    // exclude
                    Integer pCnt1 = -1;
                    if (idx > 0 && dp[idx - 1][l] != null) {
                        pCnt1 = dp[idx - 1][l];
                    }
                    //include
                    Integer pCnt2 = -1;
                    if (currlen <= l && dp[idx][l - currlen] != null && dp[idx][l - currlen] != -1) {
                        pCnt2 = 1 + dp[idx][l - currlen];
                    }
                    dp[idx][l] = Math.max(pCnt1, pCnt2);
                }
            }
            return dp[ribbonLens.length - 1][len];
        }

        public static void main(String[] args) {
            MaximumRibbonCut cr = new MaximumRibbonCut();
            int[] ribbonLengths = {2, 3, 5};
            System.out.println("BruteForce: " + cr.countRibbonPiecesBruteForce(ribbonLengths, 5));
            System.out.println("Tabular: " + cr.countRibbonPiecesTabular(ribbonLengths, 5));
            ribbonLengths = new int[]{2, 3};
            System.out.println("BruteForce: " + cr.countRibbonPiecesBruteForce(ribbonLengths, 7));
            System.out.println("Tabular: " + cr.countRibbonPiecesTabular(ribbonLengths, 7));
            ribbonLengths = new int[]{3, 5, 7};
            System.out.println("BruteForce: " + cr.countRibbonPiecesBruteForce(ribbonLengths, 13));
            System.out.println("Tabular: " + cr.countRibbonPiecesTabular(ribbonLengths, 13));
            ribbonLengths = new int[]{3, 5};
            System.out.println("BruteForce: " + cr.countRibbonPiecesBruteForce(ribbonLengths, 7));
            System.out.println("Tabular: " + cr.countRibbonPiecesTabular(ribbonLengths, 7));
        }
    }

    /*Fibonacci numbers : problems depend on previous n results*/
    static class FibonacciGenerator {
        public Integer fiboGenRecur(int n) {
            if (n < 2) {
                return n;
            }
            return fiboGenRecur(n - 1) + fiboGenRecur(n - 2);
        }

        public Integer fiboGenMemoization(int n, Map<Integer, Integer> cache) {
            if (n < 2) {
                return n;
            }
            if (cache.containsKey(n)) {
                return cache.get(n);
            }
            final Integer res1 = fiboGenMemoization(n - 1, cache);
            final Integer res2 = fiboGenMemoization(n - 2, cache);
            final int res = res1 + res2;
            cache.put(n, res);
            return res;
        }

        public Integer fiboTabular(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int idx = 2; idx <= n; idx++) {
                dp[idx] = dp[idx - 1] + dp[idx - 2];
            }
            return dp[n];
        }

        public Integer fiboTabular2(int n) {
            int n1 = 0;
            int n2 = 1;
            int tmp = 0;
            for (int idx = 0; idx < n; idx++) {
                tmp = n1 + n2;
                n2 = n1;
                n1 = tmp;
            }
            return n1;
        }

        public static void main(String[] args) {
            Map<Integer, Integer> cache = new HashMap<>();
            FibonacciGenerator fibonacciGenerator = new FibonacciGenerator();
            System.out.println("Recur: 5th Fibonacci is ---> " + fibonacciGenerator.fiboGenRecur(5));
            System.out.println("Recur: 6th Fibonacci is ---> " + fibonacciGenerator.fiboGenRecur(6));
            System.out.println("Recur: 7th Fibonacci is ---> " + fibonacciGenerator.fiboGenRecur(7));
            System.out.println("Memoization: 5th Fibonacci is ---> " + fibonacciGenerator.fiboGenMemoization(5, cache));
            System.out.println("Memoization: 6th Fibonacci is ---> " + fibonacciGenerator.fiboGenMemoization(6, cache));
            System.out.println("Memoization: 7th Fibonacci is ---> " + fibonacciGenerator.fiboGenMemoization(7, cache));
            System.out.println("Tabular: 5th Fibonacci is ---> " + fibonacciGenerator.fiboTabular(5));
            System.out.println("Tabular: 6th Fibonacci is ---> " + fibonacciGenerator.fiboTabular(6));
            System.out.println("Tabular: 7th Fibonacci is ---> " + fibonacciGenerator.fiboTabular(7));
            System.out.println("Opt: 5th Fibonacci is ---> " + fibonacciGenerator.fiboTabular2(5));
            System.out.println("Opt: 6th Fibonacci is ---> " + fibonacciGenerator.fiboTabular2(6));
            System.out.println("Opt: 7th Fibonacci is ---> " + fibonacciGenerator.fiboTabular2(7));
        }
    }

    /*Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top of the staircase, given that, at every step you can either take 1 step, 2 steps, or 3 steps.*/
    static class StairCase {

        public Integer findWaysBruteForce(int stairsLeft) {
            if (stairsLeft == 0) {
                return 1;
            }
            if (stairsLeft < 0) {
                return 0;
            }
            int noOfWays = 0;
            // take 1 step
            noOfWays = noOfWays + findWaysBruteForce(stairsLeft - 1);
            // take 2 steps
            noOfWays = noOfWays + findWaysBruteForce(stairsLeft - 2);
            // take 3 steps
            noOfWays = noOfWays + findWaysBruteForce(stairsLeft - 3);
            return noOfWays;
        }

        public Integer findWaysTabular(int stairs) {
            Integer[] dp = new Integer[stairs + 1];
            // result depends on previous 3 results
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 1 + 1;
            for (int idx = 3; idx <= stairs; idx++) {
                dp[idx] = dp[idx - 1] + dp[idx - 2] + dp[idx - 3];
            }
            return dp[stairs];
        }

        public static void main(String[] args) {
            StairCase sc = new StairCase();
            System.out.println(sc.findWaysBruteForce(3));
            System.out.println(sc.findWaysBruteForce(4));
            System.out.println(sc.findWaysBruteForce(5));
        }
    }

    /*Given a number ‘n’, implement a method to count how many possible ways there are to express ‘n’ as the sum of 1, 3, or 4.*/
    static class NumberFactor {
        public Integer findFactorBruteForce(int sum, int[] factors) {
            // base case
            if (sum == 0) {
                return 1;
            }
            if (sum < 0) {
                return 0;
            }
            int noOfWays = 0;
            for (int idx = 0; idx < factors.length; idx++) {
                noOfWays = noOfWays + findFactorBruteForce(sum - factors[idx], factors);
            }
            return noOfWays;
        }

        public Integer findFactorMemoization(int sum, int[] factors) {
            Integer[] dp = new Integer[sum + 1];
            return findFactorMemoization(sum, factors, dp);
        }

        public Integer findFactorMemoization(int sum, int[] factors, Integer[] dp) {
            // base case
            if (sum == 0) {
                return 1;
            }
            if (sum < 0) {
                return 0;
            }
            if (dp[sum] != null) {
                return dp[sum];
            }
            int noOfWays = 0;
            for (int idx = 0; idx < factors.length; idx++) {
                noOfWays = noOfWays + findFactorMemoization(sum - factors[idx], factors, dp);
            }
            dp[sum] = noOfWays;
            return noOfWays;
        }

        public Integer findFactorTabular(int sum) {
            Integer[] dp = new Integer[sum + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 2;

            for (int s = 4; s <= sum; s++) {
                dp[s] = dp[s - 1] + dp[s - 3] + dp[s - 4];
            }
            return dp[sum];
        }

        public static void main(String[] args) {
            NumberFactor en = new NumberFactor();
            int[] factors = new int[]{1, 3, 4};
            System.out.println("BruteForce: " + en.findFactorBruteForce(4, factors));
            System.out.println("Memoization: " + en.findFactorMemoization(4, factors));
            System.out.println("Tabular: " + en.findFactorTabular(4));
            System.out.println("BruteForce: " + en.findFactorBruteForce(5, factors));
            System.out.println("Memoization: " + en.findFactorMemoization(5, factors));
            System.out.println("Tabular: " + en.findFactorTabular(5));
            System.out.println("BruteForce: " + en.findFactorBruteForce(6, factors));
            System.out.println("Memoization: " + en.findFactorMemoization(6, factors));
            System.out.println("Tabular: " + en.findFactorTabular(6));
        }
    }

    /*Given an array of positive numbers, where each element represents the max number of jumps that can be made forward from that element, write a program to find the minimum number of jumps needed to reach the end of the array (starting from the first element). If an element is 0, then we cannot move through that element.*/
    static class MinJumpCounter {

        public Integer countMinJumpBruteForce(int[] jumps) {
            return countMinJumpBruteForce(jumps, 0);
        }

        public Integer countMinJumpBruteForce(int[] jumps, int currentIndex) {
            //base case
            if (currentIndex == jumps.length - 1) {
                return 0;
            }
            if (currentIndex >= jumps.length || jumps[currentIndex] == 0) {
                return null;
            }
            int start = currentIndex + 1;
            int end = currentIndex + jumps[currentIndex];
            int totalJump = Integer.MAX_VALUE;
            for (int idx = end; idx >= start; idx--) {
                Integer minJump = countMinJumpBruteForce(jumps, end--);
                if (minJump != null) {
                    totalJump = Math.min(minJump + 1, totalJump);
                }
            }
            return totalJump;
        }

        public Integer countMinJumpMemoization(int[] jumps) {
            Integer[] dp = new Integer[jumps.length];
            return countMinJumpMemoization(jumps, 0, dp);
        }

        public Integer countMinJumpMemoization(int[] jumps, int currentIndex, Integer[] dp) {
            //base case
            if (currentIndex == jumps.length - 1) {
                return 0;
            }
            if (currentIndex >= jumps.length || jumps[currentIndex] == 0) {
                return null;
            }
            if (dp[currentIndex] != null) {
                return dp[currentIndex];
            }
            int start = currentIndex + 1;
            int end = currentIndex + jumps[currentIndex];
            int totalJump = Integer.MAX_VALUE;
            for (int idx = end; idx >= start; idx--) {
                Integer minJump = countMinJumpMemoization(jumps, end--, dp);
                if (minJump != null) {
                    totalJump = Math.min(minJump + 1, totalJump);
                }
            }
            dp[currentIndex] = totalJump;
            return totalJump;
        }

        public Integer countMinJumpTabular(int[] jumps) {
            Integer[] dp = new Integer[jumps.length];
            dp[0] = 0;
            //initialize with infinity, except the first index which should be zero as we start from there
            for (int i = 1; i < jumps.length; i++)
                dp[i] = Integer.MAX_VALUE;

            for (int start = 0; start < jumps.length - 1; start++) {
                for (int end = start + 1; end <= start + jumps[start] && end < jumps.length; end++) {
                    Integer a = dp[end];
                    Integer b = dp[start] + 1;
                    dp[end] = Math.min(a, b);
                }
            }
            return dp[jumps.length - 1];

        }

        public static void main(String[] args) {
            MinJumpCounter aj = new MinJumpCounter();
            int[] jumps = {2, 1, 1, 1, 4};
            System.out.println("BruteForce: " + aj.countMinJumpBruteForce(jumps));
            System.out.println("Memoization: " + aj.countMinJumpMemoization(jumps));
            System.out.println("Tabular: " + aj.countMinJumpTabular(jumps));
            jumps = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
            System.out.println("BruteForce: " + aj.countMinJumpBruteForce(jumps));
            System.out.println("Memoization: " + aj.countMinJumpMemoization(jumps));
            System.out.println("Tabular: " + aj.countMinJumpTabular(jumps));
        }
    }

    /*Given a staircase with ‘n’ steps and an array of ‘n’ numbers representing the fee that you have to pay if you take the step. Implement a method to calculate the minimum fee required to reach the top of the staircase (beyond the top-most step). At every step, you have an option to take either 1 step, 2 steps, or 3 steps. You should assume that you are standing at the first step.*/
    static class MinJumpWithFee {
        public Integer jumpFeeBruteForce(int[] fee) {
            return jumpFeeBruteForce(fee, 0);
        }

        // backtracking
        public Integer jumpFeeBruteForce(int[] fee, int currentIndex) {
            // base case
            if (currentIndex > fee.length - 1) {
                return 0;
            }
            // recursive case
            // take 1 step
            Integer fee1 = jumpFeeBruteForce(fee, currentIndex + 1);
            // take 2 steps
            Integer fee2 = jumpFeeBruteForce(fee, currentIndex + 2);
            // take 3 steps
            Integer fee3 = jumpFeeBruteForce(fee, currentIndex + 3);

            return fee[currentIndex] + Math.min(fee1, Math.min(fee2, fee3));
        }

        public Integer jumpFeeTabular(int[] fee) {
            Integer[] dp = new Integer[fee.length + 1];
            dp[0] = 0; // no steps so no fee
            dp[1] = fee[0]; // fee for 1 step
            dp[2] = fee[0]; // fee for 1 step and take fee 1 step
            dp[3] = fee[0]; // fee for 1 step and fee 2 steps

            for (int idx = 3; idx < fee.length; idx++) {
                /*current step = idx */
                int nextStairFee1 = fee[idx] + dp[idx]; // fee of current stair + fee to reach till current stair ( 1 jump)
                /*current step = idx-1*/
                int nextStairFee2 = fee[idx - 1] + dp[idx - 1]; // fee of idx-1 stair + fee to reach till idx-1 stair ( 2 jump)
                /*current step = idx-2*/
                int nextStairFee3 = fee[idx - 2] + dp[idx - 2]; // fee of idx-2 stair + fee to reach till idx-2 stair ( 3 jump)
                /*next step fee min*/
                dp[idx + 1] = Math.min(nextStairFee1, Math.min(nextStairFee2, nextStairFee3));
            }
            return dp[fee.length];
        }

        public static void main(String[] args) {
            MinJumpWithFee sc = new MinJumpWithFee();
            int[] fee = {1, 2, 5, 2, 1, 2};
            System.out.println("BruteForce: " + sc.jumpFeeBruteForce(fee));
            System.out.println("Tabular: " + sc.jumpFeeTabular(fee));
            fee = new int[]{2, 3, 4, 5};
            System.out.println("BruteForce: " + sc.jumpFeeBruteForce(fee));
            System.out.println("Tabular: " + sc.jumpFeeTabular(fee));
        }
    }

    /*There are ‘n’ houses built in a line. A thief wants to steal maximum possible money from these houses. The only restriction the thief has is that he can’t steal from two consecutive houses, as that would alert the security system. How should the thief maximize his stealing?*/
    static class HouseThief {
        public Integer maxStealBruteForce(int[] houses) {
            return maxStealBruteForce(houses, 0);
        }

        public Integer maxStealBruteForce(int[] houses, int currentIndex) {
            // base case
            if (currentIndex >= houses.length) {
                return 0;
            }
            // recursion
            // steal current house and skip next house
            Integer moneyStole1 = houses[currentIndex] + maxStealBruteForce(houses, currentIndex + 2);
            // skip current house and steal next house
            Integer moneyStole2 = maxStealBruteForce(houses, currentIndex + 1);
            return Math.max(moneyStole1, moneyStole2);
        }

        public Integer maxSteamTabular(int[] houses) {
            Integer[] dp = new Integer[houses.length + 1]; // +1 because we have 0 houses scenarios as well
            dp[0] = 0; // no house the 0 wealth
            dp[1] = houses[0]; // 1 house then wealth of 1 house

            for (int idx = 1; idx < houses.length; idx++) { // index of house in array
                // steal current house
                int moneyStole1 = houses[idx] + dp[idx - 1];
                // skip current
                int moneyStole2 = dp[idx];
                dp[idx + 1] = Math.max(moneyStole1, moneyStole2);
            }
            return dp[houses.length];
        }

        public static void main(String[] args) {
            HouseThief ht = new HouseThief();
            int[] wealth = {2, 5, 1, 3, 6, 2, 4};
            System.out.println("BruteForce: " + ht.maxStealBruteForce(wealth));
            System.out.println("Tabular: " + ht.maxSteamTabular(wealth));
            wealth = new int[]{2, 10, 14, 8, 1};
            System.out.println("BruteForce: " + ht.maxStealBruteForce(wealth));
            System.out.println("Tabular: " + ht.maxSteamTabular(wealth));
        }
    }

    /*Longest Palindromic Subsequence*/
    /*Given a sequence, find the length of its Longest Palindromic Subsequence (LPS). In a palindromic subsequence, elements read the same backward and forward.*/
    static class LongestPalindromicSeq {
        public Integer findLPSLengthBruteForce(String str) {
            return findLPSLengthBruteForce(str, 0, str.length() - 1);
        }

        public Integer findLPSLengthBruteForce(String str, int startIndex, int endIndex) {
            //base case
            if (startIndex > endIndex) {
                return 0;
            }
            if (startIndex == endIndex) {
                return 1;
            }
            if (str.charAt(startIndex) == str.charAt(endIndex)) {
                return 2 + findLPSLengthBruteForce(str, startIndex + 1, endIndex - 1);
            }
            // increment start index
            int len1 = findLPSLengthBruteForce(str, startIndex + 1, endIndex);
            // decrement end index
            int len2 = findLPSLengthBruteForce(str, startIndex, endIndex - 1);
            int maxLen = Math.max(len1, len2);
            return maxLen;
        }

        public Integer findLPSLengthMemoization(String str) {
            Integer[][] dp = new Integer[str.length()][str.length()];
            return findLPSLengthMemoization(str, 0, str.length() - 1, dp);
        }

        public Integer findLPSLengthMemoization(String str, int startIndex, int endIndex, Integer[][] dp) {
            //base case
            if (startIndex > endIndex) {
                return 0;
            }
            if (startIndex == endIndex) {
                return 1;
            }
            if (dp[startIndex][endIndex] != null) {
                return dp[startIndex][endIndex];
            }
            if (str.charAt(startIndex) == str.charAt(endIndex)) {
                return 2 + findLPSLengthMemoization(str, startIndex + 1, endIndex - 1, dp);
            }
            // increment start index
            int len1 = findLPSLengthMemoization(str, startIndex + 1, endIndex, dp);
            // increment end index
            int len2 = findLPSLengthMemoization(str, startIndex, endIndex - 1, dp);
            int maxLen = Math.max(len1, len2);
            dp[startIndex][endIndex] = maxLen;
            return maxLen;
        }

        public Integer findLPSLengthTabular(String str) {
            Integer[][] dp = new Integer[str.length()][str.length()];
            for (int idx1 = 0; idx1 < str.length(); idx1++) {
                for (int idx2 = 0; idx2 < str.length(); idx2++) {
                    if (idx1 == idx2) {
                        dp[idx1][idx2] = 1;
                    } else {
                        dp[idx1][idx2] = 0;
                    }
                }
            }
            for (int startIndex = str.length() - 1; startIndex >= 0; startIndex--) {
                for (int endIndex = startIndex + 1; endIndex < str.length(); endIndex++) {
                    // if char at start and end same
                    if (str.charAt(startIndex) == str.charAt(endIndex)) {
                        dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];
                    } else {
                        int len1 = dp[startIndex + 1][endIndex];
                        int len2 = dp[startIndex][endIndex - 1];
                        dp[startIndex][endIndex] = Math.max(len1, len2);
                    }
                }
            }
            return dp[0][str.length() - 1];
        }

        public static void main(String[] args) {
            LongestPalindromicSeq longestPalindromicSeq = new LongestPalindromicSeq();
            System.out.println("BruteForce: " + longestPalindromicSeq.findLPSLengthBruteForce("abdbca"));
            System.out.println("Memoization: " + longestPalindromicSeq.findLPSLengthMemoization("abdbca"));
            System.out.println("Tabular: " + longestPalindromicSeq.findLPSLengthTabular("abdbca"));
            System.out.println("BruteForce: " + longestPalindromicSeq.findLPSLengthBruteForce("cddpd"));
            System.out.println("Memoization: " + longestPalindromicSeq.findLPSLengthMemoization("cddpd"));
            System.out.println("Tabular: " + longestPalindromicSeq.findLPSLengthTabular("cddpd"));
            System.out.println("BruteForce: " + longestPalindromicSeq.findLPSLengthBruteForce("pqr"));
            System.out.println("Memoization: " + longestPalindromicSeq.findLPSLengthMemoization("pqr"));
            System.out.println("Tabular: " + longestPalindromicSeq.findLPSLengthTabular("pqr"));
        }
    }

    static class LongestPalindromicSubString {
        public Integer findLPSLenBruteForce(String str) {
            return findLPSLenBruteForce(str, 0, str.length() - 1);
        }

        public Integer findLPSLenBruteForce(String str, int startIndex, int endIndex) {
            //base case
            if (startIndex > endIndex) {
                return 0;
            }
            if (startIndex == endIndex) {
                return 1;
            }
            if (str.charAt(startIndex) == str.charAt(endIndex)) {
                int remainingLen = endIndex - startIndex - 1;
                if (remainingLen == findLPSLenBruteForce(str, startIndex + 1, endIndex - 1)) {
                    return 2 + remainingLen;
                }
            }
            int len1 = findLPSLenBruteForce(str, startIndex + 1, endIndex);
            int len2 = findLPSLenBruteForce(str, startIndex, endIndex - 1);
            int maxLen = Math.max(len1, len2);
            return maxLen;
        }

        public Integer findLSPTabular(String str) {
            Boolean[][] dp = new Boolean[str.length()][str.length()];
            for (int idx1 = 0; idx1 < str.length(); idx1++) {
                for (int idx2 = 0; idx2 < str.length(); idx2++) {
                    if (idx1 == idx2) {
                        dp[idx1][idx2] = true;
                    } else {
                        dp[idx1][idx2] = false;
                    }
                }
            }
            int maxLen = 1;
            for (int startIndex = str.length() - 1; startIndex >= 0; startIndex--) {
                for (int endIndex = startIndex + 1; endIndex < str.length(); endIndex++) {
                    if (str.charAt(startIndex) == str.charAt(endIndex)) {
                        if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
                            dp[startIndex][endIndex] = true;
                            maxLen = Math.max(maxLen, (endIndex - startIndex + 1));
                        }
                    }
                }
            }
            return maxLen;
        }

        public static void main(String[] args) {
            LongestPalindromicSubString longestPalindromicSubString = new LongestPalindromicSubString();
            System.out.println("BruteForce: " + longestPalindromicSubString.findLPSLenBruteForce("abdbca"));
            System.out.println("Tabular: " + longestPalindromicSubString.findLSPTabular("abdbca"));
            System.out.println("BruteForce: " + longestPalindromicSubString.findLPSLenBruteForce("cddpd"));
            System.out.println("Tabular: " + longestPalindromicSubString.findLSPTabular("cddpd"));
            System.out.println("BruteForce: " + longestPalindromicSubString.findLPSLenBruteForce("pqr"));
            System.out.println("Tabular: " + longestPalindromicSubString.findLSPTabular("pqr"));
        }
    }

    /*Given a string, find the total number of palindromic substrings in it. Please note we need to find the total number of substrings and not subsequences.*/
    static class LPSCounter {
        public Integer findLPSCountTaular(String str) {
            Boolean[][] dp = new Boolean[str.length()][str.length()];
            int count = 0;
            for (int idx1 = 0; idx1 < str.length(); idx1++) {
                for (int idx2 = 0; idx2 < str.length(); idx2++) {
                    if (idx1 == idx2) {
                        dp[idx1][idx2] = true;
                        count++;
                    } else {
                        dp[idx1][idx2] = false;
                    }
                }
            }
            for (int startIndex = str.length() - 1; startIndex >= 0; startIndex--) {
                for (int endIndex = startIndex + 1; endIndex < str.length(); endIndex++) {
                    if (str.charAt(startIndex) == str.charAt(endIndex)) {
                        if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
                            dp[startIndex][endIndex] = true;
                            count++;
                        }
                    }
                }
            }
            return count;
        }

        public static void main(String[] args) {
            LPSCounter lpsCounter = new LPSCounter();
            System.out.println("Tabular: " + lpsCounter.findLPSCountTaular("abdbca"));
            System.out.println("Tabular: " + lpsCounter.findLPSCountTaular("cdpdd"));
            System.out.println("Tabular: " + lpsCounter.findLPSCountTaular("pqr"));
        }
    }

    /*Given a string, find the minimum number of characters that we can remove to make it a palindrome.*/
    static class MinimumDeletionPalindrome {
        public Integer findMinDeletionBruteForce(String str) {
            return str.length() - findPalindromeBruteForce(str, 0, str.length() - 1);
        }

        public Integer findPalindromeBruteForce(String str, int startIndex, int endIndex) {
            // base case
            if (startIndex > endIndex) {
                return 0;
            }
            if (startIndex == endIndex) {
                return 1;
            }

            if (str.charAt(startIndex) == str.charAt(endIndex)) {
                return 2 + findPalindromeBruteForce(str, startIndex + 1, endIndex - 1);
            }
            int len1 = findPalindromeBruteForce(str, startIndex + 1, endIndex);
            int len2 = findPalindromeBruteForce(str, startIndex, endIndex - 1);
            int maxLen = Math.max(len1, len2);
            return maxLen;
        }

        public Integer findPalindromeTabular(String str) {
            Integer[][] dp = new Integer[str.length()][str.length()];
            for (int idx1 = 0; idx1 < str.length(); idx1++) {
                for (int idx2 = 0; idx2 < str.length(); idx2++) {
                    if (idx1 == idx2) {
                        dp[idx1][idx2] = 1;
                    } else {
                        dp[idx1][idx2] = 0;
                    }
                }
            }
            for (int startIndex = str.length() - 1; startIndex >= 0; startIndex--) {
                for (int endIndex = startIndex + 1; endIndex < str.length(); endIndex++) {
                    if (str.charAt(startIndex) == str.charAt(endIndex)) {
                        dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];
                    } else {
                        int len1 = dp[startIndex + 1][endIndex];
                        int len2 = dp[startIndex][endIndex - 1];
                        dp[startIndex][endIndex] = Math.max(len1, len2);
                    }
                }
            }
            return str.length() - dp[0][str.length() - 1];
        }

        public static void main(String[] args) {
            MinimumDeletionPalindrome mdsp = new MinimumDeletionPalindrome();
            System.out.println("BruteForce: " + mdsp.findMinDeletionBruteForce("abdbca"));
            System.out.println("Memoization: " + mdsp.findPalindromeTabular("abdbca"));
            System.out.println("BruteForce: " + mdsp.findMinDeletionBruteForce("cddpd"));
            System.out.println("Memoization: " + mdsp.findPalindromeTabular("cddpd"));
            System.out.println("BruteForce: " + mdsp.findMinDeletionBruteForce("pqr"));
            System.out.println("Memoization: " + mdsp.findPalindromeTabular("pqr"));
        }
    }

    /*Given a string, we want to cut it into pieces such that each piece is a palindrome. Write a function to return the minimum number of cuts needed.

Example 1:*/
    static class MinimumCut {
        public Integer findMinCutBruteForce(String str) {
            return findMinCutBruteForce(str, 0, str.length() - 1);
        }

        public Integer findMinCutBruteForce(String str, int startIndex, int endIndex) {
            if (startIndex >= endIndex || isPalindromic(str, startIndex, endIndex)) {
                return 0;
            }
            int minimumCut = endIndex - startIndex;
            for (int i = startIndex; i <= endIndex; i++) {
                if (isPalindromic(str, startIndex, i)) {
                    minimumCut = Math.min(minimumCut, 1 + findMinCutBruteForce(str, i + 1, endIndex));
                }
            }
            return minimumCut;
        }

        public boolean isPalindromic(String str, int start, int end) {
            int x = start, y = end;
            for (; x < y && str.charAt(x) == str.charAt(y); x++, y--) ;
            return x >= y;
        }

        public Integer findMinCutTabular(String str) {
            Boolean[][] dp = new Boolean[str.length()][str.length()];
            for (int idx1 = 0; idx1 < str.length(); idx1++) {
                for (int idx2 = 0; idx2 < str.length(); idx2++) {
                    if (idx1 == idx2) {
                        dp[idx1][idx2] = true;
                    } else {
                        dp[idx1][idx2] = false;
                    }
                }
            }
            for (int startIndex = str.length() - 1; startIndex >= 0; startIndex--) {
                for (int endIndex = startIndex + 1; endIndex < str.length(); endIndex++) {
                    if (str.charAt(startIndex) == str.charAt(endIndex)) {
                        if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
                            dp[startIndex][endIndex] = true;
                        } else {
                            dp[startIndex][endIndex] = false;
                        }
                    }
                }
            }
            int[] cuts = new int[str.length()];
            for (int startIndex = str.length() - 1; startIndex >= 0; startIndex--) {
                int minCuts = str.length(); // maximum cuts
                for (int endIndex = str.length() - 1; endIndex >= startIndex; endIndex--) {
                    if (dp[startIndex][endIndex]) {
                        // we can cut here as we got a palindrome
                        // also we dont need any cut if the whole substring is a palindrome
                        minCuts = (endIndex == str.length() - 1) ? 0 : Math.min(minCuts, 1 + cuts[endIndex + 1]);
                    }
                }
                cuts[startIndex] = minCuts;
            }
            return cuts[0];
        }

        public static void main(String[] args) {
            MinimumCut minimumCut = new MinimumCut();
            System.out.println("BruteForce: " + minimumCut.findMinCutBruteForce("abdbca"));
            System.out.println("Tabular: " + minimumCut.findMinCutTabular("abdbca"));
            System.out.println("BruteForce: " + minimumCut.findMinCutBruteForce("cdpdd"));
            System.out.println("Tabular: " + minimumCut.findMinCutTabular("cdpdd"));
            System.out.println("BruteForce: " + minimumCut.findMinCutBruteForce("pqr"));
            System.out.println("Tabular: " + minimumCut.findMinCutTabular("pqr"));
            System.out.println("BruteForce: " + minimumCut.findMinCutBruteForce("pp"));
            System.out.println("Tabular: " + minimumCut.findMinCutTabular("pp"));
        }
    }

    /*Longest sequence*/
    /*Given two strings ‘s1’ and ‘s2’, find the length of the longest substring which is common in both the strings.*/
    static class LongestCommonSubstring {
        public Integer findCSLenBruteForce(String str1, String str2) {
            return findCSLenBruteForce(str1, str2, 0, 0, 0);
        }

        public Integer findCSLenBruteForce(String str1, String str2, int idx1, int idx2, int count) {
            if (str1.length() == idx1 || str2.length() == idx2) {
                return count;
            }
            int cnt1 = count;
            if (str1.charAt(idx1) == str2.charAt(idx2)) {
                cnt1 = findCSLenBruteForce(str1, str2, idx1 + 1, idx2 + 1, count + 1);
            }
            int cnt2 = findCSLenBruteForce(str1, str2, idx1 + 1, idx2, 0);
            int cnt3 = findCSLenBruteForce(str1, str2, idx1, idx2 + 1, 0);
            return Math.max(cnt1, Math.max(cnt2, cnt3));
        }

        public Integer findCSLenMemoization(String str1, String str2) {
            Map<String, Integer> dp = new HashMap<>();
            return findCSLenBruteForceMemoization(str1, str2, 0, 0, 0, dp);
        }

        public Integer findCSLenBruteForceMemoization(String str1, String str2, int idx1, int idx2, int count, Map<String, Integer> dp) {
            if (str1.length() == idx1 || str2.length() == idx2) {
                return count;
            }
            String key = idx1 + ":" + idx2 + ":" + count;
            if (dp.containsKey(key)) {
                return dp.get(key);
            }
            int cnt1 = count;
            if (str1.charAt(idx1) == str2.charAt(idx2)) {
                cnt1 = findCSLenBruteForceMemoization(str1, str2, idx1 + 1, idx2 + 1, count + 1, dp);
            }
            int cnt2 = findCSLenBruteForceMemoization(str1, str2, idx1 + 1, idx2, 0, dp);
            int cnt3 = findCSLenBruteForceMemoization(str1, str2, idx1, idx2 + 1, 0, dp);
            int max = Math.max(cnt1, Math.max(cnt2, cnt3));
            dp.put(key, max);
            return max;
        }

        public Integer findCSLenTabular(String str1, String str2) {
            Integer[][] dp = new Integer[str1.length() + 1][str2.length() + 1];
            for (int idx = 0; idx < dp[0].length; idx++) {
                dp[0][idx] = 0;
            }
            for (int idx = 0; idx < dp.length; idx++) {
                dp[idx][0] = 0;
            }
            int maxLength = 0;
            for (int idx1 = 1; idx1 <= str1.length(); idx1++) {
                for (int idx2 = 1; idx2 <= str2.length(); idx2++) {
                    if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1)) {
                        dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1];
                        maxLength = Math.max(maxLength, dp[idx1][idx2]);
                    } else {
                        dp[idx1][idx2] = 0;
                    }
                }
            }
            return maxLength;
        }

        public static void main(String[] args) {
            LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
            System.out.println("BruteForce: " + longestCommonSubstring.findCSLenBruteForce("abdca", "cbda"));
            System.out.println("Memoization: " + longestCommonSubstring.findCSLenMemoization("abdca", "cbda"));
            System.out.println("Tabular: " + longestCommonSubstring.findCSLenTabular("abdca", "cbda"));
            System.out.println("BruteForce: " + longestCommonSubstring.findCSLenBruteForce("passport", "ppsspt"));
            System.out.println("Memoization: " + longestCommonSubstring.findCSLenMemoization("passport", "ppsspt"));
            System.out.println("Tabular: " + longestCommonSubstring.findCSLenTabular("passport", "ppsspt"));
        }
    }

    /*Given two strings ‘s1’ and ‘s2’, find the length of the longest subsequence which is common in both the strings.
      A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.*/
    static class LongestCommonSequence {
        public Integer findLCSBruteForce(String str1, String str2) {
            return findLCSBruteForce(str1, str2, 0, 0);
        }

        public Integer findLCSBruteForce(String str1, String str2, int idx1, int idx2) {
            if (str1.length() == idx1 || str2.length() == idx2) {
                return 0;
            }
            if (str1.charAt(idx1) == str2.charAt(idx2)) {
                return 1 + findLCSBruteForce(str1, str2, idx1 + 1, idx2 + 1);
            }
            int cnt1 = findLCSBruteForce(str1, str2, idx1 + 1, idx2);
            int cnt2 = findLCSBruteForce(str1, str2, idx1, idx2 + 1);
            int max = Math.max(cnt1, cnt2);
            return max;
        }

        public Integer findLCSTabular(String str1, String str2) {
            Integer[][] dp = new Integer[str1.length() + 1][str2.length() + 1];
            for (int idx = 0; idx < dp[0].length; idx++) {
                dp[0][idx] = 0;
            }
            for (int idx = 0; idx < dp.length; idx++) {
                dp[idx][0] = 0;
            }
            int maxLen = 0;
            for (int idx1 = 1; idx1 <= str1.length(); idx1++) {
                for (int idx2 = 1; idx2 <= str2.length(); idx2++) {
                    if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1)) {
                        dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1];
                    } else {
                        dp[idx1][idx2] = Math.max(dp[idx1 - 1][idx2], dp[idx1][idx2 - 1]);
                    }
                    maxLen = Math.max(maxLen, dp[idx1][idx2]);
                }
            }
            return maxLen;
        }

        public static void main(String[] args) {
            LongestCommonSequence longestCommonSequence = new LongestCommonSequence();
            System.out.println("BruteForce: " + longestCommonSequence.findLCSBruteForce("abdca", "cbda"));
            System.out.println("Tabular: " + longestCommonSequence.findLCSTabular("abdca", "cbda"));
            System.out.println("BruteForce: " + longestCommonSequence.findLCSBruteForce("passport", "ppsspt"));
            System.out.println("Tabular: " + longestCommonSequence.findLCSTabular("passport", "ppsspt"));
        }
    }

    /*Given strings s1 and s2, we need to transform s1 into s2 by deleting and inserting characters. Write a function to calculate the count of the minimum number of deletion and insertion operations.*/
    static class LCSMinDeletion {
        public Integer findMinDeletion(String str1, String str2) {
            int len = Math.max(str1.length(), str2.length());
            return len - findLCSBruteForce(str1, str2, 0, 0);
        }

        public Integer findLCSBruteForce(String str1, String str2, int idx1, int idx2) {
            //base case
            if (idx1 == str1.length() || idx2 == str2.length()) {
                return 0;
            }
            // recursive
            if (str1.charAt(idx1) == str2.charAt(idx2)) {
                return 1 + findLCSBruteForce(str1, str2, idx1 + 1, idx2 + 1);
            }
            int cnt1 = findLCSBruteForce(str1, str2, idx1 + 1, idx2);
            int cnt2 = findLCSBruteForce(str1, str2, idx1, idx2 + 1);
            int max = Math.max(cnt1, cnt2);
            return max;
        }

        public Integer findMinDeletionTabular(String str1, String str2) {
            Integer[][] dp = new Integer[str1.length() + 1][str2.length() + 1];
            for (int idx = 0; idx < dp[0].length; idx++) {
                dp[0][idx] = 0;
            }
            for (int idx = 0; idx < dp.length; idx++) {
                dp[idx][0] = 0;
            }
            for (int idx1 = 1; idx1 <= str1.length(); idx1++) {
                for (int idx2 = 1; idx2 <= str2.length(); idx2++) {
                    if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1)) {
                        dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1];
                    } else {
                        dp[idx1][idx2] = Math.max(dp[idx1 - 1][idx2], dp[idx1][idx2 - 1]);
                    }
                }
            }
            return Math.max(str1.length(), str2.length()) - dp[str1.length()][str2.length()];
        }

        public static void main(String[] args) {
            LCSMinDeletion lcsMinDeletion = new LCSMinDeletion();
            System.out.println("BruteFore: " + lcsMinDeletion.findMinDeletion("abc", "fbc"));
            System.out.println("Tabular: " + lcsMinDeletion.findMinDeletionTabular("abc", "fbc"));
            System.out.println("BruteFore: " + lcsMinDeletion.findMinDeletion("abdca", "cbda"));
            System.out.println("Tabular: " + lcsMinDeletion.findMinDeletionTabular("abdca", "cbda"));
            System.out.println("BruteFore: " + lcsMinDeletion.findMinDeletion("passport", "ppsspt"));
            System.out.println("Tabular: " + lcsMinDeletion.findMinDeletionTabular("passport", "ppsspt"));
        }
    }

    /*Given a number sequence, find the length of its Longest Increasing Subsequence (LIS). In an increasing subsequence, all the elements are in increasing order (from lowest to highest).*/
    static class LISeq {
        public Integer findLongestSeqBruteForce(int[] arr) {
            return findLongestSeqBruteForce(arr, 0);
        }

        public Integer findLongestSeqBruteForce(int[] arr, int index) {
            // base case
            if (index == arr.length) {
                return 0;
            }
            // recursive
            int len1 = 0;
            if (index == 0 || arr[index - 1] < arr[index]) {
                len1 = 1 + findLongestSeqBruteForce(arr, index + 1);
            }
            int len2 = findLongestSeqBruteForce(arr, index + 1);
            return Math.max(len1, len2);
        }

        public Integer findLongestSeqMemoization(int[] arr) {
            Integer[] dp = new Integer[arr.length + 1];
            return findLongestSeqMemoization(arr, 0, dp);
        }

        public Integer findLongestSeqMemoization(int[] arr, int index, Integer[] dp) {
            // base case
            if (index == arr.length) {
                return 0;
            }
            if (dp[index] != null) {
                return dp[index];
            }
            // recursive
            int len1 = 0;
            if (index == 0 || arr[index - 1] < arr[index]) {
                len1 = 1 + findLongestSeqMemoization(arr, index + 1, dp);
            }
            int len2 = findLongestSeqMemoization(arr, index + 1, dp);
            int max = Math.max(len1, len2);
            dp[index] = max;
            return max;
        }

        public Integer findLongestSeqTabular(int[] arr) {
            Integer[] dp = new Integer[arr.length];
            Arrays.fill(dp, 1);
            int maxLen = 0;
            for (int idx1 = 1; idx1 < arr.length; idx1++) {
                dp[idx1] = 1;
                for (int idx2 = 0; idx2 < idx1; idx2++) {
                    if (arr[idx1] > arr[idx2] && dp[idx1] <= dp[idx2]) {
                        dp[idx1] = 1 + dp[idx2];
                        maxLen = Math.max(maxLen, dp[idx1]);
                    }
                }
            }
            return maxLen;
        }

        public static void main(String[] args) {
            LISeq liSeq = new LISeq();
            int[] nums = {4, 2, 3, 6, 10, 1, 12};
            System.out.println("BruteForce: " + liSeq.findLongestSeqBruteForce(nums));
            System.out.println("Memoization: " + liSeq.findLongestSeqBruteForce(nums));
            System.out.println("Tabular: " + liSeq.findLongestSeqTabular(nums));
            nums = new int[]{-4, 10, 3, 7, 15};
            System.out.println("BruteForce: " + liSeq.findLongestSeqBruteForce(nums));
            System.out.println("Memoization: " + liSeq.findLongestSeqBruteForce(nums));
            System.out.println("Tabular: " + liSeq.findLongestSeqTabular(nums));
        }
    }

    /*Given a number sequence, find the increasing subsequence with the highest sum. Write a method that returns the highest sum.*/
    static class LISSum {
        public Integer largestSumBruteForce(int[] arr) {
            return largestSumBruteForce(arr, 0, -1, 0);
        }

        public Integer largestSumBruteForce(int[] arr, int index, int previousIndex, int sum) {
            //base
            if (index == arr.length) {
                return sum;
            }
            // recursive
            int sum1 = 0;
            if (previousIndex == -1 || (arr[previousIndex] < arr[index])) {
                sum1 = largestSumBruteForce(arr, index + 1, index, sum + arr[index]);
            }
            int sum2 = largestSumBruteForce(arr, index + 1, previousIndex, sum);
            int max = Math.max(sum1, sum2);
            return max;
        }

        public Integer largestSumTabular(int[] arr) {
            Integer[] dp = new Integer[arr.length];
            for (int idx = 0; idx < arr.length; idx++) {
                dp[idx] = arr[idx];
            }
            int maxSum = Integer.MIN_VALUE;
            for (int idx1 = 1; idx1 < arr.length; idx1++) {
                for (int idx2 = 0; idx2 < idx1; idx2++) {
                    if (arr[idx2] < arr[idx1] && dp[idx1] <= dp[idx2] + arr[idx1]) {
                        dp[idx1] = dp[idx2] + arr[idx1];
                        maxSum = Math.max(maxSum, dp[idx1]);
                    }
                }
            }
            return maxSum;
        }

        public static void main(String[] args) {
            LISSum lisSum = new LISSum();
            int[] nums = {4, 1, 2, 6, 10, 1, 12};
            System.out.println("BruteForce: " + lisSum.largestSumBruteForce(nums));
            System.out.println("Tabular: " + lisSum.largestSumTabular(nums));
            nums = new int[]{-4, 10, 3, 7, 15};
            System.out.println("BruteForce: " + lisSum.largestSumBruteForce(nums));
            System.out.println("Tabular: " + lisSum.largestSumTabular(nums));
        }
    }

    /*Given two sequences ‘s1’ and ‘s2’, write a method to find the length of the shortest sequence which has ‘s1’ and ‘s2’ as subsequences.*/
    static class MinLenSCS {
        public Integer findMinSCS(String str1, String str2) {
            return findMinSCS(str1, str2, 0, 0);
        }

        public Integer findMinSCS(String str1, String str2, int idx1, int idx2) {
            //base case
            if (idx1 == str1.length()) {
                return str2.length() - idx2;
            }
            if (idx2 == str2.length()) {
                return str1.length() - idx1;
            }
            // recursive
            if (str1.charAt(idx1) == str2.charAt(idx2)) {
                return 1 + findMinSCS(str1, str2, idx1 + 1, idx2 + 1);
            }
            int len1 = 1 + findMinSCS(str1, str2, idx1, idx2 + 1);
            int len2 = 1 + findMinSCS(str1, str2, idx1 + 1, idx2);
            int min = Math.min(len1, len2);
            return min;
        }

        public Integer findMinSCSTabular(String str1, String str2) {
            Integer[][] dp = new Integer[str1.length() + 1][str2.length() + 1];

            for (int idx = 0; idx <= str2.length(); idx++) {
                dp[0][idx] = idx;
            }
            for (int idx = 0; idx <= str1.length(); idx++) {
                dp[idx][0] = idx;
            }

            for (int idx1 = 1; idx1 <= str1.length(); idx1++) {
                for (int idx2 = 1; idx2 <= str2.length(); idx2++) {
                    if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1)) {
                        dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1];
                    } else {
                        dp[idx1][idx2] = 1 + Math.min(dp[idx1][idx2 - 1], dp[idx1 - 1][idx2]);
                    }
                }
            }
            return dp[str1.length()][str2.length()];
        }

        public static void main(String[] args) {
            MinLenSCS scs = new MinLenSCS();
            System.out.println("BruteForce: " + scs.findMinSCS("abcf", "bdcf"));
            System.out.println("Tabular: " + scs.findMinSCSTabular("abcf", "bdcf"));
            System.out.println("BruteForce: " + scs.findMinSCS("dynamic", "programming"));
            System.out.println("Tabular: " + scs.findMinSCSTabular("dynamic", "programming"));
        }
    }

    /*Given a number sequence, find the minimum number of elements that should be deleted to make the remaining sequence sorted.*/
    static class MinDelForSorted {
        public Integer findMinDel(int[] arr) {
            return arr.length - findLongestIncreasingSeqBruteForce(arr, 0, -1);
        }

        public Integer findLongestIncreasingSeqBruteForce(int[] arr, int index, int prevIndex) {
            // base case
            if (index == arr.length) {
                return 0;
            }
            // recursion
            int len1 = 0;
            if (prevIndex == -1 || arr[prevIndex] < arr[index]) {
                return 1 + findLongestIncreasingSeqBruteForce(arr, index + 1, index);
            }
            int len2 = findLongestIncreasingSeqBruteForce(arr, index + 1, index);
            int max = Math.max(len1, len2);
            return max;
        }

        public Integer findMilDelTabular(int[] arr) {
            Integer[] dp = new Integer[arr.length];
            Arrays.fill(dp, 1);
            int maxLen = 0;
            for (int idx1 = 1; idx1 < arr.length; idx1++) {
                dp[idx1] = 1;
                for (int idx2 = 0; idx2 < idx1; idx2++) {
                    if (arr[idx1] > arr[idx2] && dp[idx1] <= dp[idx2]) {
                        dp[idx1] = 1 + dp[idx2];
                        maxLen = Math.max(maxLen, dp[idx1]);
                    }
                }
            }
            return arr.length - maxLen;
        }

        public static void main(String[] args) {
            MinDelForSorted minDelForSorted = new MinDelForSorted();
            int[] nums = {4, 2, 3, 6, 10, 1, 12};
            System.out.println("BruteForce: " + minDelForSorted.findMinDel(nums));
            System.out.println("Tabular: " + minDelForSorted.findMilDelTabular(nums));
            nums = new int[]{-4, 10, 3, 7, 15};
            System.out.println("BruteForce: " + minDelForSorted.findMinDel(nums));
            System.out.println("Tabular: " + minDelForSorted.findMilDelTabular(nums));
            nums = new int[]{3, 2, 1, 0};
            System.out.println("BruteForce: " + minDelForSorted.findMinDel(nums));
            System.out.println("Tabular: " + minDelForSorted.findMinDel(nums));
        }
    }

    /*Given a sequence, find the length of its longest repeating subsequence (LRS). A repeating subsequence will be the one that appears at least twice in the original sequence and is not overlapping (i.e. none of the corresponding characters in the repeating subsequences have the same index).*/
    static class LongestRepeatingSubsequence {
        public Integer findLRSBruteForce(String str) {
            return findLRSBruteForce(str, 0, 0);
        }

        public Integer findLRSBruteForce(String str, int idx1, int idx2) {
            //base case
            if (idx1 == str.length() || idx2 == str.length()) {
                return 0;
            }

            // recursive
            if (idx1 != idx2 && str.charAt(idx1) == str.charAt(idx2)) {
                return 1 + findLRSBruteForce(str, idx1 + 1, idx2 + 1);
            }
            int len1 = findLRSBruteForce(str, idx1, idx2 + 1);
            int len2 = findLRSBruteForce(str, idx1 + 1, idx2);
            return Math.max(len1, len2);
        }

        public Integer findLRS(String str) {
            Integer[][] dp = new Integer[str.length() + 1][str.length() + 1];
            for (int idx1 = 0; idx1 <= str.length(); idx1++) {
                for (int idx2 = 0; idx2 <= str.length(); idx2++) {
                    dp[idx1][idx2] = 0;
                }
            }
            int maxLen = 0;
            for (int idx1 = 1; idx1 <= str.length(); idx1++) {
                for (int idx2 = 1; idx2 <= str.length(); idx2++) {
                    if (idx1 != idx2 && str.charAt(idx1) == str.charAt(idx2)) {
                        dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1];
                    } else {
                        dp[idx1][idx2] = Math.max(dp[idx1][idx2 - 2], dp[idx1 - 1][idx2]);
                    }
                    maxLen = Math.max(maxLen, dp[idx1][idx2]);
                }
            }
            return maxLen;
        }

        public static void main(String[] args) {
            LongestRepeatingSubsequence lrs = new LongestRepeatingSubsequence();
            String str = "tomorrow";
            System.out.println("BruteForce: " + lrs.findLRSBruteForce(str));
            System.out.println("Tabular: " + lrs.findLRSBruteForce(str));
            str = "aabdbcec";
            System.out.println("BruteForce: " + lrs.findLRSBruteForce(str));
            System.out.println("Tabular: " + lrs.findLRSBruteForce(str));
            str = "fmff";
            System.out.println("BruteForce: " + lrs.findLRSBruteForce(str));
            System.out.println("Tabular: " + lrs.findLRSBruteForce(str));
        }
    }

    /*Given a string and a pattern, write a method to count the number of times the pattern appears in the string as a subsequence.*/
    static class SubseqPatternMatch {
        public Integer countPatternBruteForce(String text, String pattern) {
            return countPatternBruteForce(text, pattern, 0, 0);
        }

        public Integer countPatternBruteForce(String text, String pattern, int index, int patternIndex) {
            // base case
            if (index <= text.length() && patternIndex == pattern.length()) {
                return 1;
            }
            if (index == text.length()) {
                return 0;
            }
            // recursive
            int cnt = 0;
            if (text.charAt(index) == pattern.charAt(patternIndex)) {
                cnt = countPatternBruteForce(text, pattern, index + 1, patternIndex + 1);
            }
            cnt = cnt + countPatternBruteForce(text, pattern, index + 1, patternIndex);
            return cnt;
        }

        public Integer countPatternTabular(String text, String pattern) {
            if (text == null || pattern == null) {
                return 0;
            }
            if (text.length() < pattern.length()) {
                return 0;
            }

            Integer[][] dp = new Integer[text.length() + 1][pattern.length() + 1];
            for (int idx = 0; idx <= text.length(); idx++) {
                Arrays.fill(dp[idx], 0);
                dp[idx][0] = 1; // if empty pattern then we can have 1 match from text which is empty string
            }
            for (int idx1 = 1; idx1 <= text.length(); idx1++) {
                for (int idx2 = 1; idx2 <= pattern.length(); idx2++) {
                    int cnt = 0;
                    if (text.charAt(idx1 - 1) == pattern.charAt(idx2 - 1)) {
                        cnt = dp[idx1 - 1][idx2 - 1];
                    }
                    cnt = cnt + dp[idx1 - 1][idx2];
                    dp[idx1][idx2] = cnt;
                }
            }
            return dp[text.length()][pattern.length()];
        }

        public static void main(String[] args) {
            SubseqPatternMatch spm = new SubseqPatternMatch();
            String text = "baxmx", pattern = "ax";
            System.out.println("BruteForce: " + spm.countPatternBruteForce(text, pattern));
            System.out.println("Tabular: " + spm.countPatternTabular(text, pattern));
            text = "tomorrow";
            pattern = "tor";
            System.out.println("BruteForce: " + spm.countPatternBruteForce(text, pattern));
            System.out.println("Tabular: " + spm.countPatternTabular(text, pattern));
        }
    }

    /*Given a number sequence, find the length of its Longest Bitonic Subsequence (LBS). A subsequence is considered bitonic if it is monotonically increasing and then monotonically decreasing.*/
    static class LongestBitonicSeq {
        public Integer longestDecrSeqFwdBruteForce(int[] arr, int index, int preIndex) {
            //base case
            if (index == arr.length) {
                return 0;
            }
            // recursive case
            int len1 = 0;
            if (preIndex == -1 || arr[preIndex] > arr[index]) {
                len1 = 1 + longestDecrSeqFwdBruteForce(arr, index + 1, index);
            }
            int len2 = longestDecrSeqFwdBruteForce(arr, index + 1, index);
            return Math.max(len1, len2);
        }

        public Integer longestDecrSeqBwdBruteForce(int[] arr, int index, int preIndex) {
            //base case
            if (index < 0) {
                return 0;
            }
            // recursive case
            int len1 = 0;
            if (preIndex == -1 || arr[preIndex] > arr[index]) {
                len1 = 1 + longestDecrSeqBwdBruteForce(arr, index - 1, index);
            }
            int len2 = longestDecrSeqBwdBruteForce(arr, index - 1, index);
            return Math.max(len1, len2);
        }

        public Integer findLongestBitonicSeq(int[] arr) {
            int maxLen = 0;
            for (int idx = 0; idx < arr.length; idx++) {
                int len1 = longestDecrSeqFwdBruteForce(arr, idx, -1);
                int len2 = longestDecrSeqBwdBruteForce(arr, idx, -1);
                maxLen = Math.max(maxLen, len1 + len2 - 1);
            }
            return maxLen;
        }

        public Integer findLongestBitonicSeqTabular(int[] arr) {
            Integer[] dpFwd = new Integer[arr.length];
            for (int idx1 = 0; idx1 < arr.length; idx1++) {
                dpFwd[idx1] = 1; // at every element of arr min len of longest increasing seq is 1
                for (int idx2 = idx1 - 1; idx2 > 0; idx2--) {
                    if (arr[idx2] < arr[idx1]) {
                        dpFwd[idx1] = Math.max(dpFwd[idx1], dpFwd[idx2] + 1);
                    }
                }
            }
            Integer[] dpBwd = new Integer[arr.length];
            for (int idx1 = arr.length - 1; idx1 >= 0; idx1--) {
                dpBwd[idx1] = 1;
                for (int idx2 = idx1 + 1; idx2 < arr.length; idx2++) {
                    if (arr[idx1] > arr[idx2]) {
                        dpBwd[idx1] = Math.max(dpBwd[idx1], dpBwd[idx2] + 1);
                    }
                }
            }
            int maxLen = 0;
            for (int idx = 0; idx < arr.length; idx++) {
                maxLen = Math.max(maxLen, (dpFwd[idx] + dpBwd[idx]));
            }
            return maxLen - 1;
        }

        public static void main(String[] args) {
            LongestBitonicSeq lbs = new LongestBitonicSeq();
            int[] nums = {4, 2, 3, 6, 10, 1, 12};
            System.out.println("BruteForce: " + lbs.findLongestBitonicSeq(nums));
            System.out.println("Tabular: " + lbs.findLongestBitonicSeqTabular(nums));
            nums = new int[]{4, 2, 5, 9, 7, 6, 10, 3, 1};
            System.out.println("BruteForce: " + lbs.findLongestBitonicSeq(nums));
            System.out.println("Tabular: " + lbs.findLongestBitonicSeqTabular(nums));
        }
    }

    /*Given a number sequence, find the length of its Longest Alternating Subsequence (LAS). A subsequence is considered alternating if its elements are in alternating order.*/
    static class LongestAlternatingSeq {
        public Integer findSeqBruteForce(int[] arr) {
            return Math.max(findSeqBruteForce(arr, 0, -1, true), findSeqBruteForce(arr, 0, -1, false));
        }

        public Integer findSeqBruteForce(int[] arr, int index, int prevIndex, boolean isAsc) {
            //base
            if (index == arr.length) {
                return 0;
            }
            // recursive
            int len1 = 0;
            if (isAsc) {
                if (prevIndex == -1 || arr[index] > arr[prevIndex]) {
                    len1 = 1 + findSeqBruteForce(arr, index + 1, index, !isAsc);
                }
            } else {
                if (prevIndex == -1 || arr[index] < arr[prevIndex]) {
                    len1 = 1 + findSeqBruteForce(arr, index + 1, index, !isAsc);
                }
            }
            int len2 = findSeqBruteForce(arr, index + 1, index, isAsc);
            return Math.max(len1, len2);
        }

        public Integer findSeqTabular(int[] arr) {
            //[][0] = ascending
            //[][1] = descending
            int[][] dp = new int[arr.length][2];
            int maxLen = 0;
            for (int i = 0; i < arr.length; i++) {
                dp[i][0] = dp[i][1] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[i] > arr[j]) { // going desc to  asc
                        dp[i][0] = Math.max(dp[i][0], 1 + dp[j][1]);
                        maxLen = Math.max(dp[i][0], maxLen);
                    } else if (arr[i] < arr[j]) { // going asc to desc
                        dp[i][1] = Math.max(dp[i][1], 1 + dp[j][0]);
                        maxLen = Math.max(dp[i][1], maxLen);
                    }
                }
            }
            return maxLen;
        }

        public static void main(String[] args) {
            LongestAlternatingSeq las = new LongestAlternatingSeq();
            int[] nums = {1, 2, 3, 4};
            System.out.println("BruteForce: " + las.findSeqBruteForce(nums));
            System.out.println("Tabular: " + las.findSeqTabular(nums));
            nums = new int[]{3, 2, 1, 4};
            System.out.println("BruteForce: " + las.findSeqBruteForce(nums));
            System.out.println("Tabular: " + las.findSeqTabular(nums));
            nums = new int[]{1, 3, 2, 4};
            System.out.println("BruteForce: " + las.findSeqBruteForce(nums));
            System.out.println("Tabular: " + las.findSeqTabular(nums));
        }
    }

    /*Given strings s1 and s2, we need to transform s1 into s2 by deleting, inserting, or replacing characters. Write a function to calculate the count of the minimum number of edit operations.*/
    static class EditDistance {
        public Integer findMinOperationBruteForce(String str1, String str2) {
            return findMinOperationBruteForce(str1, str2, 0, 0);
        }

        public Integer findMinOperationBruteForce(String str1, String str2, int idx1, int idx2) {
            //base
            if (idx1 == str1.length()) {
                return str2.length() - idx2;
            }
            if (idx2 == str2.length()) {
                return str1.length() - idx1;
            }
            //recursive
            if (str1.charAt(idx1) == str2.charAt(idx2)) {
                return findMinOperationBruteForce(str1, str2, idx1 + 1, idx2 + 1);
            }
            int cnt1 = 1 + findMinOperationBruteForce(str1, str2, idx1 + 1, idx2); // deletion
            int cnt2 = 1 + findMinOperationBruteForce(str1, str2, idx1, idx2 + 1); // insertion
            int cnt3 = 1 + findMinOperationBruteForce(str1, str2, idx1 + 1, idx2 + 1); // replacement
            // we need to find out min replacement
            return Math.min(cnt1, Math.min(cnt2, cnt3));
        }

        public Integer findMinOperationTabular(String str1, String str2) {
            Integer[][] dp = new Integer[str1.length() + 1][str2.length() + 1];
            for (int idx = 0; idx < str2.length(); idx++) {
                dp[0][idx] = idx;
            }
            for (int idx = 0; idx < str1.length(); idx++) {
                dp[idx][0] = idx;
            }
            for (int idx1 = 1; idx1 < str1.length(); idx1++) {
                for (int idx2 = 1; idx2 < str2.length(); idx2++) {
                    if (str1.charAt(idx1) == str2.charAt(idx1)) {
                        dp[idx1][idx2] = dp[idx1 - 1][idx2 - 1];
                    } else {
                        dp[idx1][idx2] = 1 + Math.min(dp[idx1 - 1][idx2], Math.min(dp[idx1 - 1][idx2 - 1], dp[idx1][idx2 - 1]));
                    }
                }
            }
            return dp[str1.length()][str2.length()];
        }

        public static void main(String[] args) {
            EditDistance editDistance = new EditDistance();
            String str1, str2;
            str1 = "bat";
            str2 = "but";
            System.out.println("BruteForce: " + editDistance.findMinOperationBruteForce(str1, str2));
            System.out.println("Tabular: " + editDistance.findMinOperationBruteForce(str1, str2));
            str1 = "abdca";
            str2 = "cbda";
            System.out.println("BruteForce: " + editDistance.findMinOperationBruteForce(str1, str2));
            System.out.println("Tabular: " + editDistance.findMinOperationBruteForce(str1, str2));
            str1 = "passpot";
            str2 = "ppsspqrt";
            System.out.println("BruteForce: " + editDistance.findMinOperationBruteForce(str1, str2));
            System.out.println("Tabular: " + editDistance.findMinOperationBruteForce(str1, str2));
        }
    }

    /*Give three strings ‘m’, ‘n’, and ‘p’, write a method to find out if ‘p’ has been formed by interleaving ‘m’ and ‘n’. ‘p’ would be considered interleaving ‘m’ and ‘n’ if it contains all the letters from ‘m’ and ‘n’ and the order of letters is preserved too.*/
    static class StringInterleaving {
        public boolean isInterleaving(String m, String n, String p) {
            return isInterleaving(m, n, p, 0, 0, 0);
        }

        public boolean isInterleaving(String m, String n, String p, int mIndex, int nIndex, int pIndex) {
            //basecase
            if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length()) {
                return true;
            }
            if (pIndex == p.length()) {
                return false;
            }
            // recursive
            boolean res1 = false, res2 = false;
            if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex)) {
                res1 = isInterleaving(m, n, p, mIndex + 1, nIndex, pIndex + 1);
            }
            if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex)) {
                res2 = isInterleaving(m, n, p, mIndex, nIndex + 1, pIndex + 1);
            }
            return res1 || res2;
        }

        public boolean isInterleavingTabular(String m, String n, String p) {
            boolean[][] dp = new boolean[m.length() + 1][n.length() + 1];
            if (m.length() + n.length() > p.length()) {
                return false;
            }
            for (int mIndex = 0; mIndex <= m.length(); mIndex++) {
                for (int nIndex = 0; nIndex <= n.length(); nIndex++) {
                    if (mIndex == 0 && nIndex == 0) {
                        dp[mIndex][nIndex] = true;
                    } else if (mIndex == 0 && n.charAt(nIndex - 1) == p.charAt(mIndex + nIndex - 1)) {
                        dp[mIndex][nIndex] = dp[mIndex][nIndex - 1];
                    } else if (nIndex == 0 && m.charAt(mIndex - 1) == p.charAt(mIndex + nIndex - 1)) {
                        dp[mIndex][nIndex] = dp[mIndex - 1][nIndex];
                    } else {
                        if (mIndex > 0 && m.charAt(mIndex - 1) == p.charAt(mIndex + nIndex - 1)) {
                            dp[mIndex][nIndex] = dp[mIndex - 1][nIndex];
                        }
                        if (nIndex > 0 && n.charAt(nIndex - 1) == p.charAt(mIndex + nIndex - 1)) {
                            dp[mIndex][nIndex] = dp[mIndex][nIndex] || dp[mIndex][nIndex - 1];
                        }
                    }
                }
            }
            return dp[m.length()][n.length()];
        }

        public static void main(String[] args) {
            StringInterleaving stringInterleaving = new StringInterleaving();
            String m = "abd", n = "cef", p = "abcdef";
            System.out.println("BruteForce: " + stringInterleaving.isInterleaving(m, n, p));
            System.out.println("Tabular: " + stringInterleaving.isInterleavingTabular(m, n, p));
            m = "abd";
            n = "cef";
            p = "adcbef";
            System.out.println("BruteForce: " + stringInterleaving.isInterleaving(m, n, p));
            System.out.println("Tabular: " + stringInterleaving.isInterleavingTabular(m, n, p));
            m = "abc";
            n = "def";
            p = "abdccf";
            System.out.println("BruteForce: " + stringInterleaving.isInterleaving(m, n, p));
            System.out.println("Tabular: " + stringInterleaving.isInterleavingTabular(m, n, p));
            m = "abcdef";
            n = "mnop";
            p = "mnaobcdepf";
            System.out.println("BruteForce: " + stringInterleaving.isInterleaving(m, n, p));
            System.out.println("Tabular: " + stringInterleaving.isInterleavingTabular(m, n, p));
        }

    }

}
