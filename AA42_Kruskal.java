package dsa;

import java.util.*;

public class AA42_Kruskal {

	static class Edge {
		int src, dest, weight;

		Edge(int u, int v, int w) {
			src = u;
			dest = v;
			weight = w;
		}
	}

	// Union-Find structure for cycle detection
	static class UnionFind {
		int[] parent, rank;

		UnionFind(int V) {
			parent = new int[V];
			rank = new int[V];
			for (int i = 0; i < V; i++) {
				parent[i] = i;
				rank[i] = 0;
			}
		}

		int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]); // Path compression
			}
			return parent[x];
		}

		boolean union(int x, int y) {
			int rX = find(x);
			int rY = find(y);
			if (rX == rY)
				return false; // Cycle detected!

			// Union by rank
			if (rank[rX] < rank[rY]) {
				parent[rX] = rY;
			} else if (rank[rX] > rank[rY]) {
				parent[rY] = rX;
			} else {
				parent[rY] = rX;
				rank[rX]++;
			}
			return true;
		}
	}

	public static void kruskal(List<Edge> edges, int V) {
		// Sort edges by weight using Comparator (no Comparable needed!)
		edges.sort(Comparator.comparingInt(e -> e.weight));

		UnionFind uf = new UnionFind(V);
		List<Edge> mst = new ArrayList<>();
		int totalCost = 0;
		int edgesUsed = 0;

		for (Edge edge : edges) {
			if (uf.union(edge.src, edge.dest)) {
				mst.add(edge);
				totalCost += edge.weight;
				edgesUsed++;
				if (edgesUsed == V - 1)
					break; // MST complete
			}
		}

		// Check if graph is connected (MST should have V-1 edges)
		if (edgesUsed != V - 1) {
			System.out.println("⚠️ Graph is disconnected! MST only covers a forest. 🛑");
		}

		printMST(mst, totalCost);
	}

	public static void printMST(List<Edge> mst, int totalCost) {
		System.out.println("🌳 Kruskal's MST:");
		for (Edge edge : mst) {
			System.out.println("🔗 Edge: " + edge.src + " → " + edge.dest + " | Weight: " + edge.weight);
		}
		System.out.println("💰 Total MST Cost: " + totalCost);
	}

	// NEW: Function to visualize the built graph (edge list)
	public static void printGraph(List<Edge> edges, int V) {
		System.out.println("🧬 Built Graph (Edge List):");
		if (edges.isEmpty()) {
			System.out.println("[No edges in the graph] 🛑");
		} else {
			for (Edge edge : edges) {
				System.out.println("🔗 Edge: " + edge.src + " → " + edge.dest + " | Weight: " + edge.weight);
			}
		}
		System.out.println("💡 Total Vertices: " + V + " | Total Edges: " + edges.size());
		System.out.println("--- End of Graph ---\n");
	}

	public static void main(String[] args) {
		int V = 5; // Number of vertices
		List<Edge> edges = new ArrayList<>();

		// Sample undirected edges: (u, v, weight) — add each once (Kruskal handles
		// undirected)
		edges.add(new Edge(0, 1, 2));
		edges.add(new Edge(0, 3, 6));
		edges.add(new Edge(1, 2, 3));
		edges.add(new Edge(1, 3, 8));
		edges.add(new Edge(1, 4, 5));
		edges.add(new Edge(2, 4, 7));
		edges.add(new Edge(3, 4, 9));

		// NEW: Print the graph to verify before running Kruskal's
		printGraph(edges, V);

		kruskal(edges, V);
	}
}
