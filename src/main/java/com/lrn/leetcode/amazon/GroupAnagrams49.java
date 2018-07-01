package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams49 {

	/*two string are anagrams if their sorted stings are same*/
	public static List<List<String>> groupAnagrams(final String[] strs) {
		Map<String, List<String>> angMap = new HashMap<>();
		if((strs==null)||(strs.length==0)) {
			return null;
		}
		for(String str: strs) {
			char[] chstr = str.toCharArray();
			/*sort string*/
			Arrays.sort(chstr);
			String sortStr = new String(chstr);
			List<String> lst = angMap.get(sortStr);
			if(lst==null) {
				lst = new ArrayList<>();
				lst.add(str);
				angMap.put(sortStr, lst);
			}
			else {
				lst.add(str);
			}
		}
		return new ArrayList<>(angMap.values());
	}

	public static void main(final String[] args) {
		String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(groupAnagrams(arr));
	}

}
