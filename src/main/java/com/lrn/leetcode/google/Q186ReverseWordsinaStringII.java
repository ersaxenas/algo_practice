package com.lrn.leetcode.google;

public class Q186ReverseWordsinaStringII {
    /*2022-05-29T09:58:10.190Z
    * pd: https://leetcode.com/problems/reverse-words-in-a-string-ii/
    * assm: 1 < str len < 10000, only english lower case letters, words are separated by only 1 space, best time sol
    * appr:
    * test cases:
    * */

    public void reverseWords(char[] s) {
        if(s.length <= 1) return;
        reverse(s, 0, s.length-1);
        int start=0;
        for(int idx=0; idx <= s.length; idx++) {
            if(idx == s.length || s[idx] == ' ') {
                reverse(s, start, idx-1);
                start = idx+1;
            }
        }
    }

    public void reverse(char[] arr, int start, int end) {
        char ch;
        while(start < end ) {
            ch = arr[start];
            arr[start] = arr[end];
            arr[end] = ch;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        char[] str = "keep trying".toCharArray();
        Q186ReverseWordsinaStringII sol = new Q186ReverseWordsinaStringII();
        sol.reverseWords(str);
        System.out.println(str);
    }

}
