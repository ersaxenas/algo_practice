package com.lrn.leetcode.google;

public class Q168ExcelSheetColumnTitle {
    /* 2022-05-07T07:57:41.301Z
    https://leetcode.com/problems/excel-sheet-column-title
    * pd: Given a positive integer, return its corresponding column title as appear in an Excel sheet.
For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    *
    *
    *
    * */

    public String convertToTitle(int n) {
      StringBuilder sbr = new StringBuilder();
      while(n>0) {
          n--;
          sbr.append((char) ('A' + n%26));
          n = n/26;
      }
      return sbr.reverse().toString();// reversing here
    }

    public static void main(String[] args) {
        Q168ExcelSheetColumnTitle sol = new Q168ExcelSheetColumnTitle();
        sol.convertToTitle(702);
        for(int idx=0; idx<1000; idx++) {
            System.out.println(idx + ":" + sol.convertToTitle(idx));
        }
    }
}
