package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentWords692 {

	public static List<String> topKFrequent(final String[] words, final int k) {
		Map<String, Integer> countMap = new HashMap<>();
		for(String str: words) {
			countMap.put(str, countMap.getOrDefault(str, 0)+1);
		}
		List<String> keyList = new ArrayList<>(countMap.keySet());
		Collections.sort(keyList, new Comparator<String>() {
			@Override
			public int compare(final String word1, final String word2) {
				int cnt1 = countMap.get(word1);
				int cnt2 = countMap.get(word2);
				return (cnt1==cnt2)? word1.compareTo(word2): cnt2-cnt1;
			}
		});
		return keyList.subList(0, k);

	}

	public static void main(final String[] args) {
		String[] arr = {"i", "love", "leetcode", "i", "love", "coding"};
		System.out.println(topKFrequent(arr, 2));
	}

}
