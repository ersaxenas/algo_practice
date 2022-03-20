package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q057InsertInterval {

    /* 2021-12-28T13:09:48.401Z
    https://leetcode.com/problems/insert-interval/
     * pd: Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     * assm: arr non null elem, +ve elem
     * appr: 1) add intervals smaller then newinterval to result. interval.end < newinterval.start
     *       2). now merger all over lapping intervals. interval.start <= newinterval.end
     *      3). add rest of the intervals if any.
     * */

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if ((intervals == null || intervals.length == 0)) {
            return new int[][]{newInterval};
        }

        List<int[]> result = new ArrayList<>();
        int idx = 0;
        // first add all intervals smaller then new interval
        while (idx < intervals.length && intervals[idx][1] < newInterval[0]) {
            result.add(intervals[idx++]);
        }
        // merge overlapping intervals
        while (idx < intervals.length && intervals[idx][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
            idx++;
        }
        result.add(newInterval);
        while (idx < intervals.length) {
            result.add(intervals[idx++]);
        }
        return result.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        Q57InsertInterval sol = new Q57InsertInterval();
        LsUtil.printArray(sol.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}));
    }

}
