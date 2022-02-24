package com.lrn.practice;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class CapitolOne {
    /*
    * matrix game like ".", "-", "#"
    *
    * */
    /*
    * array operation
    *
    * */

    public void opr(int[] numbers, int[][] operations) {
        // went wrong here: this will be max heap PriorityQueue<Integer> min = new PriorityQueue<>((e1,e2) -> e2-e1);
        // priority queue sorts by smaller to larger - natural order
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for(int num: numbers) {
            min.add(num);
        }
        List<Integer> result = new ArrayList<>();
        for(int[] op: operations) {
            if(op[0] == 0) add(min, op[1]);
            if(op[0] == 1) append(min, op[1]);
            if(op[0] == 2) result.add(min.poll());
        }
        LsUtil.printList(result);
    }


    public void append(PriorityQueue<Integer> min, int num) {
        min.offer(num);
    }

    public void add(PriorityQueue<Integer> min, int num) {
        List<Integer> lst = new ArrayList<>();
        for(int e: min) {
            lst.add(e+num);
        }
        min.clear();
        min.addAll(lst);
    }

    public static void main(String[] args) {
        CapitolOne sol = new CapitolOne();
        int[] arr = new int[] {3,4,1,2};
        int[][] operations = {
                {2,0},
                {0,2},
                {1,2},
                {2,0}
        };
        sol.opr(arr, operations);
    }



}
