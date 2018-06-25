package com.lrn.leetcode.amazon;

import java.util.List;

public class LetterCombinationsofaPhoneNumber17 {

	public List<String> letterCombinations(final String digits) {
		java.util.Queue<String> queue = new java.util.LinkedList<String>();
		if((digits==null) || (digits.length() ==0)) {
			return (List<String>) queue;
		}
		/*chars set*/
		String[] charSet = {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
		/*BFS*/
		queue.add("");
		/*for each digit*/
		for(int digcnt =0; digcnt<digits.length(); digcnt++) {
			int  dg = Integer.valueOf(""+digits.charAt(digcnt));
			/*when to stop dequeuing from queue when elements in the queue have same no. of chars as no. of
			 * digits processed. ex234 loop is 3 (2nd char) when all the element in queue have 2 chars
			 * it means we have processed all the char from 2 and 3*/
			while(queue.peek().length() == digcnt) {
				String str = queue.poll();
				for(char ch: charSet[dg].toCharArray()) {
					queue.add(str+ch);
				}
			}

		}

		return (List<String>) queue;
	}

	public static void main(final String[] args) {
		LetterCombinationsofaPhoneNumber17 obj = new LetterCombinationsofaPhoneNumber17();
		obj.letterCombinations("23");

	}

}
