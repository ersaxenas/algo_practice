package com.lrn.cci.treeandgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DigraphOfObj<T> {

	Map<T, List<T>> connectedNodes = new HashMap<>();
	Set<T> setOfVerteces = new HashSet<>();
	int edges = 0;
	ArrayList<T> blankList = new ArrayList<>();

	public void add(final T vrt1, final T vrt2) {
		if (vrt1 != null) {
			setOfVerteces.add(vrt1);
		}
		if (vrt2 != null) {
			setOfVerteces.add(vrt2);
		}
		if(vrt1 != null) {
			List<T> adjNodes = connectedNodes.get(vrt1);
			if (adjNodes == null) {
				adjNodes = new ArrayList<>();
				if (vrt2 != null) {
					adjNodes.add(vrt2);
					edges++;
				}
				connectedNodes.put(vrt1, adjNodes);
			} else {
				if ((vrt2 != null) && !adjNodes.contains(vrt2)) {
					adjNodes.add(vrt2);
					edges++;
				}
			}
		}
	}

	public List<T> getAdjacentVertices(final T vrt) {
		return ((connectedNodes.get(vrt) != null)? connectedNodes.get(vrt):  blankList);
	}

	public Set<T> getVertices() {
		return setOfVerteces;
	}

	public int getNoOfVertices() {
		return setOfVerteces.size();
	}

	public static void main(final String[] args) {
		DigraphOfObj<String> digraph = new DigraphOfObj<>();
		digraph.add("a", "d");
		digraph.add("f", "b");
		digraph.add("b", "d");
		digraph.add("f", "a");
		digraph.add("d", "c");
		digraph.add("e", null);
		digraph.getVertices().forEach(vrt -> {
			System.out.println("Vertice :" + vrt + " -> " + digraph.getAdjacentVertices(vrt));
		});

	}

}
