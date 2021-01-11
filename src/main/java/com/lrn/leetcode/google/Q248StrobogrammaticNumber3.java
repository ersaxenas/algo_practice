package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q248StrobogrammaticNumber3 {
    /*
     * PD: A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
     * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
     * Input: low = "50", high = "100" Output: 3
     * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
     *
     * */

    public int strobogrammticInRange(String low, String high) {
        int count = 0;
        for (int idx = low.length(); idx <= high.length(); idx++) {
            List<String> nums = getStrobogrammaticNums(idx, low, high);
            count = count + nums.size();
        }
        return count;
    }


    char[][] PAIRS = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public List<String> getStrobogrammaticNums(int len, String start, String end) {
        if (len == 0) {
            return Arrays.asList();
        }
        List<String> result = new ArrayList<>();
        buildStrobogrammaticNum(new char[len], 0, len - 1, result, start, end);
        return result;
    }

    public void buildStrobogrammaticNum(char[] buffer, int left, int right, List<String> result, String start, String end) {
        // base
        if (left > right) { // buffer has filled
            final String num = new String(buffer);
            if ((num.length() == start.length() && num.compareTo(start) < 0) || (num.length() == end.length() && num.compareTo(end) > 0)) {
                return;
            }
            result.add(num);
            return;
        }
        for (char[] pair : PAIRS) {
            if (left == 0 && left != right && pair[0] == '0') { // we dont want nums starting with 0
                continue;
            }
            if (left == right && (pair[0] == '6' || pair[0] == '9')) {//case when len is odd num - we dont want to insert 6 or 9 at middle of buffer
                continue;
            }
            // assign chars
            buffer[left] = pair[0];
            buffer[right] = pair[1];
            buildStrobogrammaticNum(buffer, left + 1, right - 1, result, start, end);
        }
    }

    public static void main(String[] args) {
        Q248StrobogrammaticNumber3 sol = new Q248StrobogrammaticNumber3();
        System.out.println(sol.strobogrammticInRange("50", "100"));
        System.out.println(sol.strobogrammticInRange("0", "100"));
    }

}
