package com.lrn.leetcode.amazon;

public class ReverseWordsinaStringII186 {

	public void reverseWords(final char[] str) {
		int i=0, j=0;
		while(j < str.length) {
			if(str[j] == ' ') {
				reverse(str, i, j-1);
				if((j+1) < str.length) {
					i = j+1;
				}
			}
			j++;
		}
		reverse(str, i, str.length-1);
		reverse(str, 0, str.length -1);
	}

	public void reverse(final char[] str, int start, int end) {
		while(start<end) {
			char ch = str[start];
			str[start] = str[end];
			str[end] = ch;
			start++;
			end--;
		}
	}

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		ReverseWordsinaStringII186 obj = new ReverseWordsinaStringII186();
		char[] str = "generated method stub".toCharArray();
		obj.reverseWords(str);
		System.out.println(str);
	}

}
