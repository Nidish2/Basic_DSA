package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;

/**
 * This class is a multi-purpose toolkit for advanced graph algorithms,
 * primarily based on Depth First Search (DFS) and concepts from Tarjan. It
 * provides classical implementations for: 1. Finding Strongly Connected
 * Components (Tarjan's SCC Algorithm). 2. Finding Bridges in an undirected
 * graph. 3. Performing a Topological Sort on a Directed Acyclic Graph (DAG). 4.
 * Finding Articulation Points (Cut Vertices) in an undirected graph.
 */
public class AA44_Tarjan {

	/**
	 * Represents a graph using an adjacency list. This structure is versatile
	 * enough for both directed and undirected graphs.
	 */
	public static class Graph {
		private final int V;
		private final List<List<Integer>> adj;

		public Graph(int v) {
			this.V = v;
			this.adj = new ArrayList<>(v);
			for (int i = 0; i < v; i++) {
				adj.add(new ArrayList<>());
			}
		}

		/** Adds a directed edge from u to v. */
		public void addEdge(int u, int v) {
			adj.get(u).add(v);
		}

		/** Adds an undirected edge between u and v. */
		public void addUndirectedEdge(int u, int v) {
			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		public int getV() {
			return V;
		}

		public List<List<Integer>> getAdj() {
			return adj;
		}

		public void printGraph() {
			System.out.println("Graph adjacency list:");
			for (int v = 0; v < V; v++) {
				System.out.print(v + ": ");
				for (int neighbor : adj.get(v)) {
					System.out.print(neighbor + " ");
				}
				System.out.println();
			}
		}
	}

	private int time; // Shared timer for discovery/low-link calculations

	// --- 1. Strongly Connected Components (SCC) ---
	public void printSCCs(Graph graph) {
		int V = graph.getV();
		int[] disc = new int[V];
		int[] low = new int[V];
		boolean[] onStack = new boolean[V];
		Deque<Integer> stack = new ArrayDeque<>();
		Arrays.fill(disc, -1);
		this.time = 0;
		System.out.println("✅ Strongly Connected Components:");
		for (int i = 0; i < V; i++) {
			if (disc[i] == -1) {
				sccUtil(graph, i, disc, low, stack, onStack);
			}
		}
	}

	/**
	 * A utility function to find strongly connected components using DFS.
	 * 
	 * @param graph   The directed graph.
	 * @param u       The current vertex.
	 * @param disc    Discovery time of vertex u.
	 * @param low     Lowest discovery time reachable from u.
	 * @param stack   Stack to hold the current path in the DFS.
	 * @param onStack Boolean array to track vertices currently in the stack.
	 */
	private void sccUtil(Graph graph, int u, int[] disc, int[] low, Deque<Integer> stack, boolean[] onStack) {
		disc[u] = low[u] = ++time;
		stack.push(u);
		onStack[u] = true;
		for (int v : graph.getAdj().get(u)) {
			if (disc[v] == -1) {
				sccUtil(graph, v, disc, low, stack, onStack);
				low[u] = Math.min(low[u], low[v]);
			} else if (onStack[v]) {
				low[u] = Math.min(low[u], disc[v]);
			}
		}
		if (low[u] == disc[u]) {
			System.out.print("  SCC: ");
			while (true) {
				int popped = stack.pop();
				onStack[popped] = false;
				System.out.print(popped + " ");
				if (u == popped)
					break;
			}
			System.out.println();
		}
	}

	// --- 2. Bridge Finding ---
	public List<List<Integer>> findBridges(Graph graph) {
		int V = graph.getV();
		int[] disc = new int[V];
		int[] low = new int[V];
		int[] parent = new int[V];
		List<List<Integer>> bridges = new ArrayList<>();
		Arrays.fill(disc, -1);
		Arrays.fill(parent, -1);
		this.time = 0;
		System.out.println("🌉 Finding Bridges:");
		for (int i = 0; i < V; i++) {
			if (disc[i] == -1) {
				bridgeUtil(graph, i, disc, low, parent, bridges);
			}
		}
		return bridges;
	}

	/**
	 * A utility function to find bridges in the graph using DFS.
	 * 
	 * @param graph   The undirected graph.
	 * @param u       The current vertex.
	 * @param disc    Discovery time of vertex u.
	 * @param low     Lowest discovery time reachable from u.
	 * @param parent  Parent of vertex u in DFS tree.
	 * @param bridges List to store found bridges.
	 */
	private void bridgeUtil(Graph graph, int u, int[] disc, int[] low, int[] parent, List<List<Integer>> bridges) {
		disc[u] = low[u] = ++time;
		for (int v : graph.getAdj().get(u)) {
			if (v == parent[u])
				continue;
			if (disc[v] != -1) {
				low[u] = Math.min(low[u], disc[v]);
			} else {
				parent[v] = u;
				bridgeUtil(graph, v, disc, low, parent, bridges);
				low[u] = Math.min(low[u], low[v]);
				if (low[v] > disc[u]) {
					bridges.add(Arrays.asList(u, v));
				}
			}
		}
	}

	// --- 3. Topological Sort ---
	public List<Integer> topologicalSort(Graph graph) {
		int V = graph.getV();
		Deque<Integer> stack = new ArrayDeque<>();
		boolean[] visited = new boolean[V];
		boolean[] recursionStack = new boolean[V];
		System.out.println("📊 Performing Topological Sort:");
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (topoSortUtil(graph, i, visited, recursionStack, stack)) {
					System.out.println("  Error: Cycle detected. Topological sort not possible.");
					return null;
				}
			}
		}
		List<Integer> result = new ArrayList<>();
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	/**
	 * A utility function to perform DFS for topological sorting.
	 * 
	 * @param graph          The directed graph.
	 * @param u              The current vertex.
	 * @param visited        Array to track visited vertices.
	 * @param recursionStack Array to track the recursion stack.
	 * @param stack          Stack to hold the topological order.
	 * @return True if a cycle is detected, false otherwise.
	 */
	private boolean topoSortUtil(Graph graph, int u, boolean[] visited, boolean[] recursionStack,
			Deque<Integer> stack) {
		visited[u] = true;
		recursionStack[u] = true;
		for (int v : graph.getAdj().get(u)) {
			if (!visited[v]) {
				if (topoSortUtil(graph, v, visited, recursionStack, stack))
					return true;
			} else if (recursionStack[v]) {
				return true;
			}
		}
		recursionStack[u] = false;
		stack.push(u);
		return false;
	}

	// --- 4. Articulation Point Finding ---

	/**
	 * Finds all articulation points (cut vertices) in an undirected graph.
	 * 
	 * @param graph The undirected graph.
	 * @return A list of articulation points.
	 */
	public List<Integer> findArticulationPoints(Graph graph) {
		int V = graph.getV();
		int[] disc = new int[V]; // Discovery time of vertex u
		int[] low = new int[V]; // Lowest discovery time reachable from u
		int[] parent = new int[V]; // Parent of vertex u in DFS tree
		boolean[] isAP = new boolean[V]; // To store articulation points
		List<Integer> apList = new ArrayList<>(); // List to store articulation points

		Arrays.fill(disc, -1);
		Arrays.fill(parent, -1);

		this.time = 0;
		System.out.println("📍 Finding Articulation Points:");
		for (int i = 0; i < V; i++) {
			if (disc[i] == -1) {
				apUtil(graph, i, disc, low, parent, isAP);
			}
		}

		for (int i = 0; i < V; i++) {
			if (isAP[i]) {
				apList.add(i);
			}
		}
		return apList;
	}

	/**
	 * A utility function to find articulation points using DFS.
	 * 
	 * @param graph  The undirected graph.
	 * @param u      The current vertex.
	 * @param disc   Discovery time of vertex u.
	 * @param low    Lowest discovery time reachable from u.
	 * @param parent Parent of vertex u in DFS tree.
	 * @param isAP   Boolean array to mark articulation points.
	 */
	private void apUtil(Graph graph, int u, int[] disc, int[] low, int[] parent, boolean[] isAP) {
		int children = 0;
		disc[u] = low[u] = ++time;

		for (int v : graph.getAdj().get(u)) {
			if (v == parent[u])
				continue;

			if (disc[v] != -1) { // Back edge
				low[u] = Math.min(low[u], disc[v]);
			} else { // Forward edge
				children++;
				parent[v] = u;
				apUtil(graph, v, disc, low, parent, isAP);
				low[u] = Math.min(low[u], low[v]);

				// Case 1: u is the root of DFS tree and has two or more children.
				if (parent[u] == -1 && children > 1) {
					isAP[u] = true;
				}

				// Case 2: u is not root and low value of one of its children is more
				// than or equal to discovery value of u.
				if (parent[u] != -1 && low[v] >= disc[u]) {
					isAP[u] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		AA44_Tarjan solver = new AA44_Tarjan();

		// --- SCC Test ---
		System.out.println("--- Testing SCC ---");
		Graph sccGraph = new Graph(5);
		sccGraph.addEdge(1, 0);
		sccGraph.addEdge(0, 2);
		sccGraph.addEdge(2, 1);
		sccGraph.addEdge(0, 3);
		sccGraph.addEdge(3, 4);
		sccGraph.printGraph();
		solver.printSCCs(sccGraph);
		System.out.println("------------------------------------");

		// --- Bridge Finding Test ---
		System.out.println("--- Testing Bridge Finding ---");
		Graph bridgeGraph = new Graph(7);
		bridgeGraph.addUndirectedEdge(0, 1);
		bridgeGraph.addUndirectedEdge(1, 2);
		bridgeGraph.addUndirectedEdge(2, 0);
		bridgeGraph.addUndirectedEdge(1, 3);
		bridgeGraph.addUndirectedEdge(1, 4);
		bridgeGraph.addUndirectedEdge(1, 6);
		bridgeGraph.addUndirectedEdge(3, 5);
		bridgeGraph.addUndirectedEdge(4, 5);
		bridgeGraph.printGraph();
		List<List<Integer>> bridges = solver.findBridges(bridgeGraph);
		System.out.println("  Bridges found: " + bridges);
		System.out.println("------------------------------------");

		Graph bGraph = new Graph(6);
		bGraph.addUndirectedEdge(0, 1);
		bGraph.addUndirectedEdge(1, 2);
		bGraph.addUndirectedEdge(1, 3);
		bGraph.addUndirectedEdge(3, 4);
		bGraph.addUndirectedEdge(4, 5);
		bGraph.printGraph();
		List<List<Integer>> bridges2 = solver.findBridges(bGraph);
		System.out.println("  Bridges found in second graph: " + bridges2);
		System.out.println("------------------------------------");

		// --- Articulation Point Test ---
		System.out.println("--- Testing Articulation Points ---");
		Graph apGraph = new Graph(7);
		apGraph.addUndirectedEdge(0, 1);
		apGraph.addUndirectedEdge(1, 2);
		apGraph.addUndirectedEdge(2, 0);
		apGraph.addUndirectedEdge(1, 3);
		apGraph.addUndirectedEdge(1, 4);
		apGraph.addUndirectedEdge(1, 6);
		apGraph.addUndirectedEdge(3, 5);
		apGraph.addUndirectedEdge(4, 5);
		apGraph.printGraph();
		List<Integer> articulationPoints = solver.findArticulationPoints(apGraph);
		System.out.println("  Articulation Points found: " + articulationPoints);
		System.out.println("------------------------------------");

		Graph multiAPGraph = new Graph(9);
		multiAPGraph.addUndirectedEdge(0, 1);
		multiAPGraph.addUndirectedEdge(1, 2);
		multiAPGraph.addUndirectedEdge(2, 0);
		multiAPGraph.addUndirectedEdge(1, 3);
		multiAPGraph.addUndirectedEdge(3, 4);
		multiAPGraph.addUndirectedEdge(4, 5);
		multiAPGraph.addUndirectedEdge(5, 3);
		multiAPGraph.addUndirectedEdge(3, 6);
		multiAPGraph.addUndirectedEdge(6, 7);
		multiAPGraph.addUndirectedEdge(7, 8);
		multiAPGraph.addUndirectedEdge(8, 6);
		multiAPGraph.printGraph();
		List<Integer> multiAPs = solver.findArticulationPoints(multiAPGraph);
		System.out.println("  Articulation Points found in multi-AP graph: " + multiAPs);
		System.out.println("------------------------------------");

		// --- Topological Sort Test (DAG) ---
		System.out.println("--- Testing Topological Sort (DAG) ---");
		Graph dag = new Graph(6);
		dag.addEdge(5, 2);
		dag.addEdge(5, 0);
		dag.addEdge(4, 0);
		dag.addEdge(4, 1);
		dag.addEdge(2, 3);
		dag.addEdge(3, 1);
		dag.printGraph();
		List<Integer> topoOrder = solver.topologicalSort(dag);
		if (topoOrder != null) {
			System.out.println("  Topological Order: " + topoOrder);
		}
		System.out.println();

		// --- Topological Sort Test (with cycle) ---
		System.out.println("--- Testing Topological Sort (Cycle) ---");
		Graph cyclicGraph = new Graph(4);
		cyclicGraph.addEdge(0, 1);
		cyclicGraph.addEdge(1, 2);
		cyclicGraph.addEdge(2, 3);
		cyclicGraph.addEdge(3, 1);
		cyclicGraph.printGraph();
		solver.topologicalSort(cyclicGraph);
		System.out.println("------------------------------------");
	}
}
