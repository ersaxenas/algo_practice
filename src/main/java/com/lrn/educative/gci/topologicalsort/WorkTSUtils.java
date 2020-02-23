package com.lrn.educative.gci.topologicalsort;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WorkTSUtils {
    /*Given a directed graph, find the topological ordering of its vertices.*/
     static class Topologicalsort1 {
        public List<Integer> sort(int vertices, int[][] edges) {
            /*map will hold degree of vertices*/
            Map<Integer, Integer> vertexDegree = new HashMap<>();
            /*map will hold list of connected vertices for a particular vertex*/
            Map<Integer, List<Integer>> graph = new HashMap<>();
            // initialize both the maps
            for (int v = 0; v < vertices; v++) {
                vertexDegree.put(v, 0);
                graph.put(v, new ArrayList<>());
            }
            //add edges and degree
            for (int[] edge : edges) {
                int parent = edge[0];
                int child = edge[1];
                graph.get(parent).add(child);
                vertexDegree.put(child, vertexDegree.get(child) + 1);
            }
            // topological sort starts
            Queue<Integer> queue = new LinkedList<>();
            for (Map.Entry<Integer, Integer> vertex : vertexDegree.entrySet()) {
                if (vertex.getValue() == 0) {
                    // add all sources to queue
                    queue.add(vertex.getKey());
                }
            }
            List<Integer> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                final Integer vertex = queue.poll();
                result.add(vertex);
                final List<Integer> childVertex = graph.get(vertex);
                for (Integer child : childVertex) {
                    vertexDegree.put(child, vertexDegree.get(child) - 1); // decrease degree of each child
                    if (vertexDegree.get(child) == 0) { // if child vertex degree is 0 then it has become source node
                        // add if source
                        queue.add(child);
                    }
                }
            }
            /*Guard condition for cycle in the graph*/
            if (result.size() != vertices) {
                // graph must have a loop
                return new ArrayList<>();
            }
            return result;
        }

        public static void main(String[] args) {
            Topologicalsort1 topologicalsort1 = new Topologicalsort1();
            List<Integer> result = topologicalsort1.sort(4, new int[][]{new int[]{3, 2}, new int[]{3, 0}, new int[]{2, 0}, new int[]{2, 1}});
            System.out.println(result);

            result = topologicalsort1.sort(5, new int[][]{new int[]{4, 2}, new int[]{4, 3}, new int[]{2, 0}, new int[]{2, 1}, new int[]{3, 1}});
            System.out.println(result);

            result = topologicalsort1.sort(7, new int[][]{new int[]{6, 4}, new int[]{6, 2}, new int[]{5, 3}, new int[]{5, 4}, new int[]{3, 0}, new int[]{3, 1}, new int[]{3, 2}, new int[]{4, 1}});
            System.out.println(result);
        }
    }

    /*There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be completed before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, find out if it is possible to schedule all the tasks.*/
        /* [0,1,2] pre = [0,1] [1,2] => 0,1,2 => true
          [] = false or pre == null => false
          [0,1,2,3] pre = [0,1] [1,2] [2,3] [3,0] => false
          Assumptions:
          all +ve integers, no null, array of size <=100, edges are define as pair [parent,child],best time solution

        * */
    static class TaskScheduler {
        public boolean isSchedulingPossible(int tasks, int[][] prerequisites) {
            if(tasks < 0 || prerequisites == null || prerequisites.length ==0) {
                return false;
            }
            Map<Integer, Integer> degreeMap= new HashMap<>();
            Map<Integer, List<Integer>> graphMap = new HashMap<>();
            for(int v=0; v<tasks; v++) {
                degreeMap.put(v,0);
                graphMap.put(v,new ArrayList<>());
            }
            for(int[] edge : prerequisites) {
                int parent = edge[0];
                int child = edge[1];
                degreeMap.put(child, degreeMap.get(child)+1);
                graphMap.get(parent).add(child);
            }
            Queue<Integer> queue = new LinkedList<>();
            for(Map.Entry<Integer,Integer> entry: degreeMap.entrySet()) {
                if(entry.getValue() == 0) {
                    queue.add(entry.getKey());
                }
            }
            ArrayList<Integer> resultList = new ArrayList<>();
            // now bfs
            while(!queue.isEmpty()) {
                Integer vertex = queue.poll();
                resultList.add(vertex);
                // for all children
                for(Integer children : graphMap.get(vertex)) {
                    degreeMap.put(children, degreeMap.get(children) -1);
                    if(degreeMap.get(children) == 0) {
                        queue.add(children);
                    }
                }
            }
            return resultList.size() == tasks;
        }
        public static void main(String[] args) {
            TaskScheduler taskScheduler = new TaskScheduler();
            Boolean result = taskScheduler.isSchedulingPossible(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
            System.out.println("Tasks execution possible: " + result);

            result = taskScheduler.isSchedulingPossible(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
            System.out.println("Tasks execution possible: " + result);

            result = taskScheduler.isSchedulingPossible(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
            System.out.println("Tasks execution possible: " + result);

            result = taskScheduler.isSchedulingPossible(-1, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
            System.out.println("Tasks execution possible: " + result);

            result = taskScheduler.isSchedulingPossible(6, null);
            System.out.println("Tasks execution possible: " + result);

            result = taskScheduler.isSchedulingPossible(4, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 0 }});
            System.out.println("Tasks execution possible: " + result);


        }
    }
    /*There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be completed before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, write a method to find the ordering of tasks we should pick to finish all tasks.*/
    /*[] => false
    * 3 , [0,1] [1,2] order = [0,1,2]
    * 3 , [0,1] [1,2] [2,0] order = []
    * assumptions :
    * N < 100, best time solution
    * */
    static class TaskScheduler1 {

        public List<Integer> findOrder(int tasks, int[][] preReq) {
            if(tasks < 0 || preReq == null || preReq.length == 0) {
                return new ArrayList<>();
            }
            Map<Integer, Integer> degree = new HashMap<>();
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int v=0; v<tasks; v++) {
                degree.put(v,0);
                graph.put(v,new ArrayList<>());
            }
            for(int[] edge: preReq) {
                int parent = edge[0];
                int child = edge[1];
                graph.get(parent).add(child);
                degree.put(child,degree.get(child)+1);
            }
            Queue<Integer> queue = new LinkedList<>();
            for(Map.Entry<Integer,Integer> entry: degree.entrySet()) {
                if(entry.getValue() ==0) {
                    queue.add(entry.getKey());
                }
            }
            ArrayList<Integer> orderList = new ArrayList<>();
            // bfs
            while(!queue.isEmpty()) {
                Integer source = queue.poll();
                orderList.add(source);
                for(Integer child: graph.get(source)) {
                    degree.put(child, degree.get(child) -1);
                    if(degree.get(child) ==0) {
                        queue.add(child);
                    }
                }
            }
            // check for cycle
            if(orderList.size() != tasks) {
                return new ArrayList<>();
            }
            return orderList;
        }
        public static void main(String[] args) {
            TaskScheduler1 taskScheduler1 = new TaskScheduler1();
            List<Integer> result = taskScheduler1.findOrder(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
            System.out.println(result);

            result = taskScheduler1.findOrder(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
            System.out.println(result);

            result = taskScheduler1.findOrder(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
            System.out.println(result);
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
            TaskScheduler3 taskScheduler3 = new TaskScheduler3();
            taskScheduler3.printOrder(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
            System.out.println();

            taskScheduler3.printOrder(4, new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
            System.out.println();

            taskScheduler3.printOrder(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
            System.out.println();
        }
    }

    /*There is a dictionary containing words from an alien language for which we don’t know the ordering of the characters. Write a method to find the correct order of characters in the alien language.*/
    static class AlianDictoionary {
        public String findOrder(String[] words) {
            if(words == null || words.length == 0){
                return null;
            }
            Map<Character, Integer> degree = new HashMap<>();
            Map<Character, List<Character>> graph = new HashMap<>();
            for(String word: words) {
                for(char ch: word.toCharArray()) {
                    degree.putIfAbsent(ch,0);
                    graph.put(ch,new ArrayList<>());
                }
            }
            for(int idx1=0; idx1< words.length-1; idx1++) {
                String word1 = words[idx1];
                String word2 = words[idx1+1];
                int minLen = Math.min(word1.length(), word2.length());
                for(int idx2=0; idx2 < minLen; idx2++) {
                    Character parentChar = word1.charAt(idx2);
                    Character childChar = word2.charAt(idx2);
                    if(parentChar != childChar) {
                        degree.put(childChar, degree.get(childChar) +1);
                        graph.get(parentChar).add(childChar);
                        break;
                    }
                }
            }

            Queue<Character> queue = new LinkedList<>();
            for(Map.Entry<Character,Integer> entry : degree.entrySet()) {
                if(entry.getValue() ==0) {
                    queue.add(entry.getKey());
                }
            }
            StringBuilder sbr = new StringBuilder();
            while(!queue.isEmpty()) {
                Character source = queue.poll();
                sbr.append(source);
                List<Character> childList = graph.get(source);
                for(Character child: childList) {
                    degree.put(child, degree.get(child) - 1);
                    if(degree.get(child) == 0) {
                        queue.add(child);
                    }
                }
            }

            if(sbr.length() != degree.size()) {
                return null;
            }
           return sbr.toString();
        }

        public static void main(String[] args) {
            AlianDictoionary alianDictoionary = new AlianDictoionary();
            String result = alianDictoionary.findOrder(new String[] { "ba", "bc", "ac", "cab" });
            System.out.println("Character order: " + result);

            result = alianDictoionary.findOrder(new String[] { "cab", "aaa", "aab" });
            System.out.println("Character order: " + result);

            result = alianDictoionary.findOrder(new String[] { "ywx", "wz", "xww", "xz", "zyy", "zwz" });
            System.out.println("Character order: " + result);
        }
    }
    /*Given a sequence originalSeq and an array of sequences, write a method to find if originalSeq can be uniquely reconstructed from the array of sequences.
Unique reconstruction means that we need to find if originalSeq is the only sequence such that all sequences in the array are subsequences of it.*/
    static class ReconstructSequence {
        public boolean canReconstruct(int[] originalSeq, int[][] seqs) {
            Map<Integer, Integer> degree = new HashMap<>();
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(Integer num: originalSeq) {
                degree.put(num,0);
                graph.put(num,new ArrayList<>());
            }
            for(int[] seq: seqs) {
                for(int idx=1; idx<seq.length; idx++) {
                    int parent = seq[idx-1];
                    int child = seq[idx];
                    graph.get(parent).add(child);
                    degree.put(child, degree.get(child)+1);
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            for(Integer key: degree.keySet()) {
                if(degree.get(key) == 0){
                    queue.add(key);
                }
            }
            ArrayList<Integer> lst = new ArrayList<>();
            ArrayList<List<Integer>> sequences = new ArrayList<>();
            canReconstructRecursive(queue, degree, graph, lst,originalSeq, sequences);
            return sequences.size() == 1;
        }
        private void canReconstructRecursive(Queue<Integer> queue, Map<Integer, Integer> degree, Map<Integer, List<Integer>> graph, ArrayList<Integer> lst, int[] originalSeq, ArrayList<List<Integer>> sequences) {
            if(!queue.isEmpty()) {
                for(Integer currentSource: queue) {
                     lst.add(currentSource);

                     Queue<Integer> newQueue = deepCloneQueue(queue);
                     newQueue.remove(currentSource);
                     List<Integer> childrenOfCurrentSource = graph.get(currentSource);
                     for(Integer child: childrenOfCurrentSource) {
                         degree.put(child, degree.get(child) -1);
                         if(degree.get(child) == 0) {
                             newQueue.add(child);
                         }
                     }

                     canReconstructRecursive(newQueue, degree,graph,lst,originalSeq, sequences);

                     lst.remove(currentSource);
                     for(int child: childrenOfCurrentSource) {
                         degree.put(child, degree.get(child)+1);
                     }
                }
            } else {
                if(lst.size() == originalSeq.length) {
                    sequences.add(new ArrayList<>(lst));
                }
            }
        }

        public boolean canReconstruct2(int[] originalSeq, int[][] seqs) {
            Map<Integer, Integer> degree = new HashMap<>();
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(Integer num: originalSeq) {
                degree.put(num,0);
                graph.put(num,new ArrayList<>());
            }
            for(int[] seq: seqs) {
                for(int idx=1; idx<seq.length; idx++) {
                    int parent = seq[idx-1];
                    int child = seq[idx];
                    graph.get(parent).add(child);
                    degree.put(child, degree.get(child)+1);
                }
            }
           if(degree.size() != originalSeq.length) {
               return false;
           }

            Queue<Integer> queue = new LinkedList<>();
            for(Integer key: degree.keySet()) {
                if(degree.get(key) == 0){
                    queue.add(key);
                }
            }
            ArrayList<Integer> orderList = new ArrayList<>();
            while(!queue.isEmpty()) {
                if(queue.size() > 1) {
                    return false; // more then one element means there are more then one sequences
                }
                int currentSource = queue.poll();
                if(currentSource != originalSeq[orderList.size()]) {
                    return false;
                }
                orderList.add(currentSource);
                List<Integer> childrenOfCurrentSource = graph.get(currentSource);
                for(Integer child: childrenOfCurrentSource) {
                    degree.put(child, degree.get(child) -1);
                    if(degree.get(child) == 0) {
                        queue.add(child);
                    }
                }
            }
            return orderList.size() == originalSeq.length;
        }

        public Queue<Integer> deepCloneQueue(Queue<Integer> queue) {
            Queue<Integer> newQueue = new LinkedList<>();
            for(Integer elem: queue) {
                newQueue.add(elem);
            }
            return newQueue;
        }

        public static void main(String[] args) {
            ReconstructSequence reconstructSequence = new ReconstructSequence();
            boolean result = reconstructSequence.canReconstruct2(new int[] { 1, 2, 3, 4 }, new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 4 } });
            System.out.println("Can we uniquely construct the sequence: " + result);

            result = reconstructSequence.canReconstruct2(new int[] { 1, 2, 3, 4 }, new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 2, 4 } });
            System.out.println("Can we uniquely construct the sequence: " + result);

            result = reconstructSequence.canReconstruct2(new int[] { 3, 1, 4, 2, 5 }, new int[][] { new int[] { 3, 1, 5 }, new int[] { 1, 4, 2, 5 } });
            System.out.println("Can we uniquely construct the sequence: " + result);
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
            MinimumHeightTree minimumHeightTree = new MinimumHeightTree();
            List<Integer> result = minimumHeightTree.findTrees(5, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 2, 4 } });
            System.out.println("Roots of MHTs: " + result);

            result = minimumHeightTree.findTrees(4, new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 2, 3 } });
            System.out.println("Roots of MHTs: " + result);

            result = minimumHeightTree.findTrees(4, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 } });
            System.out.println("Roots of MHTs: " + result);
        }
    }



}
