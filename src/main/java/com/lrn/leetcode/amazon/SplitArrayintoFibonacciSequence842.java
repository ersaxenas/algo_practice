package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayintoFibonacciSequence842 {

	public static List<Integer> splitIntoFibonacci(final String S) {
		int LEN = S.length();
		//System.out.println(S);
		/*loop to select number length
		 * max length is 10 or less
		 * */
		for(int i=0; i<Math.min(10, LEN); i++) {
			/*as per def: leading zeros are not allowed*/
			if((S.charAt(0)=='0') && (i>0)) {
				break;
			}
			long num0 = Long.valueOf(S.substring(0, i+1));
			//System.out.println("num0 "+num0);
			/*check if number is greater then max int*/
			if(num0>Integer.MAX_VALUE) {
				break;
			}
			/*search in the string for fibonacci
			 * here we will pick next int
			 * it should start with i+1 and its length should be less then 10;
			 * */
			search: for(int j=i+1; j<Math.min(i+10, LEN);j++) {
				/*as per def: leading zeros are not allowed. here start index is i+1*/
				if((S.charAt(i+1)=='0') && (j>(i+1))) {
					break;
				}
				long num1 = Long.valueOf(S.substring(i+1, j+1));
				//System.out.println("num1 "+num1);
				if(num1>Integer.MAX_VALUE) {
					break;
				}
				List<Integer> series = new ArrayList<>();
				series.add((int)num0);
				series.add((int)num1);
				/*now we have two starting numbers of fibonacci series. Keep reading next ints to check if it is sum of last two*/
				/*sum two integers and remove num0.len + num1.len chars from string and check if rest of the string starts with
				 * num0+num1
				 * */
				int charRead = j+1;
				while(charRead<LEN) {
					long nextInt = series.get(series.size()-2) + series.get(series.size()-1);
					//System.out.println("nextInt "+ nextInt);
					String leftString = S.substring(charRead);
					if((nextInt <= Integer.MAX_VALUE) && leftString.startsWith(String.valueOf(nextInt))) {
						charRead = charRead + String.valueOf(nextInt).length();
						series.add((int)nextInt);
					} else {
						//System.out.println(" ** stop **");
						continue search;
					}
				}
				if(series.size()>=3) {
					return series;
				}
			}

		}

		return new ArrayList<>();

	}

	public static void main(final String[] args) {
		System.err.println(splitIntoFibonacci("123456579"));
		System.err.println(splitIntoFibonacci("11235813"));
		System.err.println(splitIntoFibonacci("112358130"));
		System.err.println(splitIntoFibonacci("0123"));
		System.err.println(splitIntoFibonacci("1101111"));
	}


}
