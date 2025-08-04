package dsa;

import java.util.*;

public class AA40_BellmanFord {

	static class Edge {
		int src, dest, weight;

		Edge(int u, int v, int w) {
			src = u;
			dest = v;
			weight = w;
		}
	}

	public static void bellmanFord(List<Edge> edges, int V, int source) {
		int[] dist = new int[V];
		int[] parent = new int[V];

		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
		dist[source] = 0;

		// Relax all edges |V|-1 times
		for (int i = 1; i <= V - 1; i++) {
			for (Edge edge : edges) {
				if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
					dist[edge.dest] = dist[edge.src] + edge.weight;
					parent[edge.dest] = edge.src;
				}
			}
		}

		// Check for negative-weight cycles
		boolean hasNegativeCycle = false;
		for (Edge edge : edges) {
			if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
				hasNegativeCycle = true;
				break;
			}
		}

		printShortestPaths(dist, parent, source, hasNegativeCycle);
	}

	public static void printShortestPaths(int[] dist, int[] parent, int source, boolean hasNegativeCycle) {
		if (hasNegativeCycle) {
			System.out.println("⚠️ Negative weight cycle detected! Shortest paths not reliable.");
			return;
		}
		System.out.println("🚩 Source: " + source + " | Bellman-Ford Results:");
		for (int i = 0; i < dist.length; i++) {
			System.out.print("🔗 To: " + i + " | Cost: ");
			if (dist[i] == Integer.MAX_VALUE) {
				System.out.println("Unreachable 🛑");
			} else {
				System.out.print(dist[i] + " | Path: " + source + " ");
				printPath(parent, i);
				System.out.println();
			}
		}
	}

	private static void printPath(int[] parent, int v) {
		if (parent[v] == -1)
			return;
		printPath(parent, parent[v]);
		System.out.print("→ " + v + " ");
	}

	// NEW: Function to visualize the built graph (edge list)
	public static void printGraph(List<Edge> edges, int V) {
		System.out.println("🧠 Built Graph (Edge List):");
		if (edges.isEmpty()) {
			System.out.println("[No edges in the graph] 🛑");
		} else {
			for (Edge edge : edges) {
				System.out.println("🔗 Edge: " + edge.src + " → " + edge.dest + " | Weight: " + edge.weight);
			}
		}
		System.out.println("💡 Total Vertices: " + V + " | Total Edges: " + edges.size());
		System.out.println("--- End of Graph ---");
	}

	public static void main(String[] args) {
		int V = 5; // Number of vertices
		List<Edge> edges = new ArrayList<>();

		// (src, dest, weight)
		edges.add(new Edge(0, 1, -1));
		edges.add(new Edge(0, 2, 4));
		edges.add(new Edge(1, 2, 3));
		edges.add(new Edge(1, 3, 2));
		edges.add(new Edge(1, 4, 2));
		edges.add(new Edge(3, 2, 5));
		edges.add(new Edge(3, 1, 1));
		edges.add(new Edge(4, 3, -3));

		// Print the graph
		printGraph(edges, V);

		int source = 0;
		bellmanFord(edges, V, source);
	}
}
