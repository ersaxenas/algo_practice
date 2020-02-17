package com.lrn.educative.gci.knapsack;

public class WorkDpUtils {
    public static void print2D(boolean mat[][]) {
        System.out.println("---------------------------------------");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(((mat[i][j]) ? "T" : "F") + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

    public static void print2D(Integer mat[][]) {
        System.out.println("---------------------------------------");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(((mat[i][j] != null) ? mat[i][j] : "x") + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

    /*Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack which has a capacity ‘C’.
    The goal is to get the maximum profit out of the items in the knapsack.
    Each item can only be selected once, as we don’t have multiple quantities of any item.
    */

    static class Knapsack1 {
        public Integer solveKnapsack(int[] profits, int[] weights, int capacity) {
            Integer[][] cache = new Integer[profits.length][capacity + 1];
            return solveKnapsackRecursive(profits, weights, 0, capacity, cache);
        }

        public Integer solveKnapsackRecursive(int[] profits, int[] weights, int currentIndex, int capacity, Integer[][] cache) {
            if (currentIndex >= profits.length || capacity <= 0) {
                return 0;
            }
            if (cache[currentIndex][capacity] != null) {
                return cache[currentIndex][capacity];
            }
            // select element
            Integer profit1 = 0;
            if (capacity - weights[currentIndex] >= 0) {
                profit1 = profits[currentIndex] + solveKnapsackRecursive(profits, weights, currentIndex + 1, capacity - weights[currentIndex], cache);
            }
            // skip element
            Integer profit2 = solveKnapsackRecursive(profits, weights, currentIndex + 1, capacity, cache);
            cache[currentIndex][capacity] = Math.max(profit1, profit2);
            return cache[currentIndex][capacity];
        }

        public Integer solveKnapsack2(int[] profits, int[] weights, int capacity) {
            int col = capacity + 1;
            int row = weights.length;
            int[][] dpArr = new int[row][col]; // row index = weight and col = capacity
            // fill first col with 0 as capacity will be zero
            for (int idx = 0; idx < row; idx++) {
                dpArr[idx][0] = 0;
            }
            // fill first row with weight[0] if it is less then capacity else 0
            for (int idx = 1; idx < col; idx++) {
                if (weights[0] <= idx) {
                    dpArr[0][idx] = profits[0];
                } else {
                    dpArr[0][idx] = 0;
                }
            }
            // now we have first row and col filled with profit data.
            for (int idx1 = 1; idx1 < row; idx1++) { // for each element == row = element in [a,b,c,d] start from index 1
                for (int idx2 = 1; idx2 < col; idx2++) { // for capacity from 1 to N=7
                    int wt = weights[idx1];
                    int profit1 = 0;
                    if (wt <= idx2) {
                        profit1 = profits[idx1] + dpArr[idx1 - 1][idx2 - wt];
                    }
                    int profit2 = dpArr[idx1 - 1][idx2];
                    //for [weight][profit]
                    dpArr[idx1][idx2] = Math.max(profit1, profit2);
                }
            }
            printSelectedElements(profits, weights, capacity, dpArr);
            return dpArr[row - 1][col - 1];
        }

        public Integer solveKnapsack3(int[] profits, int[] weights, int capacity) {
            int col = capacity + 1;
            int row = weights.length;
            int[] dpArr = new int[col]; // row index = weight and col = capacity
            // fill first row with weight[0] if it is less then capacity else 0
            for (int idx = 1; idx < col; idx++) {
                if (weights[0] <= idx) {
                    dpArr[idx] = profits[0];
                } else {
                    dpArr[idx] = 0;
                }
            }
            // now we have first row and col filled with profit data.
            for (int idx1 = 1; idx1 < row; idx1++) { // for each element == row = element in [a,b,c,d] start from index 1
                for (int idx2 = capacity; idx2 >= 0; idx2--) { // for capacity from 1 to N=7
                    int wt = weights[idx1];
                    int profit1 = 0;
                    if (wt <= idx2) {
                        profit1 = profits[idx1] + dpArr[idx2 - wt];
                    }
                    int profit2 = dpArr[idx2];
                    //for [weight][profit]
                    dpArr[idx2] = Math.max(profit1, profit2);
                }
            }
            //printSelectedElements(profits, weights, capacity, dpArr);
            return dpArr[col - 1];
        }

        public void printSelectedElements(int[] profits, int[] weights, int capacity, int[][] dpArr) {
            int row = weights.length - 1;
            int col = capacity;
            int totalProfit = dpArr[row][col];
            for (int idx = row; idx > 0; idx--) { // for each row starting from end till 1
                if (totalProfit != dpArr[idx - 1][col]) { // if not same as col above the current col
                    int wt = weights[idx];
                    System.out.println("wt = " + wt);
                    col = col - wt;
                    totalProfit = totalProfit - profits[idx];
                }
            }
            if (totalProfit != 0) {
                System.out.println("wt = " + weights[0]);
            }
        }

    }

    public static void main(String[] args) {
        Knapsack1 ks = new Knapsack1();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);

        maxProfit = ks.solveKnapsack2(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack2(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);

        maxProfit = ks.solveKnapsack3(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack3(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
    /*Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both subsets is equal.*/

    static class EqualSubsetSumPartition {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum = sum + num;
            }
            if (sum % 2 != 0) {
                return false;
            }
            //return canParRecursive1(nums,0,sum/2);
            //return canParRecursiveDpTopDown(nums, 0, sum / 2, new boolean[nums.length][(sum / 2) + 1]);
            return canParBottomToTop(nums);
        }

        public boolean canParRecursive1(int[] nums, int elemIndex, int sum) {

            // base exit
            if (sum == 0) {
                return true;
            }

            // guard condtion
            if (nums.length == 0 || elemIndex >= nums.length) {
                return false;
            }

            // include current element
            int currentElement = nums[elemIndex];
            if (currentElement <= sum) {
                int newsum = sum - currentElement;
                if (canParRecursive1(nums, elemIndex + 1, newsum)) {
                    return true;
                }
            }
            // skip current element
            return canParRecursive1(nums, elemIndex + 1, sum);
        }

        /*TOP BOTTOM*/
        public boolean canParRecursiveDpTopDown(int[] nums, int elemIndex, int sum, boolean[][] dp) {

            // base exit
            if (sum == 0) {
                return true;
            }

            // guard condition
            if (nums.length == 0 || elemIndex >= nums.length) {
                return false;
            }

            boolean result;
            int currentElem = nums[elemIndex];
            if (currentElem <= sum) {
                result = canParRecursiveDpTopDown(nums, elemIndex + 1, sum - currentElem, dp);
                if (result) {
                    dp[elemIndex][sum] = result;
                    return true;
                }
            }
            result = canParRecursiveDpTopDown(nums, elemIndex + 1, sum, dp);
            dp[elemIndex][sum] = result;
            return result;
        }

        /*BOTTOM TO TOP*/
        public boolean canParBottomToTop(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum = sum + num;
            }
            sum = sum / 2;
            int rows = nums.length; // represents elements from 0 to N
            int cols = sum + 1; // represents sums from 0 to sum
            boolean[][] dp = new boolean[rows][cols];
            for (int row = 0; row < rows; row++) {
                dp[row][0] = true; // for sum = 0 and empty set can be selected;
            }
            for (int col = 1; col < cols; col++) {
                if (nums[0] == col) {
                    dp[0][col] = true;
                } else {
                    dp[0][col] = false;
                }
            }
            // now we have data filled in row[0] and col[0]
            for (int row = 1; row < rows; row++) {
                for (int col = 1; col < cols; col++) { // remember col represent values of sum from 0 to sum
                    int currentElem = nums[row];
                    // include current element
                    if (currentElem <= col) {
                        int newSum = col - currentElem;
                        dp[row][col] = dp[row - 1][newSum];
                    } else { // exclude current element
                        dp[row][col] = dp[row - 1][col];
                    }
                }
            }
            return dp[rows - 1][cols - 1];
        }


        public static void main(String[] args) {
            EqualSubsetSumPartition equalSubsetSumPartition = new EqualSubsetSumPartition();
            int[] num = {1, 2, 3, 4};
            System.out.println(equalSubsetSumPartition.canPartition(num));
            num = new int[]{1, 1, 3, 4, 7};
            System.out.println(equalSubsetSumPartition.canPartition(num));
            num = new int[]{2, 3, 4, 6};
            System.out.println(equalSubsetSumPartition.canPartition(num));
        }
    }

    /*Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ‘S’.*/
    public static class SubsetSum {
        public boolean canPartitionSum(int[] nums, int S) {
            int rows = nums.length; // from 0 to N represents subset
            int cols = S + 1; // from 0 to S represent sum
            boolean[][] dp = new boolean[rows][cols];
            for (int row = 0; row < rows; row++) {
                // col 0 == sum == 0. since sum is 0 elements in the col 0 will be 0
                dp[row][0] = true;
            }
            for (int col = 1; col < cols; col++) { // row = 0
                // if sum == element then we have found the set because there can be only one element in the set.
                int currentElement = nums[0];
                int currentSum = col;
                if (currentElement == currentSum) {
                    dp[0][col] = true;
                } else {
                    dp[0][col] = false;
                }
            }
            // now rest of the metrix
            for (int row = 1; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int currentElement = nums[row];
                    int currentSum = col;
                    // include current element
                    if (dp[row - 1][currentSum]) {
                        dp[row][col] = dp[row - 1][currentSum];
                    } else if (currentElement <= currentSum) {
                        int newSum = currentSum - currentElement;
                        dp[row][col] = dp[row - 1][newSum];
                    }
                }
            }
            return dp[rows - 1][cols - 1];
        }

        public static void main(String[] args) {
            SubsetSum ss = new SubsetSum();
            int[] num = {1, 2, 3, 7};
            System.out.println(ss.canPartitionSum(num, 6));
            num = new int[]{1, 2, 7, 1, 5};
            System.out.println(ss.canPartitionSum(num, 10));
            num = new int[]{1, 3, 4, 8};
            System.out.println(ss.canPartitionSum(num, 6));
        }
    }

    /*Given a set of positive numbers, partition the set into two subsets with minimum difference between their subset sums.*/
    static class MinSubsetSumDiff {
        public Integer findPartition(int[] nums) {
            final Integer differenceBruteForce = findDifferenceBruteForce(nums, 0, 0, 0);
            return differenceBruteForce;
        }

        public Integer findPartition1(int[] nums) {
            int sum = 0;
            for (int idx = 0; idx < nums.length; idx++) {
                sum = sum + nums[idx];
            }
            Integer[][] dp = new Integer[nums.length][sum + 1];
            final Integer differenceBruteForce = findDifferenceDpTopBottom(nums, 0, 0, 0, dp);
            return differenceBruteForce;
        }

        public Integer findDifferenceBruteForce(int[] nums, int currentIndex, int sum1, int sum2) {

            // base exit
            if (currentIndex == nums.length) {
                return Math.abs(sum1 - sum2);
            }

            // recursion 1 -- include in set 1
            int diff1 = findDifferenceBruteForce(nums, currentIndex + 1, sum1 + nums[currentIndex], sum2);
            // recursion 1 -- include in set 1
            int diff2 = findDifferenceBruteForce(nums, currentIndex + 1, sum1, sum2 + nums[currentIndex]);

            return Math.min(diff1, diff2);
        }

        public Integer findDifferenceDpTopBottom(int[] nums, int currentIndex, int sum1, int sum2, Integer[][] dp) {

            // base exit
            if (currentIndex == nums.length) {
                return Math.abs(sum1 - sum2);
            }
            if (dp[currentIndex][sum1] != null) {
                return dp[currentIndex][sum1];
            }

            // recursion 1 -- include in set 1
            int diff1 = findDifferenceBruteForce(nums, currentIndex + 1, sum1 + nums[currentIndex], sum2);
            // recursion 1 -- include in set 1
            int diff2 = findDifferenceBruteForce(nums, currentIndex + 1, sum1, sum2 + nums[currentIndex]);

            int min = Math.min(diff1, diff2);
            dp[currentIndex][sum1] = min;
            return min;
        }

        public Integer findPartition2(int[] nums) {
            int sum = 0;
            for (int idx = 0; idx < nums.length; idx++) {
                sum = sum + nums[idx];
            }
            final Integer minSum = findMinDifferenceBottomTop(nums, sum / 2);
            int minDiff = (sum - minSum) - minSum;
            return minDiff;
        }

        public Integer findMinDifferenceBottomTop(int[] nums, int sum) {
            int rows = nums.length; // row represent element in nums
            int cols = sum + 1; // col represents sum
            boolean dp[][] = new boolean[rows][cols];
            /*
              0 1 2 = sum = col
            [ T x x ] 0 index = row
            [ T x x ] 1
            [ T x x ] 2
             */
            // for sum = 0
            for (int row = 0; row < rows; row++) {
                dp[row][0] = true;
            }
            // for row =0 and sum 0 to N
            for (int col = 1; col < cols; col++) {
                if (nums[0] == col) {
                    dp[0][col] = true;
                } else {
                    dp[0][col] = false;
                }
            }
             /*
              0 1 2 = sum = col
            [ T T F ] 0 index = row
            [ T x x ] 1
            [ T x x ] 2
             */
            for (int row = 1; row < rows; row++) {
                for (int col = 1; col < cols; col++) {
                    int currentElement = nums[row];
                    int S = col;
                    if (dp[row - 1][S]) {
                        dp[row][col] = dp[row - 1][S];
                    } else if (currentElement <= S) {
                        int newSum = S - currentElement;
                        dp[row][col] = dp[row - 1][newSum];
                    }
                }
            }

            int row = rows - 1;
            int col = cols - 1;
            while (!dp[row][col]) {
                if (--col < 0) {
                    col = 0;
                    row--;
                }
                if (row < 0) {
                    break;
                }
            }
            return col;
        }

        public static void main(String[] args) {
            MinSubsetSumDiff minSubsetSumDiff = new MinSubsetSumDiff();
            int[] num = {1, 2, 3, 9};
            System.out.println(minSubsetSumDiff.findPartition2(num));
            num = new int[]{1, 2, 7, 1, 5};
            System.out.println(minSubsetSumDiff.findPartition2(num));
            num = new int[]{1, 3, 100, 4};
            System.out.println(minSubsetSumDiff.findPartition2(num));
        }
    }

    /*Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.*/
    static class SubsetSumCount {
        public Integer findSubSets1(int[] nums, int S) {
            return findSubSetsRecBruteForce(nums, 0, S);
        }

        public Integer findSubSets2(int[] nums, int S) {

            Integer[][] dp = new Integer[nums.length][S + 1];
            final Integer result = findSubSetsRecTopDown(nums, 0, S, dp);
            print2D(dp);
            return result;
        }

        public Integer findSubSetsRecBruteForce(int[] nums, int currentIndex, int sum) {

            if (sum == 0) {
                return 1;
            }
            if (currentIndex >= nums.length || sum < 0) {
                return 0;
            }

            int currentElement = nums[currentIndex];
            // include
            int count1 = 0;
            if (currentElement <= sum) {
                count1 = findSubSetsRecBruteForce(nums, currentIndex + 1, sum - currentElement);
            }
            //exclude
            int count2 = findSubSetsRecBruteForce(nums, currentIndex + 1, sum);
            return count1 + count2;
        }

        public Integer findSubSetsRecTopDown(int[] nums, int currentIndex, int sum, Integer[][] dp) {

            if (sum == 0) {
                return 1;
            }
            if (currentIndex >= nums.length || sum < 0) {
                return 0;
            }
            if (dp[currentIndex][sum] != null) {
                return dp[currentIndex][sum];
            }

            int currentElement = nums[currentIndex];
            // include
            int count1 = 0;
            if (currentElement <= sum) {
                count1 = findSubSetsRecTopDown(nums, currentIndex + 1, sum - currentElement, dp);
            }
            //exclude
            int count2 = findSubSetsRecTopDown(nums, currentIndex + 1, sum, dp);
            final int cnt = count1 + count2;
            dp[currentIndex][sum] = cnt;
            return cnt;
        }

        public Integer findSubsetsBottomUp(int[] nums, int S) {
            int rows = nums.length;
            int cols = S + 1;
            Integer[][] dp = new Integer[rows][cols];
            // first col
            for (int row = 0; row < rows; row++) {
                dp[row][0] = 1;
            }
            // for first row.
            for (int col = 1; col < cols; col++) {
                if (col == nums[0]) {
                    dp[0][col] = 1;
                } else {
                    dp[0][col] = 0;
                }
            }

            for (int row = 1; row < rows; row++) {
                for (int col = 1; col < cols; col++) {
                    int currentElem = nums[row];
                    int sum = col;
                    //exclude
                    dp[row][sum] = dp[row - 1][sum];
                    if (currentElem <= sum) {
                        dp[row][sum] = dp[row][sum] + dp[row - 1][sum - currentElem];
                    }
                }
            }
            return dp[rows - 1][cols - 1];
        }


        public static void main(String[] args) {
            SubsetSumCount subsetSumCount = new SubsetSumCount();
            int[] num = {1, 1, 2, 3};
            System.out.println(subsetSumCount.findSubsetsBottomUp(num, 4));
            System.out.println(subsetSumCount.findSubsetsBottomUp(num, 2));
            num = new int[]{1, 2, 7, 1, 5};
            System.out.println(subsetSumCount.findSubsetsBottomUp(num, 9));
        }
    }
    /*You are given a set of positive numbers and a target sum ‘S’. Each number should be assigned either a ‘+’ or ‘-’ sign. We need to find the total ways to assign symbols to make the sum of the numbers equal to the target ‘S’.*/
    static class SubSetSumCount2{
        public Integer findSubSets1(int[] nums, int S) {
            int sum =0;
            for(int idx=0; idx<nums.length; idx++) {
                sum = sum + nums[idx];
            }

            return findSubSetsBruteForce(nums,0,(S+sum)/2);
        }
        public Integer findSubSetsBruteForce(int[] nums, int currentIndex, int sum)  {

            if(sum == 0) {
                return 1;
            }

            if(currentIndex == nums.length || sum < 0) {
                return 0;
            }

            int currentNum = nums[currentIndex];
            // include
            int count1 = findSubSetsBruteForce(nums,currentIndex+1,sum - currentNum);

            // exclude
            int count2 = findSubSetsBruteForce(nums,currentIndex+1, sum );

            return count1 + count2;
        }

        public Integer findSubSets2(int[] nums, int S) {
            int sum =0;
            for(int idx=0; idx<nums.length; idx++) {
                sum = sum + nums[idx];
            }
            int targetSum = (S+sum)/2;
            return findSubSetsDPTopDown(nums,0,targetSum, new Integer[nums.length][targetSum+1]);
        }

        public Integer findSubSetsDPTopDown(int[] nums, int currentIndex, int sum, Integer[][] dp)  {

            if(sum == 0) {
                return 1;
            }

            if(currentIndex == nums.length || sum < 0) {
                return 0;
            }
            if(dp[currentIndex][sum] != null) {
                return dp[currentIndex][sum];
            }

            int currentNum = nums[currentIndex];
            // include
            int count1 = findSubSetsBruteForce(nums,currentIndex+1,sum - currentNum);

            // exclude
            int count2 = findSubSetsBruteForce(nums,currentIndex+1, sum );

            int res = count1 + count2;
            dp[currentIndex][sum] = res;
            return res;
        }

        public Integer findSubSets3(int[] nums, int S) {
            int sum =0;
            for(int idx=0; idx<nums.length; idx++) {
                sum = sum + nums[idx];
            }
            int targetSum = (S+sum)/2;
            return findSubSetDpBottomToTop(nums,targetSum);
        }
        public Integer findSubSetDpBottomToTop(int[] nums, int targetSum) {
           Integer[][] dp = new Integer[nums.length][targetSum+1];
           for(int row=0; row<nums.length;row++) {
               dp[row][0] = 1;
           }
           for(int sum=1; sum<=targetSum; sum++) {
               if(sum == nums[0]) {
                   dp[0][sum] = 1;
               } else {
                   dp[0][sum] = 0;
               }
           }

           for(int idx=1; idx<nums.length; idx++) {
               for(int sum =1;sum<=targetSum;sum++) {
                   dp[idx][sum] = dp[idx-1][sum];
                   if(nums[idx] <= sum) {
                       dp[idx][sum] = dp[idx][sum] + dp[idx-1][sum - nums[idx]];
                   }
               }
           }

           return dp[nums.length-1][targetSum];
        }

        public Integer findSubSets4(int[] nums, int S) {
            int sum =0;
            for(int idx=0; idx<nums.length; idx++) {
                sum = sum + nums[idx];
            }
            int targetSum = (S+sum)/2;
            return findSubSetDpBottomToTop2(nums,targetSum);
        }

        public Integer findSubSetDpBottomToTop2(int[] nums, int targetSum) {
           Integer[] dp = new Integer[targetSum+1];
           dp[0] = 1;
           for(int sum=1; sum<=targetSum; sum++) {
               if(sum == nums[0]) {
                   dp[sum] = 1;
               } else {
                   dp[sum] = 0;
               }
           }

           for(int idx=1; idx<nums.length; idx++) {
               for(int sum =targetSum;sum >= 0;sum--) {
                   if(nums[idx] <= sum) {
                       dp[sum] = dp[sum] + dp[sum - nums[idx]];
                   }
               }
           }

           return dp[targetSum];
        }


        public static void main(String[] args) {
            SubSetSumCount2 subSetSumCount2 = new SubSetSumCount2();
            int[] num = {1, 1, 2, 3};
            System.out.println(subSetSumCount2.findSubSets4(num, 1));
            num = new int[]{1, 2, 7, 1};
            System.out.println(subSetSumCount2.findSubSets4(num, 9));
        }

    }
}
