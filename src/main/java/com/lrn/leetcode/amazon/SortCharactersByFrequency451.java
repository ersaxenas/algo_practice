package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCharactersByFrequency451 {

	static class CharData {
		char ch;
		Integer count;
		StringBuilder sbr = new StringBuilder();
		public CharData(final char ch) {
			this.ch=ch;
			this.count=1;
			sbr.append(ch);
		}

		public int incrementCount() {
			sbr.append(ch);
			return ++count;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = (prime * result) + ch;
			return result;
		}
		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			CharData other = (CharData) obj;
			if (ch != other.ch) {
				return false;
			}
			return true;
		}


	}

	public static String frequencySort(final String s) {
		StringBuilder sbr = new StringBuilder();
		if((s==null)||(s.length()==0)) {
			return sbr.toString();
		}
		List<CharData> charList = new ArrayList<>();
		char[] charArr = s.toCharArray();
		Arrays.sort(charArr);
		Character prevChar=null;
		CharData charData=null;
		for(char ch: charArr) {
			if((prevChar == null) || (prevChar != ch)) {
				if(charData==null) {
					charData = new CharData(ch);
				} else {
					charList.add(charData);
					charData = new CharData(ch);
				}
			} else {
				charData.incrementCount();
			}
			prevChar = ch;
		}
		charList.add(charData);
		Collections.sort(charList, Collections.reverseOrder(new Comparator<CharData>() {
			@Override
			public int compare(final CharData o1, final CharData o2) {
				return o1.count.compareTo(o2.count);
			}
		}));

		for(CharData chdt: charList) {
			sbr.append(chdt.sbr);
		}
		return sbr.toString();
	}

	public static void main(final String[] args) {
		System.out.println(frequencySort("his s he a ha he  ha ae"));

	}

}
