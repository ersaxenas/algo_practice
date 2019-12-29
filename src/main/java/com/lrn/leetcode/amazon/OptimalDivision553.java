package com.lrn.leetcode.amazon;

public class OptimalDivision553 {


	/*A/B = Numerator/Denominator = x
	 * for x to be large numerator = A should be large and B denominator = b should small
	 * so in case of a/b/c if we device a/b first then numerator will be small
	 * and if we device b by c first then denominator will be smaller since it is gettring devided first.
	 * answer is a/(b/c/d/e) will result in maximum results.
	 * */
	public static String optimalDivision(final int[] nums) {
		StringBuilder sbr = new StringBuilder();
		if((nums == null) || (nums.length ==0)) {
			return sbr.toString();
		}
		if(nums.length ==1) {
			sbr.append(nums[0]);
		} else if(nums.length == 2) {
			sbr.append(nums[0]).append("/").append(nums[1]);
		} else {
			sbr.append(nums[0]).append("/("+nums[1]);
			for(int cnt=2; cnt<nums.length; cnt++) {
				sbr.append("/"+nums[cnt]);
			}
			sbr.append(")");
		}
		return sbr.toString();
	}

	public static void main(final String[] args) {
		int[] nums = {1000,100,10,2};
		System.out.println(optimalDivision(nums));
	}

}
