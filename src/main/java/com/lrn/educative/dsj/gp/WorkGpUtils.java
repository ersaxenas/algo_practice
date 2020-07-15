package com.lrn.educative.dsj.gp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import static com.lrn.educative.dsj.gp.Graph.GNode;

public class WorkGpUtils {
    static class CheckBfs {
        public String bfsTraversal(Graph graph, int source) {
            Queue<Graph.GNode> queue = new LinkedList<>();
            Set<GNode> visitedNodes = new HashSet<>();
            StringBuilder sbr = new StringBuilder();
            GNode sourceNode = new GNode(source, source);
            queue.offer(sourceNode);
            visitedNodes.add(sourceNode);
            while (!queue.isEmpty()) {
                GNode currNode = queue.poll();
                sbr.append(currNode.key);
                System.out.println(currNode);
                final LinkedList<GNode> vertices = graph.getVertices(currNode);
                vertices.forEach(node -> {
                    if (!visitedNodes.contains(node)) {
                        visitedNodes.add(node);
                        queue.offer(node);
                    }
                });
            }
            return sbr.toString();
        }

        public static void main(String[] args) {
            Graph g = new Graph(5, true);
            g.addEdge(0, 1);
            g.addEdge(0, 2);
            g.addEdge(1, 3);
            g.addEdge(1, 4);
            CheckBfs checkBfs = new CheckBfs();
            System.out.println(checkBfs.bfsTraversal(g, 0));
        }
    }

    static class CheckDfs {
        private Set<GNode> visitedNodes = new HashSet<>();

        public String dfsTraversalWithRecursion(Graph graph, int source) {
            GNode sourceNode = new GNode(source, source);
            visitedNodes.add(sourceNode);
            // System.out.println(sourceNode);
            final LinkedList<GNode> vertices = graph.getVertices(sourceNode);
            StringBuilder sbr = new StringBuilder(sourceNode.key.toString());
            for (GNode node : vertices) {
                if (!visitedNodes.contains(node)) {
                    visitedNodes.add(node);
                    sbr.append(dfsTraversalWithRecursion(graph, node.key));
                }
            }
            return sbr.toString();
        }

        public String dfsTraversalWithStack(Graph graph, int source) {
            Set<GNode> gnodesVisited = new HashSet<>();
            Stack<GNode> gNodesStack = new Stack<>();
            GNode sourceNode = new GNode(source, source);
            gnodesVisited.add(sourceNode);
            gNodesStack.push(sourceNode);
            StringBuilder sbr = new StringBuilder();
            while (!gNodesStack.isEmpty()) {
                GNode currNode = gNodesStack.pop();
                //System.out.println(currNode);
                sbr.append(currNode.key);
                final LinkedList<GNode> vertices = graph.getVertices(currNode);
                for (GNode node : vertices) {
                    if (!gnodesVisited.contains(node)) {
                        gnodesVisited.add(node);
                        gNodesStack.push(node);
                    }
                }
            }
            return sbr.toString();
        }

        public static void main(String[] args) {
            Graph g = new Graph(5, true);
            g.addEdge(0, 1);
            g.addEdge(0, 2);
            g.addEdge(1, 3);
            g.addEdge(1, 4);
            CheckDfs checkDfs = new CheckDfs();
            System.out.println("with recursion : " + checkDfs.dfsTraversalWithRecursion(g, 0));
            System.out.println("With stack: " + checkDfs.dfsTraversalWithStack(g, 0));
        }
    }

    /*Cycle Detection in the Graph*/
    static class GraphCycleDetector {
        public boolean detectCycleInGraph(Graph graph) {
            Set<GNode> visitedNodes = new HashSet<>();
            Set<GNode> nodesOnStack = new HashSet<>();
            final List<GNode> allVertices = graph.getAllVertices();

            for (GNode vertex : allVertices) {
                if (checkCycle(graph, vertex, visitedNodes, nodesOnStack)) {
                    return true;
                }
            }
            return false;
        }

        public boolean checkCycle(Graph graph, GNode node, Set<GNode> visitedNodes, Set<GNode> gnodeStack) {
            if (gnodeStack.contains(node)) {
                return true;
            }
            if (visitedNodes.contains(node)) {
                return false;
            }
            visitedNodes.add(node);
            gnodeStack.add(node);
            final LinkedList<GNode> vertices = graph.getVertices(node);
            for (GNode gnode : vertices) {
                if (checkCycle(graph, gnode, visitedNodes, gnodeStack)) {
                    return true;
                }
            }
            gnodeStack.remove(node);
            return false;
        }

        public static void main(String[] args) {
            GraphCycleDetector graphCycleDetector = new GraphCycleDetector();
            Graph g1 = new Graph(4, true);
            g1.addEdge(0, 1);
            g1.addEdge(1, 2);
            g1.addEdge(1, 3);
            g1.addEdge(3, 0);
            g1.printGraph();
            System.out.println(graphCycleDetector.detectCycleInGraph(g1));

            System.out.println();
            Graph g2 = new Graph(3, true);
            g2.addEdge(0, 1);
            g2.addEdge(1, 2);
            g2.printGraph();
            System.out.println(graphCycleDetector.detectCycleInGraph(g2));
        }
    }

    /*Find "Mother Vertex" in a Graph*/
    static class MotherVertexFinder {

        public GNode findMotherVertex(Graph graph) {
            if (graph == null || graph.isEmpty()) {
                throw new IllegalStateException("Null or empty graph");
            }
            final List<GNode> allVertices = graph.getAllVertices();
            GNode lastNode = null;
            Set<GNode> visitedNodes = new HashSet<>();
            for (GNode gNode : allVertices) {
                if (!visitedNodes.contains(gNode)) {
                    depthFirstSearch(graph, gNode, visitedNodes);
                    lastNode = gNode;
                }
            }
            System.out.println("last node: " + lastNode);
            /*check if mother vertex*/
            visitedNodes.clear();
            depthFirstSearch(graph, lastNode, visitedNodes);
            if(allVertices.size() == visitedNodes.size()) {
                return lastNode;
            }
            return null;
        }

        public void depthFirstSearch(Graph graph, GNode node, Set<GNode> visitedNodes) {
           visitedNodes.add(node);
            final LinkedList<GNode> vertices = graph.getVertices(node);
            for (GNode vertex : vertices) {
                if(!visitedNodes.contains(vertex)) {
                    depthFirstSearch(graph, vertex, visitedNodes);
                }
            }
        }

        public static void main(String[] args) {
            MotherVertexFinder motherVertexFinder = new MotherVertexFinder();
            Graph g = new Graph(4, true);
            g.addEdge(0,1);
            g.addEdge(1,2);
            g.addEdge(3,0);
            g.addEdge(3,1);
            g.printGraph();
            System.out.println("Mother Vertex is: " + motherVertexFinder.findMotherVertex(g));
        }
    }

    /*Count the Number of Edges in a Graph*/
    static class EdgesFinder {
        public Integer find(Graph graph) {
            Integer edges = -1;
            if(graph == null || graph.isEmpty()) {
                throw new IllegalStateException("Graph is null of empty");
            }
            final List<GNode> allVertices = graph.getAllVertices();
            int edgeCount = 0;
            for (GNode vertex : allVertices) {
                final LinkedList<GNode> vertices = graph.getVertices(vertex);
                if(vertices != null && !vertices.isEmpty()) {
                   edgeCount = edgeCount + vertices.size();
                }
            }
            if(graph.isDirected()) {
                edges =  edgeCount;
            } else {
                edges = edgeCount / 2;
            }
            return edges;
        }

        public static void main(String[] args) {
            EdgesFinder edgesFinder = new EdgesFinder();
            Graph g = new Graph(9, true);
            g.addEdge(0,2);
            g.addEdge(0,5);
            g.addEdge(2,3);
            g.addEdge(2,4);
            g.addEdge(5,3);
            g.addEdge(5,6);
            g.addEdge(3,6);
            g.addEdge(6,7);
            g.addEdge(6,8);
            g.addEdge(6,4);
            g.addEdge(7,8);


            g.printGraph();
            System.out.println("Number of edges: " + edgesFinder.find(g));
            System.out.println();

            Graph g2 = new Graph(7, true);
            g2.addEdge(1,2);
            g2.addEdge(1,3);
            g2.addEdge(3,4);
            g2.addEdge(3,5);
            g2.addEdge(2,5);
            g2.addEdge(2,4);
            g2.addEdge(4,6);
            g2.addEdge(4,5);
            g2.addEdge(6,5);


            g2.printGraph();
            System.out.println("Number of edges: " + edgesFinder.find(g2));
        }
    }

    /*Check if a Path exists between two vertices*/
    static class PathCheck {
        public boolean checkPath(Graph graph, GNode source, GNode destination) {
            if(graph == null || graph.isEmpty()) {
                throw new IllegalStateException("Graph is null of empty");
            }
            Set<GNode> visited = new HashSet<>();
            Stack<GNode> nodeStack = new Stack<>();
            nodeStack.push(source);
            visited.add(source);
            Stack<GNode> path = new Stack<>();
            while(!nodeStack.isEmpty()) {
                GNode node = nodeStack.pop();
                path.push(node);
                if(node.equals(destination)) {
                    printPath(path);
                    return true;
                }
                final LinkedList<GNode> vertices = graph.getVertices(node);
                for (GNode vertex : vertices) {
                    if(!visited.contains(vertex)) {
                        visited.add(vertex);
                        nodeStack.push(vertex);
                    }
                }
                path.pop();
            }
            return false;
        }

        public void printPath(Stack<GNode> pathStack) {
            System.out.println();
            while(!pathStack.isEmpty()) {
                System.out.print(pathStack.pop().key+" ");
            }
            System.out.println();
        }

        public static void main(String[] args) {
            PathCheck pathCheck = new PathCheck();
            Graph g1 = new Graph(9, true);
            g1.addEdge(0,2);
            g1.addEdge(0,5);
            g1.addEdge(2,3);
            g1.addEdge(2,4);
            g1.addEdge(5,3);
            g1.addEdge(5,6);
            g1.addEdge(3,6);
            g1.addEdge(6,7);
            g1.addEdge(6,8);
            g1.addEdge(6,4);
            g1.addEdge(7,8);
            g1.printGraph();
            System.out.println("Path exists: " + pathCheck.checkPath(g1, new GNode(0,0), new GNode(7,7)));
            System.out.println();
            Graph g2 = new Graph(4,true);
            g2.addEdge(0,1);
            g2.addEdge(1,2);
            g2.addEdge(1,3);
            g2.addEdge(2,3);

            g2.printGraph();
            System.out.println("Path exists: " + pathCheck.checkPath(g2, new GNode(3,3), new GNode(0,0)));
        }
    }

    /*Check if the Given Graph is a Tree*/
    static class TreeCheck {
        boolean isTree(Graph graph) {
            if(graph == null || graph.isEmpty()) {
                throw new IllegalStateException("Graph is null of empty");
            }
            GNode sourceNode = graph.adjacencyMap.entrySet().iterator().next().getKey();
            //System.out.println("sourceNode = " + sourceNode);
            Queue<GNode> queue = new LinkedList<>();
            Set<GNode> visited = new HashSet<>();
            queue.add(sourceNode);
            visited.add(sourceNode);
            while(!queue.isEmpty()) {
                GNode currentNode = queue.poll();
                final LinkedList<GNode> vertices = graph.getVertices(currentNode);
                for (GNode vertex : vertices) {
                    if(visited.contains(vertex)) {
                        return false;
                    } else {
                        visited.add(vertex);
                        queue.add(vertex);
                    }
                }
            }
            if(visited.size() != graph.getAllVertices().size()) {
                return false;
            }
            return true;
        }
        public static void main(String[] args) {
            TreeCheck treeCheck = new TreeCheck();
            Graph g = new Graph(5, true);
            g.addEdge(0,1);
            g.addEdge(0,2);
            g.addEdge(0,3);
            g.addEdge(3,4);
            g.printGraph();
            System.out.println("isTree : " + treeCheck.isTree(g));

            Graph g2 = new Graph(4, true);
            g2.addEdge(0,1);
            g2.addEdge(0,2);
            g2.addEdge(0,3);
            g2.addEdge(3,2);
            g2.printGraph();
            System.out.println("isTree : " + treeCheck.isTree(g2));

            Graph g3 = new Graph(6, true);
            g3.addEdge(0,1);
            g3.addEdge(0,2);
            g3.addEdge(0,3);
            g3.addEdge(4,5);
            g3.printGraph();
            System.out.println("isTree : " + treeCheck.isTree(g3));
        }
    }
    /*Find the Shortest Path between Two Vertices*/
    static class ShortestPath {
        public GNode find(Graph graph, GNode source, GNode destination) {
            if(graph == null || graph.isEmpty()) {
                throw new IllegalStateException("Graph is null of empty");
            }
            if(source == null) {
                throw new IllegalStateException("Source node is null of empty");
            }
            Queue<GNode> queue = new LinkedList<>();
            Set<GNode> visitedNodes = new HashSet<>();
            source.distanceFromSource = 0;
            queue.add(source);
            visitedNodes.add(source);
            while(!queue.isEmpty()) {
                GNode currentNode = queue.poll();
                if(currentNode.equals(destination)) {
                    return currentNode;
                }
                final LinkedList<GNode> vertices = graph.getVertices(currentNode);
                for (GNode vertex : vertices) {
                    if(!visitedNodes.contains(vertex)) {
                        vertex.distanceFromSource = currentNode.distanceFromSource + 1;
                        vertex.addPathNodes(currentNode);
                        queue.offer(vertex);
                        visitedNodes.add(vertex);
                    }
                }
            }
            return null;
        }
        public static void main(String[] args) {
            ShortestPath shortestPath = new ShortestPath();
            Graph g=new Graph(5, true);
            g.addEdge(0,1);
            g.addEdge(0,2);
            g.addEdge(1,3);
            g.addEdge(3,4);
            g.addEdge(1,4);
            g.printGraph();
            final GNode dest = shortestPath.find(g, new GNode(0, 0), new GNode(4, 4));
            if(dest != null) {
                System.out.println("Distance : "+ dest.distanceFromSource +" path : " + dest.getPath());
            }
            else {
                System.out.println("path not found.");
            }
        }
    }

}
