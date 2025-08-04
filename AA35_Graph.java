package dsa;

import java.util.*;

public class AA35_Graph {

	// ------------------ Manual Implementation ------------------
	static class ManualGraph {
		int V;
		ArrayList<Integer>[] adjList;

		@SuppressWarnings("unchecked")
		ManualGraph(int vertices) {
			V = vertices;
			adjList = new ArrayList[vertices];
			for (int i = 0; i < V; i++) {
				adjList[i] = new ArrayList<>();
			}
		}

		public void addEdgeUndirected(int src, int dest) {
			adjList[src].add(dest);
			adjList[dest].add(src);
		}

		public void addEdgeDirected(int u, int v) {
			adjList[u].add(v);
		}

		public void printGraph() {
			System.out.println("Manual Graph (Adj List):");
			for (int i = 0; i < V; i++) {
				System.out.print(i + " => ");
				for (int neighbor : adjList[i]) {
					System.out.print(neighbor + " ");
				}
				System.out.println();
			}
		}

		public void bfs(int start) {
			boolean[] visited = new boolean[V];
			Deque<Integer> q = new ArrayDeque<>();
			q.add(start);
			visited[start] = true;

			System.out.print("BFS (Manual): ");
			while (!q.isEmpty()) {
				int node = q.poll();
				System.out.print(node + " ");
				for (int neighbor : adjList[node]) {
					if (!visited[neighbor]) {
						visited[neighbor] = true;
						q.add(neighbor);
					}
				}
			}
			System.out.println();
		}

		public void dfs(int start) {
			boolean[] visited = new boolean[V];
			System.out.print("DFS (Manual): ");
			dfsRecursive(start, visited);
			System.out.println();
		}

		private void dfsRecursive(int node, boolean[] visited) {
			visited[node] = true;
			System.out.print(node + " ");
			for (int neighbor : adjList[node]) {
				if (!visited[neighbor]) {
					dfsRecursive(neighbor, visited);
				}
			}
		}

		public void dfsIterative(int start) {
			boolean[] visited = new boolean[V];
			Deque<Integer> stack = new ArrayDeque<>();
			stack.push(start);

			System.out.print("DFS Iterative (Manual): ");
			while (!stack.isEmpty()) {
				int node = stack.pop();
				if (!visited[node]) {
					visited[node] = true;
					System.out.print(node + " ");
					for (int neighbor : adjList[node]) {
						if (!visited[neighbor]) {
							stack.push(neighbor);
						}
					}
				}
			}
			System.out.println();
		}
	}

	// ------------------ Edge-based Custom Class ------------------
	static class Edge {
		int src, dest, weight;
		String type;
		int capacity;

		Edge(int src, int dest, int weight, String type, int capacity) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
			this.type = type;
			this.capacity = capacity;
		}
	}

	static class EdgeGraph {
		int V;
		ArrayList<Edge>[] edgeList;

		@SuppressWarnings("unchecked")
		EdgeGraph(int vertices) {
			this.V = vertices;
			edgeList = new ArrayList[vertices];
			for (int i = 0; i < V; i++) {
				edgeList[i] = new ArrayList<>();
			}
		}

		void addEdge(int src, int dest, int weight, String type, int capacity) {
			edgeList[src].add(new Edge(src, dest, weight, type, capacity));
		}

		void printEdges() {
			System.out.println("Edge-Based Graph (Adj List with Edge Info):");
			for (int i = 0; i < V; i++) {
				System.out.print(i + " => ");
				for (Edge e : edgeList[i]) {
					System.out.print("[" + e.dest + ", w=" + e.weight + ", t=" + e.type + ", c=" + e.capacity + "] ");
				}
				System.out.println();
			}
		}

		void bfs(int start) {
			boolean[] visited = new boolean[V];
			Deque<Integer> queue = new ArrayDeque<>();
			visited[start] = true;
			queue.add(start);

			System.out.print("BFS Traversal from " + start + ": ");
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				System.out.print(curr + " ");

				for (Edge e : edgeList[curr]) {
					if (!visited[e.dest]) {
						visited[e.dest] = true;
						queue.add(e.dest);
					}
				}
			}
			System.out.println();
		}

		void dfs(int start) {
			boolean[] visited = new boolean[V];
			System.out.print("DFS Traversal from " + start + ": ");
			dfsRecursive(start, visited);
			System.out.println();
		}

		private void dfsRecursive(int curr, boolean[] visited) {
			visited[curr] = true;
			System.out.print(curr + " ");

			for (Edge e : edgeList[curr]) {
				if (!visited[e.dest]) {
					dfsRecursive(e.dest, visited);
				}
			}
		}
	}

	// ------------------ Inbuilt Java-Based Graph ------------------
	static class InbuiltGraph {
		List<Edge> edges = new ArrayList<>();
		Map<Integer, List<Integer>> adj = new HashMap<>();

		@SuppressWarnings("unused")
		void addEdge(int src, int dest) {
			edges.add(new Edge(src, dest, 0, "default", 0));
			adj.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);
		}

		void printInbuilt() {
			System.out.println("Inbuilt Graph (Adj List + Edge List):");
			System.out.println("Adjacency Map:");
			for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
				System.out.println(entry.getKey() + " => " + entry.getValue());
			}

			System.out.println("\nEdge List:");
			for (Edge e : edges) {
				System.out.println(e.src + " -> " + e.dest);
			}
		}

		void bfs(int start) {
			Set<Integer> visited = new HashSet<>();
			Deque<Integer> queue = new ArrayDeque<>();
			visited.add(start);
			queue.offer(start);

			System.out.print("BFS Traversal from " + start + ": ");
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				System.out.print(curr + " ");
				for (int neighbor : adj.getOrDefault(curr, new ArrayList<>())) {
					if (!visited.contains(neighbor)) {
						visited.add(neighbor);
						queue.offer(neighbor);
					}
				}
			}
			System.out.println();
		}

		void dfs(int start) {
			Set<Integer> visited = new HashSet<>();
			System.out.print("DFS Traversal from " + start + ": ");
			dfsUtil(start, visited);
			System.out.println();
		}

		private void dfsUtil(int curr, Set<Integer> visited) {
			visited.add(curr);
			System.out.print(curr + " ");
			for (int neighbor : adj.getOrDefault(curr, new ArrayList<>())) {
				if (!visited.contains(neighbor)) {
					dfsUtil(neighbor, visited);
				}
			}
		}
	}

	// ------------------ MAIN ------------------
	public static void main(String[] args) {
		System.out.println("🌐 GRAPH TRAVERSALS IN JAVA 🌐\n");

		// Test your topological sort DAG
		System.out.println("=== Testing Topological Sort DAG ===");
		ManualGraph topoGraph = new ManualGraph(4);
		topoGraph.addEdgeDirected(0, 1);
		topoGraph.addEdgeDirected(0, 2);
		topoGraph.addEdgeDirected(1, 3);
		topoGraph.addEdgeDirected(2, 3);
		topoGraph.printGraph();
		topoGraph.bfs(0);
		topoGraph.dfs(0);
		topoGraph.dfsIterative(0);

		System.out.println("\n------------------------------------\n");

		// Manual Graph (Original Undirected)
		ManualGraph mGraph = new ManualGraph(6);
		mGraph.addEdgeUndirected(0, 1);
		mGraph.addEdgeUndirected(0, 2);
		mGraph.addEdgeUndirected(1, 3);
		mGraph.addEdgeUndirected(2, 4);
		mGraph.addEdgeUndirected(3, 5);
		mGraph.addEdgeUndirected(4, 5);
		mGraph.printGraph();
		mGraph.bfs(0);
		mGraph.dfs(0);
		mGraph.dfsIterative(0);

		System.out.println("\n------------------------------------\n");

		// Edge-Based Graph
		EdgeGraph eGraph = new EdgeGraph(5);
		eGraph.addEdge(0, 1, 10, "road", 5);
		eGraph.addEdge(0, 2, 20, "rail", 15);
		eGraph.addEdge(2, 4, 5, "flight", 1);
		eGraph.addEdge(1, 3, 25, "road", 10);
		eGraph.addEdge(3, 4, 30, "rail", 20);
		eGraph.printEdges();
		eGraph.bfs(0);
		eGraph.dfs(0);

		System.out.println("\n------------------------------------\n");

		// Inbuilt Graph (Directed)
		InbuiltGraph iGraph = new InbuiltGraph();
		iGraph.addEdge(0, 1);
		iGraph.addEdge(0, 2);
		iGraph.addEdge(1, 3);
		iGraph.addEdge(2, 4);
		iGraph.addEdge(3, 5);
		iGraph.addEdge(4, 5);
		iGraph.printInbuilt();
		iGraph.bfs(0);
		iGraph.dfs(0);
	}
}