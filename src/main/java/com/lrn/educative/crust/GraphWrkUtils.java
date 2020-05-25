package com.lrn.educative.crust;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class GraphWrkUtils {

    static class Vertex {
        int id;
        boolean visited;
        char value;
        List<Vertex> adj_vertices;
        List<Vertex> in_vertices;

        public Vertex(char value, boolean visited) {
            this.value = value;
            this.visited = visited;
            this.adj_vertices = new ArrayList<>(); // out vertices
            this.in_vertices = new ArrayList<>(); // in vertices
        }

        public Vertex(int id, boolean visited) {
            this.id = id;
            this.visited = visited;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return id == vertex.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "V["+ id +
                    " - " + visited +
                    ']';
        }
    }

    static class Edge {
        int weight;
        boolean visited;
        Vertex src;
        Vertex dest;

        public Edge(int weight, boolean visited, Vertex src, Vertex dest) {
            this.weight = weight;
            this.visited = visited;
            this.src = src;
            this.dest = dest;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public Vertex getSrc() {
            return src;
        }

        public void setSrc(Vertex src) {
            this.src = src;
        }

        public Vertex getDest() {
            return dest;
        }

        public void setDest(Vertex dest) {
            this.dest = dest;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    " src " + src +
                    " dest " + dest +
                    " weight=" + weight +
                    '}';
        }
    }

    static class Graph {
        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        public Graph(Set<Vertex> vertices, Set<Edge> edges) {
            this.vertices.addAll(vertices);
            this.edges.addAll(edges);
        }

        public Graph(List<Vertex> vertices, List<Edge> edges) {
            this.vertices.addAll(vertices);
            this.edges.addAll(edges);
        }

        public List<Vertex> getVertices() {
            return vertices;
        }

        public void setVertices(List<Vertex> vertices) {
            this.vertices = vertices;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void setEdges(List<Edge> edges) {
            this.edges = edges;
        }

        public void addEdge(Vertex start, Vertex end) {
              start.adj_vertices.add(end);
              end.in_vertices.add(start);
        }

        boolean outEqualToIn() {
            for (Vertex vertex : vertices) {
                if(vertex.in_vertices.size() != vertex.adj_vertices.size()) {
                    return false;
                }
            }
            return true;
        }

        boolean allVisited() {
            for (Vertex vertex : vertices) {
                if(!vertex.isVisited()) {
                    return false;
                }
            }
            return true;
        }

        // This method returns the vertex with a given id if it
        // already exists in the graph, returns NULL otherwise
        Vertex vertexExists(int id) {
            for (Vertex vertex : vertices) {
                if (vertex.id == id) {
                    return vertex;
                }
            }
            return null;
        }
        // This method returns the vertex with a given id if it
        // already exists in the graph, returns NULL otherwise
        Vertex vertexExists(char value) {
            for (Vertex vertex : vertices) {
                if (vertex.value == value) {
                    return vertex;
                }
            }
            return null;
        }

        // This method generates the graph with v vertices
        // and e edges
        void generateGraph(int vertices, List<ArrayList<Integer>> edges) {
            // create vertices
            for (int i = 0; i < vertices; i++) {
                Vertex v = new Vertex(i + 1, false);
                getVertices().add(v);
            }

            // create edges
            for (int i = 0; i < edges.size(); i++) {
                Vertex src = vertexExists(edges.get(i).get(1));
                Vertex dest = vertexExists(edges.get(i).get(2));
                Edge e = new Edge(edges.get(i).get(0), false, src, dest);
                getEdges().add(e);
            }
        }

        String printGraph() {
            String result = "";

            for (int i = 0; i < getEdges().size(); i++) {
                result += getEdges().get(i).getSrc().getId() + "->"
                        + getEdges().get(i).getDest().getId() + "["
                        + getEdges().get(i).getWeight() + ", "
                        + getEdges().get(i).isVisited() + "]  ";
            }
            return result;
        }

        void printMst(int w) {
            System.out.println("MST");
            for (int i = 0; i < getEdges().size(); i++) {
                if (getEdges().get(i).isVisited() == true) {
                    System.out.println(getEdges().get(i).getSrc().getId() + "->"
                            + getEdges().get(i).getDest().getId());
                }
            }
            System.out.println("weight: " + w);
            System.out.println();
        }
    }

    static class GNode {
        public int data;
        public List<GNode> neighbors = new ArrayList<>();

        public GNode(int d) {
            data = d;
        }
    }

    static class DirectedGraphClone {
        public GNode clone(GNode sourceNode) {
            if (sourceNode == null) {
                return null;
            }
            HashMap<Integer, GNode> vertices = new HashMap<>();
            Queue<GNode> queue = new LinkedList<>();
            queue.add(sourceNode);
            vertices.put(sourceNode.data, new GNode(sourceNode.data));
            while (!queue.isEmpty()) {
                GNode node = queue.poll();
                for (GNode child : node.neighbors) {
                    if (child != null && !vertices.containsKey(child.data)) {
                        vertices.put(child.data, new GNode(child.data));
                        queue.add(child);
                    }
                    vertices.get(node.data).neighbors.add(vertices.get(child.data));
                }
            }
            return vertices.get(sourceNode.data);
        }

    }

    static class MinmumSpanningTree {

        public int findMinSpanningTree(Graph graph) {
            int vertex_count = 0;
            int weight = 0;
            PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
            //select a vertex
            for (Edge edge : graph.getEdges()) {
                minHeap.add(edge);
            }

            Vertex currentVertex = graph.vertices.get(0);
            currentVertex.setVisited(true);
            vertex_count++;
            List<Edge> temp = new ArrayList<>();
            int noOfVertices = graph.vertices.size();
            while (vertex_count < noOfVertices) {
                // find min wt edge where source has been visited.
                while(!minHeap.isEmpty()) {
                    Edge edge = minHeap.poll();
                    if(edge != null && !edge.isVisited()) { // edge is not visited
                        if(edge.getSrc().isVisited() && !edge.getDest().isVisited()) { // source is visited and destination is not visited
                            System.out.println(String.format("SELECTED: SRC [%d], DEST [%d], WT [%d]",edge.src.id,edge.dest.id,edge.weight));
                            edge.getDest().setVisited(true);
                            vertex_count++;
                            weight += edge.weight;
                            break;
                        } else {
                            temp.add(edge);
                        }
                    }
                }
                // now add the unselected edges back
                minHeap.addAll(temp);
                temp.clear();
            }
           return weight;
        }


        public static void testGraph1(MinmumSpanningTree minmumSpanningTree) {
            Graph graph = new Graph(new ArrayList<Vertex>(), new ArrayList<Edge>());
            int v = 5;

            // each edge contains the following: weight, src, dest
            ArrayList<Integer> e1 = new ArrayList<>(Arrays.asList(1, 1, 2));
            ArrayList<Integer> e2 = new ArrayList<>(Arrays.asList(1, 1, 3));
            ArrayList<Integer> e3 = new ArrayList<>(Arrays.asList(2, 2, 3));
            ArrayList<Integer> e4 = new ArrayList<>(Arrays.asList(3, 2, 4));
            ArrayList<Integer> e5 = new ArrayList<>(Arrays.asList(3, 3, 5));
            ArrayList<Integer> e6 = new ArrayList<>(Arrays.asList(2, 4, 5));

            List<ArrayList<Integer>> e = new ArrayList<>();
            e.add(e1);
            e.add(e2);
            e.add(e3);
            e.add(e4);
            e.add(e5);
            e.add(e6);

            graph.generateGraph(v, e);
            System.out.println("Testing graph 1...");
            // graph.printGraph();
            int w = minmumSpanningTree.findMinSpanningTree(graph);
            graph.printMst(w);
        }

        public static void testGraph2(MinmumSpanningTree minmumSpanningTree) {
            Graph graph = new Graph(new ArrayList<Vertex>(), new ArrayList<Edge>());
            int v = 7;

            // each edge contains the following: weight, src, dest
            ArrayList<Integer> e1 = new ArrayList<>(Arrays.asList(2, 1, 4));
            ArrayList<Integer> e2 = new ArrayList<>(Arrays.asList(1, 1, 3));
            ArrayList<Integer> e3 = new ArrayList<>(Arrays.asList(2, 1, 2));
            ArrayList<Integer> e4 = new ArrayList<>(Arrays.asList(1, 3, 4));
            ArrayList<Integer> e5 = new ArrayList<>(Arrays.asList(3, 2, 4));
            ArrayList<Integer> e6 = new ArrayList<>(Arrays.asList(2, 3, 5));
            ArrayList<Integer> e7 = new ArrayList<>(Arrays.asList(2, 4, 7));
            ArrayList<Integer> e8 = new ArrayList<>(Arrays.asList(1, 5, 6));
            ArrayList<Integer> e9 = new ArrayList<>(Arrays.asList(2, 5, 7));

            List<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
            e.add(e1);
            e.add(e2);
            e.add(e3);
            e.add(e4);
            e.add(e5);
            e.add(e6);
            e.add(e7);
            e.add(e8);
            e.add(e9);

            graph.generateGraph(v, e);
            System.out.println("Testing graph 2...");
            // graph.printGraph();
            int w = minmumSpanningTree.findMinSpanningTree(graph);
            graph.printMst(w);
        }

        public static void main(String[] args) {
            MinmumSpanningTree minmumSpanningTree = new MinmumSpanningTree();
            testGraph1(minmumSpanningTree);
            testGraph2(minmumSpanningTree);
        }
    }
    /*Word chaining*/
    static class WordChain {
        // This method creates a graph from a list of words. A node of
        // the graph contains a character representing the start or end
        // character of a word.
        Graph createGraph(List<String> words_list) {
            Graph graph = new Graph(new ArrayList<>(), new ArrayList<>());
            for (int i = 0; i < words_list.size(); i++) {
                String word = words_list.get(i);
                char start_char = word.charAt(0);
                char end_char = word.charAt(word.length() - 1);

                Vertex start = graph.vertexExists(start_char);
                if (start == null) {
                    start = new Vertex(start_char, false);
                    graph.vertices.add(start);
                }

                Vertex end = graph.vertexExists(end_char);
                if (end == null) {
                    end = new Vertex(end_char, false);
                    graph.vertices.add(end);
                }
                // Add an edge from start vertex to end vertex
                graph.addEdge(start, end);
            }
            return graph;
        }

        public boolean canChainWords(Vertex node, Vertex startNode, Graph graph) {
            // base case
            if(graph.allVisited()) { // all nodes has been visted
                for (Vertex adj_vertex : node.adj_vertices) {
                    if(adj_vertex.value == startNode.value) { // last node has a link to start node
                        return true;
                    }
                }
            }

            // recursive
            for (Vertex adj_vertex : node.adj_vertices) {
                if(!adj_vertex.isVisited()) { // not visited
                    adj_vertex.setVisited(true);
                    if(canChainWords(adj_vertex, startNode, graph)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean canChainWords(Graph graph) {
           if(graph.vertices.size() > 0 && graph.outEqualToIn()) { // if graph has some vertices and all vertices has same degree
               Vertex startNode = graph.vertices.get(0);
               return canChainWords(startNode, startNode, graph);
           }
           return false;
        }

        public boolean checkIfChainCanBeFormed(List<String> wordList) {
            if(wordList == null || wordList.isEmpty() || wordList.size() < 2) {
               return false;
            }
            Graph graph = createGraph(wordList);
            return canChainWords(graph);
        }

        public static void main(String[] args) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList("eve", "eat", "ripe", "tear"));
            WordChain wordChain = new WordChain();
            boolean result = wordChain.checkIfChainCanBeFormed(list);
            String output = result ? "true" : "false";
            System.out.println(output);
        }
    }

}
