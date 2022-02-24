package com.lrn.leetcode.google;

public class Q188BestTimeToBuyAndSellStock4 {
    /*
    * pd: Say you have an array for which the i-th element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most k transactions.
Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
Example 1:
Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:
Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
    * assm: non null elem, arrays len < 10000, best time sol
    * appr: dp
    *    https://www.youtube.com/watch?v=oDhu5uGq_ic
    *    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54113/A-Concise-DP-Solution-in-Java
    *
    * */

    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int days = prices.length;
        int transaction = k;
        if(transaction > days) {
            return calProfit(prices);
        }
        int[][] profit = new int[transaction + 1][days];
        for (int trn = 1; trn <= transaction; trn++) {
            int maxDiff = -prices[0];
            for (int day = 1; day < days; day++) {
                 profit[trn][day] = Math.max(
                         profit[trn][day-1], // not doing any transaction on this day == profit same as previous day
                         prices[day] + maxDiff // selling today
                );
                // calculate max diff
               maxDiff = Math.max(maxDiff, profit[trn-1][day] - prices[day]);
            }
        }
        return profit[transaction][days-1];
    }

    private int calProfit(int[] prices) {
        int profit = 0;
        for(int idx=1; idx<prices.length; idx++) {
            if(prices[idx] > prices[idx-1]) {
                profit = profit + (prices[idx] - prices[idx-1]);
            }
        }
        return profit;
    }

}
