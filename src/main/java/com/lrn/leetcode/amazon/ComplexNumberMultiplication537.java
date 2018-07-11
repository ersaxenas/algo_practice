package com.lrn.leetcode.amazon;

public class ComplexNumberMultiplication537 {


	/* (x+yi)*(u-vi) = (xu-yv) + (xv+yu)i
	 * */
	public static String complexNumberMultiply(final String a, final String b) {
		String[] arr1 =  a.replaceAll("i", "").split("\\+");
		int x = Integer.valueOf(arr1[0]);
		int y = Integer.valueOf(arr1[1]);
		String[] arr2 = b.replaceAll("i", "").split("\\+");
		int u = Integer.valueOf(arr2[0]);
		int v = Integer.valueOf(arr2[1]);
		String res = ((x*y) - (y*v)) + "+" + ((x*v)+(y*u)) + "i";
		return res;
	}

	public static void main(final String[] args) {
		complexNumberMultiply("1+-1i", "1+-1i");
	}

}
