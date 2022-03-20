package com.lrn.leetcode.google;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Q022GenerateParentheses {
    /*2021-12-04T06:17:56.457Z
    * https://leetcode.com/problems/generate-parentheses/
    * PD: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
    * Asmp: 1 < N < int max, best time solution
    * Apch: 1. add ( if count of op < N 2. add ) only if we have (
    *       We start by adding ( and keeping the count the we add )
    *       then we removed one ( theses
    * Time complexity : O(2^(2N))
    * */

    public List<String> generateParentheses(int n) {
        ArrayList<String> lst = new ArrayList<>();
        if(n <0) {
            return lst;
        }
        genPar(lst, new StringBuilder(), 0,0, n);
        return lst;
    }

    /*
    * ((( )))
    *
    * */
    public void genPar(List<String> result, StringBuilder buffer, int op, int cp, int N) {
        // base case
          if(op >= N && cp >= N) {
              result.add(buffer.toString());
              return;
          }

        // recursive case
        if(op < N ) {
            buffer.append("(");
            genPar(result, buffer, op+1, cp, N);
            buffer.setLength(buffer.length()-1);
        }

        if(op > 0 && cp < op) {
            buffer.append(")");
            genPar(result, buffer, op, cp+1, N);
            buffer.setLength(buffer.length()-1);
        }
    }

    public static void main(String[] args) {
        Q22GenerateParentheses generateParentheses = new Q22GenerateParentheses();
        List<String> res = generateParentheses.generateParentheses(1);
        if(!res.isEmpty()) {
            System.out.println(String.join(",", res));
        } else {
            System.out.println("empty result");
        }
    }

}
