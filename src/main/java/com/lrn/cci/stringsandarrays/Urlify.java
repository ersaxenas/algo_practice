package com.lrn.cci.stringsandarrays;

public class Urlify {

	public String replaceWhiteSpaces(final String source) {
		char[] strChars = source.toCharArray();
		int index = strChars.length - 1;
		while (index >= 0) {
			if (' ' != strChars[index]) {
				break;
			}
			index--;
		}
		int index2 = strChars.length - 1;
		while (index >= 0) {
			if (' ' == strChars[index]) {
				strChars[index2--] = '0';
				strChars[index2--] = '2';
				strChars[index2--] = '%';
				index--;
			} else {
				strChars[index2--] = strChars[index--];
			}
		}
		return new String(strChars);
	}

	public static void main(final String[] args) {
		Urlify obj = new Urlify();
		System.out.println(obj.replaceWhiteSpaces("aa bb   "));

	}

}
