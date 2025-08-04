package dsa;

import java.util.*;

import dsa.AA35_Graph.ManualGraph;

public class AA37_TopologicalSort {

	// ✅ Topological Sort using DFS (Reverse Postorder with Cycle Detection)
	public static List<Integer> topoSortDFS(ManualGraph graph) {
		boolean[] visited = new boolean[graph.V];
		boolean[] recStack = new boolean[graph.V]; // For cycle detection
		Deque<Integer> stack = new ArrayDeque<>();

		for (int i = 0; i < graph.V; i++) {
			if (!visited[i]) {
				if (dfsTopo(graph, i, visited, recStack, stack)) {
					System.out.println("⚠️ Cycle detected! Topo sort not possible.");
					return new ArrayList<>();
				}
			}
		}

		List<Integer> result = new ArrayList<>();
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	private static boolean dfsTopo(ManualGraph graph, int node, boolean[] visited, boolean[] recStack,
			Deque<Integer> stack) {
		visited[node] = true;
		recStack[node] = true; // Mark node in recursion stack

		for (int neighbor : graph.adjList[node]) {
			if (!visited[neighbor]) {
				if (dfsTopo(graph, neighbor, visited, recStack, stack)) {
					return true; // Cycle detected
				}
			} else if (recStack[neighbor]) {
				return true; // Cycle detected
			}
		}

		recStack[node] = false; // Remove from recursion stack
		stack.push(node); // Add after all children
		return false; // No cycle
	}

	// ✅ Topological Sort using Kahn's Algorithm (BFS + In-Degree)
	public static List<Integer> topoSortKahns(ManualGraph graph) {
		int[] indegree = new int[graph.V];
		for (int u = 0; u < graph.V; u++) {
			for (int v : graph.adjList[u]) {
				indegree[v]++;
			}
		}

		Deque<Integer> q = new ArrayDeque<>(); // Optimized queue
		for (int i = 0; i < graph.V; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		List<Integer> result = new ArrayList<>();
		while (!q.isEmpty()) {
			int node = q.poll();
			result.add(node);

			for (int neighbor : graph.adjList[node]) {
				indegree[neighbor]--;
				if (indegree[neighbor] == 0) {
					q.offer(neighbor);
				}
			}
		}

		if (result.size() != graph.V) {
			System.out.println("⚠️ Cycle detected! Topo sort not possible.");
			return new ArrayList<>();
		}

		return result;
	}

	public static void main(String[] args) {
		// Test your topological sort DAG
		ManualGraph g = new ManualGraph(4); // a=0, b=1, c=2, d=3
		g.addEdgeDirected(0, 1);
		g.addEdgeDirected(0, 2);
		g.addEdgeDirected(1, 3);
		g.addEdgeDirected(2, 3);

		System.out.println("=== Testing Topological Sort DAG ===");
		g.printGraph();
		g.bfs(0);
		g.dfs(0);

		// Topological Sort with Runtime Averaging
		int iterations = 1000;
		long totalDFS = 0, totalKahns = 0;
		List<Integer> dfsResult = null, kahnsResult = null;

		for (int i = 0; i < iterations; i++) {
			long start = System.nanoTime();
			dfsResult = topoSortDFS(g);
			totalDFS += System.nanoTime() - start;
		}
		for (int i = 0; i < iterations; i++) {
			long start = System.nanoTime();
			kahnsResult = topoSortKahns(g);
			totalKahns += System.nanoTime() - start;
		}

		System.out.println("\n📘 Topological Sort using DFS:");
		System.out.println(dfsResult);
		System.out.println("⏱️ Average Time (DFS): " + (totalDFS / iterations) + " ns");
		System.out.println("\n📘 Topological Sort using Kahn's Algorithm:");
		System.out.println(kahnsResult);
		System.out.println("⏱️ Average Time (Kahn's): " + (totalKahns / iterations) + " ns");

		// Test larger graph for scalability
		System.out.println("\n=== Testing Larger Graph ===");
		ManualGraph largeGraph = new ManualGraph(1000);
		for (int i = 0; i < 999; i++) {
			largeGraph.addEdgeDirected(i, i + 1);
		}
		long start = System.nanoTime();
		topoSortDFS(largeGraph);
		long dfsTime = System.nanoTime() - start;
		start = System.nanoTime();
		topoSortKahns(largeGraph);
		long kahnsTime = System.nanoTime() - start;
		System.out.println("📘 Topological Sort DFS (Large Graph, V=1000): " + dfsTime + " ns");
		System.out.println("📘 Topological Sort Kahn's (Large Graph, V=1000): " + kahnsTime + " ns");
	}
}