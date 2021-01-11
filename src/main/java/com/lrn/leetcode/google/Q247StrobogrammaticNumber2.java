package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Q247StrobogrammaticNumber2 {
    /*
    * pd: A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.
Example:
Input:  n = 2
Output: ["11","69","88","96"]
    *
    *
    * */

    public List<String> findStrobogrammatic(int n) {
        return helperRec(n, n);
    }


    private List<String> helperRec(int curLen, int len) {
        if (curLen == 0) {
            return Arrays.asList("");
        }
        if (curLen == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> prevSeries = helperRec(curLen - 2, len); // here -2 because we add two chars.
        List<String> newSeries = new ArrayList<>(); // new list we are going prepare
        for (String prv : prevSeries) {
            if (curLen != len) {
                newSeries.add("0" + prv + "0"); // on add an string  0<>0 here if curlen < len becuase 00 is equal to 0. In next recursion call we will add more chars
            }
            newSeries.add("1" + prv + "1");
            newSeries.add("6" + prv + "9");
            newSeries.add("8" + prv + "8");
            newSeries.add("9" + prv + "6");
        }
        return newSeries;
    }

    // dfs approach - easy to understand
    private final char[][] PAIRS = {{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}};
    public List<String> findStrobogrammatic2(int n) {
        if (n == 0) {
            return Arrays.asList("");
        }
        if (n == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> result = new ArrayList<>();
        buildNumberRec(new char[n], 0, n-1, result);
       return result;

    }

    public void buildNumberRec(char[] ch, int left, int right, List<String> result) {
        // base case
        if(left > right) {
            result.add(new String(ch));
            return;
        }
        for(char[] pair: PAIRS) {
            if(left == 0 && pair[0] == '0') {
                continue;// we dont want number starting with 0
            }
            if(left == right && (pair[0] == '6' || pair[0] == '9')) { // case when len is odd we dont want to put 6 or 9
                continue;
            }
            ch[left] = pair[0];
            ch[right] = pair[1];
            buildNumberRec(ch, left+1, right-1, result);
        }
    }


    public static void main(String[] args) {
        Q247StrobogrammaticNumber2 sol = new Q247StrobogrammaticNumber2();
        LsUtil.printList(sol.findStrobogrammatic2(3));
//        for(int idx=0; idx<=5; idx++ ) {
//            LsUtil.printList(sol.findStrobogrammatic(idx));
//        }
    }

}
