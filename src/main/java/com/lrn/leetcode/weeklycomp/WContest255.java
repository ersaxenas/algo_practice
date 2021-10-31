package com.lrn.leetcode.weeklycomp;

import com.lrn.leetcode.google.LsUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WContest258 {

    /*2000. Reverse Prefix of Word
     *https://leetcode.com/contest/weekly-contest-258/problems/reverse-prefix-of-word/
     * */
    public String reversePrefix(String word, char ch) {
        if (word.length() <= 1) return word;
        char[] arr = word.toCharArray();
        int start = 0, end = 0;
        for (end = 0; end < arr.length; end++) {
            if (arr[end] == ch) {
                break;
            }
        }
        char temp;
        while (start < end && end < arr.length) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return new String(arr);
    }

    /*https://leetcode.com/contest/weekly-contest-258/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/
     *2002. Maximum Product of the Length of Two Palindromic Subsequences
     * */
    public long interchangeableRectangles(int[][] rectangles) {
        Map<Double, Integer> ratioMap = new HashMap<>();
        long cnt = 0;
        for (int[] arr : rectangles) {
            double ratio = (double) arr[0] / (double) arr[1];
            int seen = ratioMap.getOrDefault(ratio, 0);
            ratioMap.put(ratio, seen + 1);
            cnt = cnt + seen;
        }
        return cnt;
    }

    class PalindromeSol {
        int max = 0;

        public int maxProduct(String s) {
            dfs(s, 0, new StringBuilder(), new StringBuilder());
            return max;
        }

        /*https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/submissions/
         *
         * */
        public void dfs(String s, int idx, StringBuilder buf1, StringBuilder buf2) {
            // base
            if (idx >= s.length()) {
                if (isPalindrome(buf1) && isPalindrome(buf2)) {
                    max = Math.max(max, buf1.length() * buf2.length());
                }
                return;
            }
            char ch = s.charAt(idx);
            buf1.append(ch);
            dfs(s, idx + 1, buf1, buf2);// add to buf1
            buf1.setLength(buf1.length() - 1);
            buf2.append(ch);
            dfs(s, idx + 1, buf1, buf2); // add to buf2
            buf2.setLength(buf2.length() - 1);
            dfs(s, idx + 1, buf1, buf2); // skip
        }

        public boolean isPalindrome(StringBuilder sbr) {
            int start = 0, end = sbr.length() - 1;
            while (start < end) {
                if (sbr.charAt(start++) != sbr.charAt(end--)) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        WContest258 sol = new WContest258();

    }


}
