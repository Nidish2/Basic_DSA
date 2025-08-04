package dsa;

import java.util.*;

public class AA41_Prims {

	static class Node {
		int vertex;
		int weight;

		Node(int v, int w) {
			this.vertex = v;
			this.weight = w;
		}
	}

	public static void prims(List<List<Node>> adjList, int V, int source) {
		int[] key = new int[V]; // Min weight to connect each vertex
		int[] parent = new int[V]; // To reconstruct MST
		boolean[] inMST = new boolean[V]; // Visited in MST

		Arrays.fill(key, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
		key[source] = 0;

		// Priority queue: nodes by key (min weight)
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
		pq.offer(new Node(source, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int u = current.vertex;

			if (inMST[u])
				continue;
			inMST[u] = true;

			for (Node neighbor : adjList.get(u)) {
				int v = neighbor.vertex;
				int weight = neighbor.weight;

				if (!inMST[v] && weight < key[v]) {
					key[v] = weight;
					parent[v] = u;
					pq.offer(new Node(v, key[v]));
				}
			}
		}

		printMST(key, parent, source);
	}

	public static void printMST(int[] key, int[] parent, int source) {
		int totalCost = 0;
		System.out.println("🌳 Prim's MST from Source: " + source);
		for (int i = 0; i < key.length; i++) {
			if (i != source && parent[i] != -1) {
				System.out.println("🔗 Edge: " + parent[i] + " → " + i + " | Weight: " + key[i]);
				totalCost += key[i];
			}
		}
		System.out.println("💰 Total MST Cost: " + totalCost);
	}

	// Add undirected edge (add to both directions)
	public static void addEdge(List<List<Node>> adjList, int u, int v, int weight) {
		adjList.get(u).add(new Node(v, weight));
		adjList.get(v).add(new Node(u, weight));
	}

	// NEW: Function to visualize the built graph (adjacency list)
	public static void printGraph(List<List<Node>> adjList, int V) {
		System.out.println("🗺️ Built Graph (Adjacency List):");
		for (int u = 0; u < V; u++) {
			System.out.print("Vertex " + u + " → ");
			if (adjList.get(u).isEmpty()) {
				System.out.println("[No neighbors]");
			} else {
				for (Node neighbor : adjList.get(u)) {
					System.out.print("(" + neighbor.vertex + ", wt: " + neighbor.weight + ") ");
				}
				System.out.println();
			}
		}
		System.out.println("--- End of Graph ---");
	}

	public static void main(String[] args) {
		int V = 5;
		List<List<Node>> adjList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adjList.add(new ArrayList<>());
		}

		// Sample undirected edges: (u, v, weight)
		addEdge(adjList, 0, 1, 2);
		addEdge(adjList, 0, 3, 6);
		addEdge(adjList, 1, 2, 3);
		addEdge(adjList, 1, 3, 8);
		addEdge(adjList, 1, 4, 5);
		addEdge(adjList, 2, 4, 7);
		addEdge(adjList, 3, 4, 9);

		// Print the built graph
		printGraph(adjList, V);

		int source = 0;
		prims(adjList, V, source);
	}
}
