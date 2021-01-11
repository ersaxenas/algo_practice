package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q249GroupShiftedStrings {
    /*
    * pd: Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"
Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
    * assm: best time sol, no whitespce, str len < 100, string array size < int max
    * appr: pattern we only shift 1 char and all the char in the shift by 1
    *       so pattern is that different between chars is always constant.
    *       a <1> b <1> c
    *       b <1> c <1> d
    *       c <1> d <1> e
    * test cases:
    * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
    * */


    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String key = prepKey(s);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public String prepKey(String str) {
        StringBuilder sbr = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int idx = 1; idx < chars.length; idx++) {
            int diff = chars[idx] - chars[idx - 1];
            diff = (diff < 0) ? diff + 26 : diff;
            sbr.append(diff);
        }
        return sbr.toString();
    }

    public static void main(String[] args) {
        Q249GroupShiftedStrings sol = new Q249GroupShiftedStrings();
        LsUtil.printList(sol.groupStrings(new String[] {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
    }

}
