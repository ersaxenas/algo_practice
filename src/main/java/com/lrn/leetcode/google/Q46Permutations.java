package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q46Permutations {
    /* 2021-12-25T07:28:39.338Z 
    https://leetcode.com/problems/permutations/
    * pd:Given a collection of distinct integers, return all possible permutations.
    * ex> [1,2,3]
    * [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
    *
    * assm : non null
    *        besttime solution
    * appr: backtracking
    *
    * */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums,result,new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    public void backtrack(int[] nums, List<List<Integer>> result, List<Integer> templist, boolean[] inuse) {
        if(templist.size() == nums.length) {
            result.add(new ArrayList<>(templist));
        } else {
            for(int idx=0; idx<nums.length; idx++) {
                if(inuse[idx]) {
                    continue;
                }
                templist.add(nums[idx]);
                inuse[idx] = true;
                backtrack(nums,result,templist,inuse);
                inuse[idx] = false;
                templist.remove(templist.size()-1);
            }
        }
    }

}
