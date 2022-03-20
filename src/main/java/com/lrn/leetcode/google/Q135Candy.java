package com.lrn.leetcode.google;

public class Q135Candy {
    /*2022-03-16T20:02:51.594Z
    * pd: https://leetcode.com/problems/candy
    * assm:
    * appr:
    * Do it in two directions.
    *The first loop makes sure children with a higher rating get more candy than its left neighbor;
    * the second loop makes sure children with a higher rating get more candy than its right neighbor.
    * test cases:
    * */
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        candy[0]=1;
        for(int idx=1; idx < ratings.length; idx++) {
            candy[idx] = 1;
            if(ratings[idx] > ratings[idx-1] ) candy[idx] = candy[idx-1] +1;
        }
        for(int idx=ratings.length-1; idx>0; idx--) {
            if(ratings[idx-1] > ratings[idx]) {
                candy[idx-1] = Math.max(candy[idx-1], candy[idx]+1);
            }
        }
        int sum = 0;
        for(int num: candy) {
            sum = sum + num;
        }
        return sum;
    }

    public static void main(String[] args) {
        Q135Candy sol = new Q135Candy();
        System.out.println(sol.candy(new int[] {1,0,2}) == 5);
        System.out.println(sol.candy(new int[] {1,2,2}) == 4);
        System.out.println(sol.candy(new int[] {1,2,87,87,87,2,1}) == 13);
    }

}
