package com.lrn.cci.stringsandarrays;

public class Palindrome {

	public boolean checkPalindrome(final String source) {
		if ((source == null) || (source.length() == 0)) {
			return true;
		}

		char[] src = source.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray();
		int backIndex = src.length - 1;
		for (int forwardIndex = 0; forwardIndex < src.length; forwardIndex++) {
			if (src[forwardIndex] != src[backIndex - forwardIndex]) {
				return false;
			}
		}

		return true;
	}

	public static void main(final String[] args) {
		Palindrome obj = new Palindrome();
		String str = "A man, a plan, a canal: Panama";
		System.out.println(obj.checkPalindrome(str));
	}
}
