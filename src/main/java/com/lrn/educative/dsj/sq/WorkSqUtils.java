package com.lrn.educative.dsj.sq;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class WorkSqUtils {
    /*Generate Binary Numbers from 1 to n using Queue*/
    static class BinaryNumGen {
        public String[] generate(int num) {
            String[] result = null;
            if (num <= 0) {
                return result;
            }
            result = new String[num];
            Queue<String> queue = new LinkedList<>();
            queue.add("1");
            for (int idx = 0; idx < num; idx++) {
                String elem = queue.poll();
                result[idx] = elem;
                queue.offer(elem + "0");
                queue.offer(elem + "1");
            }
            return result;
        }

        public static void main(String[] args) {
            BinaryNumGen binaryNumGen = new BinaryNumGen();
            String[] output = binaryNumGen.generate(5);
            for (int i = 0; i < 5; i++)
                System.out.print(output[i] + " ");
        }
    }

    /*Implement Two Stacks using one Array*/
    static class TwoStacksArray {
        int[] arr;
        int top1, top2;
        int arrSize;

        public TwoStacksArray(int size) {
            arr = new int[size];
            top1 = -1;
            top2 = size;
            arrSize = size;
        }

        public boolean isEmpty1() {
            return top1 == -1;
        }

        public boolean isEmpty2() {
            return top2 == arrSize;
        }

        public boolean isFull() {
            return top1 + 1 == top2;
        }

        public boolean push1(int elem) {
            if (isFull()) {
                return false;
            }
            arr[++top1] = elem;
            return true;
        }

        public boolean push2(int elem) {
            if (isFull()) {
                return false;
            }
            arr[--top2] = elem;
            return true;
        }

        public Integer pop1() {
            if (isEmpty1()) {
                return null;
            }
            return arr[top1--];
        }

        public Integer pop2() {
            if (isEmpty2()) {
                return null;
            }
            return arr[top2++];
        }

        public static void main(String[] args) {
            TwoStacksArray tS = new TwoStacksArray(5);
            tS.push1(11);
            tS.push1(3);
            tS.push1(7);
            tS.push2(1);
            tS.push2(9);

            System.out.println(tS.pop1());
            System.out.println(tS.pop2());
            System.out.println(tS.pop2());
            System.out.println(tS.pop2());
            System.out.println(tS.pop1());
        }
    }

    /*Reversing the First k Elements of a Queue*/
    static class ReverseFirstKElements {
        public void reverse(Queue<Integer> queue, int k) {
            if (k <= 0 || queue == null || queue.isEmpty()) {
                return;
            }
            Stack<Integer> stack = new Stack<>();
            for (int idx = 0; idx < k; idx++) {
                if (queue.isEmpty()) break;
                stack.push(queue.poll());
            }
            while (!stack.isEmpty()) {
                queue.offer(stack.pop());
            }
            for (int idx = 0; idx < queue.size() - k; idx++) {
                queue.offer(queue.poll());
            }
        }

        public static void main(String[] args) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(1);
            queue.offer(2);
            queue.offer(3);
            queue.offer(4);
            queue.offer(5);
            queue.offer(6);
            queue.offer(7);
            queue.offer(8);
            queue.offer(9);
            queue.offer(10);
            ReverseFirstKElements reverseFirstKElements = new ReverseFirstKElements();
            reverseFirstKElements.reverse(queue, 5);

            System.out.print("Queue: ");
            while (!queue.isEmpty()) {
                System.out.print(queue.poll() + " ");
            }
        }
    }

    /*Implement Queue using Stack*/
    static class QueueUsingStack {
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public void enqueue(int value) {
            stack1.push(value);
        }

        public Integer dequeue() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
            return null;
        }

        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        public static void main(String[] args) {
            QueueUsingStack queue = new QueueUsingStack();

            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);
            queue.enqueue(5);
            System.out.println(queue.dequeue());
            queue.enqueue(6);
            queue.enqueue(7);
            queue.enqueue(8);
            System.out.println(queue.dequeue());
            queue.enqueue(9);
            queue.enqueue(10);
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
        }
    }

    /*Sort the Values in a Stack*/
    static class StackSort {
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public void sort(Stack<Integer> stack) {
            if (stack == null || stack.empty()) {
                return;
            }
            while (!stack.isEmpty()) {
                Integer elem = stack.pop();
                if (stack2.isEmpty() || elem > stack2.peek()) {
                    stack2.push(elem);
                } else {
                    while (!stack2.isEmpty() && stack2.peek() > elem) {
                        stack.push(stack2.pop());
                    }
                    stack2.push(elem);
                }
            }
            while (!stack2.isEmpty()) {
                stack.push(stack2.pop());
            }
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(97);
        stack.push(4);
        stack.push(42);
        stack.push(12);
        stack.push(60);
        stack.push(23);
        StackSort stackSort = new StackSort();
        stackSort.sort(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    static class PostfixEvaluator {
        public Double evaluate(String expression) {
            if (expression == null || expression.trim().length() == 0) {
                return null;
            }
            Set<Character> characterSet = new HashSet<>(Arrays.asList('-', '+', '*', '/'));
            Stack<Double> doubleStack = new Stack<>();
            String[] arr = null;
            if(expression.contains(",")) {
                arr = expression.split(",");
            } else {
                final char[] chars = expression.toCharArray();
                arr = new String[chars.length];
                for(int idx=0; idx<chars.length; idx++) {
                  arr[idx] = Character.toString(chars[idx]);
                }
            }
            for (int idx = 0; idx < arr.length; idx++) {
                String val = arr[idx];
                if (val.length() > 1) {
                    doubleStack.push(Double.valueOf(val));
                    continue;
                }
                char ch = val.charAt(0);

                if (Character.isDigit(ch)) {
                    doubleStack.push((double) Character.getNumericValue(ch));
                } else {
                    double op2 = doubleStack.pop();
                    double op1 = doubleStack.pop();
                    switch (ch) {
                        case '+':
                            doubleStack.push(op1 + op2);
                            break;
                        case '-':
                            doubleStack.push(op1 - op2);
                            break;
                        case '*':
                            doubleStack.push(op1 * op2);
                            break;
                        case '/':
                            doubleStack.push(op1 / op2);
                            break;
                        default:
                            System.out.println("Invalid character: " + ch);
                    }
                }
            }
            return doubleStack.pop();
        }

        public static void main(String[] args) {
            PostfixEvaluator postfixEvaluator = new PostfixEvaluator();
            System.out.println(postfixEvaluator.evaluate("921*-8-4+"));
            System.out.println(postfixEvaluator.evaluate("9,4,2,+,*,6,14,7,/,+,*"));
        }
    }

    /*Next Greater Element using Stack*/
    static class NextGreaterElement {
        public Integer[] getNextGreaterNum(int[] arr) {
            if (arr == null || arr.length == 0) {
                return null;
            }
            Integer[] result = new Integer[arr.length];
            Stack<Integer> stack = new Stack<>();
            for (int idx = arr.length - 1; idx >= 0; idx--) {
                while (!stack.isEmpty() && stack.peek() <= arr[idx]) {
                    stack.pop();
                }

                if (stack.isEmpty()) {
                    result[idx] = -1;
                } else {
                    result[idx] = stack.peek();
                }
                stack.push(arr[idx]);
            }
            return result;
        }

        public static void main(String[] args) {
            NextGreaterElement nextGreaterElement = new NextGreaterElement();
            int arr[] = {4, 6, 3, 2, 8, 1, 11};
            System.out.println(Arrays.toString(arr));
            Integer result[] = nextGreaterElement.getNextGreaterNum(arr);
            System.out.println(Arrays.toString(result));
        }
    }

    /*Solve a Celebrity Problem using a Stack*/
    static class WhoIsACelebrity {
        public int findCelebrity(int[][] party, int numPeople) {
            int celebrity = -1;
            if (party == null || numPeople <= 1) {
                return celebrity;
            }
            Stack<Integer> personStack = new Stack<>();
            for (int idx = 0; idx < numPeople; idx++) {
                personStack.push(idx);
            }
            celebrity = findPersonWhoDoesntKnowAnybody(party, personStack);
            if(checkIfCeleb(party, celebrity)) {
                return celebrity;
            } else {
                return -1;
            }
        }

        public int findPersonWhoDoesntKnowAnybody(int[][] party, Stack<Integer> personStack) {
            while (!personStack.isEmpty() && personStack.size() > 1) {
                int person1 = personStack.pop();
                if (personStack.isEmpty()) {
                    return person1;
                }
                int person2 = personStack.pop();
                if (party[person1][person2] == 0) {
                    personStack.push(person1);
                } else {
                    personStack.push(person2);
                }
            }
            return personStack.pop();
        }

        public boolean checkIfCeleb(int[][] party, int person) {
             boolean result = true;
            for (int idx = 0; idx < party.length; idx++) {
                if(idx==person) {continue;}
                if(party[person][idx] == 1 || party[idx][person]==0) {
                    result = false;
                    break;
                }
            }
            return result;
        }
        public static void main(String[] args) {
            WhoIsACelebrity whoIsACelebrity = new WhoIsACelebrity();
            int [][] party1 = {
                    {0,1,1,0},
                    {1,0,1,1},
                    {0,0,0,0},
                    {0,1,1,0},
            };

            int [][] party2 = {
                    {0,1,1,0},
                    {1,0,1,1},
                    {0,0,0,1},
                    {0,1,1,0},
            };

            int [][] party3 = {
                    {0,0,0,0},
                    {1,0,0,1},
                    {1,0,0,1},
                    {1,1,1,0},
            };

            System.out.println(whoIsACelebrity.findCelebrity(party1,4));
            System.out.println(whoIsACelebrity.findCelebrity(party2,4));
            System.out.println(whoIsACelebrity.findCelebrity(party3,4));
        }
    }
    /*Check for Balanced Parentheses using a Stack*/
    static class BalancedParentheses {
        public boolean isBalanced(String expression) {
            boolean result = true;
            if(expression == null || expression.trim().length() == 0) {
                return result;
            }
            Stack<Integer> stack = new Stack<>();
            Map<Character, Integer> charToIntegerMap = new HashMap<>();
            charToIntegerMap.put('(',1);
            charToIntegerMap.put(')',-1);
            charToIntegerMap.put('{',2);
            charToIntegerMap.put('}',-2);
            charToIntegerMap.put('[',3);
            charToIntegerMap.put(']',-3);
            for (Character ch : expression.toCharArray()) {
                 if(charToIntegerMap.containsKey(ch)) {
                     final Integer item = charToIntegerMap.get(ch);
                     if(item>0) {
                         stack.push(item);
                     } else {
                         if(stack.isEmpty()) {
                             result = false;
                             break;
                         }
                         stack.pop();
                     }
                 }
            }
           if(!stack.isEmpty()) {
             result = false;
           }
            return result;
        }
        public static void main(String[] args) {
            BalancedParentheses balancedParentheses = new BalancedParentheses();
            System.out.println(balancedParentheses.isBalanced("{[({})]}"));
        }
    }
    /*Create Stack where min() gives minimum in O(1) */
    static class StackMinValO1 {
        Stack<Integer> stackMain = new Stack<>();
        Stack<Integer> stackMin = new Stack<>();

        public Integer pop() {
            if(stackMain.isEmpty()) {
                throw new EmptyStackException();
            }
            stackMin.pop();
            return stackMain.pop();
        }

        public Integer min() {
            if(stackMain.isEmpty()) {
                throw new EmptyStackException();
            }
            return stackMin.pop();
        }

        public void push(Integer val) {
            if(val == null) {
                throw new IllegalArgumentException();
            }
            stackMain.push(val);
            if(stackMin.isEmpty()) {
                stackMin.push(val);
            } else {
                if(stackMin.peek() <= val) {
                    stackMin.push(stackMin.peek());
                } else {
                    stackMin.push(val);
                }
            }
        }
        public static void main(String[] args) {
            StackMinValO1 stack = new StackMinValO1();
            stack.push(5);
            stack.push(2);
            stack.push(4);
            stack.push(1);
            stack.push(3);
            stack.push(9);
            System.out.println(stack.min());
            stack.pop();
            stack.pop();
            stack.pop();
            System.out.println(stack.min());
        }
    }



}


