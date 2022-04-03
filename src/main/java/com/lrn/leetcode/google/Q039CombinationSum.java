package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q039CombinationSum {
    /*2021-12-20T16:05:06.081Z
      https://leetcode.com/problems/combination-sum/
     * PD: Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
     * The same repeated number may be chosen from candidates unlimited number of times.
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * assm: arr elem -> non null, no dups  , best time solution
     * appr: DP
     * */
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        boolean[][] dp = new boolean[candidates.length][target + 1];

        sumrec(candidates, target, 0, new ArrayList<>(), dp);
        return result;
    }

    public void sumrec(int[] candidates, int target, int idx, List<Integer> res, boolean[][] dp) {
        //base
        if (target == 0) {
            result.add(new ArrayList<>(res));
            //LsUtil.printList(res);
            dp[idx][target] = true;
            return;
        }
        if (idx >= candidates.length) {
            return;
        }
        if (dp[idx][target]) {
            return;
        }
        // recursive
        // include
        if (candidates[idx] <= target) {
            res.add(candidates[idx]);
            sumrec(candidates, target - candidates[idx], idx, res, dp);
            res.remove(res.size() - 1);
        }
        // skip
        sumrec(candidates, target, idx + 1, res, dp);
    }


    /*
     * PD: Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
     * The same repeated number may be chosen from candidates unlimited number of times.
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * assm: arr elem -> non null, no dups  , best time solution
     * appr: bakctracking
     * */
    public List<List<Integer>> combinationSumbk(int[] candidates, int target) {
        List<List<Integer>> resultbk = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return resultbk;
        }
        backtrack(candidates,target,0,new ArrayList<>(), resultbk);
        return resultbk;
    }

    public void backtrack(int[] candidates, int target, int start, List<Integer> cur, List<List<Integer>> resultbk) {
         if(target > 0) {
            for(int idx=start; idx<candidates.length; idx++) {
                if(candidates[idx] <= target) {
                    cur.add(candidates[idx]);
                    backtrack(candidates,target - candidates[idx], idx, cur,resultbk);
                    cur.remove(cur.size()-1);
                }
            }
         } else if(target == 0) {
            resultbk.add(new ArrayList<>(cur));
            LsUtil.printList(cur);
         }
    }


    public static void main(String[] args) {
        Q039CombinationSum sol = new Q039CombinationSum();
//        long dptime = System.nanoTime();
//        try {
//            List<List<Integer>> lists = sol.combinationSum(new int[]{2, 3, 6, 7, 4, 9, 23, 78},288);
//        } finally {
//            dptime = System.nanoTime() - dptime;
//            System.out.printf("dptime = %dms%n", (dptime / 1_000_000));
//        }
//        long bktime = System.nanoTime();
//        try {
//            List<List<Integer>> lists = sol.combinationSumbk(new int[]{2, 3, 6, 7, 4, 9, 23, 78},288);
//        } finally {
//            bktime = System.nanoTime() - bktime;
//            System.out.printf("bktime = %dms%n", (bktime / 1_000_000));
//        }
        List<List<Integer>> lists = sol.combinationSumbk(new int[]{8,7,4,3},11);
    }

}
