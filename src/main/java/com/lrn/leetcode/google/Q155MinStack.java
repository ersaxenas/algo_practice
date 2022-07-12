package com.lrn.leetcode.google;

import java.util.Stack;

public class Q155MinStack {
    /*2022-04-22T07:38:14.166Z
    * pd: Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
    * assm: no nulls, stack size < 1000, best time sol
    * appr: stack with node pointing to min value node
    *
    * */


   static class MinStack {

        class SNode {
           public int val;
           public SNode min;
           public SNode(int val) {
               this.val = val;
           }
           public SNode getMin() {
               if(min!=null) {
                   return min;
               }
               return this;
           }

           @Override
           public String toString() {
               return "{" +
                       "val=" + val +
                       ", min=" + getMin().val +
                       '}';
           }
       }

        Stack<SNode> stack = new Stack<>();
        public MinStack() { }

        public void push(int x) {
           SNode node = new SNode(x);
          if(!stack.isEmpty() && x >= stack.peek().getMin().val){
              node.min=stack.peek().getMin();
          }
          stack.push(node);
        }

        public void pop() {
          stack.pop();
        }

        public int top() {
           return stack.peek().val;
        }

        public int getMin() {
           return stack.peek().getMin().val;
        }

    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

}
