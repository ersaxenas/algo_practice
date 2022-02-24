package com.lrn.leetcode.amazon;

public class MonotoneIncreasingDigits738 {

	public static int monotoneIncreasingDigits(final int N) {
		char[] arr = String.valueOf(N).toCharArray();
		int idx=1;
		/*find where next element is less then previous element*/
		while((idx<arr.length) && (arr[idx-1]<=arr[idx])) {
			idx++;
		}
		/*now if found and element where next is less then previous
		 * start decrementing the previous element by 1 a
		 * */
		while(((idx>0) && (idx<arr.length)) && (arr[idx-1]>arr[idx])) {
			idx--;
			arr[idx]--;
		}
		/*now fill rest of the elements with 9*/
		for(int cnt=idx+1; cnt<arr.length; cnt++) {
			arr[cnt] = '9';
		}
		return Integer.parseInt(new String(arr));
	}

	public static void main(final String[] args) {
		System.out.println(monotoneIncreasingDigits(123454321));
		System.out.println(monotoneIncreasingDigits(333222));

	}

}
