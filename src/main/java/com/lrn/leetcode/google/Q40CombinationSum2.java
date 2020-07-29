package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q40CombinationSum2 {
    /*
    * pd: Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
    * Each number in candidates may only be used once in the combination.
    * Note: All numbers (including target) will be positive integers.
    * The solution set must not contain duplicate combinations.
    * assm: arr -> non null, non -ve, 1 < n < 10000
    *              best time solution
    * appr: backtracking
    *       sort the array will help in avoiding dups
    *
    * */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] inuse = new boolean[candidates.length];
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0, inuse);
        return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> tmplist, int[] candidates, int target, int start, boolean[] inuse) {
        if(target > 0) {
            for(int idx=start; idx<candidates.length; idx++) {
                if(idx > 0 && candidates[idx] == candidates[idx-1] && !inuse[idx-1] ) continue;
                if(candidates[idx] <= target) {
                    tmplist.add(candidates[idx]);
                    inuse[idx] = true;
                    backtrack(result, tmplist, candidates, target - candidates[idx], idx+1, inuse);
                    inuse[idx] = false;
                    tmplist.remove(tmplist.size()-1);
                } else {
                    break;
                }
            }
        } else if( target == 0) {
            result.add(new ArrayList<>(tmplist));
        }
    }

    public static void main(String[] args) {
        Q40CombinationSum2 sol = new Q40CombinationSum2();
        LsUtil.printListOfList(sol.combinationSum2(new int[] {10,1,2,7,6,1,5}, 8));
        LsUtil.printListOfList(sol.combinationSum2(new int[] {2,5,2,1,2}, 5));
    }

}
