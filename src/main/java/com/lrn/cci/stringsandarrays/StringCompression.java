package com.lrn.cci.stringsandarrays;

public class StringCompression {

	public String compress(final String source) {
		if (!isRepeatChar(source)) {
			return source;
		}
		int repeatCount = 1;
		StringBuilder sbr = new StringBuilder();
		for (int index = 0; index < source.length(); index++) {
			if (((index + 1) < source.length()) && (source.charAt(index) == source.charAt(index + 1))) {
				repeatCount++;
			} else {
				sbr.append(source.charAt(index)).append(repeatCount);
				repeatCount = 1;
			}
		}
		return sbr.toString();
	}

	public boolean isRepeatChar(final String source) {
		for (int index = 0; index < source.length(); index++) {
			if (((index + 1) < source.length()) && (source.charAt(index) == source.charAt(index + 1))) {
				return true;
			}
		}
		return false;
	}

	public static void main(final String[] args) {
		StringCompression cmp = new StringCompression();
		System.out.println(cmp.compress("addegedsss"));
		System.out.println(cmp.compress("abcde"));

	}

}
