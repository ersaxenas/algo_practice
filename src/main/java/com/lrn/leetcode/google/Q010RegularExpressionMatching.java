package com.lrn.leetcode.google;


public class Q010RegularExpressionMatching {
    /*hard : https://leetcode.com/problems/regular-expression-matching/*/
    public boolean isMatch(String str, String pattern) {
        if (pattern.isEmpty()) {
            return str.isEmpty();
        }
        return isMatch(str,pattern,0,0);
    }

    public boolean isMatch(String str, String pt, int sidx, int pidx) {
        //base
        if (pt.length() == pidx) {
            return str.length() == sidx;
        }
        // recursive
        boolean isDotOrAzMatch = ((sidx < str.length() && pidx < pt.length()) && (pt.charAt(pidx) == '.' || str.charAt(sidx) == pt.charAt(pidx))); // is current char . or matches the pattern

        if(pidx+1 < pt.length() && pt.charAt(pidx+1) == '*') { // if next char is *
          if(isMatch(str,pt,sidx,pidx+2)) { // skip pattern - case of 0 match
              return true;
          }
          return isDotOrAzMatch && isMatch(str,pt,sidx+1, pidx); // 1 or more match move to next char in text. case .* or a-z*
        }
        return isDotOrAzMatch && isMatch(str, pt, sidx+1,pidx+1); // check next char
    }

    public static void main(String[] args) {
        Q10RegularExpressionMatching q10RegularExpressionMatching = new Q10RegularExpressionMatching();
        System.out.println(q10RegularExpressionMatching.isMatch("abcd","d*"));
        System.out.println(q10RegularExpressionMatching.isMatch("aaab","a*a"));
        System.out.println(q10RegularExpressionMatching.isMatch("aaaaab",".*c"));
        System.out.println(q10RegularExpressionMatching.isMatch("aa","a"));
        System.out.println(q10RegularExpressionMatching.isMatch("aaa","a*a"));
        System.out.println(q10RegularExpressionMatching.isMatch("aa","a*"));
        System.out.println(q10RegularExpressionMatching.isMatch("aa","*"));
        System.out.println(q10RegularExpressionMatching.isMatch("ab",".*"));
        System.out.println(q10RegularExpressionMatching.isMatch("aab","c*a*b"));
        System.out.println(q10RegularExpressionMatching.isMatch("mississippi","mis*is*ip*i"));
        System.out.println(q10RegularExpressionMatching.isMatch("mississippi","mis*is*p*i"));
        System.out.println(q10RegularExpressionMatching.isMatch("",".*"));
    }

}
