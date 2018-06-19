package com.lrn.cci.dynamicprogramming;

import java.util.HashMap;

public class CountWays {
	static HashMap<Long, Long> cache = new HashMap<>();

	public static long countways(final long n) {

		if(n<0) {
			return 0;
		} else if(n==0) {
			return 1;
		} else {
			Long result = cache.getOrDefault(n, -1l);
			if(result > -1) {
				return result;
			} else {

				result = countways(n-1) + countways(n-2) + countways(n-3);
				cache.put(n, result);
				return result;
			}

		}

	}

	public static void main(final String[] args) {
		int rng =50;
		for(int cnt=0;cnt<rng;cnt++) {
			System.out.println("steps " +cnt	+ " ways "+countways(cnt));
		}

	}

}
