package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q227BasicCalculator2 {
    /*
    * pd: Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
    * assm: best time sol, string len < int max, given expression is always valid
    * appr: same as q224
    * test case:
    * Input: "3+2*2" Output: 7
    * Input: " 3/2 " Output: 1
    * Input: " 3+5 / 2 " Output: 5
    * */

    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Deque<Character> queue = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (ch != ' ') {
                queue.add(ch);
            }
        }
        queue.add('#');
        return evalExpr(queue);
    }

    public int evalExpr(Deque<Character> queue) {
        int prvNum = 0, num = 0, sum = 0;
        char prvsign = '+';
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            if (ch >= '0' && ch <= '9') {
                num = num * 10 + (ch - '0');
            } else {
                switch (prvsign) {
                    case '+':
                        sum = sum + prvNum;
                        prvNum = num;
                        break;
                    case '-':
                        sum = sum + prvNum;
                        prvNum = -num;
                        break;
                    case '*':
                        prvNum = prvNum * num;
                        break;
                    case '/':
                        prvNum = (int) Math.floor(prvNum / num);
                        break;
                }
                num = 0;
                prvsign = ch;
            }
        }
        return sum + prvNum;
    }

    public static void main(String[] args) {
        Q227BasicCalculator2 sol = new Q227BasicCalculator2();
        System.out.println(sol.calculate("3+2*2") == 7 ? "pass" : "fail");
        System.out.println(sol.calculate(" 3/2 ") == 1 ? "pass" : "fail");
        System.out.println(sol.calculate("3+5 / 2") == 5 ? "pass" : "fail");
    }


}
