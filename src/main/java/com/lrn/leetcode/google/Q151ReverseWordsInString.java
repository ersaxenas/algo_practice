package com.lrn.leetcode.google;

public class Q151ReverseWordsInString {
    /*2022-04-10T14:37:30.936Z
    * pd: https://leetcode.com/problems/reverse-words-in-a-string/
    * assm: string len < 1000, best time sol.
    * appr: using string builder
    * test cases:
    * */

    public String reverseWords(String s) {
        StringBuilder buff = new StringBuilder();
        int idx=0, prv;
        while(idx < s.length()) {
            while(idx < s.length() && s.charAt(idx) == ' ') idx++;// skip spaces
            prv=idx;
            while(idx < s.length() && s.charAt(idx) != ' ') idx++;// skip letters till prv space is encountered
            if(prv < idx) {
                buff.insert(0, " " + s.substring(prv,idx));
            }
        }
        return buff.substring(1);
    }

    public static void main(String[] args) {
      Q151ReverseWordsInString sol = new Q151ReverseWordsInString();
      String words = "the sky is blue";
        System.out.println(sol.reverseWords(words));
    }


}
