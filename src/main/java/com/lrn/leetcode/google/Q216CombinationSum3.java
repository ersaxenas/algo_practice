package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q216CombinationSum3 {
    /*
    * pd: Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
    * assm: 2 <= K <= 9, 1 <=n <= 60
    * appr: backtracking
    * test cases:
    * k=3, n=7 -> { 1, 2, 4 }
    * k=3, n=9 -> { 1, 2, 6 } { 1, 3, 5 } { 2, 3, 4 }
    * k=4, n=1 -> { }
    * k=9, n=45 -> {1,2,3,4,5,6,7,8,9}
    *
    * */

    public List<List<Integer>> combinationSum3(int k, int n) {
       List<List<Integer>> result = new ArrayList<>();
       backtrack(result, k, n, new HashSet<>(),1);
       return result;
    }

    public void backtrack(List<List<Integer>> result, int k, int sum, Set<Integer> temp, int start) {
        if(k == 0 && sum == 0) {
           result.add(new ArrayList<>(temp));
        } else if(sum > 0 && k > 0) {
           for(int idx=start; idx<=9; idx++) {
                   temp.add(idx);
                   backtrack(result, k-1, sum-idx, temp, idx+1);
                   temp.remove(idx);
           }
        }
    }

    public static void main(String[] args) {
        Q216CombinationSum3 sol = new Q216CombinationSum3();
        LsUtil.printListOfList(sol.combinationSum3(3, 7));
    }

}
