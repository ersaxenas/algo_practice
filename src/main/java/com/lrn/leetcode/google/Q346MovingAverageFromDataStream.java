package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Queue;

public class Q346MovingAverageFromDataStream {
    /*
    * pd: Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
    * assm: size < 100, element are int, best time sol
    * appr: sliding window
    * test cases:
    * */
    Queue<Integer> queue = new ArrayDeque<>();
    int size=0;
    double sum;
    public void movingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        if(!queue.isEmpty() && queue.size() == this.size) {
            sum = sum - queue.poll();
        }
        queue.add(val);
        sum = sum + val;
        return sum / queue.size();
    }

    public static void main(String[] args) {
        Q346MovingAverageFromDataStream sol = new Q346MovingAverageFromDataStream();
        sol.movingAverage(3);
        System.out.println(sol.next(1));
        System.out.println(sol.next(10));
        System.out.println(sol.next(3));
        System.out.println(sol.next(5));
    }

}
