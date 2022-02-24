package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q753CrackingtheSafe {
    /*
    * pd: There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.
While entering a password, the last n digits entered will automatically be matched against the correct password.
For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.
Return any password of minimum length that is guaranteed to open the box at some point of entering it.
    * assm: 1 < n < 4 , 1< K < 10
    * appr: dfs
    * test cases:
    *
    * */

    public String crackSafe(int n, int k) {
        StringBuilder sbr = new StringBuilder();
        for (int idx = 0; idx < n; idx++) {
            sbr.append('0');
        }
        int noOfCombPossible = (int) Math.pow(k, n);
        HashSet<String> visited = new HashSet<>();
        visited.add(sbr.toString());
        crackSafeRec(sbr, visited,noOfCombPossible, n, k);
        return sbr.toString();
    }

    private boolean crackSafeRec(StringBuilder pwd, Set<String> visitedComb, int noOfCombPossible, int n, int k) {
        if (noOfCombPossible == visitedComb.size()) {
            return true;
        }
        String lastDigits = pwd.substring(pwd.length()-n+ 1);
        for (int idx = 0; idx < k; idx++) {
          String comb = lastDigits+(char)('0'+idx);
          if(visitedComb.add(comb)) {
              pwd = pwd.append((char)('0'+idx));
              if(crackSafeRec(pwd,visitedComb,noOfCombPossible, n, k)) {
                  return true;
              }
              visitedComb.remove(comb);
              pwd.setLength(pwd.length()-1);
          }
        }
        return false;
    }

    public static void main(String[] args) {
        Q753CrackingtheSafe sol = new Q753CrackingtheSafe();
        System.out.println(sol.crackSafe(2, 2));
    }

}
