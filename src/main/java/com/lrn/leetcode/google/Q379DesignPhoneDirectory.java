package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Q379DesignPhoneDirectory {
    /*
    * pd: Design a Phone Directory which supports the following operations:
get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
    * assm: 1 <  num < 10000
    *
    * */

    static class PhoneDirectory {

        /**
         * Initialize your data structure here
         *
         * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
         */
        int maxNum;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        public PhoneDirectory(int maxNumbers) {
            for (int idx = 0; idx < maxNumbers; idx++) {
                minHeap.add(idx);
            }
            this.maxNum = maxNumbers;
        }

        /**
         * Provide a number which is not assigned to anyone.
         *
         * @return - Return an available number. Return -1 if none is available.
         */
        public int get() {
            if (!minHeap.isEmpty()) {
                return minHeap.poll();
            }
            return -1;

        }

        /**
         * Check if a number is available or not.
         */
        public boolean check(int number) {
            return minHeap.contains(number);
        }

        /**
         * Recycle or release a number.
         */
        public void release(int number) {
            if (number < maxNum && !minHeap.contains(number)) {
                minHeap.add(number);
            }
        }
    }

    public static void main(String[] args) {
        PhoneDirectory sol = null;
        final List<String> actions = Arrays.asList("PhoneDirectory", "release", "get", "check", "check", "release", "check", "get", "check", "check", "check");
        final List<Integer> inp = Arrays.asList(2, 1, -1, 1, 1, 1, 1, -1, 0, 1, 1);
        for(int idx=0; idx<actions.size(); idx++) {
            String action = actions.get(idx);
            switch (action) {
                case "PhoneDirectory": sol = new PhoneDirectory(inp.get(idx)); System.out.println("null"); break;
                case "get": System.out.println(sol.get()); break;
                case "check": System.out.println(sol.check(inp.get(idx))); break;
                case "release": sol.release(inp.get(idx)); System.out.println("null"); break;
                default:
                    throw new IllegalStateException("Unexpected value: " + action);
            }
        }

    }

}
