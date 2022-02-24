package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Queue;

public class Q224BasicCalculator {
    /*https://www.youtube.com/watch?v=C_jxn1hTn6Q
    * pd: Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
    * assm: best time sol
    * appr: queue DFS
    * test cases:
    * Input: "1 + 1" Output: 2
    * Input: " 2-1 + 2 " Output: 3
    * Input: "(1+(4+5+2)-3)+(6+8)" Output: 23
    * */

    public int calculate(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        Queue<Character> queue = new ArrayDeque<>();
        for(Character ch: s.toCharArray()) {
            if(ch != ' ') {
                queue.offer(ch);
            }
        }
        queue.offer(' ');
        return (int) evaluateExpr(queue);
    }

    public double evaluateExpr(Queue<Character> queue) {
        double sum=0, num=0, prv=0;
        char prevOp = '+';

        while(!queue.isEmpty()) {
            char ch = queue.poll();
            if(ch >= '0' && ch <= '9') {
                num = num * 10 + (ch - '0');
            } else if (ch == '('){
                num = evaluateExpr(queue);
            } else { // ch is + - * /
                switch(prevOp) { // we are calculating previous operation
                    case '+':
                        sum = sum + prv; //
                        prv = num;
                        break;
                    case '-':
                        sum = sum + prv;
                        prv = -num;
                        break;
                    case '*':
                        prv = prv * num;
                        break;
                    case '/':
                        prv = prv / num;
                        break;
                }
                if(ch == ')') {
                    break;
                }
                prevOp = ch;
                num =0;
            }
        }

       return sum + prv;
    }

    public static void main(String[] args) {
        Q224BasicCalculator sol = new Q224BasicCalculator();
        System.out.println(sol.calculate("4 - 2 + 1 * 2"));
        System.out.println(sol.calculate("1 + 1"));
        System.out.println(sol.calculate(" 2-1 + 2 "));
        System.out.println(sol.calculate("(1+(4+5+2)-3)+(6+8)"));
    }


}
