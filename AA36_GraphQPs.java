package dsa;

import java.util.ArrayList;
import dsa.AA35_Graph.ManualGraph;

public class AA36_GraphQPs {

	// ------------------ Graph Questions ------------------

	// 1. Find all paths exists between two nodes using DFS in O (V ^ V)
	public static void findAllPaths(ManualGraph graph, int src, int dest, boolean[] visited, ArrayList<Integer> path) {
		visited[src] = true;
		path.add(src);

		if (src == dest) {
			System.out.println("Path: " + path);
		} else {
			for (int neighbor : graph.adjList[src]) {
				if (!visited[neighbor]) {
					findAllPaths(graph, neighbor, dest, visited, path);
				}
			}
		}
		// Backtrack
		path.remove(path.size() - 1);
		visited[src] = false;
	}

	// 2. Cycle detection in undirected graph using DFS
	// Driver
	public static boolean isCyclicUndirected(ManualGraph graph) {
		boolean[] visited = new boolean[graph.V];
		for (int i = 0; i < graph.V; i++)
			if (!visited[i] && isCyclicUndirected(graph, i, visited, -1))
				return true;
		return false;
	}

	private static boolean isCyclicUndirected(ManualGraph graph, int node, boolean[] visited, int parent) {
		visited[node] = true;

		for (int neighbor : graph.adjList[node]) {
			if (!visited[neighbor] && isCyclicUndirected(graph, neighbor, visited, node))
				return true;
			else if (neighbor != parent)
				return true; // Found a back edge
		}
		return false;
	}

	// 3. Cycle detection in directed graph using DFS
	// Driver
	public static boolean isCyclicDirected(ManualGraph graph) {
		boolean[] visited = new boolean[graph.V];
		boolean[] recursionStack = new boolean[graph.V];

		for (int i = 0; i < graph.V; i++)
			if (!visited[i] && isCyclicDirected(graph, i, visited, recursionStack))
				return true;
		return false;
	}

	// Helper DFS
	private static boolean isCyclicDirected(ManualGraph graph, int node, boolean[] visited, boolean[] recursionStack) {
		visited[node] = true;
		recursionStack[node] = true;

		for (int neighbor : graph.adjList[node]) {
			if (!visited[neighbor] && isCyclicDirected(graph, neighbor, visited, recursionStack))
				return true;
			else if (recursionStack[neighbor])
				return true; // Back edge in directed graph
		}
		recursionStack[node] = false;
		return false;
	}

	public static void main(String[] args) {
		ManualGraph graph = new ManualGraph(5);
		graph.addEdgeUndirected(0, 1);
		graph.addEdgeUndirected(0, 2);
		graph.addEdgeUndirected(1, 3);
		graph.addEdgeUndirected(2, 3);
		graph.addEdgeUndirected(3, 4);
		graph.addEdgeUndirected(1, 4);
		graph.addEdgeUndirected(2, 4);
		graph.printGraph();
		graph.bfs(0);
		graph.dfs(0);

		// 1. Find all paths exists between two nodes using DFS
		System.out.println("\nFinding all paths between 0 and 4:");
		findAllPaths(graph, 0, 4, new boolean[graph.V], new ArrayList<>());

		// 2. Cycle detection in undirected graph
		System.out.println("\nChecking for cycles in the Undirected graph:");
		if (isCyclicUndirected(graph)) {
			System.out.println("Graph contains a cycle.");
		} else {
			System.out.println("Graph does not contain a cycle.");
		}

		// 3. Cycle detection in directed graph
		ManualGraph directedGraph = new ManualGraph(5);
		directedGraph.addEdgeDirected(0, 1);
		directedGraph.addEdgeDirected(1, 2);
		directedGraph.addEdgeDirected(2, 0); // This creates a cycle
		directedGraph.addEdgeDirected(3, 4);
		directedGraph.printGraph();
		directedGraph.bfs(0);
		directedGraph.dfs(0);
		System.out.println("\nChecking for cycles in the Directed graph:");
		if (isCyclicDirected(directedGraph)) {
			System.out.println("Graph contains a cycle.");
		} else {
			System.out.println("Graph does not contain a cycle.");
		}

	}
}
