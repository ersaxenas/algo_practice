package com.lrn.leetcode.google;

public class Q058LengthOfLastWord {

    /*2021-12-28T13:14:00.238Z
    https://leetcode.com/problems/length-of-last-word/
    * pd: Given a string s consists of some words separated by spaces, return the length of the last word in the string. If the last word does not exist, return 0.
      A word is a maximal substring consisting of non-space characters only.
    * assm: str len < 10000, str may contain ws, only english letters and spaces, best time sol
    * appr:
    *
    * Test cases:
    * 1. "hello world" ans: 5
    * 2. "" ans 0
    * 3. " " ans 0
    * 4. null ans 0
    * */

    public int lengthOfLastWord2(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        int len =0;
        for(int idx =s.length()-1; idx>=0; idx--) {
            if(s.charAt(idx) !=  ' ') { // not white space
                len++;
            } else {
                if(len > 0) break;
            }
        }

        return len;
    }

    public int lengthOfLastWord(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        Integer lc = null, fc = null;
        for(int idx =s.length()-1; idx>=0; idx--) {
            if(lc == null && s.charAt(idx) != ' ') {
                lc = idx;
            } else if (lc != null && fc == null && s.charAt(idx) == ' ') {
                fc = idx;
            }
        }
        if(fc == null) {
            return lc==null ? 0 : lc+1;
        }
        return lc - fc;
    }


    public static void main(String[] args) {
        Q058LengthOfLastWord sol = new Q058LengthOfLastWord();
        System.out.println(sol.lengthOfLastWord2("hello world"));
        System.out.println(sol.lengthOfLastWord2(" hello world   "));
        System.out.println(sol.lengthOfLastWord2("helloworld"));
        System.out.println(sol.lengthOfLastWord2(" "));
        System.out.println(sol.lengthOfLastWord2("      "));
        System.out.println(sol.lengthOfLastWord2("    a  "));
        System.out.println(sol.lengthOfLastWord2(""));
        System.out.println(sol.lengthOfLastWord2(null));
    }


}
