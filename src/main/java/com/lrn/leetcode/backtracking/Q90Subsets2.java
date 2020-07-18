package com.lrn.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q90Subsets2 {
    /*
     * pd: Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     * assm: arr elem -> non null, dups are allowed
     *       best time solution
     * appr: backtracking
     *                                    [] i 0
     *                                   /
     *                                 [1]  i - 1
     *                                /
     *                              [2] i - 2
     *                             /
     *                           [2]  i -3
     *
     * */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> tmplist, int[] nums, int startIndex) {
        result.add(new ArrayList<>(tmplist));
        for (int idx = startIndex; idx < nums.length; idx++) {
            if (idx > startIndex && nums[idx] == nums[idx - 1]) continue;
            tmplist.add(nums[idx]);
            backtrack(result, tmplist, nums, idx + 1);
            tmplist.remove(tmplist.size() - 1);
        }
    }

}
