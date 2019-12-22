package com.lrn.educative.dsj.gp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {
    int vertices;
    Map<GNode,LinkedList<GNode>> adjacencyMap;
    boolean isDirected;
    static class GNode {
      Integer key;
      Integer data;
      Integer distanceFromSource;
      GNode parent;
      List<GNode> pathNodes = new ArrayList<>();

        public GNode(Integer key, Integer data) {
            this.key = key;
            this.data = data;
        }

        public void addPathNodes(GNode node) {
            pathNodes.addAll(node.pathNodes);
            pathNodes.add(node);
        }
        public void addPathNode(GNode node) {
            pathNodes.add(node);
        }

        public String getPath() {
            final String path = pathNodes.stream().map(node -> String.valueOf(node.key)).collect(Collectors.joining("->"));
            return path + "->" + key;
        }

        @Override
        public String toString() {
            return "(" + key + "," + data + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GNode gNode = (GNode) o;
            return Objects.equals(key, gNode.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        adjacencyMap = new LinkedHashMap<>(vertices);
        this.isDirected = directed;
        for (int idx = 0; idx <vertices; idx++) {
            GNode node = new GNode(idx,idx);
            adjacencyMap.put(node, new LinkedList<>());
        }
    }

    boolean isDirected() {return isDirected;}

    public boolean isEmpty() {
        return adjacencyMap.isEmpty();
    }

    public void printGraph() {
        String type = (isDirected) ? "Directed" : "UnDirected";
        System.out.println(String.format(">>Adjacency List of %s Graph<<",type));
        adjacencyMap.forEach((gnode, gNodeLinkedList) -> {
            if(!gNodeLinkedList.isEmpty()) {
                final String nodes = gNodeLinkedList.stream().map(node -> node.toString()).map(val -> "| " + val + " |").collect(Collectors.joining(" - "));
                System.out.println("V["+gnode.key+"] -> "+ nodes);
            }
        });

    }
    public void addEdge(int source, int destination) {
      GNode snode = new GNode(source,source);
      GNode dnode = new GNode(destination,destination);
      if(adjacencyMap.containsKey(snode)) {
          adjacencyMap.get(snode).add(dnode);
          if(!isDirected()) {
              adjacencyMap.get(dnode).add(snode);
          }
      }
    }

    public Set<GNode> getEdges() {
        return (adjacencyMap.isEmpty()) ? new HashSet<>() : adjacencyMap.keySet();
    }

    public LinkedList<GNode> getVertices(GNode gNode) {
        return (adjacencyMap.containsKey(gNode)) ? adjacencyMap.get(gNode) : new LinkedList<>();
    }

    public List<GNode> getAllVertices() {
        return (adjacencyMap.isEmpty()) ? new ArrayList<>() : new ArrayList<>(adjacencyMap.keySet());
    }


    public static void main(String[] args) {
        Graph g= new Graph(4, true);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        g.printGraph();
    }

}
