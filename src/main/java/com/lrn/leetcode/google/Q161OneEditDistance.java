package com.lrn.leetcode.google;

public class Q161OneEditDistance {
    /*
    * pd:
    * assm: 0 < string len < 1000, only small/upper case a to z and 0 to 9 letters, best time sol
    * appr: dp or https://leetcode.com/problems/one-edit-distance/discuss/50098/My-CLEAR-JAVA-solution-with-explanation
    * test cases:
    *
    * */

    /*
 * There're 3 possibilities to satisfy one edit distance apart:
 *
 * 1) Replace 1 char:
 	  s: a B c
 	  t: a D c
 * 2) Delete 1 char from s:
	  s: a D  b c
	  t: a    b c
 * 3) Delete 1 char from t
	  s: a   b c
	  t: a D b c
 */
    public boolean isOneEditDistance2(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1));
                else // s is longer than t, so the only possibility is deleting one char from s
                    return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }


    /*DP sol: looks overkill infront of intutive sol.*/
    public boolean isOneEditDistance(String s, String t) {
        if(s == null && t == null) return false;
        if(s.equals(t)) return false;
        int[][] dp = new int[s.length()+1][t.length()+1];
        dp[0][0] = 0;
        for(int col=1; col <= t.length(); col++) {
            dp[0][col] = dp[0][col-1]+1;
        }
        for(int row=1; row <= s.length(); row++) {
            dp[row][0] = dp[row-1][0]+1;
        }
        for(int row=1; row <= s.length(); row++) {
            for(int col=1; col <= t.length(); col++) {
                if(s.charAt(row-1) == t.charAt(col-1)) {
                    dp[row][col] = dp[row-1][col-1];
                } else if(row == col) {
                    dp[row][col] = dp[row-1][col-1]+1;
                }
                else {
                    dp[row][col] = Math.min(dp[row-1][col], dp[row][col-1]) +1;
                }
            }
        }
        return (dp[s.length()][t.length()] == 1) ? true : false;
    }


}
