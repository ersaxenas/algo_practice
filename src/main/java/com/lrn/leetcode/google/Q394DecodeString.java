package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Queue;

public class Q394DecodeString {
    /*
    * pd: Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
    * assm: string len < 100, ans is expected to < 10000, bestime sol, lower case chars ascii
    * appr:
    * test cases:
    *
    * */

    public String decodeString(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return decodeQ(str);
    }
   int index = 0;
    public String decodeQ(String str) {
        StringBuilder result = new StringBuilder();
        while (index < str.length() && str.charAt(index) != ']') { // stop at ]
            if (!Character.isDigit(str.charAt(index))) { // read a to z letters
                result.append(str.charAt(index++));
            } else {
                int cnt = 0;
                while (index < str.length() && Character.isDigit(str.charAt(index))) {
                    cnt = cnt * 10 + str.charAt(index++) - '0'; // read number
                }
                index++; // ignore [ -- every number if followed by [
                String decode = decodeQ(str);
                index++; // ignore ]
                while(cnt >0) {
                    result.append(decode);
                    cnt--;
                }
            }
        }
        return result.toString();
    }


    public static void main(String[] args) {
        Q394DecodeString sol = new Q394DecodeString();
        System.out.println(new Q394DecodeString().decodeString("3[a]2[bc]"));
        System.out.println(new Q394DecodeString().decodeString("3[a2[bc]]"));
    }


}
