package com.lrn.leetcode.backtracking;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q47Permutations2 {
    /*
    * ps: Given a collection of numbers that might contain duplicates, return all possible unique permutations.
    * assm: arr elem -> non null, contains dups
    *       best time sol
    * appr: backtracking
    * */

    public List<List<Integer>> permuteUnique(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      Arrays.sort(nums);
      boolean[] inuse = new boolean[nums.length];
      backtrack(result, new ArrayList<>(), nums, inuse);
      return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> tmplist, int[] nums, boolean[] inuse) {
        if(nums.length == tmplist.size()) {
            result.add(new ArrayList<>(tmplist));
            return;
        }
        for(int idx=0; idx<nums.length; idx++) {
            if(inuse[idx] || (idx > 0 && nums[idx] == nums[idx-1] && !(inuse[idx-1]))) {continue;}
            tmplist.add(nums[idx]);
            inuse[idx] = true;
            backtrack(result, tmplist, nums, inuse);
            inuse[idx] = false;
            tmplist.remove(tmplist.size()-1);
        }
    }

    public static void main(String[] args) {
        Q47Permutations2 sol = new Q47Permutations2();
//        LsUtil.printListOfList(sol.permuteUnique(new int[] {1,1,2}));
        LsUtil.printListOfList(sol.permuteUnique(new int[] {3,3,0,3}));
    }

}
