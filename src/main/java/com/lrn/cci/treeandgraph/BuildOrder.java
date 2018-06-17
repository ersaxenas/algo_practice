package com.lrn.cci.treeandgraph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BuildOrder<T> {

	/*
	 * Build order/ precedence order / scheduler / course curricular all are same problem solved by topological sort.
	 */
	Set<T> marked = new HashSet<>();
	Stack<T> stack = new Stack<>();

	public void buildOder(final DigraphOfObj<T> graph) {
		Set<T> setOfVertices = graph.getVertices();
		setOfVertices.forEach(vertice -> {
			if(!marked.contains(vertice)) {
				dfs(graph, vertice);
			}
		});

	}

	public void dfs(final DigraphOfObj<T> graph, final T vertice) {
		marked.add(vertice);
		for (T adjVert : graph.getAdjacentVertices(vertice)) {
			if (!marked.contains(adjVert)) {
				dfs(graph, adjVert);
			}
		}
		stack.push(vertice);
	}

	public Iterable<T> getBuildOrder() {
		Stack<T> stackReverse = new Stack<>();
		while(!stack.isEmpty()) {
			stackReverse.push(stack.pop());
		}
		return stackReverse;
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

		System.out.println("*******************");
		BuildOrder<String> buildOrder = new BuildOrder<>();
		buildOrder.buildOder(digraph);
		buildOrder.getBuildOrder().forEach(vrt -> {
			System.out.print(vrt + " -> ");
		});

	}

}
