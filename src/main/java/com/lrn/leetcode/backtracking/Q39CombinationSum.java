package com.lrn.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q39CombinationSum {
    /*
    * pd: Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
    * The same repeated number may be chosen from candidates unlimited number of times.
    * Note: All numbers (including target) will be positive integers.
    * The solution set must not contain duplicate combinations.
    * appr: backtracking
    * */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      List<List<Integer>> result = new ArrayList<>();
      if(candidates == null || candidates.length ==0) {
          return result;
      }
      backtrack(result, new ArrayList<>(), candidates, target, 0);
      return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> tmplist, int[] candidates, int target, int start) {
        if(target > 0) {
           for(int idx=start; idx<candidates.length; idx++) {
               if(candidates[idx] <= target) {
                   tmplist.add(candidates[idx]);
                   backtrack(result, tmplist, candidates, target-candidates[idx], idx);
                   tmplist.remove(tmplist.size()-1);
               }
           }
        } else if( target == 0) {
            result.add(new ArrayList<>(tmplist));
        }
    }
}
