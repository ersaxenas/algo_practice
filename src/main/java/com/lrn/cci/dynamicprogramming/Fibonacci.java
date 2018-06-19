package com.lrn.cci.dynamicprogramming;

public class Fibonacci {

	public int genFeb1(final int n) {
		if(n==0) {
			return 0;
		}
		if(n==1) {
			return 1;
		}
		return genFeb1(n-1) + genFeb1(n-2);
	}

	public int genFeb2(int n) {
		if(n==0) {
			return 0;
		}
		int a=0, b=1, c=0;
		for(int cnt=2; cnt<n;n++) {
			c=a+b;
			a=b;
			b=c;
		}
		return a+b;
	}

}
