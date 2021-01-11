package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class Q359LoggerRateLimiter {
    /*
    * pd: Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
It is possible that several messages arrive roughly at the same time.
    * assm: best time sol, text len < 10000 chars, non null text.
    * appr:
    * test cases:
    * */
    class LogMP {
        String message;
        int timestamp;

        public LogMP(String message, int timestamp) {
            this.message = message;
            this.timestamp = timestamp;
        }
    }
    Queue<LogMP> queue = new ArrayDeque<>();
    HashSet<String> hashSet = new HashSet<>();
    int timeWindow = 10;

    public boolean shouldPrintMessage(int timestamp, String message) {
        if(queue.isEmpty()) {
            queue.add(new LogMP(message, timestamp));
            hashSet.add(message);
            return true;
        } else {
            while(!queue.isEmpty() && timestamp - queue.peek().timestamp >= timeWindow) {
                hashSet.remove(queue.poll().message);
            }
            if(hashSet.add(message)) {
                queue.add(new LogMP(message, timestamp));
                return true;
            }
        }
       return false;
    }

    public static void main(String[] args) {
        Q359LoggerRateLimiter logger = new Q359LoggerRateLimiter();

// logging string "foo" at timestamp 1
        System.out.println(logger.shouldPrintMessage(1, "foo") ? "pass" : "fail");

// logging string "bar" at timestamp 2
        System.out.println(logger.shouldPrintMessage(2, "bar") ? "pass" : "fail");

// logging string "foo" at timestamp 3
        System.out.println(!logger.shouldPrintMessage(3, "foo") ? "pass" : "fail");

// logging string "bar" at timestamp 8
        System.out.println(!logger.shouldPrintMessage(8, "bar") ? "pass" : "fail");

// logging string "foo" at timestamp 10
        System.out.println(!logger.shouldPrintMessage(10, "foo") ? "pass" : "fail");

// logging string "foo" at timestamp 11
        System.out.println(logger.shouldPrintMessage(11, "foo") ? "pass" : "fail");
    }



}
