package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q091DecodeWays {
    /*2022-01-11T16:18:49.093Z
    https://leetcode.com/problems/decode-ways
     * pd: A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     * assm: non null elem, best time sol
     * appr: dp https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation
     * https://leetcode.com/problems/decode-ways/solution/
     *  test cases: 
     *
     * */

    public int numDecodings(String s) {
        return backtrack(s.toCharArray(), 0, new ArrayList<>(), s);
    }

    public int backtrack(char[] s, int start, List<String> tmplist, String str) {
        if (start >= s.length) {
            LsUtil.printList(tmplist);
            return 1;
        }
        if (Character.getNumericValue(s[start]) < 1) {
            return 0;
        }
        tmplist.add(str.substring(start, start + 1));
        int cnt = backtrack(s, start + 1, tmplist, str);
        tmplist.remove(tmplist.size() - 1);

        if (start + 1 < s.length && (Character.getNumericValue(s[start]) * 10 + Character.getNumericValue(s[start + 1])) <= 26) {
            tmplist.add(str.substring(start, start + 2));
            cnt = cnt + backtrack(s, start + 2, tmplist, str);
            tmplist.remove(tmplist.size() - 1);
        }
        return cnt;
    }

    public int numDecodingBU(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int[] dp = new int[str.length() + 1];
        dp[0] = 1;
        dp[1] = (str.charAt(0) == '0') ? 0 : 1;
        for (int idx = 2; idx <= str.length(); idx++) {
            int num1 = Integer.parseInt(str.substring(idx - 1, idx));
            int num2 = Integer.parseInt(str.substring(idx - 2, idx));
            if (num1 > 0 && num1 <= 9) {
                dp[idx] = dp[idx - 1];
            }
            if (num2 >= 10 && num2 <= 26) {
                dp[idx] += dp[idx - 2];
            }
        }
        LsUtil.printArray(dp);
        return dp[str.length()];
    }


    public static void main(String[] args) {
        Q91DecodeWays sol = new Q91DecodeWays();
        System.out.println(sol.numDecodingBU("221022"));
//        System.out.println(sol.numDecodings("12"));
//        System.out.println(sol.numDecodings("226"));
//        System.out.println(sol.numDecodings("0"));
//        System.out.println(sol.numDecodings("27"));
//        System.out.println(sol.numDecodingBU("221228"));
    }


}
