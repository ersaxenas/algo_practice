package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q282ExpressionAddOp {
    /*
     *  pd: Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
     * */

    public List<String> addOperators(String num, int target) {
          if(num.length() == 0) {
              return new ArrayList<>();
          }
          List<String> result = new ArrayList<>();
          backtrack(result, num, 0, 0, 0, "", target);
          return result;
    }

    public void backtrack(List<String> result, String num, int pos, long calcVal, long prevNum, String expr, int target) {
        if (pos == num.length()) {
            if (calcVal == target) {
                result.add(expr);
            }
            return;
        }
        for (int idx = pos; idx < num.length(); idx++) {
            if(idx!=pos && num.charAt(pos) == '0') {
                break;
            }
            long curNum = Long.parseLong(num.substring(pos, idx + 1));
            if (pos == 0) {
                backtrack(result, num, idx + 1, curNum, curNum, expr + curNum, target);
            } else {
                backtrack(result, num, idx + 1, calcVal+curNum, curNum, expr +"+"+curNum, target);
                backtrack(result, num, idx + 1, calcVal-curNum, -curNum, expr +"-"+curNum, target);
                backtrack(result, num, idx + 1, calcVal - prevNum + prevNum * curNum, prevNum * curNum, expr +"*"+curNum, target);
            }
        }
    }

    public static void main(String[] args) {
        Q282ExpressionAddOp sol = new Q282ExpressionAddOp();
        //LsUtil.printList(sol.addOperators("232", 8));
        LsUtil.printList(sol.addOperators("105", 5));
    }


}
