package dsa;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Deque;

/**
 * This class provides a classical implementation of Kosaraju's algorithm. The
 * graph data structure is encapsulated in a nested static class `Graph`,
 * separating the data representation from the algorithm logic.
 * 
 * Overall time complexity of the algorithm is O(V + E), where V is the number
 * of vertices and E is the number of edges in the graph. This is because the
 * algorithm consists of two depth-first searches (DFS) and a graph
 * transposition, each of which takes O(V + E) time.
 * 
 * overall space complexity is O(V + E) for storing the graph and its transpose,
 * as well as the visited array and stack used during the DFS traversals.
 * 
 */
public class AA43_Kosaraju {

	/**
	 * Represents a directed graph using an adjacency list. This nested class
	 * encapsulates all graph-related data and operations.
	 */
	public static class Graph {
		private final int V; // Number of vertices
		private final List<List<Integer>> adj;// Adjacency list

		/**
		 * Constructor for the Graph.
		 * 
		 * @param v The number of vertices in the graph.
		 */
		public Graph(int v) {
			this.V = v;
			this.adj = new ArrayList<>(V);
			for (int i = 0; i < v; i++) {
				adj.add(new ArrayList<>());
			}
		}

		/**
		 * Adds a directed edge from vertex v to vertex w.
		 * 
		 * @param u The source vertex.
		 * @param v The destination vertex.
		 */
		public void addEdge(int u, int v) {
			adj.get(u).add(v);
		}

		/**
		 * Returns the number of vertices in the graph.
		 * 
		 * @return The vertex count.
		 */
		public int getV() {
			return V;
		}

		/**
		 * Returns the adjacency list of the graph.
		 * 
		 * @return The array of linked lists representing the adjacency list.
		 */
		public List<List<Integer>> getAdj() {
			return adj;
		}

		/**
		 * Creates and returns the transpose of this graph. In the transposed graph, the
		 * direction of every edge is reversed.
		 * 
		 * @return A new Graph object representing the transpose. O (V + E) time
		 *         complexity
		 */
		private Graph getTranspose() {
			Graph g = new Graph(V);
			for (int u = 0; u < V; u++) {
				for (int v : this.adj.get(u)) {
					g.addEdge(v, u);
				}
			}
			return g;
		}

		/**
		 * Prints the adjacency list of the graph.
		 */
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

	/**
	 * This function performs a depth-first search (DFS) on the graph starting from
	 * vertex `v`. It marks the vertex as visited and recursively visits all its
	 * neighbors. After all neighbors are visited, it pushes the vertex onto the
	 * stack. This ensures that vertices are added to the stack in the order of
	 * their finishing times, which is crucial for the second pass of the Kosaraju's
	 * algorithm. Basically it is topological sorting of the graph.
	 *
	 * @param graph   The graph to traverse.
	 * @param v       The current vertex.
	 * @param visited An array to keep track of visited vertices.
	 * @param stack   The stack to store vertices based on finish time.
	 * 
	 *                O (V + E) time complexity
	 */
	private static void fillOrder(Graph graph, int v, boolean[] visited, Deque<Integer> stack) {
		visited[v] = true;
		for (int neighbor : graph.getAdj().get(v)) {
			if (!visited[neighbor]) {
				fillOrder(graph, neighbor, visited, stack);
			}
		}
		stack.push(v);
	}

	/**
	 * A recursive DFS function to traverse the graph and print the vertices of a
	 * strongly connected component.
	 *
	 * @param graph   The graph to traverse (specifically, the transposed graph).
	 * @param v       The current vertex.
	 * @param visited An array to keep track of visited vertices.
	 * 
	 *                O (V + E) time complexity
	 */
	private static void dfsForSCC(Graph graph, int v, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + " ");
		for (int neighbor : graph.getAdj().get(v)) {
			if (!visited[neighbor]) {
				dfsForSCC(graph, neighbor, visited);
			}
		}
	}

	/**
	 * The main method that finds and prints all strongly connected components in a
	 * given graph.
	 *
	 * @param graph The graph on which to find SCCs.
	 * 
	 *              O (V + E) time complexity
	 */
	public static void printSCCs(Graph graph) {
		Deque<Integer> stack = new ArrayDeque<>();
		int V = graph.getV();
		boolean[] visited = new boolean[V];

		// Step 1: Fill stack with vertices in order of finishing times.
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				fillOrder(graph, i, visited, stack);
			}
		}

		// Step 2: Create the transpose of the graph.
		Graph transposedGraph = graph.getTranspose();

		// Step 3: Process vertices from the stack to find SCCs on the transposed graph.
		Arrays.fill(visited, false); // Reset visited array for the second DFS pass.

		System.out.println("Strongly Connected Components are:");
		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (!visited[v]) {
				dfsForSCC(transposedGraph, v, visited);
				System.out.println(); // Newline after printing one complete SCC.
			}
		}
	}

	/**
	 * The main driver method to test the algorithm.
	 */
	public static void main(String[] args) {
		System.out.println("🔥 Kosaraju's Algorithm 🔥");

		// Example Graph 1
		Graph g1 = new Graph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);

		g1.printGraph();

		printSCCs(g1);
		System.out.println("------------------------------------");

		// Example Graph 2
		Graph g2 = new Graph(8);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 0);
		g2.addEdge(1, 3);
		g2.addEdge(3, 4);
		g2.addEdge(4, 5);
		g2.addEdge(5, 6);
		g2.addEdge(6, 4);
		g2.addEdge(6, 7);

		g2.printGraph();

		printSCCs(g2);
	}
}