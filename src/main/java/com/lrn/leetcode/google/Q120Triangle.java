package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q120Triangle {
    /*2022-02-06T10:21:41.605Z
    * pd: https://leetcode.com/problems/triangle
    * assm: elem < 10000, -10000 < sum < 10000
    * appr: DP
    * Testcases : 
    * */

    // bottom up
    public int minimumTotal2(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() ==0) return 0;
        int rows = triangle.size();
        int cols = triangle.get(rows-1).size();
        int[] dp = new int[cols];
        // start from bottom row and calculate min sum for each index
        // for last row each element is the min sum
        // for second last row min sum = min((elem at idx + next row elem idx), (elem idx, next row elem idx+1))
        // populate last row to dp
        List<Integer> lastRow = triangle.get(rows-1);
        for(int col=0; col<cols; col++) {
            dp[col] = lastRow.get(col);
        }
        // calcuate min sum for each row starting from second last row
        for(int row=rows-2; row >= 0; row--) {
            List<Integer> rowElemList = triangle.get(row);
            int colsInRow = rowElemList.size();
            for(int col=0; col < colsInRow; col++) {
                dp[col] = rowElemList.get(col) + Math.min(dp[col], dp[col+1]);
            }
        }

        return dp[0];
    }


    // top down recursive
    public int minimumTotal(List<List<Integer>> triangle) {
        return triangle.get(0).get(0) + minTRec(triangle, 1, 0, new HashMap<>());
    }

    public int minTRec(List<List<Integer>> triangle, int row, int idx, Map<String, Integer> cache) {
        if(row >= triangle.size()) return 0;
        String key = row+":"+idx;
        if(cache.containsKey(key)) return cache.get(key);
        int sum1 = triangle.get(row).get(idx) + minTRec(triangle, row+1, idx,  cache);
        int sum2 = triangle.get(row).get(idx+1) + minTRec(triangle, row+1, idx+1, cache);
        cache.put(key, Math.min(sum1,sum2));
        return  cache.get(key);
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(-7),
                Arrays.asList(-2,1),
                Arrays.asList(-5,-5,9),
                Arrays.asList(-4,-5,4,4),
                Arrays.asList(-6,-6,2,-1,-5),
                Arrays.asList(3,7,8,-3,7,-9),
                Arrays.asList(-9,-1,-9,6,9,0,7),
                Arrays.asList(-7,0,-6,-8,7,1,-4,9),
                Arrays.asList(-3,2,-6,-9,-7,-6,-9,4,0),
                Arrays.asList(-8,-6,-3,-9,-2,-6,7,-5,0,7),
                Arrays.asList(-9,-1,-2,4,-2,4,4,-1,2,-5,5),
                Arrays.asList(1,1,-6,1,-2,-4,4,-2,6,-6,0,6),
                Arrays.asList(-3,-3,-6,-2,-6,-2,7,-9,-5,-7,-5,5,1));
        Q120Triangle sol = new Q120Triangle();
        System.out.println(sol.minimumTotal(triangle));
        System.out.println(sol.minimumTotal2(triangle));
    }
}
