package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets79 {

	public List<List<Integer>> subsets(final int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		Arrays.sort(nums);

		for(Integer num: nums) {
			List<List<Integer>> tmp = new ArrayList<>();
			for(List<Integer> qlst : result) {
				ArrayList<Integer> lst = new ArrayList<>(qlst);
				lst.add(num);
				tmp.add(lst);
			}
			result.addAll(tmp);
		}
		return result;
	}

	public static void main(final String[] args) {
		int[] arr = {1,2,3};
		Subsets79 obj = new Subsets79();
		obj.subsets(arr);

	}
}
