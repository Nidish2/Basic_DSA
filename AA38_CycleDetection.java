package dsa;

import java.util.*;

import dsa.AA35_Graph.ManualGraph;

public class AA38_CycleDetection {

	// Cycle Detection in Undirected Graph using DFS
	public static boolean isCyclicDFS(ManualGraph graph) {
		boolean[] visited = new boolean[graph.V];
		for (int i = 0; i < graph.V; i++) {
			if (!visited[i]) {
				if (dfsCycle(graph, i, visited, -1)) {
					return true; // Cycle detected
				}
			}
		}
		return false; // No cycle
	}

	private static boolean dfsCycle(ManualGraph graph, int node, boolean[] visited, int parent) {
		visited[node] = true;
		for (int neighbor : graph.adjList[node]) {
			if (!visited[neighbor]) {
				if (dfsCycle(graph, neighbor, visited, node)) {
					return true; // Cycle detected
				}
			} else if (neighbor != parent) {
				return true; // Cycle detected
			}
		}
		return false; // No cycle
	}

	// Cycle Detection in Undirected Graph using BFS
	public static boolean isCyclicBFS(ManualGraph graph) {
		boolean[] visited = new boolean[graph.V];
		for (int i = 0; i < graph.V; i++) {
			if (!visited[i]) {
				if (bfsCycle(graph, i, visited)) {
					return true; // Cycle detected
				}
			}
		}
		return false; // No cycle
	}

	private static boolean bfsCycle(ManualGraph graph, int start, boolean[] visited) {
		int[] parent = new int[graph.V];
		Arrays.fill(parent, -1);
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (int neighbor : graph.adjList[node]) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					parent[neighbor] = node;
					queue.add(neighbor);
				} else if (parent[node] != neighbor) {
					return true; // Cycle detected
				}
			}
		}
		return false; // No cycle
	}

	// Cycle Detection in Directed Graph using DFS
	public static boolean isCyclicDirectedDFS(ManualGraph graph) {
		boolean[] visited = new boolean[graph.V];
		boolean[] recStack = new boolean[graph.V];
		for (int i = 0; i < graph.V; i++) {
			if (!visited[i]) {
				if (dfsDirectedCycle(graph, i, visited, recStack)) {
					return true; // Cycle detected
				}
			}
		}
		return false; // No cycle
	}

	private static boolean dfsDirectedCycle(ManualGraph graph, int node, boolean[] visited, boolean[] recStack) {
		visited[node] = true;
		recStack[node] = true;

		for (int neighbor : graph.adjList[node]) {
			if (!visited[neighbor]) {
				if (dfsDirectedCycle(graph, neighbor, visited, recStack)) {
					return true; // Cycle detected
				}
			} else if (recStack[neighbor]) {
				return true; // Cycle detected
			}
		}

		recStack[node] = false;
		return false; // No cycle
	}

	// Cycle Detection in Directed Graph using BFS (Kahn's Algorithm)
	public static boolean isCyclicDirectedBFS(ManualGraph graph) {
		int[] inDegree = new int[graph.V];
		for (int i = 0; i < graph.V; i++) {
			for (int neighbor : graph.adjList[i]) {
				inDegree[neighbor]++;
			}
		}

		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < graph.V; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		int count = 0;
		while (!queue.isEmpty()) {
			int node = queue.poll();
			count++;
			for (int neighbor : graph.adjList[node]) {
				inDegree[neighbor]--;
				if (inDegree[neighbor] == 0) {
					queue.add(neighbor);
				}
			}
		}

		return count != graph.V; // Cycle exists if not all nodes processed
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManualGraph cg = new ManualGraph(5);
		cg.addEdgeUndirected(0, 1);
		cg.addEdgeUndirected(1, 2);
		cg.addEdgeUndirected(2, 0); // Cycle here
		cg.addEdgeUndirected(3, 4);
		cg.addEdgeUndirected(4, 1); // Another cycle
		cg.addEdgeUndirected(4, 2); // Another cycle
		cg.printGraph();
		cg.bfs(0);
		cg.dfs(0);
		System.out.println();

		ManualGraph ag = new ManualGraph(5);
		ag.addEdgeUndirected(0, 1);
		ag.addEdgeUndirected(1, 2);
		ag.addEdgeUndirected(2, 3);
		ag.addEdgeUndirected(3, 4);
		ag.printGraph();
		ag.bfs(0);
		ag.dfs(0);
		System.out.println();

		// Cycle Detection in undirected Graph using DFS
		System.out.println("\nCycle Detection in Undirected Graph using DFS:");
		cg.printGraph();
		System.out.println("Cycle Detected: " + isCyclicDFS(cg));
		ag.printGraph();
		System.out.println("Cycle Detected: " + isCyclicDFS(ag));

		// Cycle Detection in undirected Graph using BFS
		System.out.println("\nCycle Detection in Undirected Graph using BFS:");
		cg.printGraph();
		System.out.println("Cycle Detected: " + isCyclicBFS(cg));
		ag.printGraph();
		System.out.println("Cycle Detected: " + isCyclicBFS(ag));

		ManualGraph dcg = new ManualGraph(5);
		dcg.addEdgeDirected(0, 1);
		dcg.addEdgeDirected(1, 2);
		dcg.addEdgeDirected(2, 0); // Cycle here
		dcg.addEdgeDirected(3, 4);
		dcg.addEdgeDirected(4, 1); // Another cycle
		dcg.printGraph();
		dcg.bfs(0);
		dcg.dfs(0);
		System.out.println();

		ManualGraph dag = new ManualGraph(5);
		dag.addEdgeDirected(0, 1);
		dag.addEdgeDirected(1, 2);
		dag.addEdgeDirected(2, 3);
		dag.addEdgeDirected(3, 4);
		dag.printGraph();
		dag.bfs(0);
		dag.dfs(0);
		System.out.println();

		// Cycle Detection in Directed Graph using DFS
		System.out.println("\nCycle Detection in Directed Graph using DFS:");
		dcg.printGraph();
		System.out.println("Cycle Detected: " + isCyclicDirectedDFS(dcg));
		dag.printGraph();
		System.out.println("Cycle Detected: " + isCyclicDirectedDFS(dag));

		// Cycle Detection in Directed Graph using BFS (Kahn's Algorithm)
		System.out.println("\nCycle Detection in Directed Graph using BFS (Kahn's Algorithm):");
		dcg.printGraph();
		System.out.println("Cycle Detected: " + isCyclicDirectedBFS(dcg));
		dag.printGraph();
		System.out.println("Cycle Detected: " + isCyclicDirectedBFS(dag));
	}
}