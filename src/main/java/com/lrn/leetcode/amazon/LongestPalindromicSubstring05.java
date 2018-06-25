package com.lrn.leetcode.amazon;

public class LongestPalindromicSubstring05 {

	private int lo, maxLen;

	public String longestPalindrome(final String s) {
		/*initialize with 0*/
		lo =0;
		maxLen=0;
		if(s.length() <=2) {
			return s;
		}
		for(int cnt=0; cnt<s.length(); cnt++) {
			extendPalindrome(s, cnt, cnt); /* odd number of elements in the char array/string ex "aca"*/
			extendPalindrome(s, cnt, cnt+1); /*even number of elements in the char array/string ex "bb" */
		}
		return s.substring(lo, lo+maxLen);
	}

	private void extendPalindrome(final String s, int left, int right) {
		/*start from center and go to left and right comparing chars at each step
		 * if chars are same then string is palindrome.
		 * */
		while(((left>=0) && (right < s.length())) && (s.charAt(left) == s.charAt(right))) {
			left--; right++;
		}
		if(maxLen < (right-left -1)) {
			maxLen = right-left-1;
			lo = left + 1;
		}
	}

	public static void main(final String[] args) {
		LongestPalindromicSubstring05 obj = new LongestPalindromicSubstring05();
		System.err.println(obj.longestPalindrome("babad"));
		System.err.println(obj.longestPalindrome("cbbd"));

	}

}
