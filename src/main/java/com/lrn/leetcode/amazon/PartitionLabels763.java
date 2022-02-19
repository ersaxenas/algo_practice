package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels763 {

	static class Solution {
		public List<Integer> partitionLabels(final String S) {
			int[] last = new int[26];
			for (int i = 0; i < S.length(); ++i) {
				last[S.charAt(i) - 'a'] = i;
			}

			int j = 0, anchor = 0;
			List<Integer> ans = new ArrayList();
			for (int i = 0; i < S.length(); ++i) {
				int chMax = last[S.charAt(i) - 'a'];
				j = Math.max(j, chMax);
				if (i == j) {
					int size = (i - anchor) +1;
					ans.add(size);
					anchor = i + 1;
				}
			}
			return ans;
		}
	}

	public static void main(final String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.partitionLabels("abccaddbeffe"));
	}

}
