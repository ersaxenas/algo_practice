package com.lrn.leetcode.amazon;

import java.util.Arrays;

public class CountPrimes204 {

	public static int countPrimes(final int n) {
		if(n<2) {
			return 0;
		}
		/*here index represent number and value represent if it is a prime
		 * [false, false, true, true, false, true, false ]
		 *    0      1      2     3      4     5      6
		 * */
		boolean[] isPrimeNumer = new boolean[n];
		Arrays.fill(isPrimeNumer, true);
		isPrimeNumer[0]=false;
		isPrimeNumer[1]=false;
		int cnt =0;
		for(int idx1=2; idx1<n; idx1++) {
			if(isPrimeNumer[idx1]==true) {
				cnt++;
				for(int idx2=2; (idx1*idx2)<n; idx2++) {
					isPrimeNumer[idx1*idx2] = false;
				}
			}
		}
		return cnt;
	}

	public static void main(final String[] args) {
		countPrimes(10);

	}

}
