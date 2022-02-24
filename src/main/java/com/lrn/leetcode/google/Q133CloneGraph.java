package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q133CloneGraph {
    /* https://leetcode.com/problems/clone-graph
    * pd: iven a reference of a node in a connected undirected graph.
Return a deep copy (clone) of the graph.
Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
class Node {
    public int val;
    public List<Node> neighbors;
}
    * assm: 1 < node val <= 100, node val is unique, no. of nodes <= 100, no self loop, no repeated edges, graph is connected - all vertex are connected at least 1 path.
    * appr: BFS : visit graphs and use map to keep track of nodes.
    * test cases:
    *  1 - 2
    *  |   |
    *  4 - 3
    *
    * */

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        // check empty graph, single node graph
        if(node == null) {
            return null;
        }
        if(node.neighbors.isEmpty()) {
            return new Node(node.val);
        }

        Node newnode = new Node(node.val);
        HashMap<Integer, Node> nodemap = new HashMap<>();
        nodemap.put(node.val, newnode);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()) {
               int qs = queue.size();
               for(int idx=0; idx<qs; idx++) {
                    // visit
                   final Node v = queue.poll();
                   for(Node child: v.neighbors) {
                       if(!nodemap.containsKey(child.val)) { // not visited
                           queue.add(child);
                           nodemap.put(child.val, new Node(child.val));
                       }
                       nodemap.get(v.val).neighbors.add(nodemap.get(child.val));
                   }
               }
        }
        return newnode;
    }

    public static void main(String args[]) {
        Q133CloneGraph sol = new Q133CloneGraph();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors.add(n2);
        n2.neighbors.add(n3);
        n3.neighbors.add(n4);
        n4.neighbors.add(n1);
        final Node node = sol.cloneGraph(n1);
        System.out.println(node.val);
    }


}
