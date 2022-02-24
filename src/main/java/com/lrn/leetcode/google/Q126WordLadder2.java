package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q126WordLadder2 {

    /*2022-02-23T20:37:09.091Z
     * pd: https://leetcode.com/problems/word-ladder-ii/
     * assm:
     * appr: bfs and dfs : https://leetcode.com/problems/word-ladder-ii/discuss/40475/My-concise-JAVA-solution-based-on-BFS-and-DFS
     * test cases:
     * */

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) {
            return result;
        }
        Map<String, Integer> distance = new HashMap<>();// track distance from start
        Map<String,List<String>> neighborMap = getNeighborMap(beginWord, endWord, dict);
        Map<String,List<String>> graph = buildGraph(beginWord, endWord,neighborMap, distance);
        dfs(beginWord, endWord, graph, new ArrayList<>(), distance, result);
        return result;
    }
    public void dfs(String node, String end, Map<String,List<String>> graph, List<String> tmp, Map<String, Integer> distance, List<List<String>> result ) {
        tmp.add(node);
        if(end.equals(node)) {
            result.add(new ArrayList<>(tmp));
        } else {
            for(String child: graph.get(node)) {
                    if(distance.get(child) == distance.get(node)+1) {
                        dfs(child, end, graph, tmp, distance,result);
                    }
            }
        }
        tmp.remove(tmp.size()-1);
    }

    public Map<String,List<String>> buildGraph(String start, String end,Map<String,List<String>> neighborMap, Map<String, Integer> distance) {
        HashMap<String,List<String>> graph = new HashMap<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.add(start);
        distance.put(start, 0);
        boolean endFound = false;
        while(!queue.isEmpty()) {
            int qsize = queue.size();
            for(int idx=0; idx<qsize; idx++) {
                String node = queue.poll();
                int curDist = distance.get(node);
                graph.put(node, graph.getOrDefault(node,new ArrayList<>()));
                for(String neighbor: neighborMap.get(node)) {
                    graph.get(node).add(neighbor);
                    if(!distance.containsKey(neighbor)) { // visited
                        distance.put(neighbor, curDist+1);
                        if(end.equals(neighbor)) {
                            endFound = true;
                        } else {
                            queue.add(neighbor);
                        }
                    }
                }
                if(endFound) break;
            }
        }
        return graph;
    }


    public Map<String,List<String>> getNeighborMap(String start, String end,Set<String> nodes) {
      Map<String, List<String>> neighborMap = new HashMap<>();
        neighborMap.put(start, findNeighbors(start, end,nodes));
        for(String node: nodes) {
            neighborMap.put(node, findNeighbors(node, end,nodes));
        }
      return neighborMap;
    }

    public List<String> findNeighbors(String node, String end, Set<String> nodes) {
        List<String> neighbors = new ArrayList<>();
        char[] word = node.toCharArray();
        for (int idx = 0; idx < word.length; idx++) {
            char och = word[idx];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (och == ch) continue;
                word[idx] = ch;
                final String newWord = String.valueOf(word);
                if(nodes.contains(newWord)) {
                    neighbors.add(newWord);
                }
                if(end.equals(newWord)) return Arrays.asList(end);
            }
            word[idx] = och;
        }
        return neighbors;
    }

    public static void main(String[] args) {
        Q126WordLadder2 sol = new Q126WordLadder2();
        System.out.println(sol.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }


}
