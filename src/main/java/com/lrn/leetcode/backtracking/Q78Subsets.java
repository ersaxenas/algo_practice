package com.lrn.leetcode.backtracking;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.List;

public class Q78Subsets {
    /*
    * pd: Given a set of distinct integers, nums, return all possible subsets (the power set).'
    * Note: The solution set must not contain duplicate subsets.
    * Assm: arr elem -> non null, no dups
    *       best time solution
    *
    * Appr: backtracking
    *
    * */

    public List<List<Integer>> subsets(int[] nums) {
         List<List<Integer>> lst = new ArrayList<>();
         backtrack(lst, new ArrayList<>(), nums, 0 );
         return lst;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> tmplist, int[] nums, int startIndex) {
        result.add(new ArrayList<>(tmplist));
        for(int idx=startIndex; idx<nums.length; idx++) {
            tmplist.add(nums[idx]);
            backtrack(result,tmplist,nums,idx+1);
            tmplist.remove(tmplist.size()-1);
        }
    }

    public static void main(String[] args) {
         Q78Subsets sol = new Q78Subsets();
         LsUtil.printListOfList(sol.subsets(new int[] {1,2,3}));
    }
}
