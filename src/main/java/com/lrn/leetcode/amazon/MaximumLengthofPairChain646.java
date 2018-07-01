package com.lrn.leetcode.amazon;

import java.util.LinkedHashSet;
import java.util.Set;

public class MaximumLengthofPairChain646 {
	static class ETuple {
		int a,b,level;
		public ETuple(final int a, final int b, final int level) {
			this.a =a;
			this.b=b;
			this.level=level;
		}
		public boolean canBeNext(final ETuple tuple) {
			return b>tuple.a;
		}
	}

	public int findLongestChain(final int[][] pairs) {
		if((pairs == null) || (pairs.length ==0)) {
			return 0;
		}
		int maxlevel=0;
		int row = pairs.length;
		int cols = pairs[0].length;
		Set<ETuple> pairSet = new LinkedHashSet<>();

		/*BFS*/





		return 0;
	}



}
