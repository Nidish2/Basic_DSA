package dsa;

import java.util.*;

public class AA28_BST {

	// ------------------- 1️⃣ Manual BST Implementation -------------------
	static class TreeNode {
		int key;
		TreeNode left, right;

		TreeNode(int key) {
			this.key = key;
		}
	}

	static class ManualBST {
		TreeNode root;

		// 🔼 Insert a Value
		public void insert(int key) {
			root = insert(root, key);
		}

		private TreeNode insert(TreeNode root, int key) {
			if (root == null)
				return new TreeNode(key);
			if (key < root.key)
				root.left = insert(root.left, key);
			else if (key > root.key)
				root.right = insert(root.right, key);
			return root;
		}

		// 🔍 Search a Value
		public boolean search(int key) {
			return search(root, key);
		}

		private boolean search(TreeNode root, int key) {
			if (root == null)
				return false;
			if (root.key == key)
				return true;
			return key < root.key ? search(root.left, key) : search(root.right, key);
		}

		// Delete a key using inorder successor
		public void deleteSuccessor(int key) {
			root = deleteSuccessor(root, key);
		}

		private TreeNode deleteSuccessor(TreeNode node, int key) {
			if (node == null)
				return null;
			if (key < node.key)
				node.left = deleteSuccessor(node.left, key);
			else if (key > node.key)
				node.right = deleteSuccessor(node.right, key);
			else {
				// One or no child
				if (node.left == null)
					return node.right;
				else if (node.right == null)
					return node.left;

				// Two children: replace with inorder successor
				node.key = findMinValue(node.right);
				node.right = deleteSuccessor(node.right, node.key);
			}
			return node;
		}

		// Delete a key using inorder predecessor
		public void deletePredecessor(int key) {
			root = deletePredecessor(root, key);
		}

		private TreeNode deletePredecessor(TreeNode node, int key) {
			if (node == null)
				return null;
			if (key < node.key)
				node.left = deleteSuccessor(node.left, key);
			else if (key > node.key)
				node.right = deleteSuccessor(node.right, key);
			else {
				// One or no child
				if (node.left == null)
					return node.right;
				else if (node.right == null)
					return node.left;

				// Two children: delete the inorder predecessor
				node.key = findMaxValue(node.left);
				node.left = deletePredecessor(node.left, node.key);
			}
			return node;
		}

		public int findMinValue() {
			if (root == null)
				throw new IllegalStateException("Tree is empty");
			return findMinValue(root);
		}

		private int findMinValue(TreeNode node) {
			while (node.left != null)
				node = node.left;
			return node.key;
		}

		public int findMaxValue() {
			if (root == null)
				throw new IllegalStateException("Tree is empty");
			return findMaxValue(root);
		}

		private int findMaxValue(TreeNode node) {
			while (node.right != null)
				node = node.right;
			return node.key;
		}

		// 🌳 Traversals
		public void inorder() {
			inorder(root);
			System.out.println();
		}

		private void inorder(TreeNode node) {
			if (node == null)
				return;
			inorder(node.left);
			System.out.print(node.key + " ");
			inorder(node.right);
		}

		// 🔼 Preorder or Depth-First Search (DFS)
		public void preorder() {
			preorder(root);
			System.out.println();
		}

		private void preorder(TreeNode node) {
			if (node == null)
				return;
			System.out.print(node.key + " ");
			preorder(node.left);
			preorder(node.right);
		}

		public void postorder() {
			postorder(root);
			System.out.println();
		}

		private void postorder(TreeNode node) {
			if (node == null)
				return;
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.key + " ");
		}

		public void bfs() {
			if (root == null)
				return;
			Deque<TreeNode> q = new LinkedList<>();
			q.add(root);
			while (!q.isEmpty()) {
				TreeNode node = q.poll();
				System.out.print(node.key + " ");
				if (node.left != null)
					q.add(node.left);
				if (node.right != null)
					q.add(node.right);
			}
			System.out.println();
		}
	}

	// ------------------- 2️⃣ Inbuilt BST using TreeSet -------------------
	static class InbuiltBST {
		TreeSet<Integer> bst = new TreeSet<>();

		public void insert(int key) {
			bst.add(key);
		}

		public boolean search(int key) {
			return bst.contains(key);
		}

		public void delete(int key) {
			bst.remove(key);
		}

		public void inorder() {
			for (int key : bst)
				System.out.print(key + " ");
			System.out.println();
		}

		public void minMax() {
			if (!bst.isEmpty()) {
				System.out.println("Min: " + bst.first());
				System.out.println("Max: " + bst.last());
			}
		}
	}

	// ------------------- 🧪 Main Demo -------------------
	public static void main(String[] args) {
		System.out.println("🔧 Manual BST:");
		ManualBST bst = new ManualBST();
		bst.insert(50);
		bst.insert(30);
		bst.insert(70);
		bst.insert(20);
		bst.insert(40);
		bst.insert(60);
		bst.insert(80);

		System.out.print("Inorder: ");
		bst.inorder();
		System.out.print("Preorder / DFS: ");
		bst.preorder();
		System.out.print("Postorder: ");
		bst.postorder();
		System.out.print("BFS / Level Order Traversal: ");
		bst.bfs();
		System.out.println("Min Value: " + bst.findMinValue());
		System.out.println("Max Value: " + bst.findMaxValue());

		System.out.println("Search 40: " + bst.search(40));
		bst.deletePredecessor(70);
		System.out.print("After deleting 70 using Inorder Predecesor, Inorder: ");
		bst.inorder();
		bst.deleteSuccessor(20);
		System.out.print("After deleting 20 using Inorder Successor, Inorder: ");
		bst.inorder();

		System.out.println("\n🌳 Inbuilt TreeSet:");
		InbuiltBST inbuilt = new InbuiltBST();
		inbuilt.insert(50);
		inbuilt.insert(30);
		inbuilt.insert(70);
		inbuilt.insert(20);
		inbuilt.insert(40);
		inbuilt.insert(60);
		inbuilt.insert(80);

		System.out.print("Inorder: ");
		inbuilt.inorder();
		System.out.println("Search 60: " + inbuilt.search(60));
		inbuilt.delete(60);
		System.out.print("After deleting 60: ");
		inbuilt.inorder();
		inbuilt.minMax();
	}
}
