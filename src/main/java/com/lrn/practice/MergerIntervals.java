package com.lrn.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergerIntervals {

    static class Interval {
        int start, end;

        public Interval(int start,int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class EmpInt {
        Interval interval;
        int idx;
        int eidx;

        public EmpInt(Interval interval, int idx, int eidx) {
            this.interval = interval;
            this.idx = idx;
            this.eidx = eidx;
        }
    }


    public List<Interval> freeTime(List<List<Interval>> schedule) {
        List<Interval> freeInterval = new ArrayList<>();
        PriorityQueue<EmpInt> minHeap = new PriorityQueue<>((a,b) -> a.interval.start - b.interval.end);
        for(int idx=0; idx<schedule.size(); idx++) {
            minHeap.add(new EmpInt(schedule.get(idx).get(0), 0, idx));
        }
        int prvEnd=minHeap.peek().interval.end;
        while(!minHeap.isEmpty()) {
              EmpInt ci = minHeap.poll();
              if(ci.interval.start > prvEnd) {
                  freeInterval.add(new Interval(prvEnd, ci.interval.start));
              }
              prvEnd = Math.max(prvEnd, ci.interval.end);
              if(ci.idx+1 < schedule.get(ci.eidx).size()) {
                  minHeap.add(new EmpInt(schedule.get(ci.eidx).get(ci.idx+1), ci.idx+1, ci.eidx));
              }
        }
        return freeInterval;
    }

    public static void main(String[] args) {
        MergerIntervals sol = new MergerIntervals();
        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result = sol.freeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        result = sol.freeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = sol.freeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
    }

}
