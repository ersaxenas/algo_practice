package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q77Combination {
    /* https://leetcode.com/problems/combinations
    *pd: Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
    *assm: non negative num, 1 < N < 100, 1 < 10
    * appr: backtracking
    * test case :
    * Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
    * */

    public List<List<Integer>> combine(int N, int K) {
       List<List<Integer>> result = new ArrayList<>();
       if(K > N) {
           return result;
       }
       backtrack(result, new ArrayList<>(), 1, N, K);
       return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> tmplist, int start, int N, int K) {
        if(tmplist.size() == K) {
            result.add(new ArrayList<>(tmplist));
        } else {
            for(int idx=start; idx<=N; idx++) {
                tmplist.add(idx);
                backtrack(result, tmplist,idx+1, N, K);
                tmplist.remove(tmplist.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        Q77Combination sol = new Q77Combination();
        LsUtil.printListOfList(sol.combine(4,2));

    }

}
