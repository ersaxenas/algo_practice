package com.lrn.leetcode.amazon;

public class SumOfTwoIntegerInSortedArray15 {

	public int[] twoSum(final int[] numbers, final int target) {
		return sumOfTwo(numbers, 0, numbers.length-1, target);

	}

	public int[] sumOfTwo(final int[] numbers,final int low, final int hi ,final int target) {
		/*since array is sorted move one pointer forward and one backward*/
		int[] indices = new int[2];
		if((numbers == null) || ((hi-low) < 1)) {
			return indices;
		}
		int left =low, right=hi;
		while(left<right) {
			int sum = numbers[left] + numbers[right];
			if(sum == target) {
				indices[0] = left+1;
				indices[1] = right+1;
				return indices;
			}
			else if(sum > target) {
				right--;
			} else {
				left++;
			}
		}
		return indices;
	}

	public static void main(final String[] args) {
		SumOfTwoIntegerInSortedArray15 obj = new SumOfTwoIntegerInSortedArray15();
		int[] arr = {2,7,11,15};
		int aaa[] = obj.twoSum(arr, 9);
		for(int cnt=0;cnt<aaa.length; cnt++) {
			System.out.println(aaa[cnt]);
		}
	}

}
