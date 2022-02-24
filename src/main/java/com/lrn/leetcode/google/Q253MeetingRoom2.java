package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Q253MeetingRoom2 {
    /*
     * pd: Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
     * assm: input arr size < int max, best time sol, int stat and end are non -ve nums
     * appr: sort time interval by start time
     *
     * test cases:
     * Input: [[0, 30],[5, 10],[15, 20]] Output: 2
     * Input: [[7,10],[2,4]] Output: 1
     * */

    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null ) {
            return 0;
        }
        if(intervals.length < 2) {
            return intervals.length;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((e1,e2) -> e1[1] - e2[1]);
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int maxSize = 0;
        for(int[] interval : intervals) {
             while(!minHeap.isEmpty() && minHeap.peek()[1] <= interval[0]) {
                 minHeap.poll();
             }
             minHeap.add(interval);
             maxSize = Math.max(maxSize, minHeap.size());
        }
        return maxSize;
    }

    public static void main(String[] args) {
        Q253MeetingRoom2 sol = new Q253MeetingRoom2();
        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };
        System.out.println(sol.minMeetingRooms(intervals) == 2 ? "pass" : "fail");
        intervals = new int[][] {
                {7, 10},
                {2, 4}
        };
        System.out.println(sol.minMeetingRooms(intervals) == 1 ? "pass" : "fail");
        intervals = new int[][] {};
        System.out.println(sol.minMeetingRooms(intervals) == 0 ? "pass" : "fail");
    }

}
