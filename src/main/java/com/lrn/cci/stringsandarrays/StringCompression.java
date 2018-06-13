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

	public String decompress(final char[] array, final int index) {
		int startIndex = index;
		// a1d2e1g1e1d1s3
		StringBuilder sbr = new StringBuilder();
		while (startIndex < array.length) {
			Data data = extractEnc(array, startIndex);
			for (int charCnt = 0; charCnt < data.num; charCnt++) {
				sbr.append(data.ch);
			}
			startIndex = data.nextIndex;
		}
		return sbr.toString();
	}

	public Data extractEnc(final char[] array, final int startIndex) {
		char cha = array[startIndex];
		int cnt = startIndex + 1;
		if (cnt >= array.length) {
			return null;
		}
		StringBuilder sbr = new StringBuilder();
		while (true) {
			if ((cnt < array.length) && Character.isDigit(array[cnt])) {
				sbr.append(array[cnt]);
				cnt++;
			} else {
				break;
			}
		}
		Data da = new Data();
		da.ch = cha;
		da.num = Integer.valueOf(sbr.toString());
		da.nextIndex = cnt;
		System.out.println(da);
		return da;
	}

	class Data {
		char ch;
		int num;
		int nextIndex;

		@Override
		public String toString() {
			return "Data [ch=" + ch + ", num=" + num + ", nextIndex=" + nextIndex + "]";
		}
	}

	public static void main(final String[] args) {
		StringCompression cmp = new StringCompression();
		System.out.println(cmp.compress("addegedsss"));
		System.out.println(cmp.compress("abcde"));
		System.out.println(cmp.decompress("a1d2e1g1e1d1s3".toCharArray(), 0));

	}

}
