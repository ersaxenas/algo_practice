package com.lrn.leetcode.google;

public class Q344ReverseString {
    /*
    * pd: Write a function that reverses a string. The input string is given as an array of characters char[].
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
You may assume all the characters consist of printable ascii characters.
    * assm: string contains ascii printable chars only, string len < 10000, best time sol.
    * appr:
    * Test cases:
    * Input: ["h","e","l","l","o"] Output: ["o","l","l","e","h"]
    * Input: ["H","a","n","n","a","h"] Output: ["h","a","n","n","a","H"]
    * */

    public void reverseString(char[] s) {
        if(s == null || s.length <=1) {
            return;
        }
        int start=0, end=s.length-1;
        while(start < end) {
            char ch = s[start];
            s[start] = s[end];
            s[end] = ch;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Q344ReverseString sol = new Q344ReverseString();
        char[] str = "hello".toCharArray();
        sol.reverseString(str);
        System.out.println("str = " + new String(str));
    }
}
