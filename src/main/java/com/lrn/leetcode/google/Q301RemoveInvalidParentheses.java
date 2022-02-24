package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Q301RemoveInvalidParentheses {


    public List<String> removeInvalidParentheses(String s) {
        /*count open and close parentheses to be removed*/
        int openRemove = 0, closeRemove = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            char ch = s.charAt(idx);
            if (ch == '(') {
                openRemove++;
            } else if (ch == ')') {
                closeRemove = openRemove == 0 ? closeRemove + 1 : closeRemove;
                openRemove = openRemove > 0 ? openRemove - 1 : openRemove;
            }
        }
        final HashSet<String> result = new HashSet<>();
        makeValidExpr(s, 0, 0, 0 , openRemove, closeRemove, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    public void makeValidExpr(String s, int idx, int open, int close, int openRemove, int closeRemove, StringBuilder buffer, HashSet<String> result) {
        // base
        if (idx == s.length()) {
            if (openRemove == 0 && closeRemove == 0) {
                result.add(buffer.toString());
            }
        } else {
            // backtracking
            char ch = s.charAt(idx);
            if( (ch == '(' &&  openRemove > 0) || (ch == ')' &&  closeRemove > 0)  ) { // ignore this parentheses by not adding it to expr
                makeValidExpr(s, idx+1, open, close,
                        (ch == '(') ? openRemove-1 : openRemove, // decrement count
                        (ch == ')') ? closeRemove-1 : closeRemove, // decrement count
                        buffer,
                        result
                        );
            }
            // now we have ignored all the required open and close parentheses
            buffer.append(ch); // include char when we recurse means chars is considered in the final expression
            if(ch != '(' && ch != ')') { // if current char is not open or close parentheses
              makeValidExpr(s, idx+1, open, close, openRemove, closeRemove, buffer, result); // include char and not incrementing open and close count
            } else if(ch == '(') {
                makeValidExpr(s, idx+1, open+1, close, openRemove, closeRemove, buffer, result);
            } else if(open > close) { // only consider current closing parentheses if we have open parentheses in the expression
                makeValidExpr(s, idx+1, open, close+1, openRemove, closeRemove, buffer, result);
            }
           buffer.deleteCharAt(buffer.length()-1); // remove char from expression
        }
    }


    public static void main(String[] args) {
        Q301RemoveInvalidParentheses sol = new Q301RemoveInvalidParentheses();
         LsUtil.printList(sol.removeInvalidParentheses("()())()"));
         LsUtil.printList(sol.removeInvalidParentheses(")("));
    }

}
