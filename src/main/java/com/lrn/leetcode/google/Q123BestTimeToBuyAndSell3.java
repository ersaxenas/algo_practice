package com.lrn.leetcode.google;

public class Q123BestTimeToBuyAndSell3 {
    /*
    * pd: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
    * assm: prices len < 10000, 0 < prices < 1000, best time sol, best memory sol.
    * appr: sliding window : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/solution/
    * test cases:
    * Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
    * */

    public int maxProfit(int[] prices) {
        int lbuy=prices[0], N=prices.length-1, rsell=prices[N];
        int[] lprofit = new int[prices.length];
        int[] rprofit = new int[prices.length];
        for(int idx=1; idx < prices.length; idx++) {
            // for left side array idx take min buy price from elem seen in the window and sell price is at idx
            lbuy = Math.min(lbuy, prices[idx]); // min buy price till idx
            lprofit[idx] = Math.max(lprofit[idx-1], prices[idx] - lbuy); // Max (profit till now , sell prices at idx - min buy prices)
            // for right side array idx take max sell price so far seen and buy price is the new elem at [prices.len - idx]
            rsell = Math.max(rsell, prices[prices.length-idx]);// max sell prices till N - idx
            rprofit[N - idx] = Math.max(rprofit[N-idx+1],(rsell - prices[N-idx])); // Max (profit till now,  buy prices at N-idx - max sell prices
        }
        int maxprofit = 0;
        for(int idx=0; idx < lprofit.length; idx++) {
            maxprofit = Math.max(maxprofit, lprofit[idx] + ((idx == rprofit.length-1) ? 0 : rprofit[idx+1]));
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        Q123BestTimeToBuyAndSell3 sol = new Q123BestTimeToBuyAndSell3();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

}
