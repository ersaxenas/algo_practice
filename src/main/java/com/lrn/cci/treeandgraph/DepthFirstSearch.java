package com.lrn.cci.treeandgraph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class DepthFirstSearch {

	boolean marked[];
	int markedTo[];
	int sourceNode;

	public DepthFirstSearch(final Graph graph, final int src) {
		marked = new boolean[graph.getV()];
		markedTo = new int[graph.getV()];
		Arrays.fill(marked, false);
		Arrays.fill(markedTo, -1);
		sourceNode = src;
		dfs(src, graph);
	}

	public void dfs(final int src, final Graph graph) {
		marked[src] = true;
		for(int adjV : graph.adj(src)) {
			if(!marked[adjV]) {
				dfs(adjV, graph);
				markedTo[adjV] = src;
			}
		}
	}

	public boolean hasPathTo(final int vert) {
		return marked[vert];
	}

	public Iterator<Integer> pathTo(final int vert) {
		if(marked[vert]) {
			return null;
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(vert);
		int start = vert;
		while(start != sourceNode) {
			stack.push(start);
			/*go to parent node*/
			start = markedTo[start];
		}
		stack.push(sourceNode);
		return stack.iterator();
	}


}
