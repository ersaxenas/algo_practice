package com.lrn.cci.treeandgraph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BreadthFirstSearch {

	boolean marked[];
	int markedTo[];
	int distanceTo[];
	int sourceNode;

	public BreadthFirstSearch(final Graph graph, final int src) {
		marked = new boolean[graph.getV()];
		markedTo = new int[graph.getV()];
		distanceTo = new int[graph.getV()];
		this.sourceNode = src;
		Arrays.fill(marked, false);
		Arrays.fill(markedTo, -1);
		Arrays.fill(distanceTo, 0);
	}

	public void bfs(final Graph graph, final int src) {
		marked[src] = true;
		markedTo[src] = src;
		distanceTo[src] = 0;
		Queue<Integer> queue =  new LinkedList<>();
		queue.add(src);

		while(!queue.isEmpty()) {
			int currentNode = queue.poll();
			for(int adjV: graph.adj(currentNode)) {
				if(!marked[adjV]) {
					queue.add(adjV);
					marked[adjV] = true;
					markedTo[adjV] = currentNode;
					distanceTo[adjV] = distanceTo[currentNode] + 1;
				}
			}
		}
	}

	public boolean hasPathTo(final int vert) {
		return marked[vert];
	}

	public Iterator<Integer> pathTo(final int vert) {
		Stack<Integer> stack = new Stack<>();
		if(!marked[vert]) {
			//return null or empty collection since no path is found
			return stack.iterator();
		}
		int node = vert;
		while(node != sourceNode) {
			stack.push(node);
			node = markedTo[node];
		}
		stack.push(sourceNode);
		return stack.iterator();
	}

}
