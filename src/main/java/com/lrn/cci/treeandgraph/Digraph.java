package com.lrn.cci.treeandgraph;

import java.util.ArrayList;
import java.util.List;

public class Digraph implements Graph {
	private final int V;
	private int E = 0;
	private final List<Integer> adj[];

	public Digraph(final int noOfVertex) {
		V = noOfVertex;
		adj = new ArrayList[noOfVertex];
		for (int cnt = 0; cnt < noOfVertex; cnt++) {
			adj[cnt] = new ArrayList<Integer>();
		}
	}

	public void addEdge(final int v, final int w) {
		adj[v].add(w);
		E++;
	}

	public int getV() {
		return V;
	}

	public int getE() {
		return E;
	}

	public Iterable<Integer> adj(final int v) {
		return adj[v];
	}

}
