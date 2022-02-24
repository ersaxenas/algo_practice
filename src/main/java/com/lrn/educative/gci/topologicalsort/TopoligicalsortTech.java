package com.lrn.educative.gci.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopoligicalsortTech {
    /**/
    static class Topologicalsort1 {
        public List<Integer> sort(int vertices, int[][] edges) {
            /*Prepare degree map : map will hold degree of vertices*/
            Map<Integer, Integer> vertexDegree = new HashMap<>();
            /*Prepare graph : map will hold list of connected vertices for a particular vertex*/
            Map<Integer, List<Integer>> graph = new HashMap<>();
            /* initialize both the maps : add to degree and graph map */
            for (int v = 0; v < vertices; v++) {
                vertexDegree.put(v, 0);
                graph.put(v, new ArrayList<>());
            }
            /* add edges and degree */
            for (int[] edge : edges) {
                int parent = edge[0];
                int child = edge[1];
                graph.get(parent).add(child);
                vertexDegree.put(child, vertexDegree.get(child) + 1);
            }
            /* topological sort starts */
            /* add vertices having degree zero : source nodes*/
            Queue<Integer> queue = new LinkedList<>();
            for (Map.Entry<Integer, Integer> vertex : vertexDegree.entrySet()) {
                if (vertex.getValue() == 0) {
                    // add all sources to queue
                    queue.add(vertex.getKey());
                }
            }
            /*List will hold the results*/
            List<Integer> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                /*deque source node*/
                final Integer vertex = queue.poll();
                result.add(vertex);
                final List<Integer> childVertex = graph.get(vertex);
                /*Decrease the degree of the each child and add to queue if degree is zero*/
                for (Integer child : childVertex) {
                    vertexDegree.put(child, vertexDegree.get(child) - 1); // decrease degree of each child
                    if (vertexDegree.get(child) == 0) { // if child vertex degree is 0 then it has become source node
                        // add if source
                        queue.add(child);
                    }
                }
            }
            /*Guard condition for cycle in the graph : */
            /*If all vertices are present in the list then we have visited all the nodes
            * and there is no cycle. If there is a cycle then list will not have all the nodes.*/
            if (result.size() != vertices) {
                // graph must have a loop
                return new ArrayList<>();
            }
            return result;
        }
    }

    /*There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be completed before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, write a method to print all possible ordering of tasks meeting all prerequisites.*/
    static class TaskScheduler3 {
        public void printOrder(int tasks, int[][] preReq) {
            if(tasks < 0 || preReq == null || preReq.length == 0) {
                return;
            }
            Map<Integer,Integer> degree = new HashMap<>();
            Map<Integer,List<Integer>> graph = new HashMap<>();
            for(int v=0; v<tasks; v++) {
                degree.put(v,0);
                graph.put(v,new ArrayList<>());
            }
            for(int[] edge: preReq) {
                int parent = edge[0];
                int child = edge[1];
                degree.put(child, degree.get(child)+1);
                graph.get(parent).add(child);
            }
            Queue<Integer> queue = new LinkedList<>();
            for(Map.Entry<Integer,Integer> entry: degree.entrySet()) {
                if(entry.getValue() ==0) {
                    queue.add(entry.getKey());
                }
            }
            findAllOrder(queue, degree,graph,new ArrayList<>());

        }

        private void findAllOrder(Queue<Integer> queue, Map<Integer, Integer> degree, Map<Integer, List<Integer>> graph, ArrayList<Integer> orderList) {
            if(!queue.isEmpty()) {
                for(Integer vertex: queue) {
                    orderList.add(vertex);
                    Queue<Integer> newQueue = makeDeepCopy(queue);
                    // remove current vertex
                    newQueue.remove(vertex);
                    List<Integer> children = graph.get(vertex);
                    for(Integer child: children) {
                        degree.put(child,degree.get(child) - 1);
                        if(degree.get(child) == 0) {
                            newQueue.add(child);
                        }
                    }
                    findAllOrder(newQueue, degree,graph,orderList);
                    // removed vertex from order
                    orderList.remove(vertex);
                    for(Integer child: children) {
                        degree.put(child,degree.get(child) + 1);
                    }
                }
            } else {
                if(orderList.size() == degree.size()) {
                    System.out.println(orderList);
                }
            }
        }

        public Queue<Integer> makeDeepCopy(Queue<Integer> queue) {
            Queue<Integer> newQueue = new LinkedList<>();
            for(Integer elem: queue) {
                newQueue.add(elem);
            }
            return newQueue;
        }

        public static void main(String[] args) {
            WorkTSUtils.TaskScheduler3 taskScheduler3 = new WorkTSUtils.TaskScheduler3();
            taskScheduler3.printOrder(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
            System.out.println();

            taskScheduler3.printOrder(4, new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
            System.out.println();

            taskScheduler3.printOrder(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
            System.out.println();
        }
    }

    /*We are given an undirected graph that has characteristics of a k-ary tree. In such a graph, we can choose any node as the root to make a k-ary tree. The root (or the tree) with the minimum height will be called Minimum Height Tree (MHT). There can be multiple MHTs for a graph. In this problem, we need to find all those roots which give us MHTs. Write a method to find all MHTs of the given graph and return a list of their roots.*/
    static class MinimumHeightTree {
        public List<Integer> findTrees(int nodes, int[][] edges) {
            List<Integer> minHeightTree = new ArrayList<>();
            if(nodes <=0) {
                return minHeightTree;
            }
            if(nodes == 1) {
                minHeightTree.add(0);
                return minHeightTree;
            }
            Map<Integer,Integer>  degree = new HashMap<>();
            Map<Integer,List<Integer>> graph = new HashMap<>();
            for(int idx=0; idx<nodes; idx++) {
                degree.put(idx,0);
                graph.put(idx,new ArrayList<>());
            }
            for(int[] edge: edges) {
                int node1 = edge[0];
                int node2 = edge[1];
                graph.get(node1).add(node2);
                graph.get(node2).add(node1);
                degree.put(node1,degree.get(node1) +1);
                degree.put(node2,degree.get(node2) +1);
            }
            Queue<Integer> queue = new LinkedList<>();
            for(int node: degree.keySet()) {
                if(degree.get(node) ==1) {
                    queue.add(node);
                }
            }
            int totalNodes = nodes;
            while(totalNodes>2) {
                int queueSize = queue.size();
                for(int idx=0; idx<queueSize; idx++) {
                    int leafNode = queue.poll();
                    List<Integer> childrenofCurrentLeafNode = graph.get(leafNode);
                    for(int child: childrenofCurrentLeafNode) {
                        degree.put(child, degree.get(child) -1);
                        if(degree.get(child) ==1) {
                            queue.add(child);
                        }
                    }
                }
                totalNodes = totalNodes -queueSize;
            }
            minHeightTree.addAll(queue);
            return minHeightTree;
        }

        public static void main(String[] args) {
            WorkTSUtils.MinimumHeightTree minimumHeightTree = new WorkTSUtils.MinimumHeightTree();
            List<Integer> result = minimumHeightTree.findTrees(5, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 2, 4 } });
            System.out.println("Roots of MHTs: " + result);

            result = minimumHeightTree.findTrees(4, new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 2, 3 } });
            System.out.println("Roots of MHTs: " + result);

            result = minimumHeightTree.findTrees(4, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 } });
            System.out.println("Roots of MHTs: " + result);
        }
    }


}
