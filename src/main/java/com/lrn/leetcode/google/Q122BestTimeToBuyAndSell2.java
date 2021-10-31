package com.lrn.leetcode.google;

public class Q122BestTimeToBuyAndSell2 {
    /*
    * pd: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    * assm:
    * appr:
    * test cases:
    *
    * */

    // using sliding window
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int ws=0, totalprofit=0, wprofit=0, cprofit=0;
        for(int we=1; we < prices.length; we++) {
            if((prices[we] - prices[ws]) > wprofit)  {
                wprofit = Math.max(prices[we] - prices[ws], wprofit);
            } else {// buyprice < sellprice so shrink window
                totalprofit = totalprofit + wprofit;
                ws=we;
                wprofit = 0;
            }
        }
        return totalprofit + wprofit;
    }

    public static void main(String[] args) {
        Q122BestTimeToBuyAndSell2 sol = new Q122BestTimeToBuyAndSell2();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }


}
