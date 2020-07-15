package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Q20ValidParentheses {
    /*
    * PD: Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    * An input string is valid if:
    * Open brackets must be closed by the same type of brackets.
    * Open brackets must be closed in the correct order.
    * Note that an empty string is also considered valid.
    * Asmp: input : will contain ( ) { } [ ] and space chars only, best time solution
    * Approach: take 3 stacks for each ty[e of parentheses
    *           start reading string char by char
    *           if open par then push to stack else if close par then pop from stack
    *           at the end if stacks are not empty then not valid else valid
    * */

    public boolean isValid(String str) {
        if(str == null || str.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for(char ch: str.toCharArray()) {
            switch (ch) {
                case '(' :
                     stack.push(ch);
                    break;
                case ')' :
                    if(stack.isEmpty() || stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                    break;
                case '{' :
                    stack.push(ch);
                    break;
                case '}' :
                    if(stack.isEmpty() || stack.peek() != '{') {
                        return false;
                    }
                    stack.pop();
                    break;
                case '[' :
                    stack.push(ch);
                    break;
                case ']' :
                    if(stack.isEmpty() || stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                    break;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String str) {
        if(str == null || str.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for(int idx=0; idx<str.length(); idx++) {
            char ch = str.charAt(idx);
            if(ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if(ch == ')' || ch == '}' || ch == ']') {
                if(stack.isEmpty()) {
                    return false;
                }
                Character peek = stack.peek();
                if((ch == ')' && peek != '(') || (ch == '}' && peek != '{') || (ch == ']' && peek != '[') ) {
                   return false;
               }
               stack.pop();
            }
           if(stack.size() > str.length()) {
               return false;
           }
        }
        return stack.isEmpty();
    }

    public boolean isValid3(String str) {
        if(str == null || str.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for(int idx=0; idx<str.length(); idx++) {
            char ch = str.charAt(idx);
            if(ch == ' ') {continue;}
            if(ch == '(') {
                stack.push(')');
            } else if(ch == '{') {
                stack.push('}');
            } else if( ch == '[') {
                stack.push(']');
            } else if(stack.isEmpty() || stack.pop() != ch || stack.size() > str.length()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Q20ValidParentheses q20ValidParentheses = new Q20ValidParentheses();
        System.out.println(q20ValidParentheses.isValid3("()"));
        System.out.println(q20ValidParentheses.isValid3("() {} [ ]"));
        System.out.println(q20ValidParentheses.isValid3("( ]"));
        System.out.println(q20ValidParentheses.isValid3("([)]"));
        System.out.println(q20ValidParentheses.isValid3("{[]}"));
        System.out.println(q20ValidParentheses.isValid3(""));
        System.out.println(q20ValidParentheses.isValid3("()(){}{{{{{((((((((((((((((((((((((((((((((((((((({{{{{{{{{))))"));
    }


}
