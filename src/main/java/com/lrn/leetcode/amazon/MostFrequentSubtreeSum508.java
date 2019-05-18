package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentSubtreeSum508 {


	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(final int x) { val = x; }
		@Override
		public String toString() {
			return "val=" + val + "";
		}
	}

	int maxcount=0;
	public int[] findFrequentTreeSum(final TreeNode root) {
		Map<Integer, Integer> sumCount = new HashMap<>();
		sum(root,sumCount);
		List<Integer> lst = new ArrayList<>();
		for(int key: sumCount.keySet()) {
			if(sumCount.get(key) == maxcount) {
				lst.add(key);
			}
		}
		int[] arr = new int[lst.size()];
		for(int idx=0; idx<arr.length; idx++) {
			arr[idx] = lst.get(idx);
		}
		return arr;
	}

	public int sum(final TreeNode node, final Map<Integer, Integer> sumCount) {
		if(node==null) {
			return 0;
		}
		int sum = sum(node.left, sumCount) + sum(node.right,sumCount) + node.val;
		sumCount.put(sum, sumCount.getOrDefault(sum, 0)+1) ;
		maxcount = Math.max(maxcount	, sumCount.get(sum));
		return sum;
	}


}
