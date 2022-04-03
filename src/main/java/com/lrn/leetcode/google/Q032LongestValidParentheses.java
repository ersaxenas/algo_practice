package com.lrn.leetcode.google;

import java.util.Stack;

public class Q032LongestValidParentheses {

    /* 2021-12-16T06:37:30.792Z
      https://leetcode.com/problems/longest-valid-parentheses/
     */
    public int longestValidParentheses(String s) {
          Stack<Integer> stack = new Stack<>();
          stack.push(-1);
          int res = 0;
          for(int idx=0; idx<s.length(); idx++) {
              if(s.charAt(idx) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                  // if current char is closing ) and check if previous char is opening ( and size > 1 because we push -1 to stack to cover edge cases
                 stack.pop(); // remove opening (
                 int indexOnTop = stack.peek();
                 res = Math.max(res, (idx-indexOnTop)); // calculate the difference between index
              } else {
                  stack.push(idx);
              }
          }
          return res;
    }

    public static void main(String[] args) {
        Q032LongestValidParentheses sol = new Q032LongestValidParentheses();
        System.out.println(sol.longestValidParentheses(")()"));
    }
}
