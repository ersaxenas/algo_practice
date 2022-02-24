package com.lrn.leetcode.amazon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordBreak139 {


	public boolean wordBreak(final String s, final List<String> wordDict) {
		HashSet<String> dict = new HashSet<>(wordDict);
		boolean[] checkornot = new boolean[s.length()+1];
		checkornot[0] = true;
		for(int i=1; i<=s.length(); i++) {
			for(int j=0; j<i; j++) {
				if(checkornot[j] && dict.contains(s.substring(j, i))) {
					checkornot[i] = true;
					break;
				}
			}
		}
		return checkornot[s.length()];
	}


	public static void main(final String[] args) {
		String str = "aaaaaaa";
		WordBreak139 obj = new WordBreak139();
		List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat","leet","code","apple", "pen","aaaa","aaa");
		System.out.println(obj.wordBreak(str, wordDict));

	}

}
