package com.lrn.leetcode.google;


public class Q6ZigZag {
    public String convert(String str, int rows) {
        if(str == null || str.length() == 0) {
            return str;
        }
        StringBuilder[] buffer = new StringBuilder[rows];
        for(int bi=0; bi<buffer.length; bi++) {
            buffer[bi] = new StringBuilder();
        }
        int chIdx =0;
        while(chIdx < str.length()) {
            for(int idx=0; idx<rows && chIdx<str.length(); idx++) {
                buffer[idx].append(str.charAt(chIdx++));
            }
            for(int idx=rows-2; idx>0 && chIdx < str.length(); idx--) {
                buffer[idx].append(str.charAt(chIdx++));
            }
        }

        String sb="";
        for(int bi=0; bi<buffer.length; bi++) {
            sb = sb + buffer[bi].toString();
        }
        return sb;
    }

    public static void main(String[] args) {
        Q6ZigZag q6ZigZag = new Q6ZigZag();
        System.out.println(q6ZigZag.convert("PAYPALISHIRING", 3));
    }
}
