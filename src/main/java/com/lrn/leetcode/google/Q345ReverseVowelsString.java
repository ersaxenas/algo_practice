package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q345ReverseVowelsString {
    /*
    * pd: Write a function that takes a string as input and reverse only the vowels of a string.
    * assm: string contains only printable ascii chars, string len < 10000, best time sol
    * appr:
    * test cases:
    * Input: "hello" Output: "holle"
    * Input: "leetcode" Output: "leotcede"
    * */

    public String reverseVowels(String s) {
        if(s==null || s.length() <=1) {
            return s;
        }
        char[] str = s.toCharArray();
        int start=0, end = s.length()-1;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        while(start < end) {
            while(start < str.length && !vowels.contains(str[start])){start++;};
            while(end >=0 && !vowels.contains(str[end])) {end--;}
            if(start < end) {
                char ch = str[start];
                str[start] = str[end];
                str[end] = ch;
                start++;
                end--;
            }
        }
        return new String(str);
    }

    public static void main(String[] args) {
        Q345ReverseVowelsString sol = new Q345ReverseVowelsString();
        System.out.println(sol.reverseVowels("hello"));
        System.out.println(sol.reverseVowels("leetcode"));
    }

}
