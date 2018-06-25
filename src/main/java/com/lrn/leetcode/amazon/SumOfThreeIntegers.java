package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfThreeIntegers {

	public List<List<Integer>> threeSum(final int numbers[]) {
		int index = 0;
		/*sort array*/
		Arrays.sort(numbers);
		List<List<Integer>> result = new ArrayList<>();
		while(index < (numbers.length -2)) {
			int arr[] = twoSum(numbers, index+1, numbers.length-1, 0-numbers[index]);
			if(arr != null) {
				result.add(Arrays.asList(numbers[index], numbers[arr[0]], numbers[arr[1]]));
			}
			index++;
		}
		return result;
	}

	public int[] twoSum(final int[] numbers, final int low, final int hi, final int target) {
		/*since array is sorted move one pointer forward and one backward*/
		int[] indices = null;
		if((numbers == null) || ((hi-low) <1)) {
			return indices;
		}
		int left=low, right=hi;
		while(left<right) {
			int total = numbers[left] + numbers[right];
			if(total == target) {
				indices = new int[2];
				indices[0]=left;
				indices[1]=right;
				return indices;
			} else if(total > target) {
				right--;
			} else {
				left++;
			}
		}
		return indices;
	}

	public static void main(final String[] args) {
		SumOfThreeIntegers obj = new SumOfThreeIntegers();
		int[] arr1 = {-1, 0, 1, 2, -1, -4};
		int[] arr = {0,0,0};
		for (List<Integer> lst : obj.threeSum(arr)) {
			System.out.println(lst);
		}
		for (List<Integer> lst : obj.threeSum(arr1)) {
			System.out.println(lst);
		}
	}


}
