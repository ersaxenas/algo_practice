package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q90SubsetsII {
    /*2022-01-11T16:21:53.961Z
    * https://leetcode.com/problems/subsets-ii/
    * assm:
    * appr:
    * testcase:
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
