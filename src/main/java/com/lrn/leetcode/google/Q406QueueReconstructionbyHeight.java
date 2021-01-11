package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Q406QueueReconstructionbyHeight {
    /*
    * pd: You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.
Reconstruct and return the queue that is represented by the input array people. The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).
    * assm: queue always has solution, best time sol, array contains +ve values only, array size < 1000
    * appr:
    * test cases:
    * Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]] Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
    * Input: people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]] Output: [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
    * */

    public int[][] reconstructQueue(int[][] people) {
        if(people == null || people.length <= 1) {
            return people;
        }
        Arrays.sort(people, (p1,p2) -> {
            // first sort on height descending and then sort by position ascending
            // [0] == height, [1] == position
            return p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0];
        });

        LinkedList<int[]> result = new LinkedList<>();
        for(int[] person: people) {
            result.add(person[1],person);
        }

        return result.toArray(new int[people.length][]);
    }

    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // if the heights are equal, compare k-values
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        List<int[]> output = new LinkedList<>();
        for(int[] p : people){
            output.add(p[1], p);
        }

        int n = people.length;
        return output.toArray(new int[n][2]);
    }

    public static void main(String[] args) {
       Q406QueueReconstructionbyHeight sol = new Q406QueueReconstructionbyHeight();
       LsUtil.printArray(sol.reconstructQueue(new int[][] {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}}));
       LsUtil.printArray(sol.reconstructQueue(new int[][] {{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}}));
    }

}
