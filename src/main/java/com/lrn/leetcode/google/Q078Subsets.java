package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q078Subsets {
    /* 2022-01-07T11:51:51.682Z
    https://leetcode.com/problems/subsets
     * pd: Given a set of distinct integers, nums, return all possible subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     * assm; non null, arr len 1 < N < 100
     * appr: back tracking     - result [ {}, {1}, {1,2}, {1,3}, {1,2,3}, {2}, {2,3} ]
     *       idx 0 - 2 {} - {1}
     *           -> 1 -2 {1} - {1,2}
     *              ->  2 - 2 {1,2} - {1,2,3}
     *                  -> 3 - 2 {1,2,3}
     *                    return
     *                 return remove 3
     *             2 - 2 - {1,3}
     *                  -> 3 - 2 {1,3}
     *                     return
     *             return remove 2
     *       idx 1 - 2 {} - {2}  -- remove 1 and add 2 to list
     *           -> 2 -2 {1} - {2,3}
     *              ->  3 - 2 {2,3} - {2,3}  return;
     * Test cases:
     * [1,2,3] - [],[1],[1,2],[1,3],[1,2,3],[2],[2,3],[3]
     * [] - []
     * */

    public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      backtrack(result, new ArrayList<>(), nums, 0);
      return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> tmplist, int[] nums, int startIndex) {
        result.add(new ArrayList<>(tmplist));
        for(int idx=startIndex; idx< nums.length; idx++) {
            tmplist.add(nums[idx]);
            backtrack(result, tmplist, nums, idx+1);
            tmplist.remove(tmplist.size()-1);
        }
    }
}
