package com.lrn.leetcode.google;

public class Q150ReverseWordsInString {
    /*
    * pd: https://leetcode.com/problems/reverse-words-in-a-string/
    * assm: string len < 1000, best time sol.
    * appr: using string builder
    * test cases:
    * */

    public String reverseWords(String s) {
        StringBuilder buff = new StringBuilder();
        int idx=0, a=0, b=0;
        while(idx < s.length()) {
            while(idx < s.length() && s.charAt(idx) == ' ') idx++;
            a=idx;
            while(idx < s.length() && s.charAt(idx) != ' ') idx++;
            if(a < idx) {
                buff.insert(0, " " + s.substring(a,idx));
            }
        }
        return buff.substring(1);
    }


}
