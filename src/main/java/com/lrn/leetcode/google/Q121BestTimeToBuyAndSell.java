package com.lrn.leetcode.google;

public class Q121BestTimeToBuyAndSell {
    /*
    * pd: Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
    * assm : non null elem, best time sol, arr len < 1000
    * appr: for each sell day x you want to buy at lowest price from day 1 to x-1
    * Ex. [7, 1, 5, 3, 6, 4]
    * day 1: buy option [7], sell option [] - can by at 7 but cannot sell since we are allowed to perform only 1 transaction a day
    * day 2: buy option [7], sell option [1] - can by at 7 and sell at 1 which will result in loss
    * day 3: buy option [7,1], sell option [5] - can by at 1 (min of buy options) and sell at 5 which will result in profit
    * day 4: buy option [7,1,5], sell option [3] - can by at 1 (min of buy options) and sell at 3 which will result in profit
    *   .
    * day n : buy option [0 to n-1], sell option [n]
    * Test cases:
    * [7, 1, 5, 3, 6, 4] ans 5
    * */

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <2) {
            return 0;
        }
        int min=prices[0], profit=0;
        for(int idx=1; idx<prices.length; idx++) {
            profit = Math.max(profit, prices[idx]-prices[idx-1]);
            min = Math.min(min,prices[idx]);
        }
       return profit;
    }

    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length <2) {
            return 0;
        }
        int pb=0, profit=0;
        for(int ps=1; ps<prices.length; ps++) {
            int pr= prices[ps] - prices[pb];
            if(pr>=0) {
                profit = Math.max(profit, pr);
            } else {
               pb = ps;
            }
        }
       return profit;
    }

    public static void main(String[] args) {
        Q121BestTimeToBuyAndSell sol = new Q121BestTimeToBuyAndSell();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    
}
