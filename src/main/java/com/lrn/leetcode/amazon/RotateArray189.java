package com.lrn.leetcode.amazon;

public class RotateArray189 {

	public static int[] rotate1(final int[] nums, final int k) {
		int[] newNums = new int[nums.length];
		int startIdx = nums.length - k;
		for(int idx=0; idx<nums.length; idx++) {
			if(startIdx ==nums.length) {
				startIdx=0;
			}
			newNums[idx] = nums[startIdx];
			startIdx++;
		}
		return newNums;
	}

	public static void rotate2(final int[] nums, int k) {
		/*to safe against small arrays*/
		k = k%nums.length;
		/*reverse whole array*/
		reverse(nums, 0, nums.length-1);
		/*reverse till 0 to k-1*/
		reverse(nums, 0, k-1);
		/*k to end*/
		reverse(nums, k, nums.length-1);
	}

	public static void reverse(final int[] arr, int start, int end) {
		while(start < end) {
			int tmp = arr[end];
			arr[end] = arr[start];
			arr[start] = tmp;
			end--;
			start++;
		}
	}

	public static void main(final String[] args) {
		int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
		rotate2(arr, 3);
		System.out.println("finished");
	}

}
