package com.lrn.educative.gci.mergeinterval;

import java.util.*;

public class WorkMIUtils {

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    /*Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.*/
    static class OverlappingMerger {
        public List<Interval> mergeIntervals(List<Interval> intervals) {
            if (intervals == null || intervals.size() < 2) {
                return intervals;
            }
            List<Interval> mergedIntervals = new ArrayList<>();
            Collections.sort(intervals, Comparator.comparingInt((Interval a) -> a.start));
            final ListIterator<Interval> intervalListIterator = intervals.listIterator();
            final Interval firstInterval = intervalListIterator.next();
            int prevIntervalStart = firstInterval.start, prevIntervalEnd = firstInterval.end;
            while (intervalListIterator.hasNext()) {
                final Interval currentInterval = intervalListIterator.next();
                if (currentInterval.start <= prevIntervalEnd) {
                    /*overlapping intervals so adjust the end. Now on to next interval to see if it also overlaps with new end*/
                    prevIntervalEnd = Math.max(prevIntervalEnd, currentInterval.end);
                } else {
                    /*no overlaps between prevInterval and current Interval*/
                    mergedIntervals.add(new Interval(prevIntervalStart, prevIntervalEnd));
                    /*reset start and end*/
                    prevIntervalStart = currentInterval.start;
                    prevIntervalEnd = currentInterval.end;
                }
            }
            /*merge last interval*/
            mergedIntervals.add(new Interval(prevIntervalStart, prevIntervalEnd));
            return mergedIntervals;
        }

        public static void main(String[] args) {
            OverlappingMerger overlappingMerger = new OverlappingMerger();
            List<Interval> input = new ArrayList<Interval>();
            input.add(new Interval(1, 4));
            input.add(new Interval(2, 5));
            input.add(new Interval(7, 9));
            System.out.print("Merged intervals: ");
            for (Interval interval : overlappingMerger.mergeIntervals(input))
                System.out.print("[" + interval.start + "," + interval.end + "] ");
            System.out.println();

            input = new ArrayList<Interval>();
            input.add(new Interval(6, 7));
            input.add(new Interval(2, 4));
            input.add(new Interval(5, 9));
            System.out.print("Merged intervals: ");
            for (Interval interval : overlappingMerger.mergeIntervals(input))
                System.out.print("[" + interval.start + "," + interval.end + "] ");
            System.out.println();

            input = new ArrayList<Interval>();
            input.add(new Interval(1, 4));
            input.add(new Interval(2, 6));
            input.add(new Interval(3, 5));
            System.out.print("Merged intervals: ");
            for (Interval interval : overlappingMerger.mergeIntervals(input))
                System.out.print("[" + interval.start + "," + interval.end + "] ");
            System.out.println();
        }

    }

    /*Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.*/
    /* Assumptions: sorted by start time
       max element in the array <= 1000, numbers are -ve only,

     Approach: interval in the array a<-->b, interval to be inserted x<-->y
     if b (end) < x (start) then non overlapping intervals - add to final list
     if b (end) >= x (start) then overlapping intervals - start merging two intervals. start = min(x,a) end = (y,b)
     if a (start) <= y (end) then overlapping interval continue merging
     if a (start) > y (end) then non overlapping
    * */
    static class MutuallyExclusiveInervals {
        public List<Interval> merge(List<Interval> intervals, Interval newInterval) {
            int index = 0;
            List<Interval> mergedIntervals = new ArrayList<>();
            // insert new interval to list
            while (index < intervals.size() && (intervals.get(index).end < newInterval.start)) { /*no overlap*/
                mergedIntervals.add(intervals.get(index++)); /*add non overlapping intervals*/
            }
            // merge interval that overlap with inserted interval
            while (index < intervals.size() && (intervals.get(index).start <= newInterval.end)) { /* overlap - merge*/
                newInterval.start = Math.min(intervals.get(index).start, newInterval.start);
                newInterval.end = Math.max(intervals.get(index).end, newInterval.end);
                index++;
            }
            mergedIntervals.add(newInterval);
            while (index < intervals.size()) { /*no overlap*/
                mergedIntervals.add(intervals.get(index)); /*add non overlapping intervals*/
                index++;
            }
            return mergedIntervals;
        }

        public static void main(String[] args) {
            MutuallyExclusiveInervals mutuallyExclusiveInervals = new MutuallyExclusiveInervals();
            List<Interval> input = new ArrayList<Interval>();
            input.add(new Interval(1, 3));
            input.add(new Interval(5, 7));
            input.add(new Interval(8, 12));
            System.out.print("Intervals after inserting the new interval: ");
            for (Interval interval : mutuallyExclusiveInervals.merge(input, new Interval(4, 6)))
                System.out.print("[" + interval.start + "," + interval.end + "] ");
            System.out.println();

            input = new ArrayList<Interval>();
            input.add(new Interval(1, 3));
            input.add(new Interval(5, 7));
            input.add(new Interval(8, 12));
            System.out.print("Intervals after inserting the new interval: ");
            for (Interval interval : mutuallyExclusiveInervals.merge(input, new Interval(4, 10)))
                System.out.print("[" + interval.start + "," + interval.end + "] ");
            System.out.println();

            input = new ArrayList<Interval>();
            input.add(new Interval(2, 3));
            input.add(new Interval(5, 7));
            System.out.print("Intervals after inserting the new interval: ");
            for (Interval interval : mutuallyExclusiveInervals.merge(input, new Interval(1, 4)))
                System.out.print("[" + interval.start + "," + interval.end + "] ");
            System.out.println();

        }
    }

    /*Given two lists of intervals, find the intersection of these two lists. Each list consists of disjoint intervals sorted on their start time.*/
   /*
   Assumptions: lists are not null and empty and are sorted by start time of the interval, intervals are positive numbers only. Lists can be quite long. Have sufficient memory to keep merged list in the memory. No overlapping intervals are present within the lists.
   Interval:  0 <= start time <=integer.max and 0 <= integer.max
   Approach: iterate over both the list and check if two intervals are overlapping. Fetch next interval from list which is finishing first.
   interval 1 : start1, end1
   interval 2: start2, end2
   two interval will overlap if start1 <= start2 <= end1 or start2 <= start1 <= end2.
   so overlapping intervals will be start = max(start1, start2) and end = min(end1, end2)
   * */
    static class IntersectionOfTwoListWithIntervals {
        public List<Interval> findIntersection(List<Interval> list1, List<Interval> list2) {
            List<Interval> resultList = new ArrayList<>();
            if (list1 == null || list1.isEmpty() || list2 == null || list2.isEmpty()) {
                return resultList;
            }
            Interval interval1, interval2;
            int index1 = 0, index2 = 0;
            while (index1 < list1.size() && index2 < list2.size()) {
                interval1 = list1.get(index1);
                interval2 = list2.get(index2);
                if ((interval1.start <= interval2.start && interval2.start <= interval1.end) || (interval2.start <= interval1.start && interval1.start <= interval2.end)) {
                    int start = Math.max(interval1.start, interval2.start);
                    int end = Math.min(interval1.end, interval2.end);
                    resultList.add(new Interval(start, end));
                }
                if (interval1.end <= interval2.end) { /**/
                    index1++;
                } else {
                    index2++;
                }
            }
            return resultList;
        }

        public static void main(String[] args) {
            IntersectionOfTwoListWithIntervals intersectionOfTwoListWithIntervals = new IntersectionOfTwoListWithIntervals();
            Interval[] input1 = new Interval[]{new Interval(1, 3), new Interval(5, 6), new Interval(7, 9)};
            Interval[] input2 = new Interval[]{new Interval(2, 3), new Interval(5, 7)};
            List<Interval> result = intersectionOfTwoListWithIntervals.findIntersection(Arrays.asList(input1), Arrays.asList(input2));
            System.out.print("Intervals Intersection: ");
            for (Interval interval : result)
                System.out.print("[" + interval.start + "," + interval.end + "] ");
            System.out.println();

            input1 = new Interval[]{new Interval(1, 3), new Interval(5, 7), new Interval(9, 12)};
            input2 = new Interval[]{new Interval(5, 10)};
            result = intersectionOfTwoListWithIntervals.findIntersection(Arrays.asList(input1), Arrays.asList(input2));
            System.out.print("Intervals Intersection: ");
            for (Interval interval : result)
                System.out.print("[" + interval.start + "," + interval.end + "] ");
        }
    }

    /*Given an array of intervals representing ‘N’ appointments, find out if a person can attend all the appointments.*/
    /*
    Assumptions: Intervals cannot be null, list can be null or empty, intervals are positive numbers, Numbers of intervals in the collection 0 < N < integer.max.
    Approach:
    1. Sort intervals by start time
    2. check if previous interval end time > start time of the current interval.
    * */
    static class ConflictingAppointments {
        public boolean checkIfConflictingAppointments(List<Interval> intervals) {
            if (intervals == null || intervals.size() <= 1) {
                return true;
            }
            intervals.sort(Comparator.comparingInt((Interval interval) -> interval.start));
            Interval previousInterval = intervals.get(0);
            for (int index = 1; index < intervals.size(); index++) {
                Interval currentInterval = intervals.get(index);
                if (previousInterval.end > currentInterval.start) {
                    return false;
                }
                previousInterval = currentInterval;
            }
            return true;
        }

        public static void main(String[] args) {
            ConflictingAppointments conflictingAppointments = new ConflictingAppointments();
            Interval[] intervals = {new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)};
            boolean result = conflictingAppointments.checkIfConflictingAppointments(Arrays.asList(intervals));
            System.out.println("Can attend all appointments: " + result);

            Interval[] intervals1 = {new Interval(6, 7), new Interval(2, 4), new Interval(8, 12)};
            result = conflictingAppointments.checkIfConflictingAppointments(Arrays.asList(intervals1));
            System.out.println("Can attend all appointments: " + result);

            Interval[] intervals2 = {new Interval(4, 5), new Interval(2, 3), new Interval(3, 6)};
            result = conflictingAppointments.checkIfConflictingAppointments(Arrays.asList(intervals2));
            System.out.println("Can attend all appointments: " + result);
        }
    }

    /*Given a list of intervals representing the start and end time of ‘N’ meetings, find the minimum number of rooms required to hold all the meetings.*/
    /*Problem : find maximum number of overlapping intervals
     * Assumptions: All intervals are +ve numbers, interval list is not sorted, intervals can be loaded in memory, no memory constrains
     * best time algorithm
     * Approach: sort intervals by start time.
     * use min heap DS (priority queue) which sorts on min end time.
     * add meetings to heap one by one.
     * Before adding new meeting to heap remove ended meeting.
     * keep a count of max queue size it will be the answer.
     * */

    static class MaxOverlappingInterval {
        public int findIntervals(List<Interval> meetings) {
            int maxInterval = 0;
            if (meetings == null || meetings.isEmpty()) {
                return maxInterval;
            }
            PriorityQueue<Interval> minHeap = new PriorityQueue<>(meetings.size(), Comparator.comparingInt((Interval a) -> a.end));
            /*sort meetings by start time*/
            meetings.sort(Comparator.comparingInt((Interval a) -> a.start));
            for (Interval meeting : meetings) {
                /*remove ended meetings*/
                while (!(minHeap.isEmpty()) && (meeting.start >= minHeap.peek().end)) { // TODO pay attention it >= NOT > . A meeting end at 1 another start at 1 then it is not and overlap and previous meeting must be removed
                    minHeap.poll();
                }
                /*add meeting to heap*/
                minHeap.add(meeting);
                /*keep the max size of the queue*/
                maxInterval = Math.max(maxInterval, minHeap.size());
            }

            return maxInterval;
        }

        public static void main(String[] args) {
            MaxOverlappingInterval maxOverlappingInterval = new MaxOverlappingInterval();
            List<Interval> input = new ArrayList<Interval>() {
                {
                    add(new Interval(4, 5));
                    add(new Interval(2, 3));
                    add(new Interval(2, 4));
                    add(new Interval(3, 5));
                }
            };
            int result = maxOverlappingInterval.findIntervals(input);
            System.out.println("Minimum meeting rooms required: " + result);

            input = new ArrayList<Interval>() {
                {
                    add(new Interval(1, 4));
                    add(new Interval(2, 5));
                    add(new Interval(7, 9));
                }
            };
            result = maxOverlappingInterval.findIntervals(input);
            System.out.println("Minimum meeting rooms required: " + result);

            input = new ArrayList<Interval>() {
                {
                    add(new Interval(6, 7));
                    add(new Interval(2, 4));
                    add(new Interval(8, 12));
                }
            };
            result = maxOverlappingInterval.findIntervals(input);
            System.out.println("Minimum meeting rooms required: " + result);

            input = new ArrayList<Interval>() {
                {
                    add(new Interval(1, 4));
                    add(new Interval(2, 3));
                    add(new Interval(3, 6));
                }
            };
            result = maxOverlappingInterval.findIntervals(input);
            System.out.println("Minimum meeting rooms required: " + result);

            input = new ArrayList<Interval>() {
                {
                    add(new Interval(4, 5));
                    add(new Interval(2, 3));
                    add(new Interval(2, 4));
                    add(new Interval(3, 5));
                }
            };
            result = maxOverlappingInterval.findIntervals(input);
            System.out.println("Minimum meeting rooms required: " + result);
        }
    }

    /*We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running. Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.*/

    static class MaxCpuLoad {

        static class Job {
            int start, end, cpuload;

            public Job(int start, int end, int cpuload) {
                this.start = start;
                this.end = end;
                this.cpuload = cpuload;
            }
        }

        public int findCpuLoad(List<Job> jobs) {
            int maxCpuLoad = 0;
            if (jobs == null || jobs.isEmpty()) {
                return 0;
            }
            /*sort by start*/
            jobs.sort(Comparator.comparingInt((Job job) -> job.start));
            /*minHeap : priority queue - sorts on min end time*/
            PriorityQueue<Job> minHeap = new PriorityQueue<>(jobs.size(), Comparator.comparingInt((Job job) -> job.end));
            int currentCpuLoad = 0;
            for (Job job : jobs) {

                /*remove finished jobs*/
                while (!(minHeap.isEmpty()) && (minHeap.peek().end <= job.start)) {
                    final Job jobFinished = minHeap.poll();
                    currentCpuLoad = currentCpuLoad - jobFinished.cpuload;
                }
                /*add job to queue*/
                minHeap.add(job);
                currentCpuLoad = currentCpuLoad + job.cpuload;
                /*max cpu load*/
                maxCpuLoad = Math.max(maxCpuLoad, currentCpuLoad);
            }
            return maxCpuLoad;
        }

        public static void main(String[] args) {
            MaxCpuLoad maxCpuLoad = new MaxCpuLoad();
            List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
            System.out.println("Maximum CPU load at any time: " + maxCpuLoad.findCpuLoad(input));

            input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
            System.out.println("Maximum CPU load at any time: " + maxCpuLoad.findCpuLoad(input));

            input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
            System.out.println("Maximum CPU load at any time: " + maxCpuLoad.findCpuLoad(input));
        }
    }

    /*PROBLEM: For ‘K’ employees, we are given a list of intervals representing the working hours of each employee. Our goal is to find out if there is a free interval that is common to all employees. You can assume that each list of employee working hours is sorted on the start time.*/
    /*
     * Assumptions: List can be null or empty, No employee will have empty or null list, intervals will have only +ve values, Data can be of any length, data set can be loaded in memory.
     *             Best time approach is to be developed, space requirement is relaxed,
     * [[[1-3],[5-7],[7-10]]
     *  [[2-3],[5-7],[12-15]
     *  [[4-6],[10-12]]
     * ]
     * Approach: Use MinHeap to have a collection which is started by start time.
     * Now insert first time interval of each employee.
     * Take the top of the heap and compare with the rest. Now check if there is any gap between the first slots, if there is then you have a free slot.  Not remove top and repeat. When you remove add next interval of the same employee.
     * */
    static class FreeInterval {
        class EmployeeInterval {
            Interval interval;
            int employeeIndexInTheList;
            int intervalIndex;

            public EmployeeInterval(Interval interval, int employeeIndexInTheList, int intervalIndex) {
                this.interval = interval;
                this.employeeIndexInTheList = employeeIndexInTheList;
                this.intervalIndex = intervalIndex;
            }

            @Override
            public String toString() {
                return "EmployeeInterval{" +
                        "interval=" + interval +
                        ", employeeIndexInTheList=" + employeeIndexInTheList +
                        ", intervalIndex=" + intervalIndex +
                        '}';
            }
        }

        public List<Interval> findFreeInterval(List<List<Interval>> employeesIntervals) {
            List<Interval> freeIntervals = new ArrayList<>();
            if (employeesIntervals == null || employeesIntervals.isEmpty()) {
                return freeIntervals;
            }
            PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>(Comparator.comparingInt((EmployeeInterval ei) -> ei.interval.start));
            /*load the first interval of each employee*/
            for (int i = 0; i < employeesIntervals.size(); i++) {
                final Interval interval = employeesIntervals.get(i).get(0);
                minHeap.offer(new EmployeeInterval(interval, i, 0));
            }
            /*start comparing the intervals*/
            EmployeeInterval previousInterval = minHeap.peek();
            while (!minHeap.isEmpty()) {
                EmployeeInterval queueTop = minHeap.poll();
                if (previousInterval.interval.end <  queueTop.interval.start) { /*if there is gap between two intervals*/
                    freeIntervals.add(new Interval(previousInterval.interval.end, queueTop.interval.start));
                    previousInterval = queueTop;
                } else { /*NO GAP or overlapping interval*/
                    if (previousInterval.interval.end <  queueTop.interval.end) {
                        previousInterval = queueTop;
                    }
                }

                /*now remove current interval (queue top) and add next interval to the queue for the same employee*/
                final List<Interval> empIntervals = employeesIntervals.get(queueTop.employeeIndexInTheList); // get emp list
                if (empIntervals.size() > 0 && ((queueTop.intervalIndex + 1) < empIntervals.size())) { // check if there is more interval available for emp
                    final Interval interval = empIntervals.get(queueTop.intervalIndex + 1);
                    minHeap.offer(new EmployeeInterval(interval, queueTop.employeeIndexInTheList, queueTop.intervalIndex + 1)); // add next interval for emp to heap.
                }
            }
            return freeIntervals;
        }

        public static void main(String[] args) {
           FreeInterval freeInterval = new FreeInterval();

            List<List<Interval>> input = new ArrayList<>();
            input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
            input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
            List<Interval> result = freeInterval.findFreeInterval(input);
            System.out.print("Free intervals: ");
            for (Interval interval : result)
                System.out.print("[" + interval.start + ", " + interval.end + "] ");
            System.out.println();

            input = new ArrayList<>();
            input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
            input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
            input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
            result = freeInterval.findFreeInterval(input);
            System.out.print("Free intervals: ");
            for (Interval interval : result)
                System.out.print("[" + interval.start + ", " + interval.end + "] ");
            System.out.println();

            input = new ArrayList<>();
            input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
            input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
            input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
            result = freeInterval.findFreeInterval(input);
            System.out.print("Free intervals: ");
            for (Interval interval : result)
                System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
    }


}


