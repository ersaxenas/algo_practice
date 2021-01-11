package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q946ValidateStackSequences {
    /*
    * pd: Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
    * assm: arr len < 1000, 0 < elem < 1000, elem are distinct, best time sol
    * appr: two pointers
    * test cases:
    * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
* Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false
Explanation: 1 cannot be popped before 2.
    * */

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> deque = new ArrayDeque<>();
        int pidx=0;
        for(int pe : pushed) {
            deque.push(pe);
            while(!deque.isEmpty() && deque.peek() == popped[pidx]) {
                deque.pop();
                pidx++;
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        Q946ValidateStackSequences sol = new Q946ValidateStackSequences();
        System.out.println(sol.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(sol.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }


}
