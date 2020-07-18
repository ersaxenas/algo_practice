package com.lrn.leetcode.backtracking;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.List;

public class Q46Permutations {
    /*
     * pd: Given a collection of distinct integers, return all possible permutations.
     * assm: arr elem - not null, no dups
     *       best time solution
     * appr: backtracking
     * */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> tmplist, int[] nums) {
        if (tmplist.size() == nums.length) {
            result.add(new ArrayList<>(tmplist));
            return;
        }
        for (int idx = 0; idx < nums.length; idx++) {
            if (tmplist.contains(nums[idx])) {
                continue;
            }
            tmplist.add(nums[idx]);
            backtrack(result, tmplist, nums);
            tmplist.remove(tmplist.size() - 1);
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] inuse = new boolean[nums.length];
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    public void backtrack2(List<List<Integer>> result, List<Integer> tmplist, int[] nums, boolean[] inuse) {
        if (tmplist.size() == nums.length) {
            result.add(new ArrayList<>(tmplist));
        }
        for (int idx = 0; idx < nums.length; idx++) {
            if (inuse[idx]) {
                continue;
            }
            tmplist.add(nums[idx]);
            inuse[idx] = true;
            backtrack2(result, tmplist, nums, inuse);
            inuse[idx] = false;
            tmplist.remove(tmplist.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q46Permutations sol = new Q46Permutations();
        List<List<Integer>> permute;
        long bc1 = System.nanoTime();
        try {
            permute = sol.permute(new int[]{1, 2, 3, 4, 5, 6});
        } finally {
            bc1 = System.nanoTime() - bc1;
            System.out.printf("bc1 = %dms%n", (bc1 / 1_000_000));
        }
        LsUtil.printListOfList(permute);

        long bc2 = System.nanoTime();
        try {
            permute = sol.permute2(new int[] {1,2,3,4,5,6});
        } finally {
            bc2 = System.nanoTime() - bc2;
            System.out.printf("bc1 = %dms%n", (bc2 / 1_000_000));
        }
        LsUtil.printListOfList(permute);
    }
}
