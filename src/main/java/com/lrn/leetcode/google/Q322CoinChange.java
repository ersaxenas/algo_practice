package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q322CoinChange {
    /* https://leetcode.com/problems/coin-change/
    * pd:You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.
    * assm: non fractions, non null elem, 1 <= coin <= 100, 1 <= amount < INT.MAX, best time sol.
    * appr: sort count array.
    *       start from last
    * test cases:
    * Input: coins = [1,2,5], amount = 11 Output: 3
    * */

    // sol: https://leetcode.com/problems/coin-change/discuss/77368/*Java*-Both-iterative-and-recursive-solutions-with-explanations

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) {
            return 0;
        }
        Arrays.sort(coins);
       return backtrack(coins, amount, coins.length-1);
    }

    public int backtrack(int[] coins, int amount, int idx) {
        if(amount ==0) {
            return 0;
        }
        if(amount > 0 && idx >= 0) {
            int maxCoins = amount / coins[idx];
            int minCoins = Integer.MAX_VALUE;
            for(int nc=0; nc <= maxCoins; nc++) {
                int res = backtrack(coins, amount - (nc*coins[idx]), idx-1);
                if(res != -1) {
                    minCoins = Math.min(minCoins, res+nc);
                }
            }
            return (minCoins == Integer.MAX_VALUE)? -1: minCoins;
        }
       return -1;
    }

    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int amt=0; amt <=amount; amt++) { // from amount 0 to given amount
            for(int coin: coins) { // for each coin
                if(amt >= coin) { // if current amount is >= coin value
                    dp[amt] = Math.min(dp[amt], dp[amt-coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
   // BFS also a good sol.
    public int coinChange3(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];

        queue.offer(amount);
        visited[amount] = true;
        int currLevel = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(queue);
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                for (int coin : coins) {
                    int child = curr - coin;
                    if (child == 0) {
                        return currLevel;
                    } else if (child > 0 && !visited[child]) {
                        queue.add(child);
                        visited[child] = true;
                    }
                }
            }
            currLevel++;
        }

        return -1;
    }
    public static void main(String[] args) {
        Q322CoinChange sol = new Q322CoinChange();
        System.out.println(sol.coinChange3(new int[]{1, 2, 5}, 11));
        System.out.println(sol.coinChange2(new int[]{186, 419, 83, 408}, 6249));
        System.out.println(sol.coinChange2(new int[]{2}, 11));
    }

}
