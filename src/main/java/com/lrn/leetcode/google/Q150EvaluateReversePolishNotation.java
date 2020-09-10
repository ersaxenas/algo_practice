package com.lrn.leetcode.google;

import java.util.Stack;

public class Q150EvaluateReversePolishNotation {
    /*
    * pd: Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.
Note:
Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
    * test cases:
    * Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
* Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
    * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
*  assm: non null elem, array len < 1000, best time sol, expression is valid and always results in a valid number, no divide by 0 scenario
* appr: iterate over array
*       keep storing numbers in the stack
*       When encountered a * / + - sign the two elements from the stack and perform the operation and store the result in the stack.
*       when finished with the array return top elem of the stack.
    * */

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        String operation = "*/+-";
        int num1, num2;
        for (String token : tokens) {
            if (operation.contains(token)) {
                num1 = stack.pop();
                num2 = stack.pop();
                if ("+".equals(token)) {
                    stack.push(num2 + num1);
                } else if ("-".equals(token)) {
                    stack.push(num2 - num1);
                } else if ("*".equals(token)) {
                    stack.push(num2 * num1);
                } else if ("/".equals(token)) {
                    stack.push(num2 / num1);
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        Q150EvaluateReversePolishNotation sol = new Q150EvaluateReversePolishNotation();
        System.out.println(sol.evalRPN(new String[]{"4", "-2", "/", "2", "-3", "-", "-"}));
        System.out.println(sol.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(sol.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(sol.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

}
