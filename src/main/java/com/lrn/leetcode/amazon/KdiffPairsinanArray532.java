package com.lrn.leetcode.amazon;

import java.util.HashMap;
import java.util.Map;

public class KdiffPairsinanArray532 {

	public static int findPairs(final int[] nums, final int k) {
		if((nums==null)||(nums.length==0)|| (k <0)) {
			return 0;
		}
		Map<Integer,Integer> intMap = new HashMap<>();
		for(Integer num : nums) {
			intMap.put(num, intMap.getOrDefault(num, 0)+1);
		}
		if(k==0) {
			/*check if a key appears twice. Because only key - key == 0 so check for duplicates*/
			return (int) intMap.values().stream().filter(count -> count>1).count();
		}
		int count =0;
		for(Integer num: intMap.keySet()) {
			if(intMap.containsKey(num + k)) {
				count++;
			}
		}
		return count;
	}

	public static void main(final String[] args) {
		int[] arr = {3, 1, 4, 1, 5};
		int[] arr1 = {1, 2, 3, 4, 5};
		int[] arr2 = {1, 3, 1, 5, 4};
		System.out.println(findPairs(arr, 2));
		System.out.println(findPairs(arr1, 1));
		System.out.println(findPairs(arr2, 0));
	}

}
