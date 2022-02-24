package com.lrn.leetcode.google;

import java.util.LinkedList;
import java.util.Queue;

public class Q38CountAndSay {

    /* 2021-12-20T15:55:07.508Z
      https://leetcode.com/problems/count-and-say/
     */
    public String countAndSay(int n) {
        // n==0
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        countAndSayRec(n, queue);
        StringBuilder sbr = new StringBuilder();
        for(int elem: queue) {
            sbr.append(String.valueOf(elem));
        }
        return sbr.toString();
    }

    public void countAndSayRec(int n, Queue<Integer> queue) {
        // base case
        if (n <= 1) {
             return;
        }
        // recursive
        int cursize = queue.size();
        Integer prevdig = null, cnt = 0;
        for (int idx = 0; idx < cursize; idx++) {
            int curdig = queue.poll();
            if (prevdig == null) { // starting
                prevdig = curdig;
                cnt++;
            } else if (prevdig == curdig) { // same digit again
                cnt++;
            } else { // digit is changing
                queue.add(cnt);
                queue.add(prevdig);
                cnt = 1;
                prevdig = curdig;
            }
        }
        if(cnt!=0) {
            queue.add(cnt);
            queue.add(prevdig);
        }
        countAndSayRec(n - 1, queue);
    }

    // same approach with improvement by adding 9 at the end
    public String countAndSay2(int n) {
        // n==0
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(0);
        countAndSayRec(n, queue);
        StringBuilder sbr = new StringBuilder();
        for(int elem: queue) {
            if(elem != 0)
                sbr.append(String.valueOf(elem));
        }
        return sbr.toString();
    }

    public void countAndSayRec2(int n, Queue<Integer> queue) {
        // base case
        if (n <= 1) {
            return;
        }
        // recursive
        int cursize = queue.size();
        Integer prevdig = null, cnt = 0;
        for (int idx = 0; idx < cursize; idx++) {
            int curdig = queue.poll();
            if (prevdig == null) { // starting
                prevdig = curdig;
                cnt++;
            } else if (prevdig == curdig) { // same digit again
                cnt++;
            } else { // digit is changing
                queue.add(cnt);
                queue.add(prevdig);
                cnt = 1;
                prevdig = curdig;
            }
        }
        queue.add(0);
        countAndSayRec2(n - 1, queue);
    }


    public static void main(String[] args) {
        Q38CountAndSay sol = new Q38CountAndSay();
        for(int idx=1; idx<10;idx++) {
            System.out.println(idx + " : " +sol.countAndSay(idx));
        }
    }

}
