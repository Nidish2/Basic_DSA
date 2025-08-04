package dsa;

import java.util.*;

public class AA39_Dijkstra {

	static class Node {
		int vertex;
		int weight;

		Node(int v, int w) {
			this.vertex = v;
			this.weight = w;
		}
	}

	public static void dijkstra(List<List<Node>> adjList, int V, int source) {
		int[] dist = new int[V];
		int[] parent = new int[V];
		boolean[] visited = new boolean[V];

		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
		dist[source] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
		pq.offer(new Node(source, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int u = current.vertex;

			if (visited[u])
				continue;
			visited[u] = true;

			for (Node neighbor : adjList.get(u)) {
				int v = neighbor.vertex;
				int weight = neighbor.weight;

				if (!visited[v] && dist[u] + weight < dist[v]) {
					dist[v] = dist[u] + weight;
					parent[v] = u;
					pq.offer(new Node(v, dist[v]));
				}
			}
		}
		printShortestPaths(dist, parent, source);
	}

	public static void printShortestPaths(int[] dist, int[] parent, int source) {
		System.out.println("📍 Source: " + source);
		for (int i = 0; i < dist.length; i++) {
			System.out.print("🛣️ To: " + i + " | Cost: ");
			if (dist[i] == Integer.MAX_VALUE) {
				System.out.println("Unreachable ❌");
			} else {
				System.out.print(dist[i] + " | Path: " + source + " ");
				printPath(parent, i);
				System.out.println();
			}
		}
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

	private static void printPath(int[] parent, int v) {
		if (parent[v] == -1)
			return;
		printPath(parent, parent[v]);
		System.out.print("→ " + v + " ");
	}

	public static void addEdge(List<List<Node>> adjList, int u, int v, int weight) {
		adjList.get(u).add(new Node(v, weight));
	}

	public static void main(String[] args) {
		int V = 6;
		List<List<Node>> adjList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adjList.add(new ArrayList<>());
		}

		// Sample edges: (u, v, weight)
		addEdge(adjList, 0, 1, 4);
		addEdge(adjList, 0, 2, 1);
		addEdge(adjList, 2, 1, 2);
		addEdge(adjList, 1, 3, 1);
		addEdge(adjList, 2, 3, 5);
		addEdge(adjList, 3, 4, 3);
		addEdge(adjList, 4, 5, 1);

		printGraph(adjList, V); // Visualize the graph

		int source = 0;
		dijkstra(adjList, V, source);
	}
}
