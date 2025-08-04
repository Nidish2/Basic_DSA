package dsa;

import java.util.*;

public class AA26_Traversals {

	// ------------------- 1️⃣ Manual Tree -------------------
	static class TreeNode {
		int val;
		TreeNode left, right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	// 🧠 Preorder: Root - Left - Right
	public static void preorder(TreeNode node) {
		if (node == null)
			return;
		System.out.print(node.val + " ");
		preorder(node.left);
		preorder(node.right);
	}

	// 🔍 Inorder: Left - Root - Right
	public static void inorder(TreeNode node) {
		if (node == null)
			return;
		inorder(node.left);
		System.out.print(node.val + " ");
		inorder(node.right);
	}

	// 🔚 Postorder: Left - Right - Root
	public static void postorder(TreeNode node) {
		if (node == null)
			return;
		postorder(node.left);
		postorder(node.right);
		System.out.print(node.val + " ");
	}

	// 🚀 DFS (recursive = same as preorder)
	public static void dfsPre(TreeNode node) {
		preorder(node);
	}

	public static void dfsStack(TreeNode root) {
		if (root == null)
			return;

		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			System.out.print(node.val + " ");

			// Push right first so that left is processed first (LIFO)
			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);
		}
	}

	// 🧊 BFS / Level Order using Queue
	public static void bfs(TreeNode root) {
		if (root == null)
			return;

		Deque<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			System.out.print(node.val + " ");

			if (node.left != null)
				q.add(node.left);
			if (node.right != null)
				q.add(node.right);
		}
	}

	// BFS / Level Order using Queue with Levels printed by new line
	public static void bfsNLine(TreeNode root) {
		if (root == null)
			return;

		Deque<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(null); // Marker for level end

		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node == null) {
				System.out.println(); // New line for next level
				if (!q.isEmpty())
					q.add(null); // Add marker for next level
				continue;
			}
			System.out.print(node.val + " ");

			if (node.left != null)
				q.add(node.left);
			if (node.right != null)
				q.add(node.right);
		}
	}

	// Create sample tree
	public static TreeNode createTree() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		return root;
	}

	// ------------------- 🧪 Main Demo -------------------
	public static void main(String[] args) {

		System.out.println("🔧 Tree Traversals:");

		TreeNode manualRoot = createTree();
		System.out.print("Preorder: ");
		preorder(manualRoot);
		System.out.println();
		System.out.print("Inorder: ");
		inorder(manualRoot);
		System.out.println();
		System.out.print("Postorder: ");
		postorder(manualRoot);
		System.out.println();
		System.out.print("DFS using Preorder: ");
		dfsPre(manualRoot);
		System.out.println();
		System.out.print("DFS using Stack: ");
		dfsStack(manualRoot);
		System.out.println();
		System.out.print("BFS / Level Order Trsversal in a line: ");
		bfs(manualRoot);
		System.out.println();
		System.out.print("BFS / Level Order Trsversal with levels in new line: \n");
		bfsNLine(manualRoot);
		System.out.println();
		System.out.println("--------------------------------------------------");
	}
}
