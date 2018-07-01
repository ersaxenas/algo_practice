package com.lrn.leetcode.amazon;

import java.util.Arrays;

public class ProductofArrayExceptSelf238 {
	public static Integer[] productExceptSelf(final Integer[] nums) {
		Integer n = nums.length;
		Integer[] res = new Integer[n];
		System.out.println(Arrays.asList(nums));
		res[0] = 1;
		System.out.println(Arrays.asList(res));
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] * nums[i - 1];
			System.out.println(Arrays.asList(res));
		}
		System.out.println("--------------------");
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] *= right;
			right *= nums[i];
			System.out.println(Arrays.asList(res));
		}
		System.out.println(Arrays.asList(res));
		return res;
	}

	public static void main(final String[] args) {
		Integer[] arr = {1,2,3,4};
		productExceptSelf(arr);
	}

}

