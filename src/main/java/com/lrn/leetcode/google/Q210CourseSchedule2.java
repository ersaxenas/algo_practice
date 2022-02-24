package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringJoiner;

public class Q210CourseSchedule2 {
    /*
    * pd: There are a total of n courses you have to take labelled from 0 to n - 1.
Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]
    * assm: array: non null elem, size < 10000
    * appr: dfs - mother node
    * */

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return new int[]{0};
        }
        Map<Integer, Integer> degree = new HashMap<>();
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int idx=0; idx<numCourses; idx++) {
            degree.put(idx,0);
            adj.put(idx, new ArrayList<>());
        }
        for(int[] pre: prerequisites) {
            degree.put(pre[0], degree.get(pre[0])+1);
            adj.get(pre[1]).add(pre[0]);

        }
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int node: degree.keySet()) {
            if(degree.get(node) == 0) {
                queue.add(node);
            }
        }
        while(!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for(int cnode: adj.get(node)) {
                degree.put(cnode, degree.get(cnode)-1);
                if(degree.get(cnode) == 0) {
                    queue.add(cnode);
                }
            }
        }
        if(result.size() != numCourses) {
           return new int[]{};
        }
        int[] res = new int[result.size()];
        for(int idx=0; idx<result.size(); idx++) {
            res[idx] = result.get(idx);
        }
        return res;
    }


    public static void main(String[] args) {
        Q210CourseSchedule2 sol = new Q210CourseSchedule2();
        LsUtil.printArray(sol.findOrder(2, new int[][]{{0, 1}}));
        LsUtil.printArray(sol.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));
    }

}
