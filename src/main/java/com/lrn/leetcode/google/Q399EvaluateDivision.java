package com.lrn.leetcode.google;

import com.lrn.cci.likedlist.Palindrome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q399EvaluateDivision {
    /*
    * pd: You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
Return the answers to all queries. If a single answer cannot be determined, return -1.0.
Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
    * assm:
    * appr:
    * test cases:
    * */


        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String, Map<String, Double>> graph = new HashMap<>();
            for (int idx = 0; idx < equations.size(); idx++) {
                String v1 = equations.get(idx).get(0);
                String v2 = equations.get(idx).get(1);
                Map<String, Double> v1Map = graph.getOrDefault(v1, new HashMap<>());
                v1Map.put(v2, values[idx]);
                graph.put(v1, v1Map);
                Map<String, Double> v2Map = graph.getOrDefault(v2, new HashMap<>());
                v2Map.put(v1, 1 / values[idx]);
                graph.put(v2, v2Map);
            }

            double[] result = new double[queries.size()];
            for(int idx=0; idx<queries.size(); idx++) {
                result[idx] = backtrack(graph, queries.get(idx).get(0), queries.get(idx).get(1), 1, new HashSet<>());
            }
            return result;
        }

        public double backtrack(Map<String, Map<String, Double>> graph, String start, String end, double prod, Set<String> visited) {
            double res = -1;
            visited.add(start);
            final Map<String, Double> neighbours = graph.getOrDefault(start, new HashMap<>());
            if (neighbours.containsKey(end)) {
                 res =  prod * neighbours.get(end);
            } else {
                for (String neighbour : neighbours.keySet()) {
                    if (!visited.contains(neighbour)) {
                        res = backtrack(graph, neighbour, end, prod * neighbours.get(neighbour), visited);
                        if (res != -1) {
                            break;
                        }
                    }
                }
            }
            visited.remove(start);
            return res;
        }


    public static void main(String[] args) {
        Q399EvaluateDivision sol = new Q399EvaluateDivision();
        sol.calcEquation(
                Arrays.asList(Arrays.asList("a","b"),Arrays.asList("b","c")),
                new double[] {2.0, 3.0},
                Arrays.asList(Arrays.asList("a","c"), Arrays.asList("b","a"), Arrays.asList("a","e"), Arrays.asList("a","a"), Arrays.asList("x","x"))
        );
    }

}
