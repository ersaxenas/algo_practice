package com.lrn.cci.treeandgraph;


public interface Graph {
	 
	/**
	 * establish an edge/link between two vertices/nodes
	 */
	 public void addEdge(int v, int w);
	 /**
	  * get number of vertex/nodes
	  * */
	 public int getV(); 
	 /**
	  * get number of edges
	  * @return
	  */
	 public int getE(); 
	 /**
	  * get adjacent edges of a vertex/nodes v
	  * @param v
	  * @return number of adjacent ndoes 
	  */
	 public Iterable<Integer> adj(int v); 
	 
	
}
