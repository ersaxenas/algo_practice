package com.lrn.leetcode.google;

public class Q171ExcelSheetColNum {
    /*
    * pd: Given a column title as appear in an Excel sheet, return its corresponding column number.
For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
Example 1:
Input: "A"
Output: 1
Example 2:
Input: "AB"
Output: 28
Example 3:
Input: "ZY"
Output: 701
Constraints:
1 <= s.length <= 7
s consists only of uppercase English letters.
s is between "A" and "FXSHRXW".
    * assm: no white spaces, string len <= 7, only upper case english letters
    * appr: converting to base 26. think of it as string numb and converting to number only in this case instead of base 10 we have base 26.
    *
    *
    * */

    public int titleToNumber(String str) {
        int result = 0;
        for(char ch: str.toCharArray()) {
            result = result * 26 + (ch - 'A' + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Q171ExcelSheetColNum sol = new Q171ExcelSheetColNum();
        System.out.println(sol.titleToNumber("A") == 1 ? "passed" : "failed");
        System.out.println(sol.titleToNumber("AB") == 28 ? "passed" : "failed");
        System.out.println(sol.titleToNumber("ZY") == 701 ? "passed" : "failed");
    }

}
