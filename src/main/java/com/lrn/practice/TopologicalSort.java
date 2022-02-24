package com.lrn.practice;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologicalSort {
    /*
     * All Tasks Scheduling Orders (hard)
     * */

    public void printOrders(int tasks, int[][] prereq) {
        if (tasks <= 1) {
            return;
        }

        Map<Integer, Integer> degree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int idx = 0; idx < tasks; idx++) {
            degree.put(idx, 0);
            graph.put(idx, new ArrayList<>());
        }
        for (int[] edge : prereq) {
            int parent = edge[0];
            int child = edge[1];
            degree.put(child, degree.get(child) + 1);
            graph.get(parent).add(child);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int vertex : degree.keySet()) {
            if (degree.get(vertex) == 0) {
                queue.add(vertex);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
       backtrack(result, queue, graph, degree, new ArrayList<>());
        LsUtil.printListOfList(result);
    }

    public void backtrack(List<List<Integer>> result, Deque<Integer> queue, Map<Integer, List<Integer>> graph, Map<Integer, Integer> degree, List<Integer> tasksScheduled) {
        if (tasksScheduled.size() == degree.size()) {
            result.add(new ArrayList<>(tasksScheduled));
        } else {
            for(int vertex: queue) {
                tasksScheduled.add(vertex);
                Deque<Integer> newQueue = new ArrayDeque<>(queue);
                newQueue.remove(vertex);
                List<Integer> children = graph.get(vertex);
                for (int child : children) {
                    degree.put(child, degree.get(child) - 1);
                    if(degree.get(child) ==0) {
                       newQueue.add(child);
                    }
                }

                backtrack(result, newQueue, graph,degree,tasksScheduled);

                for (int child : children) {
                    degree.put(child, degree.get(child) + 1);
                }
                tasksScheduled.remove(tasksScheduled.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        TopologicalSort sol = new TopologicalSort();
        sol.printOrders(3, new int[][]{new int[]{0, 1}, new int[]{1, 2}});
        sol.printOrders(4, new int[][]{new int[]{3, 2}, new int[]{3, 0}, new int[]{2, 0}, new int[]{2, 1}});
    }


}
