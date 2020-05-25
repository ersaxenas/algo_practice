package com.lrn.educative.crust;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class StackQueueWrkUtils {
    /*Implement Stack Using Queues*/
    static class StackUsingQueues {
        public Queue<Integer> queueForPush = new LinkedList<>();
        public Queue<Integer> queueBackup = new LinkedList<>();

        public void push(Integer data) {
            if (data == null) {
                return; // throw exception
            }
            queueForPush.add(data);
        }

        public Integer pop() {
            if (queueForPush.isEmpty()) {
                return -1;
            } else {
                while (queueForPush.size() > 1) {
                    queueBackup.add(queueForPush.poll());
                }
            }
            Integer elem = queueForPush.poll();
            queueForPush.addAll(queueBackup);
            queueBackup.clear();
            return elem;
        }

        public static void main(String[] args) {
            StackUsingQueues sqc = new StackUsingQueues();
            System.out.println("Pop(): " + sqc.pop());
            sqc.push(3);
            sqc.push(5);
            sqc.push(9);
            System.out.println("Pop(): " + sqc.pop());
            sqc.push(10);
            sqc.push(16);
            System.out.println("Pop(): " + sqc.pop());
        }

    }

    static class StackUsingQueues2 {
        public Queue<Integer> queue1 = new LinkedList<>();
        public Queue<Integer> queue2 = new LinkedList<>();
        public Queue<Integer> queueForPop = queue1;

        public void push(Integer data) {
            if (queue1.isEmpty() && queue2.isEmpty()) {
                queue1.add(data);
                queueForPop = queue1;
            } else if (queue1.isEmpty()) {
                queue1.add(data);
                queue1.addAll(queue2);
                queue2.clear();
                queueForPop = queue1;
            } else {
                queue2.add(data);
                queue2.addAll(queue1);
                queue1.clear();
                queueForPop = queue2;
            }
        }

        public Integer pop() {
            if (queueForPop.isEmpty()) {
                return -1;
            }
            return queueForPop.poll();
        }

        public static void main(String[] args) {
            StackUsingQueues2 sqc = new StackUsingQueues2();
            System.out.println("Pop(): " + sqc.pop());
            sqc.push(3);
            sqc.push(5);
            sqc.push(9);
            System.out.println("Pop(): " + sqc.pop());
            sqc.push(10);
            sqc.push(16);
            System.out.println("Pop(): " + sqc.pop());
        }
    }

    /*Implement Queue Using Stacks*/
    static class QueueUsingStack {
        Stack<Integer> stackForEnqueue = new Stack<>();
        Stack<Integer> stackForDequeue = new Stack<>();

        public void enqueue(Integer data) {
            stackForEnqueue.push(data);
        }

        public Integer dequeue() {
            if (stackForDequeue.isEmpty()) {
                while (!stackForEnqueue.isEmpty()) {
                    stackForDequeue.push(stackForEnqueue.pop());
                }
            }
            return stackForDequeue.isEmpty() ? -1 : stackForDequeue.pop();
        }

        public static void main(String[] args) {
            QueueUsingStack qsc = new QueueUsingStack();
            System.out.println("dequeue()" + " = " + qsc.dequeue());
            qsc.enqueue(3);
            qsc.enqueue(6);
            qsc.enqueue(16);
            System.out.println("dequeue()" + " = " + qsc.dequeue());
            qsc.enqueue(8);
            qsc.enqueue(4);
            System.out.println("dequeue()" + " = " + qsc.dequeue());
        }
    }

    /*Evaluate Arithmetic Expressions*/
    static class ArithmetciExpressionEvaluator {
        Stack<Character> stackOperation = new Stack<>();
        Set<Character> operatorSet = new HashSet<>(Arrays.asList('+','-','*','/'));

        public boolean isOperator(Character ch) {
            return operatorSet.contains(ch);
        }

        public boolean precede(Character op1, Character op2) {
            return (op1.equals('*') || op1.equals('/')) && (op2.equals('+') || op2.equals('-'));
        }

        public List<String> convertToPostfix(String expression) {
            List<String> postfix = new ArrayList<>();
            StringBuilder sbr = new StringBuilder();
            for(int idx=0; idx<expression.length(); idx++) {
                Character ch = expression.charAt(idx);
                if(ch.equals(' ') || ch.equals('\t')) {
                    continue;
                } // 3 + 44 + 7 * 3
                if(isOperator(ch)) {
                    postfix.add(sbr.toString());
                    sbr.setLength(0);
                    if(!stackOperation.isEmpty() && !precede(ch, stackOperation.peek())) {// lower precedence operator at the top
                     while(!stackOperation.isEmpty()) {
                         postfix.add(String.valueOf(stackOperation.pop()));
                     }
                        stackOperation.push(ch);
                    } else {
                        stackOperation.push(ch);
                    }
                } else {
                    sbr.append(ch);
                }
            }
            if(sbr.length() > 0) {
                postfix.add(sbr.toString());
            }
            while(!stackOperation.isEmpty()) {
                postfix.add(String.valueOf(stackOperation.pop()));
            }
            postfix.forEach(System.out::println);
            return postfix;
        }

        public Double evaluatePostFix(List<String> postFix) {
            Stack<Double> operands = new Stack<>();
            Double d1,d2;
            for (String elem : postFix) {
                 switch(elem) {
                     case "*" :
                         Double mul = operands.pop() * operands.pop();
                         operands.push(mul);
                         break;
                     case "/" :
                          d1 = operands.pop();
                          d2 = operands.pop();
                         Double div = d2 / d1;
                         operands.push(div);
                         break;
                     case "+" :
                         Double sum = operands.pop() + operands.pop();
                         operands.push(sum);
                         break;
                     case "-" :
                          d1 = operands.pop();
                          d2 = operands.pop();
                         Double sub = d2 -d1;
                         operands.push(sub);
                         break;
                     default:
                         operands.push(Double.valueOf(elem));
                 }
            }
            return operands.pop();
        }

        public Double solveArithmaticExpr(String expr) {
            List<String> postFix= convertToPostfix(expr);
            return evaluatePostFix(postFix);
        }

        public static void main(String[] args) {
            ArithmetciExpressionEvaluator arithmetciExpressionEvaluator = new ArithmetciExpressionEvaluator();
            System.out.println(arithmetciExpressionEvaluator.solveArithmaticExpr("3+6*5-1/2.5"));
        }


    }

}
