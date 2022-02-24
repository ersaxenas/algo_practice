package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q218TheSkylineProblem {
    /*
     * pd: https://leetcode.com/problems/the-skyline-problem/
     *
     *
     *
     * */
    static class QBuilding {
        int d, height=0;
        private boolean isStart;
        public QBuilding withStart(int start, int height) {
            this.d = start;
            this.height = -height;
            isStart = true;
            return this;
        }
        public QBuilding withEnd(int end, int height) {
            this.d = end;
            this.height = height;
            return this;
        }

        @Override
        public String toString() {
            return "QBuilding{" +
                    "[" + d +
                    "," + height +
                    "," + isStart +
                    ']';
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<QBuilding> buildingList = new ArrayList<>();
        for(int[] dim: buildings) {
            buildingList.add(new QBuilding().withStart(dim[0],dim[2]));
            buildingList.add(new QBuilding().withEnd(dim[1],dim[2]));
        }
        Collections.sort(buildingList, (b1, b2) -> ((b1.d == b2.d) ? b1.height - b2.height : b1.d - b2.d));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((b1,b2) -> b2 - b1);
        maxHeap.add(0);
        int prevMax = 0;
        for (QBuilding building : buildingList) {
           if(building.isStart) {
               maxHeap.add(-building.height);
           } else {
               maxHeap.remove(building.height);
           }
            final Integer currMax = maxHeap.peek();
            if(prevMax != currMax) {
               result.add(Arrays.asList(building.d, currMax));
               prevMax = currMax;
            }
        }
        return result;
    }

    public List<List<Integer>> getSkyline2(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for(int[] h:height) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if(prev != cur) {
                result.add(Arrays.asList(h[0], cur));
                prev = cur;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q218TheSkylineProblem sol = new Q218TheSkylineProblem();
        int[][] dimentions = new int[][]{
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}
        };
        //LsUtil.printListOfList(sol.getSkyline(dimentions));
        dimentions = new int[][]{
                {0, 2, 3},
                {2, 5, 3}
        };
        LsUtil.printListOfList(sol.getSkyline(dimentions));
        LsUtil.printListOfList(sol.getSkyline2(dimentions));
    }
}
