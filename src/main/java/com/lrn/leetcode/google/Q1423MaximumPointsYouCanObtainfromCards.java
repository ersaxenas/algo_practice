package com.lrn.leetcode.google;

public class Q1423MaximumPointsYouCanObtainfromCards {
    /*
    * pd: There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
Your score is the sum of the points of the cards you have taken.
Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
    * assm: max card < 1000, k < maxcard, best time sol
    * appr: sliding window
    * test cases:
    * */

    public int maxScore(int[] cardPoints, int k) {
        int sum=0, maxSum = 0;
        int low=0, hi = cardPoints.length-1;
        while(low <k && low < cardPoints.length) {
            sum = sum + cardPoints[low];
            low++;
        }
        maxSum = sum;
        if(k >= cardPoints.length) {
            return maxSum;
        }
        low--;
        while(low >= 0) {
          sum = sum - cardPoints[low--] + cardPoints[hi--];
          maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }



    public static void main(String[] args) {
        Q1423MaximumPointsYouCanObtainfromCards sol = new Q1423MaximumPointsYouCanObtainfromCards();
        System.out.println(sol.maxScore(new int[]{1,79,80,1,1,1,200,1}, 3));
        System.out.println(sol.maxScore(new int[]{9,7,7,9,7,7,9}, 3));
        System.out.println(sol.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
    }


}
