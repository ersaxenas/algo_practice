package com.lrn.leetcode.weeklycomp;

import java.util.*;

public class WContest287 {


    /**
     * https://leetcode.com/contest/weekly-contest-287/problems/minimum-number-of-operations-to-convert-time/
     * You are given two strings current and correct representing two 24-hour times.
     *
     * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
     *
     * In one operation you can increase the time current by 1, 5, 15, or 60 minutes. You can perform this operation any number of times.
     *
     * Return the minimum number of operations needed to convert current to correct.
     */

    static class NumOperation {
        public int convertTime(String current, String correct) {
            int curh = Integer.valueOf(current.substring(0,2))*60 + Integer.valueOf(current.substring(3));
            int corh = Integer.valueOf(correct.substring(0,2))*60 + Integer.valueOf(correct.substring(3));
            System.out.println(curh + ", "+ corh);

            int mindiff = corh - curh;
            int op =0;
            List<Integer> lst = Arrays.asList(60,15,5,1);
            for(int div: lst) {
                if(mindiff >= div) {
                    op = op + mindiff / div;
                    mindiff = mindiff % div;
                    System.out.println(op + ", "+ mindiff);
                }
            }
            return op;
        }

        public static void main(String[] args) {
            NumOperation numOperation = new NumOperation();
            numOperation.convertTime("09:41", "10:34");
        }

    }


}
