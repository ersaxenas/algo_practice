package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q323NumberConnectedComponentsUndirectedGraph {
    /*
     * pd: Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
     * assm: no loops in the graph, no. of nodes < 1000, best time sol
     * appr: bfs
     * test cases:
     * */

    public int countComponents(int n, int[][] edges) {
        if (n <= 0) {
            return 0;
        }
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int idx = 0; idx < n; idx++) {
            adjMap.put(idx, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjMap.get(edge[0]).add(edge[1]);
            adjMap.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int path = 0;
        for (int node : adjMap.keySet()) {
            if (!visited.contains(node)) {
                path++;
                queue.add(node);
                while (!queue.isEmpty()) {
                    final Integer pnode = queue.poll();
                    visited.add(pnode);
                    for (int cnode : adjMap.get(pnode)) {
                        if (!visited.contains(cnode)) {
                            queue.add(cnode);
                        }
                    }
                }
            }
        }
        return path;
    }

    public static void main(String[] args) {
        Q323NumberConnectedComponentsUndirectedGraph sol = new Q323NumberConnectedComponentsUndirectedGraph();
        System.out.println(sol.countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
    }

}
