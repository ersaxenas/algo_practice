package com.lrn.cci.treeandgraph;

import java.util.ArrayList;
import java.util.List;


public class Unigraph implements Graph{
	/*number of vertices or nodes*/
	private final int V;
	/*number of edges*/
	private int E = 0;
	/*
	 * an array of bag or list of nodes.
	 * for each node list/bag will have connected nodes
	 * [0] -> List[5,3,10]
	 * [1] -> List[4]
	 * [2] -> []
	 *
	 * here node/vertex 0 has path to node 5,3,10
	 * node 1 has path to 4
	 * and node 2 doesn't have any path
	 * */
	private final List<Integer> adj[];

	public Unigraph(final int noOfVertex) {
		/*initialize no. of vertex/nodes*/
		V=noOfVertex;
		/*Initialize bag or list of connected nodes*/
		adj = new ArrayList[noOfVertex];
		/*initialize each element of array*/
		for(int cnt=0; cnt<noOfVertex; cnt++) {
			adj[cnt] = new ArrayList<Integer>();
		}
	}

	/**
	 * establish link between two nodes
	 */
	@Override
	public void addEdge(final int v, final int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	@Override
	public int getV() {
		return V;
	}

	@Override
	public int getE() {
		return E;
	}

	@Override
	public Iterable<Integer> adj(final int v) {
		return adj[v];
	}


}
