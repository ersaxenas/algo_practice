package com.lrn.leetcode.amazon;

import java.util.HashMap;

public class LongestSubStringwithoutRepeatChar03 {


	public Integer getLongestSubString(final String str) {
		HashMap<Character, Integer> charPos = new HashMap<>();
		/*start of string == low
		 * end of string == hi
		 * move hi in forward direction
		 * if character don't repeat then calculate max = end - start + 1 (since string chars start with 0)
		 * if character repeat move start to end;
		 * */
		int low=0, hi=0, max=0;
		for(low=0,hi=0;hi<str.length(); ++hi) {
			Character ch = str.charAt(hi);
			if(charPos.containsKey(ch)) {
				low = Math.max(low, charPos.get(ch)+1);
			}
			charPos.put(ch, hi);
			max = Math.max(max, (hi-low)+1);
		}
		return max;
	}

	public static void main(final String[] args) {
		LongestSubStringwithoutRepeatChar03 obj = new LongestSubStringwithoutRepeatChar03();
		System.out.println(obj.getLongestSubString("abcabcbb"));
		System.out.println(obj.getLongestSubString("bbbbb"));
		System.out.println(obj.getLongestSubString("pwwkew"));
		System.out.println(obj.getLongestSubString(""));
	}

}
