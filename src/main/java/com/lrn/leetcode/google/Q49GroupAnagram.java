package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Q49GroupAnagram {

    /*
     * pd: Given an array of strings, group anagrams together.
     * assm: no blank strings
     *       best time sol
     * appr:
     *     iterate over arrys
     *     sort the string and store index in map<String, list> collection
     * */

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        if (strs.length == 1) {
            result.add(Arrays.asList(strs[0]));
            return result;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cc = str.toCharArray();
            Arrays.sort(cc);
            String ss = new String(cc);
            List<String> lst = map.getOrDefault(ss, new ArrayList<>());
            lst.add(str);
            map.put(ss,lst);
        }
        result.addAll(map.values());
        return result;
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        if (strs.length == 1) {
            result.add(Arrays.asList(strs[0]));
            return result;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] arr = new int[26];
            for(char c: str.toCharArray()) {
                arr[c - 'a']++;
            }
            String key = Arrays.toString(arr);
            List<String> lst = map.getOrDefault(key, new ArrayList<>());
            lst.add(str);
            map.put(key,lst);
        }
        result.addAll(map.values());
        return result;
    }

    public static void main(String[] args) {
        Q49GroupAnagram sol = new Q49GroupAnagram();
        LsUtil.printListOfList(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        LsUtil.printListOfList(sol.groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

}
