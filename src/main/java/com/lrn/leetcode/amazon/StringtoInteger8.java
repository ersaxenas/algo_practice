package com.lrn.leetcode.amazon;

public class StringtoInteger8 {
	public int myAtoi(final String str) {
		if((str == null) || (str.length() ==0)) {
			return 0;
		}
		char[] chars = str.toCharArray();
		int index =0, total=0;
		while(index < str.length()) {
			if(chars[index] == ' ') {
				index++;
			}
			else {
				break;
			}
		}
		if(index == chars.length) {
			return 0;
		}
		boolean isPositive = true;
		if((chars[index] == '-')) {
			isPositive = false;
			index++;
		} else if(chars[index] == '+') {
			index++;
		}

		while(index<chars.length) {
			int chardiff = (chars[index]) - '0';
			if((chardiff>=0) && (chardiff <=9)) {
				if((total < (Integer.MAX_VALUE/10)) || ((total == (Integer.MAX_VALUE/10)) && (chardiff <= (Integer.MAX_VALUE%10)))) {
					total = (10 * total) + chardiff;
					index++;
				} else {
					return (isPositive)? Integer.MAX_VALUE:Integer.MIN_VALUE;
				}
			} else {
				break;
			}
		}


		return (isPositive)?total: total*-1;
	}

	public static void main(final String[] args) {
		StringtoInteger8 obj = new StringtoInteger8();
		System.out.println(obj.myAtoi("42"));
		System.out.println(obj.myAtoi("   -42"));
		System.out.println(obj.myAtoi("4193 with words"));
		System.out.println(obj.myAtoi("words and 987"));
		System.out.println(obj.myAtoi("-91283472332"));
		System.out.println(obj.myAtoi(" "));
	}


}
