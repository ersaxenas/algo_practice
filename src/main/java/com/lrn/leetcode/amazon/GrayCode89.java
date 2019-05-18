package com.lrn.leetcode.amazon;

import java.util.LinkedList;
import java.util.List;

public class GrayCode89 {
	/*bit shift*/
	public List<Integer> grayCode(final int n) {
		List<Integer> result = new LinkedList<>();
		for (int i = 0; i < (1<<n); i++) {
			result.add(i ^ (i>>1));
		}
		return result;
	}
}
