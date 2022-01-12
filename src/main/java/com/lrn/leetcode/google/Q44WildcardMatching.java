package com.lrn.leetcode.google;


import java.util.Arrays;

public class Q44WildcardMatching {
    /* 2021-12-24T11:23:02.669Z
    https://leetcode.com/problems/wildcard-matching/
     * PD: Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     * Note:
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
     * assm: String not contain spaces,
     *       best time solution
     * appr:
     *      start comparing both string each char
     *      1. char at i in str == pattern then move to next char
     *      2). if pattern is ? then move to next char
     *      3). if pattern next char is *
     *          then
     *          a). empty pattern - ignore * and move to next char of pattern
     *          b). ignore curr char and move next char of str
     * */

    public boolean isMatch(String s, String p) {
        // check of empty string and pattern
        if (s.isEmpty()) {
            return p.isEmpty() || "*".equals(p);
        }
        return isMatchRec(s, 0, p, 0);
    }

    public boolean isMatchRec(String s, int sidx, String p, int pidx) {
        if (sidx == s.length()) {
            while (pidx < p.length() && p.charAt(pidx) == '*') {
                pidx++;
            }
            return p.length() == pidx;
        }

        boolean charMatchOrQMatch = ((sidx < s.length() && pidx < p.length()) && (p.charAt(pidx) == '?' || s.charAt(sidx) == p.charAt(pidx)));

        if (pidx < p.length() && p.charAt(pidx) == '*') { // case when it starts with *
            if (isMatchRec(s, sidx, p, pidx + 1)) { // skip pattern so +1 empty group
                return true;
            }
            return isMatchRec(s, sidx + 1, p, pidx);// skip string char
        }
        return charMatchOrQMatch && isMatchRec(s, sidx + 1, p, pidx + 1);
    }

    public boolean isMatchBU(String s, String p) {
        // init base cases
        int slen = s.length(), plen = p.length();
        boolean[][] res = new boolean[slen + 1][plen + 1]; // all elements are initialized with false
        int sidx = slen, pidx = plen;
        // pattern index is at the end. now for each string index
        for (; sidx >= 0; sidx--) {
            if (sidx == slen && pidx == plen) {// string index is at the end and pattern index is at the end == match
                res[sidx][pidx] = true;
            } else {// string index is NOT the end and pattern index is at the end == no match
                res[sidx][pidx] = false;
            }
        }
        // String index is at the end and if pattern has 1 or more * chars at the end
        sidx = slen;
        for (pidx = plen - 1; pidx >= 0; pidx--) {
            if (p.charAt(pidx) == '*') { // if  pattern char is * then match
                res[sidx][pidx] = true;
            } else {
                break;
            }
        }
        // now start from string last char and pattern last char
        for (sidx = slen - 1; sidx >= 0; sidx--) { // for each string char
            for (pidx = plen - 1; pidx >= 0; pidx--) { // for each pattern char
                 if(p.charAt(pidx) == '*') { // if pattern char is *
                     //case 1 skip the pattern
                     boolean case1 = res[sidx][pidx+1];
                     //case 2 skip current char of string
                     boolean case2 = res[sidx+1][pidx];
                     // if either of case1 or case2 is true
                     res[sidx][pidx] = case1 || case2;
                 } else if(p.charAt(pidx) == '?' || ( s.charAt(sidx) == p.charAt(pidx) )) { // if current pattern char is ? or current char matches string current char
                     res[sidx][pidx] = res[sidx+1][pidx+1]; // if rest of the string is matching then match
                 }
            }
        }
        return res[0][0];
    }


    public static void main(String[] args) {
        Q44WildcardMatching sol = new Q44WildcardMatching();
        String str = "aa", p = "a";
        System.out.println(String.format("String '%s', pattern '%s' : %b",str,p,sol.isMatch(str, p)));
        str="aa";p= "*";
        System.out.println(String.format("String '%s', pattern '%s' : %b",str,p,sol.isMatch(str, p)));
        str="cb";p= "?a";
        System.out.println(String.format("String '%s', pattern '%s' : %b",str,p,sol.isMatchBU(str, p)));
        str="cb";p= "?b";
        System.out.println(String.format("String '%s', pattern '%s' : %b",str,p,sol.isMatchBU(str, p)));
        str="adceb";p= "*a*b";
        System.out.println(String.format("String '%s', pattern '%s' : %b",str,p,sol.isMatchBU(str, p)));
        str = "adceb";
        p = "a*c*b*";
        System.out.println(String.format("String '%s', pattern '%s' : %b", str, p, sol.isMatchBU(str, p)));
        str="acdcb";p= "a*c?b";
        System.out.println(String.format("String '%s', pattern '%s' : %b",str,p,sol.isMatchBU(str, p)));
        str="ho";p= "ho**";
        System.out.println(String.format("String '%s', pattern '%s' : %b",str,p,sol.isMatchBU(str, p)));
    }

}
